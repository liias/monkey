package io.github.liias.monkey.ide.actions.appsettings;

import com.google.common.base.Throwables;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.compiler.CompilerPaths;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import io.github.liias.monkey.deserializer.Deserializer;
import io.github.liias.monkey.deserializer.type.MonkeyType;
import io.github.liias.monkey.deserializer.type.MonkeyTypeHash;
import io.github.liias.monkey.project.dom.manifest.Manifest;
import io.github.liias.monkey.project.module.util.MonkeyModuleUtil;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import io.github.liias.monkey.project.sdk.tools.SimulatorHelper;
import org.apache.sanselan.util.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.github.liias.monkey.Utils.createGeneralCommandLine;
import static io.github.liias.monkey.Utils.getForWinLinOrMac;

public class SimulatorCommunication {
  private static final Logger LOG = Logger.getInstance("#io.github.liias.monkey.ide.actions.appsettings.SimulatorCommunication");

  public static final int SIMULATOR_PORT_MIN = 42877;
  public static final int SIMULATOR_PORT_MAX = 42882;

  public static final String SIMULATOR_PING = "MARCO";
  public static final String SIMULATOR_PONG = "POLO";

  public static final String SIMULATOR_SETTINGS_QUERY_PREFIX = "SETTINGS_WHERE_ARE_YOU ";
  public static final String SIMULATOR_SETTINGS_RESPONSE_NEGATIVE = "YOUR SETTINGS ARE IN ANOTHER CASTLE";

  public static final String SIMULATOR_TRIGGER_NEW_SETTINGS_PREFIX = "INPUT_NEW_SETTINGS ";
  public static final String SIMULATOR_TRIGGER_NEW_SETTINGS_RESPONSE_OK = "OK";

  public static final String SIMULATOR_SETTINGS_PATH = "0:/GARMIN/APPS/SETTINGS/";

  @NotNull
  private final Module module;

  @NotNull
  private final Sdk sdk;

  private final String manifestApplicationId;

  public SimulatorCommunication(@NotNull Module module) {
    this.module = module;
    this.sdk = checkNotNull(ModuleRootManager.getInstance(module).getSdk());

    Project project = module.getProject();
    VirtualFile moduleRootDir = project.getBaseDir();
    final Manifest manifest = MonkeyModuleUtil.getManifest(project, moduleRootDir);
    this.manifestApplicationId = manifest.getApplication().getId().getStringValue();
  }

  @NotNull
  public Map<MonkeyType, MonkeyType> parseFromSim() throws IOException, ExecutionException {
    Optional<String> remoteSettingsPath = doWithSocket(socket -> getDeviceFilePath(socket, manifestApplicationId));

    if (!remoteSettingsPath.isPresent() || remoteSettingsPath.get().isEmpty()) {
      return new HashMap<>();
    }

    File tempSettings = File.createTempFile("temp_appsettings", ".tmp");
    tempSettings.mkdirs();

    boolean b = pullSettingsFileFromSimulator(remoteSettingsPath.get(), tempSettings);
    if (!b) {
      return new HashMap<>();
    }

    Map<MonkeyType, MonkeyType> settings = readSettingsFile(tempSettings);
    tempSettings.delete();

    return settings;
  }

  @NotNull
  private Map<MonkeyType, MonkeyType> readSettingsFile(File settingsFile) throws IOException {
    byte[] fileBytes = IOUtils.getFileBytes(settingsFile);
    Deserializer deserializer = new Deserializer(fileBytes);


    Optional<MonkeyTypeHash> settings = deserializer.getTypes()
      .stream()
      .filter(monkeyType -> monkeyType instanceof MonkeyTypeHash)
      .map(monkeyType -> (MonkeyTypeHash) monkeyType)
      .findFirst();

    return settings.map(MonkeyTypeHash::getItems).orElse(new HashMap<>());
  }

  private boolean pullSettingsFileFromSimulator(String remotePath, File localFile) throws IOException, ExecutionException {
    remotePath = sanitizePath(remotePath);
    String filePath = sanitizePath(localFile.getAbsolutePath());
    int exitValue = callShellCommand("pull", remotePath, filePath);
    return exitValue == 0;
  }

  // moduleName should actually be .prg filename, without extension
  private boolean sendSettingsFileToSimulator(File tempSettings, String remotePath) throws ExecutionException, IOException {
    String tempSettingsPath = sanitizePath(tempSettings.getAbsolutePath());
    int exitValue = callShellCommand("push", tempSettingsPath, remotePath);
    return exitValue == 0;
  }

  private int callShellCommand(String command, String fromPath, String toPath) throws ExecutionException, IOException {
    VirtualFile moduleOutputDir = CompilerPaths.getModuleOutputDirectory(module, false);
    String outputDir = moduleOutputDir.getPath() + File.separator;

    SimulatorHelper simulatorHelper = new SimulatorHelper(null, sdk, outputDir);
    Optional<Integer> simulatorPortNTimes = simulatorHelper.findSimulatorPortNTimes();
    if (!simulatorPortNTimes.isPresent()) {
      throw new IOException("simulator not found");
    }
    int simulatorPort = simulatorPortNTimes.get();
    Process process = createShellCmd(simulatorPort, outputDir, command, fromPath, toPath).createProcess();
    try {
      process.waitFor(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new IOException("shell did not close in time");
    }
    int i = process.exitValue();

    return i;
  }

  // with shell we can poll if simulator is running and ready
  private GeneralCommandLine createShellCmd(int port, String outputDir, String command, String fromPath, String toPath) {
    String shellExecutableName = getForWinLinOrMac("shell.exe", "shell");
    String sdkBinPath = MonkeySdkType.getBinPath(sdk);
    String exePath = sdkBinPath + shellExecutableName;

    //command is "pull" or "push"
    return createGeneralCommandLine(outputDir, exePath)
      .withParameters("--transport=tcp", "--transport_args=127.0.0.1:" + port, command, fromPath, toPath);
  }

  private static String sanitizePath(String path) {
    String sanitizedPath = path;
    if (sanitizedPath.contains(" ")) {
      sanitizedPath = '"' + path + '"';
    }
    return sanitizedPath;
  }

  public static String getDeviceFilePath(Socket clientSocket, String manifestId) {
    try {
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      out.println(SIMULATOR_SETTINGS_QUERY_PREFIX + manifestId);

      try (InputStream inputStream = clientSocket.getInputStream();
           BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
      ) {
        String inputLine = br.readLine();
        if ((inputLine == null) || (inputLine.equals(SIMULATOR_SETTINGS_RESPONSE_NEGATIVE))) {
          throw new IOException("Bad response from get settings path command");
        }
        return inputLine;
      }
    } catch (IOException e) {
      Throwables.propagate(e);
    }
    return null;
  }

  public static Socket openSocket(String hostname, int port) throws IOException {
    Socket socket = new Socket();
    socket.setSoTimeout(500); // read timeout
    socket.connect(new InetSocketAddress(hostname, port), 100); // connect timeout
    return socket;
  }

  public static <T> Optional<T> doWithSocket(Function<Socket, T> callback) {
    for (int port = SIMULATOR_PORT_MIN; port <= SIMULATOR_PORT_MAX; port++) {
      try (
        Socket socket = openSocket("localhost", port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
      ) {
        // Sends "MARCO" and waits for "POLO" to find correct socket
        writer.println(SIMULATOR_PING);
        if (reader.lines().anyMatch(l -> l.equals(SIMULATOR_PONG))) {
          LOG.info("Simulator found on port " + port);
          T val = callback.apply(socket);
          return Optional.of(val);
        }
      } catch (IOException ignored) {
      }
    }
    return Optional.empty();
  }


  public boolean sendToSimulator(byte[] serialize) {
    try {
      File tempSettings = File.createTempFile("temp_appsettings", ".tmp");
      tempSettings.mkdirs();

      Files.write(tempSettings.toPath(), serialize);

      String prgNameWithoutExtension = module.getName();

      String remotePath = SIMULATOR_SETTINGS_PATH + prgNameWithoutExtension + ".set";

      sendSettingsFileToSimulator(tempSettings, remotePath);
      tempSettings.delete();

      return notifySimulator(remotePath);
    } catch (IOException | ExecutionException e) {
      Throwables.propagate(e);
    }
    return false;
  }

  private boolean notifySimulator(String remoteSettingsPath) {
    Optional<Boolean> refreshed = doWithSocket(socket -> refreshSettingsFile(socket, manifestApplicationId, remoteSettingsPath));
    return refreshed.isPresent();
  }

  public static Boolean refreshSettingsFile(Socket clientSocket, String manifestId, String remoteSettingsPath) {
    String command = SIMULATOR_TRIGGER_NEW_SETTINGS_PREFIX + manifestId + " " + remoteSettingsPath;

    try {
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      out.println(command);

      try (InputStream inputStream = clientSocket.getInputStream();
           BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
      ) {
        String inputLine = br.readLine();
        if (inputLine == null || !inputLine.equals(SIMULATOR_TRIGGER_NEW_SETTINGS_RESPONSE_OK)) {
          throw new IOException("Bad response from notifying simulator about new settings path");
        }
        return true;
      }
    } catch (IOException e) {
      Throwables.propagate(e);
    }
    return false;
  }
}

package io.github.liias.monkey.ide.actions.appsettings;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.compiler.CompilerPaths;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import io.github.liias.monkey.project.dom.manifest.Manifest;
import io.github.liias.monkey.project.module.util.MonkeyModuleUtil;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import io.github.liias.monkey.project.sdk.tools.SimulatorHelper;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

  public AppSettingsManager.SettingsAndLanguages parseFromSim() throws IOException, ExecutionException {
    Optional<String> remoteSettingsPath = getRemoteSettingsPath(manifestApplicationId);

    if (!remoteSettingsPath.isPresent() || remoteSettingsPath.get().isEmpty()) {
      return null;
    }

    File tempSettings = File.createTempFile("temp_appsettings", ".tmp");
    tempSettings.mkdirs();

    boolean b = pullSettingsFileFromDevice(remoteSettingsPath.get(), tempSettings);
    if (!b) {
      return null;
    }

    System.out.println(tempSettings.getAbsolutePath());
    // TODO: read temp settings

    //tempSettings.delete();
    return null;
  }

  private boolean pullSettingsFileFromDevice(String remotePath, File localFile) throws IOException, ExecutionException {
    VirtualFile moduleOutputDir = CompilerPaths.getModuleOutputDirectory(module, false);
    String outputDir = moduleOutputDir.getPath() + File.separator;
    SimulatorHelper simulatorHelper = new SimulatorHelper(null, sdk, outputDir);


    Optional<Integer> simulatorPortNTimes = simulatorHelper.findSimulatorPortNTimes();
    if (!simulatorPortNTimes.isPresent()) {
      throw new IOException("simulator not found");
    }
    int simulatorPort = simulatorPortNTimes.get();

    remotePath = sanitizePath(remotePath);
    String filePath = sanitizePath(localFile.getAbsolutePath());

    Process process = createShellCmd(simulatorPort, outputDir, remotePath, filePath).createProcess();
    try {
      process.waitFor(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new IOException("shell did not close in time");
    }
    int i = process.exitValue();
    System.out.println("exit value: " + i);

    return true;
  }

  // with shell we can poll if simulator is running and ready
  private GeneralCommandLine createShellCmd(int port, String outputDir, String remotePath, String localPath) {
    String shellExecutableName = getForWinLinOrMac("shell.exe", "shell");
    String sdkBinPath = MonkeySdkType.getBinPath(sdk);
    String exePath = sdkBinPath + shellExecutableName;

    return createGeneralCommandLine(outputDir, exePath)
        .withParameters("--transport=tcp", "--transport_args=127.0.0.1:" + port, "pull", remotePath, localPath);
  }


  private static String sanitizePath(String path) {
    String sanitizedPath = path;
    if (sanitizedPath.contains(" ")) {
      sanitizedPath = '"' + path + '"';
    }
    return sanitizedPath;
  }

  public static String getDeviceFilePath(Socket clientSocket, String manifestId) throws IOException {
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
  }

  // Sends "MARCO" and waits for "POLO" to find correct socket
  public static Optional<String> getRemoteSettingsPath(String manifestApplicationId) {
    for (int port = SIMULATOR_PORT_MIN; port <= SIMULATOR_PORT_MAX; port++) {
      try (
          Socket socket = new Socket("localhost", port);
          BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
      ) {
        writer.println(SIMULATOR_PING);
        if (reader.lines().anyMatch(l -> l.equals(SIMULATOR_PONG))) {
          LOG.info("Simulator found on port " + port);
          String remoteSettingsPath = getDeviceFilePath(socket, manifestApplicationId);
          return Optional.of(remoteSettingsPath);
        }
      } catch (IOException ignored) {
      }
    }
    return Optional.empty();
  }
}

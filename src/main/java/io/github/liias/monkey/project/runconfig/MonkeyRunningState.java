package io.github.liias.monkey.project.runconfig;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.process.KillableColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.execution.util.ProgramParametersUtil;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.SystemInfoRt;
import com.intellij.openapi.vfs.CharsetToolkit;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import org.jetbrains.annotations.NotNull;

import java.io.*;

// Starts app in simulator
public class MonkeyRunningState extends CommandLineState {
  public static final int SIMULATOR_PORT_MIN = 1234;
  public static final int SIMULATOR_PORT_MAX = 1238;
  public static final String SHELL_SUCCESS_CONNECTED_TO_SIMULATOR = "Connection Finished";
  private MonkeyParameters monkeyParameters;
  private ConsoleView console;

  protected MonkeyRunningState(ExecutionEnvironment environment) {
    super(environment);
  }

  public MonkeyParameters getMonkeyParameters() throws ExecutionException {
    if (monkeyParameters == null) {
      monkeyParameters = createMcParameters();
    }
    return monkeyParameters;
  }

  private MonkeyParameters createMcParameters() throws ExecutionException {
    final MonkeyParameters params = new MonkeyParameters();
    final MonkeyModuleBasedConfiguration runConfig = getConfiguration();
    final MonkeyRunConfigurationModule configurationModule = runConfig.getConfigurationModule();
    final int classPathType = MonkeyParameters.SDK_AND_CLASSES;
    params.configureByModule(configurationModule.getModule(), classPathType);
    ProgramParametersUtil.configureConfiguration(params, runConfig);
    return params;
  }

  @Override
  @NotNull
  public ExecutionResult execute(@NotNull final Executor executor, @NotNull final ProgramRunner runner) throws ExecutionException {
    console = createConsole(executor);

    GeneralCommandLine runSimulatorCmd = createRunSimulatorCmd();
    runSimulatorCmd.createProcess();

    if (!trySimulatorConnection()) {
      throw new ExecutionException("Could not connect to simulator");
    }

    final ProcessHandler runInSimHandler = startProcess();
    if (console != null) {
      console.attachToProcess(runInSimHandler);
    }

    return new DefaultExecutionResult(console, runInSimHandler);
  }

  @NotNull
  @Override
  protected ProcessHandler startProcess() throws ExecutionException {
    GeneralCommandLine runInSimulatorCmd = createRunInSimulatorCmd();
    return new KillableColoredProcessHandler(runInSimulatorCmd);
  }

  private MonkeyModuleBasedConfiguration getConfiguration() {
    if (getEnvironment().getRunnerAndConfigurationSettings() == null) {
      throw new RuntimeException("runnerAndConfigurationSettings is null");
    }
    final RunConfiguration configuration = getEnvironment().getRunnerAndConfigurationSettings().getConfiguration();
    if (configuration == null || !(configuration instanceof MonkeyModuleBasedConfiguration)) {
      throw new RuntimeException("runnerAndConfigurationSettings.getConfiguration() is null or wrong type");
    }
    return (MonkeyModuleBasedConfiguration) configuration;
  }

  // with shell we can poll if simulator is running and ready
  private GeneralCommandLine createShellCmd(String outputDir, int port) throws ExecutionException {
    final Sdk sdk = getMonkeyParameters().getSdk();
    String sdkBinPath = MonkeySdkType.getBinPath(sdk);
    String shellExecutableName = getForWinLinOrMac("shell.exe", "shell");
    String exePath = sdkBinPath + shellExecutableName;

    return createGeneralCommandLine(outputDir, exePath)
        .withParameters("--transport=tcp", "--transport_args=127.0.0.1:" + port, "tvm", "help");
  }


  private boolean trySimulatorConnection() throws ExecutionException {
    MonkeyParameters monkeyParameters = getMonkeyParameters();
    String outputDir = monkeyParameters.getOutputPath().getPath() + File.separator;

    final int maxAttempts = 5;
    for (int i = 0; i < maxAttempts; i++) {
      if (isConnectedToSimulator(outputDir, i + 1)) {
        return true;
      }
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        console.print(e.getMessage(), ConsoleViewContentType.ERROR_OUTPUT);
      }
    }
    return false;
  }

  private boolean isConnectedToSimulator(String outputDir, int i) {
    for (int port = SIMULATOR_PORT_MIN; port < SIMULATOR_PORT_MAX; port++) {
      console.print("Attempt " + i + " to connect to simulator (port " + port + ")...\n", ConsoleViewContentType.NORMAL_OUTPUT);
      try {
        Process process = createShellCmd(outputDir, port).createProcess();
        return hasProcessOutputLineContaining(process, SHELL_SUCCESS_CONNECTED_TO_SIMULATOR);
      } catch (ExecutionException e) {
        console.print(e.getMessage(), ConsoleViewContentType.ERROR_OUTPUT);
      }
    }
    return false;
  }

  private static boolean hasProcessOutputLineContaining(Process process, String successLine) {
    try (InputStream inputStream = process.getInputStream();
         InputStreamReader in = new InputStreamReader(inputStream);
         BufferedReader br = new BufferedReader(in)
    ) {
      if (br.lines().anyMatch(l -> l.contains(successLine))) {
        return true;
      }
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return false;
  }

  private GeneralCommandLine createRunSimulatorCmd() throws ExecutionException {
    final Sdk sdk = getMonkeyParameters().getSdk();
    String sdkBinPath = MonkeySdkType.getBinPath(sdk);

    String simulatorExecutableName = getForWinLinOrMac("simulator.exe", "ConnectIQ.app");
    String exePath = sdkBinPath + simulatorExecutableName;
    return createGeneralCommandLine(sdkBinPath, exePath).withRedirectErrorStream(true);
  }

  private static GeneralCommandLine createGeneralCommandLine(String workDirectory, String exePath) {
    if (SystemInfo.isLinux) {
      return new GeneralCommandLine()
          .withWorkDirectory(workDirectory)
          .withExePath("wine")
          .withParameters(exePath);
    }

    if (SystemInfo.isMac) {
      return new GeneralCommandLine()
          .withWorkDirectory(workDirectory)
          .withExePath("open")
          .withParameters(exePath);
    }

    return new GeneralCommandLine()
        .withWorkDirectory(workDirectory)
        .withExePath(exePath);
  }

  private static String getForWinLinOrMac(String winLin, String mac) {
    if (SystemInfoRt.isWindows || SystemInfoRt.isLinux) {
      return winLin;
    } else if (SystemInfoRt.isMac) {
      return mac;
    }
    throw new UnsupportedOperationException("OS is unsupported: " + SystemInfoRt.OS_NAME);
  }

  private static String getSimulatorExecutableName() {
    return getForWinLinOrMac("simulator.exe", "ConnectIQ.app");
  }

  private GeneralCommandLine createRunInSimulatorCmd() throws ExecutionException {
    MonkeyParameters monkeyParameters = getMonkeyParameters();
    String outputDir = monkeyParameters.getOutputPath().getPath() + File.separator;
    String outputName = getEnvironment().getProject().getName() + ".prg";
    String prgPath = outputDir + outputName;

    Sdk sdk = monkeyParameters.getSdk();

    return new GeneralCommandLine()
        .withWorkDirectory(MonkeySdkType.getBinPath(sdk))
        .withCharset(CharsetToolkit.UTF8_CHARSET)
        .withExePath(MonkeySdkType.getMonkeydoBatPath(sdk))
        .withParameters(prgPath, getConfiguration().getTargetDeviceId());
  }

}

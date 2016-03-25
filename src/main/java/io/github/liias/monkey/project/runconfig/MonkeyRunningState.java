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
import com.intellij.execution.util.ProgramParametersUtil;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.CharsetToolkit;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

// Starts app in simulator
public class MonkeyRunningState extends CommandLineState {
  private MonkeyParameters monkeyParameters;

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
    GeneralCommandLine runSimulatorCmd = createRunSimulatorCmd();
    ProcessBuilder pb = new ProcessBuilder(runSimulatorCmd.getExePath())
        .directory(runSimulatorCmd.getWorkDirectory());

    try {
      pb.start();
    } catch (IOException e) {
      throw new ExecutionException(e.getMessage());
    }

    final ProcessHandler runInSimHandler = startProcess();
    final ConsoleView console = createConsole(executor);
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

  private GeneralCommandLine createRunSimulatorCmd() throws ExecutionException {
    final MonkeyParameters monkeyParameters = getMonkeyParameters();
    final Sdk sdk = monkeyParameters.getSdk();
    String sdkBinPath = MonkeySdkType.getBinPath(sdk);
    String connectiq = SystemInfo.isWindows ? "connectiq.bat" : "connectiq";
    String exePath = sdkBinPath + connectiq;

    return new GeneralCommandLine()
        .withWorkDirectory(sdkBinPath)
        .withExePath(exePath);
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

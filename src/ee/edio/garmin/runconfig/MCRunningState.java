package ee.edio.garmin.runconfig;

import com.google.common.collect.ImmutableList;
import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.EncodingEnvironmentUtil;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.process.KillableColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.util.ProgramParametersUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.vfs.CharsetToolkit;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

// Starts app in simulator
public class MCRunningState extends CommandLineState {
  private MCParameters mcParameters;

  protected MCRunningState(ExecutionEnvironment environment) {
    super(environment);
  }

  public MCParameters getMcParameters() throws ExecutionException {
    if (mcParameters == null) {
      mcParameters = createMcParameters();
    }
    return mcParameters;
  }

  private MCParameters createMcParameters() throws ExecutionException {
    final MCParameters params = new MCParameters();
    final MCModuleBasedConfiguration runConfig = getConfiguration();
    final MCRunConfigurationModule configurationModule = runConfig.getConfigurationModule();
    final int classPathType = MCParameters.SDK_AND_CLASSES;
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

  private MCModuleBasedConfiguration getConfiguration() {
    if (getEnvironment().getRunnerAndConfigurationSettings() == null) {
      throw new RuntimeException("runnerAndConfigurationSettings is null");
    }
    final RunConfiguration configuration = getEnvironment().getRunnerAndConfigurationSettings().getConfiguration();
    if (configuration == null || !(configuration instanceof MCModuleBasedConfiguration)) {
      throw new RuntimeException("runnerAndConfigurationSettings.getConfiguration() is null or wrong type");
    }
    return (MCModuleBasedConfiguration) configuration;
  }

  private GeneralCommandLine createRunSimulatorCmd() throws ExecutionException {
    final MCParameters mcParameters = getMcParameters();
    final Sdk sdk = mcParameters.getSdk();
    String sdkPath = sdk.getHomePath() + File.separator;
    String sdkBinPath = sdkPath + "bin" + File.separator;
    GeneralCommandLine commandLine = new GeneralCommandLine()
        .withWorkDirectory(sdkBinPath);
    commandLine.setExePath(sdkBinPath + "connectiq.bat");
    return commandLine;
  }

  private GeneralCommandLine createRunInSimulatorCmd() throws ExecutionException {
    final MCParameters mcParameters = getMcParameters();
    final Sdk sdk = mcParameters.getSdk();
    String sdkPath = sdk.getHomePath() + File.separator;

    final List<String> pathList = mcParameters.getClassPath().getPathList();
    if (pathList.isEmpty()) {
      throw new ExecutionException("pathlist is empty");
    }
    final String outputDir = pathList.get(0) + File.separator;
    // e.g C:\Users\xyz\workspace\esimene\aabb\production\esimene
    Project project = getEnvironment().getProject();
    String sdkBinPath = sdkPath + "bin" + File.separator;
    String outputName = project.getName() + ".prg";
    String prgPath = outputDir + outputName;

    final MCModuleBasedConfiguration runConfig = getConfiguration();
    final String deviceId = runConfig.getTargetDeviceId();

    ImmutableList.Builder<String> parameters = ImmutableList.<String>builder()
        .add(prgPath)
        .add(deviceId);

    GeneralCommandLine commandLine = new GeneralCommandLine()
        .withWorkDirectory(sdkBinPath)
        .withCharset(CharsetToolkit.UTF8_CHARSET);
    EncodingEnvironmentUtil.setLocaleEnvironmentIfMac(commandLine);

    commandLine.setExePath(sdkBinPath + "monkeydo.bat");
    commandLine.addParameters(parameters.build());
    return commandLine;
  }

}

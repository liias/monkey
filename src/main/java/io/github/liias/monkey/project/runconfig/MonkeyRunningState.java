package io.github.liias.monkey.project.runconfig;

import com.google.common.base.Preconditions;
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
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.impl.VirtualDirectoryImpl;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import io.github.liias.monkey.project.sdk.tools.SimulatorHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static io.github.liias.monkey.Utils.createGeneralCommandLine;
import static io.github.liias.monkey.Utils.getForWinLinOrMac;

// Starts app in simulatorHelper
public class MonkeyRunningState extends CommandLineState {

  private SimulatorHelper simulatorHelper;
  private MonkeyParameters monkeyParameters;
  private ConsoleView console;

  protected MonkeyRunningState(ExecutionEnvironment environment) {
    super(environment);
  }

  public MonkeyParameters getMonkeyParameters() throws ExecutionException {
    if (monkeyParameters == null) {
      monkeyParameters = createMonkeyParameters();
    }
    return monkeyParameters;
  }

  private MonkeyParameters createMonkeyParameters() throws ExecutionException {
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

    if (DeploymentTarget.DEVICE.getId().equals(getConfiguration().getDeploymentTargetId())) {
      VirtualFile copiedPrg = copyBuiltPrgToDevice();

      String message = String.format("Generated %s\nYou can now start the app on your device.", copiedPrg);
      console.print(message, ConsoleViewContentType.NORMAL_OUTPUT);
      ProcessHandler processHandler = new DummyProcessHandler();
      processHandler.destroyProcess();
      return new DefaultExecutionResult(console, processHandler);
    }

    MonkeyParameters monkeyParameters = getMonkeyParameters();
    Sdk sdk = monkeyParameters.getSdk();
    String outputDir = monkeyParameters.getOutputPath().getPath() + File.separator;

    simulatorHelper = new SimulatorHelper(console, sdk, outputDir);

    GeneralCommandLine runSimulatorCmd = createRunSimulatorCmd();
    runSimulatorCmd.createProcess();

    if (!simulatorHelper.findSimulatorPortNTimes(5, 1000).isPresent()) {
      throw new ExecutionException("Could not connect to simulatorHelper");
    }

    final ProcessHandler runInSimHandler = startProcess();
    if (console != null) {
      console.attachToProcess(runInSimHandler);
    }

    AnAction[] actions = createActions(console, runInSimHandler, executor);
    return new DefaultExecutionResult(console, runInSimHandler, actions);
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

  private VirtualFile copyBuiltPrgToDevice() throws ExecutionException {
    try {
      MonkeyParameters monkeyParameters = getMonkeyParameters();
      String prgPath = monkeyParameters.getOutputPath().findChild(getPrgName()).getPath();
      String deviceDirectory = getConfiguration().getDeviceDirectory();

      return copyPrgTo(prgPath, deviceDirectory);
    } catch (IOException e) {
      throw new ExecutionException(e);
    }
  }

  @NotNull
  private VirtualFile copyPrgTo(String prgOutputPath, String outputPath) throws IOException {
    LocalFileSystem lfs = LocalFileSystem.getInstance();
    VirtualFile fromOutputPrg = lfs.refreshAndFindFileByPath(prgOutputPath);
    Preconditions.checkNotNull(fromOutputPrg);

    VirtualDirectoryImpl toOutputDirectory = (VirtualDirectoryImpl) lfs.refreshAndFindFileByPath(outputPath);
    Preconditions.checkNotNull(toOutputDirectory);

    String fileName = fromOutputPrg.getName();

    VirtualFile existingPrg = toOutputDirectory.refreshAndFindChild(fileName);

    return ApplicationManager.getApplication()
      .runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {
          if (existingPrg != null) {
            Preconditions.checkState(!existingPrg.isDirectory()); // just in case to catch mistakes
            existingPrg.delete(this);
          }
          return fromOutputPrg.copy(this, toOutputDirectory, fileName);
        }
      );
  }

  private GeneralCommandLine createRunSimulatorCmd() throws ExecutionException {
    final Sdk sdk = getMonkeyParameters().getSdk();
    String sdkBinPath = MonkeySdkType.getBinPath(sdk);

    String simulatorExecutableName = getForWinLinOrMac("simulator.exe", "ConnectIQ.app");
    String exePath = sdkBinPath + simulatorExecutableName;
    return createGeneralCommandLine(sdkBinPath, exePath).withRedirectErrorStream(true);
  }

  private GeneralCommandLine createRunInSimulatorCmd() throws ExecutionException {
    MonkeyParameters monkeyParameters = getMonkeyParameters();
    String prgPath = monkeyParameters.getOutputPath().findChild(getPrgName()).getPath();

    Sdk sdk = monkeyParameters.getSdk();

    return createGeneralCommandLine(MonkeySdkType.getBinPath(sdk), MonkeySdkType.getMonkeydoBatPath(sdk))
      .withParameters(prgPath, getConfiguration().getTargetDeviceId());
  }

  public String getPrgName() {
    return getEnvironment().getProject().getName() + ".prg";
  }


  private static class DummyProcessHandler extends ProcessHandler {

    @Override
    protected void destroyProcessImpl() {
      notifyProcessTerminated(0);
    }

    @Override
    protected void detachProcessImpl() {
      notifyProcessDetached();
    }

    @Override
    public boolean detachIsDefault() {
      return false;
    }

    @Nullable
    @Override
    public OutputStream getProcessInput() {
      return null;
    }
  }
}

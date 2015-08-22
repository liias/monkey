package ee.edio.garmin.runconfig;

import com.google.common.collect.ImmutableList;
import com.intellij.execution.CantRunException;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.EncodingEnvironmentUtil;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.process.KillableColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.util.ProgramParametersUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathsList;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

// Starts app in simulator
public class MonkeyCRunningState extends CommandLineState {
  private MCParameters mcParameters;

  protected MonkeyCRunningState(ExecutionEnvironment environment) {
    super(environment);
  }

  public MCParameters getMcParameters() throws ExecutionException {
    if (mcParameters == null) {
      mcParameters = createMcParameters();
    }
    return mcParameters;
  }

/*  protected JavaParameters createJavaParameters() throws ExecutionException {
    final JavaParameters params = new JavaParameters();
    final JavaRunConfigurationModule module = myConfiguration.getConfigurationModule();

    final int classPathType = JavaParametersUtil.getClasspathType(module,
        myConfiguration.MAIN_CLASS_NAME,
        false);
    final String jreHome = myConfiguration.ALTERNATIVE_JRE_PATH_ENABLED ? myConfiguration.ALTERNATIVE_JRE_PATH : null;
    JavaParametersUtil.configureModule(module, params, classPathType, jreHome);
    params.setMainClass(myConfiguration.MAIN_CLASS_NAME);
    setupJavaParameters(params);

    return params;
  }*/

  private MCParameters createMcParameters() throws ExecutionException {
    final MCParameters params = new MCParameters();
    final MCModuleBasedConfiguration runConfig = getConfiguration();
    final MonkeyCRunConfigurationModule configurationModule = runConfig.getConfigurationModule();

    final int classPathType = MCParameters.SDK_AND_CLASSES;

    params.configureByModule(configurationModule.getModule(), classPathType);

    //final String jreHome = runConfig.ALTERNATIVE_JRE_PATH_ENABLED ? myConfiguration.ALTERNATIVE_JRE_PATH : null;
    //JavaParametersUtil.configureModule(configurationModule, params, classPathType, null);
    //params.setMainClass(myConfiguration.MAIN_CLASS_NAME);
    // setupJavaParameters(params);
    ProgramParametersUtil.configureConfiguration(params, runConfig);

    return params;
  }

  @NotNull
  @Override
  protected ProcessHandler startProcess() throws ExecutionException {
    GeneralCommandLine commandLine = createRunCmd();
    commandLine.withCharset(CharsetToolkit.UTF8_CHARSET);
    EncodingEnvironmentUtil.setLocaleEnvironmentIfMac(commandLine);
    return new KillableColoredProcessHandler(commandLine);
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

  private GeneralCommandLine createRunCmd() throws ExecutionException {
    final MCModuleBasedConfiguration runConfig = getConfiguration();
    //final String outputFilePath = runConfig.getOutputFilePath();
    final MonkeyCRunConfigurationModule configurationModule = runConfig.getConfigurationModule();
    final Module module = configurationModule.getModule();

    final MCParameters mcParameters = getMcParameters();
    final Sdk sdk = mcParameters.getSdk();
    String sdkPath = sdk.getHomePath() + File.separator;

    final PathsList classPath = mcParameters.getClassPath();
    final List<String> pathList = mcParameters.getClassPath().getPathList();
    if (pathList.isEmpty()) {
      throw new ExecutionException("pathlist is empty");
    }
    final String outputDir = pathList.get(0) + File.separator;
//    C:\Users\Madis\workspace\esimene\aabb\production\esimene
    Project project = getEnvironment().getProject();
    String projectBasePath = project.getBasePath();
    String sdkBinPath = sdkPath + "bin" + File.separator;
    String outputName = project.getName() + ".prg";

    //getEnvironment().getConfigurationSettings();
    //String outputDir = projectBasePath + File.separator + "bin" + File.separator;
    String prgPath = outputDir + outputName;

    /*ImmutableList.Builder<String> parameters = ImmutableList.<String>builder()
        .add("--transport=tcp")
        .add("tvm")
        .add("help");

    int maxAttempts = 5;
    int currentAttempt = 0;
    boolean connectionEstablished = false;

    String port = "1234";
    parameters.add("--transport_args=127.0.0.1:" + port);*/

    ImmutableList.Builder<String> parameters = ImmutableList.<String>builder()
        .add(prgPath)
        .add("vivoactive");
    //.add("-d", "vivoactive");


    GeneralCommandLine commandLine = new GeneralCommandLine()
        .withWorkDirectory(sdkBinPath);

    commandLine.setExePath(sdkBinPath + "monkeydo.bat");
    commandLine.addParameters(parameters.build());
    //commandLine.withWorkDirectory();
//    for(int port = 1234; port < 1238; ++port) {


    /*          while((line = br.readLine()) != null) {
            if(line.contains("Connection Finished")) {
              connectionEstablished = true;
              break;
            }
          }

          if(connectionEstablished) {
            br.close();
            in.close();
            return true;
          }*/
    return commandLine;
  }

  public static Sdk getTheModuleSdk(final Module module) throws CantRunException {
    final Sdk sdk = ModuleRootManager.getInstance(module).getSdk();
    if (sdk == null) {
      throw new CantRunException("No SDK for module " + module.getName());
    }
    final VirtualFile homeDirectory = sdk.getHomeDirectory();
    if (homeDirectory == null || !homeDirectory.isValid()) {
      throw CantRunException.jdkMisconfigured(sdk, module);
    }
    return sdk;
  }
}

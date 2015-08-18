package ee.edio.garmin.runconfig;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.EncodingEnvironmentUtil;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.KillableColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MonkeyCRunningState extends CommandLineState {
  protected MonkeyCRunningState(ExecutionEnvironment environment) {
    super(environment);
  }

  @NotNull
  @Override
  protected ProcessHandler startProcess() throws ExecutionException {

//    GeneralCommandLine commandLine = createBuildCmd();
    //GeneralCommandLine commandLine = createBuildCmd();
    GeneralCommandLine commandLine = createRunCmd();
//    commandLine.withWorkDirectory(myWorkDirectory);

//    commandLine.getEnvironment().putAll(myExtraEnvironment);
//    commandLine.getEnvironment().put(GoConstants.GO_ROOT, StringUtil.notNullize(myGoRoot));



   /* GeneralCommandLine commandLine = !myPtyDisabled ? new PtyCommandLine() : generalCommandLine;
    commandLine.setExePath(ObjectUtils.notNull(myExePath, GoSdkService.getGoExecutablePath(myGoRoot)));
    commandLine.getEnvironment().putAll(myExtraEnvironment);
    commandLine.getEnvironment().put(GoConstants.GO_ROOT, StringUtil.notNullize(myGoRoot));
    commandLine.getEnvironment().put(GoConstants.GO_PATH, StringUtil.notNullize(myGoPath));

    Collection<String> paths = ContainerUtil.newArrayList();
    ContainerUtil.addIfNotNull(paths, StringUtil.nullize(commandLine.getEnvironment().get(GoConstants.PATH), true));
    ContainerUtil.addIfNotNull(paths, StringUtil.nullize(EnvironmentUtil.getValue(GoConstants.PATH), true));
    ContainerUtil.addIfNotNull(paths, StringUtil.nullize(myEnvPath, true));
    commandLine.getEnvironment().put(GoConstants.PATH, StringUtil.join(paths, File.pathSeparator));
*/
//    commandLine.withWorkDirectory(myWorkDirectory);
//    commandLine.addParameters(myParameterList.getList());
//    commandLine.setPassParentEnvironment(myPassParentEnvironment);
    commandLine.withCharset(CharsetToolkit.UTF8_CHARSET);
    EncodingEnvironmentUtil.setLocaleEnvironmentIfMac(commandLine);
//    return commandLine;
    return new KillableColoredProcessHandler(commandLine);

  }

  private GeneralCommandLine createRunCmd() {
    Project project = getEnvironment().getProject();
    String projectBasePath = project.getBasePath();
    String sdkPath = "C:\\Users\\Madis\\sdks\\connectiq-sdk-win-1.1.3";
    String sdkBinPath = sdkPath + File.separator + "bin" + File.separator;
    String outputName = project.getName() + ".prg";
    String outputDir = projectBasePath + File.separator + "bin" + File.separator;

    ImmutableList.Builder<String> parameters = ImmutableList.<String>builder()
        .add("--transport=tcp")
        .add("tvm")
        .add("help");

    int maxAttempts = 5;
    int currentAttempt = 0;
    boolean connectionEstablished = false;

    String port = "1234";
    parameters.add("--transport_args=127.0.0.1:" + port);

    GeneralCommandLine commandLine = new GeneralCommandLine()
        .withWorkDirectory(sdkBinPath);

    commandLine.setExePath(sdkBinPath + "shell.exe");
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
}

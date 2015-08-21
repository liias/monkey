package ee.edio.garmin.runconfig;

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
import org.jetbrains.annotations.NotNull;

import java.io.File;

// Starts app in simulator
public class MonkeyCRunningState extends CommandLineState {
  protected MonkeyCRunningState(ExecutionEnvironment environment) {
    super(environment);
  }

  @NotNull
  @Override
  protected ProcessHandler startProcess() throws ExecutionException {
    GeneralCommandLine commandLine = createRunCmd();
    commandLine.withCharset(CharsetToolkit.UTF8_CHARSET);
    EncodingEnvironmentUtil.setLocaleEnvironmentIfMac(commandLine);
    return new KillableColoredProcessHandler(commandLine);
  }

  private GeneralCommandLine createRunCmd() {
    Project project = getEnvironment().getProject();
    String projectBasePath = project.getBasePath();
    String sdkPath = "C:\\Users\\Madis\\sdks\\connectiq-sdk-win-1.1.3";
    String sdkBinPath = sdkPath + File.separator + "bin" + File.separator;
    String outputName = project.getName() + ".prg";
    String outputDir = projectBasePath + File.separator + "bin" + File.separator;
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
}

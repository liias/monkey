package ee.edio.garmin.runconfig;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.EncodingEnvironmentUtil;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.KillableColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.vfs.CharsetToolkit;
import org.jetbrains.annotations.NotNull;

public class MonkeyCRunningState extends CommandLineState {
  protected MonkeyCRunningState(ExecutionEnvironment environment) {
    super(environment);
  }

  @NotNull
  @Override
  protected ProcessHandler startProcess() throws ExecutionException {

    GeneralCommandLine commandLine = new GeneralCommandLine();
    commandLine.setExePath("notepad.exe");
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
}

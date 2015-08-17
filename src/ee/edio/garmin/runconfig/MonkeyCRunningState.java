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
    GeneralCommandLine commandLine = createBuildCmd();
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
        .add("\\bin\\simulator.exe")
        .add();
    return null;


  }

  public GeneralCommandLine createBuildCmd() {
    Project project = getEnvironment().getProject();
    String projectBasePath = project.getBasePath();
    String sdkPath = "C:\\Users\\Madis\\sdks\\connectiq-sdk-win-1.1.3";
    String sdkBinPath = sdkPath + File.separator + "bin" + File.separator;

    String outputName = project.getName() + ".prg";
    String outputDir = projectBasePath + File.separator + "bin" + File.separator;

    boolean hasResources = true;
    String sourcePath = projectBasePath + File.separator + "source" + File.separator;


    ImmutableList.Builder<String> parameters = ImmutableList.<String>builder()
        .add("-a", sdkBinPath + "api.db")
        .add("-i", sdkBinPath + "api.debug.xml")
        .add("-o", outputDir + outputName);
//        .add("-w") //debug info


    if (hasResources) {
      parameters.add("-z", projectBasePath + File.separator + "resources" + File.separator + "resources.xml")
          .add("-z", projectBasePath + File.separator + "resources" + File.separator + "menus" + File.separator + "menu.xml")
          .add("-z", projectBasePath + File.separator + "resources" + File.separator + "layouts" + File.separator + "layout.xml");
    }
    parameters.add("-m", projectBasePath + File.separator + "manifest.xml")
        .add("-u", sdkBinPath + "devices.xml")
        .add("-p", sdkBinPath + "projectInfo.xml"); // optional file


    VirtualFile sourceDir = project.getBaseDir().findChild("source");
    if (sourceDir == null) {
      throw new RuntimeException("source dir does not exist");
    }
    List<VirtualFile> sourceFiles = Arrays.asList(sourceDir.getChildren());

    ImmutableList<String> sourceFilePaths = FluentIterable.from(sourceFiles)
        .filter(new Predicate<VirtualFile>() {
          @Override
          public boolean apply(VirtualFile virtualFile) {
            return "mc".equals(virtualFile.getExtension());
          }
        }).transform(new Function<VirtualFile, String>() {
          @Override
          public String apply(VirtualFile virtualFile) {
            return virtualFile.getPath();
          }
        }).toList();

    parameters.addAll(sourceFilePaths);
    //parameters.add(sourcePath + "EsimeneView.mc", sourcePath + "EsimeneMenuDelegate.mc", sourcePath + "EsimeneApp.mc");

    parameters.add("-d", "vivoactive_sim");

    GeneralCommandLine commandLine = new GeneralCommandLine();
    commandLine.setExePath("java");
    commandLine.addParameters("-Dfile.encoding=UTF-8", "-Dapple.awt.UIElement=true");
    String classPath = "C:\\Program Files\\Java\\jre1.8.0_51\\lib\\tools.jar;";
    classPath += sdkBinPath + "monkeybrains.jar" + ";";
    commandLine.addParameters("-classpath", classPath);
    commandLine.addParameters("com.garmin.monkeybrains.Monkeybrains");

    commandLine.addParameters(parameters.build());
    return commandLine;
  }
}

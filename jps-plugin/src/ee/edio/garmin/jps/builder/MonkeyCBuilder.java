package ee.edio.garmin.jps.builder;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.BaseOSProcessHandler;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildOutputConsumer;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ProjectBuildException;
import org.jetbrains.jps.incremental.TargetBuilder;
import org.jetbrains.jps.incremental.resources.ResourcesBuilder;
import org.jetbrains.jps.incremental.resources.StandardResourceBuilderEnabler;
import org.jetbrains.jps.model.module.JpsModule;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MonkeyCBuilder extends TargetBuilder<MonkeyCSourceRootDescriptor, MonkeyCTarget> {
  public static final String NAME = "Monkey C";

  private final static Logger LOG = Logger.getInstance(MonkeyCBuilder.class);

  public MonkeyCBuilder() {
    super(Arrays.asList(MonkeyCTargetType.PRODUCTION, MonkeyCTargetType.TESTS));
    ResourcesBuilder.registerEnabler(new StandardResourceBuilderEnabler() {
      @Override
      public boolean isResourceProcessingEnabled(@NotNull JpsModule module) {
        return false;
        //return module.getModuleType() != JpsGoModuleType.INSTANCE;
      }
    });
  }


  @Override
  public void build(@NotNull MonkeyCTarget target, @NotNull DirtyFilesHolder<MonkeyCSourceRootDescriptor, MonkeyCTarget> holder,
                    @NotNull BuildOutputConsumer outputConsumer, @NotNull CompileContext context) throws ProjectBuildException, IOException {
    LOG.debug(target.getPresentableName());
    if (!holder.hasDirtyFiles() && !holder.hasRemovedFiles()) return;
    JpsModule jpsModule = target.getModule();
    for (String contentRootUrl : jpsModule.getContentRootsList().getUrls()) {
      String contentRootPath = new URL(contentRootUrl).getPath();
      final String projectName = context.getProjectDescriptor().getProject().getName();
      final GeneralCommandLine buildCmd = createBuildCmd(projectName, contentRootPath);
      runBuildProcess(context, buildCmd, contentRootPath);
    }
  }

  private static void runBuildProcess(@NotNull CompileContext context, @NotNull GeneralCommandLine commandLine, @NotNull String path)
      throws ProjectBuildException {
    try {
      final Process process = commandLine.createProcess();
      BaseOSProcessHandler handler = new BaseOSProcessHandler(process, commandLine.getCommandLineString(), Charset.defaultCharset());
      handler.startNotify();
      handler.waitFor();
    } catch (ExecutionException e) {
      throw new ProjectBuildException(e.getMessage());
    }
  }

  public GeneralCommandLine createBuildCmd(String projectName, String rootPath) {

    final File file = new File(FileUtil.toSystemIndependentName(rootPath));

    Pattern pattern = Pattern.compile(".*\\.mc");

    final List<File> mcFiles = FileUtil.findFilesByMask(pattern, file);
    final ImmutableList<String> sourceFilePaths = FluentIterable.from(mcFiles).transform(new Function<File, String>() {
      @Override
      public String apply(File file) {
        return file.getAbsolutePath();
      }
    }).toList();

    //Project project = getEnvironment().getProject();
    //String projectBasePath = project.getBasePath();
    String projectBasePath = rootPath;
    String sdkPath = "C:\\Users\\Madis\\sdks\\connectiq-sdk-win-1.1.3";
    String sdkBinPath = sdkPath + File.separator + "bin" + File.separator;

    String outputName = projectName + ".prg";
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

  @NotNull
  @Override
  public String getPresentableName() {
    return NAME;
  }
}

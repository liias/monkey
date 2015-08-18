package ee.edio.garmin.runconfig.builder;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.BaseOSProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
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
    //if (jpsModule.getModuleType() != JpsGoModuleType.INSTANCE) return;

    //JpsTypedModule<JpsSimpleElement<JpsGoModuleProperties>> module = jpsModule.asTyped(JpsGoModuleType.INSTANCE);
    //assert module != null;
    //JpsSdk<JpsDummyElement> sdk = getSdk(context, module);
    //File executable = new File(sdk.getHomePath(), "bin/" + GoEnvironmentUtil.getBinaryFileNameForPath(GoConstants.GO_EXECUTABLE_NAME));
    //File outputDirectory = getBuildOutputDirectory(module, target.isTests(), context);

    for (String contentRootUrl : jpsModule.getContentRootsList().getUrls()) {
      String contentRootPath = new URL(contentRootUrl).getPath();
      final String projectName = context.getProjectDescriptor().getProject().getName();
      final GeneralCommandLine buildCmd = createBuildCmd(projectName, contentRootPath);

      //GeneralCommandLine commandLine = new GeneralCommandLine();
      //commandLine.getEnvironment().put(GoConstants.GO_PATH, JpsGoLibrariesExtensionService.getInstance().retrieveGoPath(module));
      //commandLine.withWorkDirectory(contentRootPath);
      //commandLine.setExePath(executable.getAbsolutePath());
      //commandLine.addParameter("build");
      //String outExecutable = GoEnvironmentUtil.getExecutableResultForModule(contentRootPath, outputDirectory.getAbsolutePath());
      //commandLine.addParameters("-o", FileUtil.toSystemDependentName(outExecutable));

      runBuildProcess(context, buildCmd, contentRootPath);
    }
  }

  private static void runBuildProcess(@NotNull CompileContext context, @NotNull GeneralCommandLine commandLine, @NotNull String path)
      throws ProjectBuildException {
    try {
      final Process process = commandLine.createProcess();
      BaseOSProcessHandler handler = new BaseOSProcessHandler(process, commandLine.getCommandLineString(), Charset.defaultCharset());
      //final KillableColoredProcessHandler killableColoredProcessHandler = new KillableColoredProcessHandler(commandLine);
      handler.startNotify();
      handler.waitFor();

    } catch (ExecutionException e) {
      throw new ProjectBuildException(e.getMessage());
    }
  }

  public GeneralCommandLine createBuildCmd(String projectName, String rootPath) {

    final File file = new File(FileUtil.toSystemIndependentName(rootPath));

    final LocalFileSystem localFileSystem = LocalFileSystem.getInstance();
    final VirtualFile sourceDir = localFileSystem.findFileByIoFile(file);

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


    //VirtualFile sourceDir = project.getBaseDir().findChild("source");
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

  @NotNull
  @Override
  public String getPresentableName() {
    return NAME;
  }
}

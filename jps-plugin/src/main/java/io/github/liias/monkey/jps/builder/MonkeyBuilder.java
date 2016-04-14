package io.github.liias.monkey.jps.builder;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.BaseOSProcessHandler;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.projectRoots.JdkUtil;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.util.SystemProperties;
import io.github.liias.monkey.jps.model.JpsMonkeyModuleProperties;
import io.github.liias.monkey.jps.model.JpsMonkeyModuleType;
import io.github.liias.monkey.jps.model.JpsMonkeySdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildOutputConsumer;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ProjectBuildException;
import org.jetbrains.jps.incremental.TargetBuilder;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.jetbrains.jps.incremental.resources.ResourcesBuilder;
import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.JpsSimpleElement;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import org.jetbrains.jps.model.library.sdk.JpsSdk;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsTypedModule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MonkeyBuilder extends TargetBuilder<MonkeySourceRootDescriptor, MonkeyBuildTarget> {
  public static final String NAME = "Monkey C";

  private final static Logger LOG = Logger.getInstance(MonkeyBuilder.class);
  public static final String MONKEYBRAINS_FQN = "com.garmin.monkeybrains.Monkeybrains";
  public static final String MONKEYBRAINS_JAR_FILENAME = "monkeybrains.jar";


  private static final Pattern COMPILE_OUTPUT_LINE_WITH_FILE_AND_LINE_INFO = Pattern.compile("(?<type>(ERROR|WARNING)):(?<file>.*):(?<lineNo>[0-9]*):(?<message>.*)");
  private static final String COMPILER_OUTPUT_TYPE_ERROR = "ERROR";
  private static final String COMPILER_OUTPUT_TYPE_WARNING = "WARNING";

  public MonkeyBuilder() {
    super(Arrays.asList(MonkeyBuildTargetType.PRODUCTION, MonkeyBuildTargetType.TESTS));
    ResourcesBuilder.registerEnabler(module -> module.getModuleType() != JpsMonkeyModuleType.INSTANCE);
  }

  @Override
  public void build(@NotNull MonkeyBuildTarget target, @NotNull DirtyFilesHolder<MonkeySourceRootDescriptor, MonkeyBuildTarget> holder,
                    @NotNull BuildOutputConsumer outputConsumer, @NotNull CompileContext context) throws ProjectBuildException, IOException {
    LOG.debug(target.getPresentableName());
    if (!holder.hasDirtyFiles() && !holder.hasRemovedFiles()) return;

    JpsModule jpsModule = target.getModule();
    if (jpsModule.getModuleType() != JpsMonkeyModuleType.INSTANCE) return;

    JpsTypedModule<JpsSimpleElement<JpsMonkeyModuleProperties>> module = jpsModule.asTyped(JpsMonkeyModuleType.INSTANCE);
    assert module != null;

    final JpsElement propertiesUntyped = target.getModule().getProperties();
    if (!(propertiesUntyped instanceof JpsSimpleElement)) {
      throw new ProjectBuildException("module properties has wrong type");
    }

    @SuppressWarnings("unchecked")
    JpsSimpleElement<JpsMonkeyModuleProperties> properties = (JpsSimpleElement<JpsMonkeyModuleProperties>) propertiesUntyped;
    JpsMonkeyModuleProperties moduleProperties = properties.getData();
    final String targetDeviceId = moduleProperties.TARGET_DEVICE_ID;

    JpsSdk<JpsDummyElement> sdk = getSdk(context, module);

    File outputDirectory = getBuildOutputDirectory(jpsModule, target.isTests(), context);

    for (String contentRootUrl : jpsModule.getContentRootsList().getUrls()) {
      String contentRootPath = VfsUtilCore.urlToPath(contentRootUrl);
      final String projectName = context.getProjectDescriptor().getProject().getName();
      final GeneralCommandLine buildCmd = createBuildCmd(projectName, contentRootPath, outputDirectory, sdk.getHomePath(), targetDeviceId);
      runBuildProcess(context, buildCmd, contentRootPath);
    }
  }

  private static void runBuildProcess(@NotNull CompileContext context, @NotNull GeneralCommandLine commandLine, @NotNull String path)
      throws ProjectBuildException {
    try {
      LOG.debug(commandLine.getCommandLineString());
      final Process process = commandLine.createProcess();
      BaseOSProcessHandler handler = new BaseOSProcessHandler(process, commandLine.getCommandLineString(), Charset.defaultCharset());

      handler.addProcessListener(new ProcessAdapter() {
        @Override
        public void onTextAvailable(ProcessEvent event, Key outputType) {
          final String text = event.getText();
          if (!StringUtil.isEmptyOrSpaces(text)) {
            CompilerMessage compilerMessage = compilerMessageFromOutputLine(text.trim());
            context.processMessage(compilerMessage);
          }
        }
      });

      handler.startNotify();
      handler.waitFor();
    } catch (ExecutionException e) {
      throw new ProjectBuildException(e.getMessage());
    }
  }

  @NotNull
  private static CompilerMessage compilerMessageFromOutputLine(String lineText) {
    Matcher matcher = COMPILE_OUTPUT_LINE_WITH_FILE_AND_LINE_INFO.matcher(lineText);
    if (matcher.matches()) {
      String type = matcher.group("type").trim();
      String filePath = matcher.group("file").trim();
      int lineNumber = Integer.parseInt(matcher.group("lineNo"));
      String errorMessage = matcher.group("message").trim();
      boolean error = type.equals(COMPILER_OUTPUT_TYPE_ERROR);
      BuildMessage.Kind kind = error ? BuildMessage.Kind.ERROR : BuildMessage.Kind.WARNING;
      return new CompilerMessage("", kind, errorMessage, filePath, -1, -1, -1, lineNumber, -1);
    } else if (lineText.startsWith(COMPILER_OUTPUT_TYPE_WARNING + ":")) {
      String prefix = COMPILER_OUTPUT_TYPE_WARNING + ":";
      String msg = lineText.substring(prefix.length()).trim();
      return new CompilerMessage("", BuildMessage.Kind.WARNING, msg);
    } else if (lineText.startsWith(COMPILER_OUTPUT_TYPE_ERROR + ":")) {
      String prefix = COMPILER_OUTPUT_TYPE_ERROR + ":";
      String msg = lineText.substring(prefix.length()).trim();
      return new CompilerMessage("", BuildMessage.Kind.ERROR, msg);
    }

    return new CompilerMessage("", BuildMessage.Kind.INFO, lineText);
  }

  // TODO: paths that contain spaces should be quoted?
  public GeneralCommandLine createBuildCmd(String projectName, String projectRootPath, File outputDirectory, String sdkHomePath, String targetDeviceId) {
    final File projectRoot = new File(FileUtil.toSystemIndependentName(projectRootPath));

    // TODO: Use module sources functionality instead
    Pattern sourcePattern = Pattern.compile(".*\\.mc");
    final List<File> mcFiles = FileUtil.findFilesByMask(sourcePattern, projectRoot);
    final ImmutableList<String> sourceFilePaths = FluentIterable.from(mcFiles)
        .transform(File::getAbsolutePath).toList();

    // TODO: Use module resources functionality instead
    Pattern resourcePattern = Pattern.compile(".*\\.xml");
    final List<File> xmlFiles = FileUtil.findFilesByMask(resourcePattern, projectRoot);
    final ImmutableList<String> resourceFilePaths = FluentIterable.from(xmlFiles)
        .filter(file -> file != null && file.getParentFile().getAbsolutePath().contains("resource"))
        .transform(File::getAbsolutePath).toList();

    String sdkPath = sdkHomePath + File.separator;
    String sdkBinPath = sdkPath + "bin" + File.separator;

    String outputName = projectName + ".prg";

    String outputDir = outputDirectory.getAbsolutePath() + File.separator;

    ImmutableList.Builder<String> parameters = ImmutableList.<String>builder()
        .add("-a", sdkBinPath + "api.db")
        .add("-i", sdkBinPath + "api.debug.xml")
        .add("-o", outputDir + outputName)
        .add("-w"); // Show compilation warnings in the Console
//        .add("-g") // Print debug output (-g)

    // TODO: check what -e means
/*    if(outputPath.endsWith(".iq")) {
      cmdLine.addArgument("-e");
    }*/

    if (!resourceFilePaths.isEmpty()) {
      // in format: -z C:\xyz\resources\layouts\layout.xml;C:\xyz\resources\menus\menu.xml;C:\xyz\resources\resources.xml
      StringBuilder builder = new StringBuilder();
      for (String resourceFilePath : resourceFilePaths) {
        // if not first
        if (builder.length() != 0) {
          builder.append(File.pathSeparator);
        }
        builder.append(resourceFilePath);
      }
      parameters.add("-z", builder.toString());
    }

    String manifestXmlPath = projectRootPath + File.separator + "manifest.xml";
    String devicesXmlPath = sdkBinPath + "devices.xml";
    String projectInfoXmlPath = sdkBinPath + "projectInfo.xml"; // todo: is this file optional?
    parameters.add("-m", manifestXmlPath)
        .add("-u", devicesXmlPath)
        .add("-p", projectInfoXmlPath); // optional file?

    // in format: C:\xyz\source\aaApp.mc C:\xyz\source\aaMenuDelegate.mc C:\xyz\source\aaView.mc
    parameters.addAll(sourceFilePaths);

    final String deviceId = targetDeviceId != null ? targetDeviceId : "round_watch";
    final String deviceSim = deviceId + "_sim";
    // parameters.add("-r"); // if release build
    parameters.add("-d", deviceSim);

    //final String javaHome = SystemProperties.getJavaHome();
    //String javaPath = javaHome + File.separator + "bin" + File.separator + "java";
    final String jreHome = findJreHome() + File.separator;
    String javaPath = jreHome + "bin" + File.separator + "java";
    GeneralCommandLine commandLine = new GeneralCommandLine();
    commandLine.setExePath(javaPath);
    commandLine.addParameters("-Dfile.encoding=UTF-8", "-Dapple.awt.UIElement=true");

    String monkeybrainsJarPath = sdkBinPath + MONKEYBRAINS_JAR_FILENAME;
    commandLine.addParameters("-jar", monkeybrainsJarPath);

    commandLine.addParameters(parameters.build());

    return commandLine;
  }

  // searches some common directories and misconfigurations
  public static String findJreHome() {
    String javaHome = SystemProperties.getJavaHome();
    Optional<String> jreHome = Stream.of(javaHome, new File(javaHome).getParent(), System.getenv("JDK_16_x64"), System.getenv("JDK_16"))
        .filter(Objects::nonNull)
        .filter(JdkUtil::checkForJre)
        .findFirst();

    if (jreHome.isPresent()) {
      return jreHome.get();
    }

    throw new RuntimeException("could not find JRE. java.home=" + javaHome);
  }

  @NotNull
  private static JpsSdk<JpsDummyElement> getSdk(@NotNull CompileContext context,
                                                @NotNull JpsModule module) throws ProjectBuildException {
    JpsSdk<JpsDummyElement> sdk = module.getSdk(JpsMonkeySdkType.INSTANCE);
    if (sdk == null) {
      String errorMessage = "No SDK for module " + module.getName();
      context.processMessage(new CompilerMessage(NAME, BuildMessage.Kind.ERROR, errorMessage));
      throw new ProjectBuildException(errorMessage);
    }
    return sdk;
  }


  @NotNull
  private static File getBuildOutputDirectory(@NotNull JpsModule module,
                                              boolean forTests,
                                              @NotNull CompileContext context) throws ProjectBuildException {
    JpsJavaExtensionService instance = JpsJavaExtensionService.getInstance();
    File outputDirectory = instance.getOutputDirectory(module, forTests);
    if (outputDirectory == null) {
      String errorMessage = "No output dir for module " + module.getName();
      context.processMessage(new CompilerMessage(NAME, BuildMessage.Kind.ERROR, errorMessage));
      throw new ProjectBuildException(errorMessage);
    }
    if (!outputDirectory.exists()) {
      FileUtil.createDirectory(outputDirectory);
    }
    return outputDirectory;
  }

  @NotNull
  @Override
  public String getPresentableName() {
    return NAME;
  }
}

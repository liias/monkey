package io.github.liias.monkey.project.module;

import com.intellij.compiler.CompilerWorkspaceConfiguration;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.projectRoots.impl.SdkConfigurationUtil;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.ReadonlyStatusHandler;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.DocumentUtil;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import io.github.liias.monkey.project.configuration.TargetDeviceModuleExtension;
import io.github.liias.monkey.project.dom.manifest.Manifest;
import io.github.liias.monkey.project.dom.manifest.Products;
import io.github.liias.monkey.project.dom.sdk.projectinfo.NewProjectFileMap;
import io.github.liias.monkey.project.dom.sdk.projectinfo.ProjectInfo;
import io.github.liias.monkey.project.module.util.ExternalTemplateUtil;
import io.github.liias.monkey.project.module.util.MonkeyModuleUtil;
import io.github.liias.monkey.project.runconfig.MonkeyConfigurationType;
import io.github.liias.monkey.project.runconfig.MonkeyModuleBasedConfiguration;
import io.github.liias.monkey.project.runconfig.TargetDevice;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import io.github.liias.monkey.project.ui.module.MonkeyModuleWizardStep;
import io.github.liias.monkey.project.ui.module.newProject.MonkeyApplicationModifiedSettingsStep;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootType;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.liias.monkey.project.module.util.MonkeyModuleUtil.MANIFEST_XML;

public class MonkeyModuleBuilder extends ModuleBuilder implements ModuleBuilderListener {
  private static final Logger LOG = Logger.getInstance("#io.github.liias.monkey.project.module.MonkeyModuleBuilder");
  public static final String PROJECT_INFO_XML = "projectInfo.xml";
  public static final String FILE_TYPE_SOURCE = "source";
  public static final TargetDevice DEFAULT_TARGET_DEVICE = TargetDevice.SQUARE_WATCH;

  // Not null only if new project where we need to generate content based on this type
  @Nullable
  private final String appType;

  public MonkeyModuleBuilder(String appType) {
    this.appType = appType;
  }

  @Override
  public void setupRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
    addListener(this);

    final Module module = rootModel.getModule();
    final MonkeySdkType sdkType = MonkeySdkType.getInstance();

    Sdk sdk = findAndSetSdk(module, sdkType);

    CompilerModuleExtension compilerModuleExtension = rootModel.getModuleExtension(CompilerModuleExtension.class);
    compilerModuleExtension.setExcludeOutput(true);
    compilerModuleExtension.inheritCompilerOutputPath(true);

    if (myJdk != null) {
      rootModel.setSdk(myJdk);
    } else {
      rootModel.inheritSdk();
    }

    ContentEntry contentEntry = doAddContentEntry(rootModel);
    if (contentEntry != null) {
      VirtualFile sourceRoot = createSourcePath("source");
      contentEntry.addSourceFolder(sourceRoot, JavaSourceRootType.SOURCE);

      // TODO: there can be many resource folders, e.g based on language or device that come from SDK's example project definitions
      VirtualFile resourcesRoot = createSourcePath("resources");
      contentEntry.addSourceFolder(resourcesRoot, JavaResourceRootType.RESOURCE);
    }

    final TargetDeviceModuleExtension targetDeviceModuleExtension = rootModel.getModuleExtension(TargetDeviceModuleExtension.class);
    targetDeviceModuleExtension.setTargetDevice(DEFAULT_TARGET_DEVICE);

    final Project project = rootModel.getProject();

    VirtualFile[] files = rootModel.getContentRoots();

    if (files.length > 0) {
      final VirtualFile contentRoot = files[0];
      StartupManager.getInstance(project).runWhenProjectIsInitialized(() ->
          ApplicationManager.getApplication().invokeLater(() ->
              DocumentUtil.writeInRunUndoTransparentAction(() ->
                  createProject(project, contentRoot, module)
              )
          )
      );
    }
  }

  @Override
  public int getWeight() {
    return 100;
  }

  private VirtualFile createSourcePath(String dirname) {
    String path = getContentEntryPath() + File.separator + dirname;
    new File(path).mkdirs();
    return LocalFileSystem.getInstance().refreshAndFindFileByPath(FileUtil.toSystemIndependentName(path));
  }

  @Override
  protected boolean isAvailable() {
    return false;
  }

  private void createProject(Project project, VirtualFile contentRoot, Module module) {
    final MonkeySdkType sdkType = MonkeySdkType.getInstance();
    Sdk sdk = findAndSetSdk(module, sdkType);
    if (sdk != null) {
      VirtualFile sdkBinDir = sdkType.getBinDir(sdk);
      // add new files only if manifest file does not exist already
      if (contentRoot.findChild(MANIFEST_XML) == null) {
        createResourcesAndLibs(module, contentRoot, sdkBinDir);
        fillTemplates(module, contentRoot);
      }
      setupRunConfiguration(module);
    }
  }

  private Sdk findAndSetSdk(Module module, MonkeySdkType sdkType) {
    Sdk sdk = ModuleRootManager.getInstance(module).getSdk();
    if (sdk == null) {
      sdk = ProjectRootManager.getInstance(module.getProject()).getProjectSdk();
    }
    if (sdk == null) {
      Comparator<Sdk> preferredSdkComparator = (o1, o2) -> {
        if (o1.getSdkType() instanceof MonkeySdkType) {
          return 1;
        } else if (o2.getSdkType() instanceof MonkeySdkType) {
          return -1;
        }
        return 0;
      };
      SdkConfigurationUtil.configureDirectoryProjectSdk(module.getProject(), preferredSdkComparator, sdkType);
      sdk = ProjectRootManager.getInstance(module.getProject()).getProjectSdk();
    }
    return sdk;
  }

  private void createResourcesAndLibs(final Module module, final VirtualFile rootDir, VirtualFile sdkBinDir) {
    final Project project = module.getProject();
    ProjectInfo sdkProjectInfo = getSdkProjectInfo(project, sdkBinDir);
    List<NewProjectFileMap> newProjectFileMaps = sdkProjectInfo.getNewProjectFilesMaps().getNewProjectFileMaps();

    // TODO: allow user to choose apptype's app (i.e Simple or Complex), instead of selecting random first
    final NewProjectFileMap newProjectFileMap = newProjectFileMaps.stream()
        .filter(fileMap -> fileMap != null && appType.equals(fileMap.getAppType().getStringValue())
        )
        .findFirst().get();
    final GenericAttributeValue<String> baseDir = newProjectFileMap.getBaseDir(); // e.g templates/watch-app/simple

    String appName = module.getName(); // used in resources.xml to set freeform app name
    String sanitizedName = WordUtils.capitalize(appName);
    Map<String, String> substitutions = new HashMap<>();
    substitutions.put("appName", appName);
    substitutions.put("appClassName", sanitizedName + "App");
    substitutions.put("viewClassName", sanitizedName + "View");
    substitutions.put("delegateClassName", sanitizedName + "Delegate");
    substitutions.put("menuDelegateClassName", sanitizedName + "MenuDelegate");
    VelocityContext context = new VelocityContext();
    for (Map.Entry<String, String> substitution : substitutions.entrySet()) {
      context.put(substitution.getKey(), substitution.getValue());
    }

    try {
      for (io.github.liias.monkey.project.dom.sdk.projectinfo.File file : newProjectFileMap.getFiles()) {
        final String relativeFilePath = file.getValue(); // e.g resources/images/launcher_icon.png
        String toRelativeFilePath = relativeFilePath;
        // source files are prefixed with app name
        if (FILE_TYPE_SOURCE.equals(file.getType().getStringValue())) {
          final Path relativeFilePathPath = Paths.get(relativeFilePath);
          final Path fileName = relativeFilePathPath.getFileName(); // e.g App.mc
          final Path parent = relativeFilePathPath.getParent(); // e.g source
          toRelativeFilePath = parent + "/" + sanitizedName + fileName;
        }
        final VirtualFile templateFile = sdkBinDir.findFileByRelativePath(baseDir + "/" + relativeFilePath);
        // this handles creating child directories as well
        VirtualFile newFile = VfsUtil.copyFileRelative(project, templateFile, rootDir, toRelativeFilePath);

        if (!newFile.getFileType().isBinary()) {
          String content = getParsedFileContent(context, newFile);
          VfsUtil.saveText(newFile, content);
        }
      }
    } catch (IOException e) {
      LOG.error(e);
    }
  }

  private String getParsedFileContent(Context context, VirtualFile file) throws FileNotFoundException {
    FileReader fileReader = new FileReader(VfsUtil.virtualToIoFile(file));
    final VelocityEngine velocityEngine = ExternalTemplateUtil.getEngine();
    StringWriter writer = new StringWriter();
    velocityEngine.evaluate(context, writer, "Monkey", fileReader);
    return writer.toString();
  }

  private void fillTemplates(final Module module, final VirtualFile contentRoot) {
    final Project project = module.getProject();
    CommandProcessor.getInstance().executeCommand(project, () -> {
      Runnable action = () -> {
        final Manifest manifest = MonkeyModuleUtil.getManifest(project, contentRoot);
        if (manifest != null) {
          StartupManager.getInstance(project).runWhenProjectIsInitialized(() -> FileDocumentManager.getInstance().saveAllDocuments());
          configureManifest(manifest, module, appType);
        }
      };

      ApplicationManager.getApplication().runWriteAction(action);
    }, "Create project", null);

  }

  private static void configureManifest(Manifest manifest, Module module, String appType) {
    if (appType == null) {
      throw new RuntimeException("app type is null");
    }
    final PsiFile manifestFile = getValidatedPsiFile(manifest);
    if (manifestFile == null) {
      return;
    }

    String applicationId = MonkeyModuleUtil.generateProjectId();
    String entryClassName = WordUtils.capitalize(module.getName()) + "App";

    manifest.getApplication().getId().setValue(applicationId);
    manifest.getApplication().getType().setValue(appType);
    manifest.getApplication().getName().setValue("@Strings.AppName");
    // entry is a class which extends Toybox.Application.AppBase
    manifest.getApplication().getEntry().setValue(entryClassName);
    manifest.getApplication().getLauncherIcon().setValue("@Drawables.LauncherIcon");

    Products products = manifest.getApplication().getProducts();
    XmlTag productsRootTag = products.getXmlTag();
    XmlTag productTag = productsRootTag.createChildTag("product", productsRootTag.getNamespace(), "", false);
    productTag = productsRootTag.addSubTag(productTag, true);
    productTag.setAttribute("id", null, DEFAULT_TARGET_DEVICE.getId());
    productTag.collapseIfEmpty();

    CodeStyleManager.getInstance(manifestFile.getProject()).reformat(manifestFile);
  }

  private static PsiFile getValidatedPsiFile(DomElement domElement) {
    final XmlTag rootTag = domElement.getXmlTag();
    if (rootTag == null) {
      return null;
    }

    final PsiFile psiFile = rootTag.getContainingFile();
    if (psiFile == null) {
      return null;
    }

    final VirtualFile virtualFile = psiFile.getVirtualFile();
    if (virtualFile == null ||
        !ReadonlyStatusHandler.ensureFilesWritable(psiFile.getProject(), virtualFile)) {
      return null;
    }

    return psiFile;
  }

  private static ProjectInfo getSdkProjectInfo(Project project, VirtualFile sdkBinDir) {
    final VirtualFile projectInfoFile = sdkBinDir.findChild(PROJECT_INFO_XML);
    return projectInfoFile != null ? MonkeyModuleUtil.loadDomElement(project, projectInfoFile, ProjectInfo.class) : null;
  }

  private void setupRunConfiguration(Module module) {
    final Project project = module.getProject();
    final RunManager runManager = RunManager.getInstance(project);
    final ConfigurationFactory configurationFactory = MonkeyConfigurationType.getInstance().getFactory();
    final RunnerAndConfigurationSettings settings = runManager.createRunConfiguration(module.getName(), configurationFactory);
    final MonkeyModuleBasedConfiguration configuration = (MonkeyModuleBasedConfiguration) settings.getConfiguration();
    configuration.setModule(module);
    configuration.setTargetDeviceId(DEFAULT_TARGET_DEVICE.getId());
    runManager.addConfiguration(settings, false);
    runManager.setSelectedConfiguration(settings);
  }

  @NotNull
  @Override
  public ModuleType getModuleType() {
    return MonkeyModuleType.getInstance();
  }

  @Override
  public boolean isSuitableSdkType(SdkTypeId sdkType) {
    return sdkType == MonkeySdkType.getInstance();
  }

  @Override
  public void moduleCreated(@NotNull Module module) {
    CompilerWorkspaceConfiguration.getInstance(module.getProject()).CLEAR_OUTPUT_DIRECTORY = false;
  }

  @Override
  public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext context, @NotNull ModulesProvider modulesProvider) {
    return new ModuleWizardStep[]{new MonkeyModuleWizardStep(this, context)};
  }

  @Nullable
  @Override
  public ModuleWizardStep modifySettingsStep(@NotNull SettingsStep settingsStep) {
    if (appType == null) {
      return null;
    }
    return new MonkeyApplicationModifiedSettingsStep(this, settingsStep);
  }
}

package ee.edio.garmin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.intellij.compiler.CompilerWorkspaceConfiguration;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.DumbAwareRunnable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.projectRoots.impl.SdkConfigurationUtil;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.ReadonlyStatusHandler;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.ExternalChangeAction;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.DocumentUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import ee.edio.garmin.dom.manifest.Manifest;
import ee.edio.garmin.dom.manifest.Products;
import ee.edio.garmin.dom.sdk.projectinfo.NewProjectFileMap;
import ee.edio.garmin.dom.sdk.projectinfo.ProjectInfo;
import ee.edio.garmin.module.MonkeyModuleWizardStep;
import ee.edio.garmin.sdk.MonkeySdkType;
import ee.edio.garmin.util.ExternalTemplateUtil;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ee.edio.garmin.MonkeyUtil.loadDomElement;

public class MonkeyModuleBuilder extends JavaModuleBuilder implements SourcePathsBuilder, ModuleBuilderListener {
  private static final Logger LOG = Logger.getInstance("#ee.edio.garmin.MonkeyModuleBuilder");
  public static final String MANIFEST_XML = "manifest.xml";
  public static final String PROJECT_INFO_XML = "projectInfo.xml";
  public static final String FILE_TYPE_SOURCE = "source";
  private final String appType;

  public MonkeyModuleBuilder(String appType) {
    this.appType = appType;
  }

  @Override
  public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
    addListener(this);
    super.setupRootModel(modifiableRootModel);

    final Project project = modifiableRootModel.getProject();
    VirtualFile[] files = modifiableRootModel.getContentRoots();

    final Module module = modifiableRootModel.getModule();
    if (files.length > 0) {
      final VirtualFile contentRoot = files[0];
      StartupManager.getInstance(project).runWhenProjectIsInitialized(new DumbAwareRunnable() {
        @Override
        public void run() {
          ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
              DocumentUtil.writeInRunUndoTransparentAction(new Runnable() {
                @Override
                public void run() {
                  createProject(project, contentRoot, module);
                }
              });
            }
          });
        }
      });
    }
  }

  @Override
  protected boolean isAvailable() {
    return false;
  }

  private void createProject(Project project, VirtualFile contentRoot, Module module) {
    VirtualFile sourceRoot = null;
    try {
      sourceRoot = contentRoot.createChildDirectory(this, "source");
    } catch (IOException e) {
      LOG.error(e);
    }

    final MonkeySdkType sdkType = MonkeySdkType.getInstance();
    Sdk sdk = findSdk(module, sdkType);
    VirtualFile sdkBinDir = sdkType.getBinDir(sdk);
    createResourcesAndLibs(module, contentRoot, sdkBinDir);
    //PsiDirectory sourceDir = sourceRoot != null ? PsiManager.getInstance(project).findDirectory(sourceRoot) : null;
    fillTemplates(module, contentRoot);
  }

  private Sdk findSdk(Module module, MonkeySdkType sdkType) {
    Sdk sdk = ModuleRootManager.getInstance(module).getSdk();
    if (sdk == null) {
      sdk = ProjectRootManager.getInstance(module.getProject()).getProjectSdk();
    }
    if (sdk == null) {
      Comparator<Sdk> preferredSdkComparator = new Comparator<Sdk>() {
        @Override
        public int compare(Sdk o1, Sdk o2) {
          if (o1.getSdkType() instanceof MonkeySdkType) {
            return 1;
          } else if (o2.getSdkType() instanceof MonkeySdkType) {
            return -1;
          }
          return 0;
        }
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
    final NewProjectFileMap newProjectFileMap = Iterables.find(newProjectFileMaps, new Predicate<NewProjectFileMap>() {
      @Override
      public boolean apply(@Nullable NewProjectFileMap newProjectFileMap) {
        return newProjectFileMap != null && appType.equals(newProjectFileMap.getAppType().getStringValue());
      }
    });
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
      for (ee.edio.garmin.dom.sdk.projectinfo.File file : newProjectFileMap.getFiles()) {
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
    CommandProcessor.getInstance().executeCommand(project, new ExternalChangeAction() {
      @Override
      public void run() {
        Runnable action = new Runnable() {
          @Override
          public void run() {
            final Manifest manifest = getManifest(project, contentRoot);
            if (manifest != null) {
              StartupManager.getInstance(project).runWhenProjectIsInitialized(new Runnable() {
                @Override
                public void run() {
                  FileDocumentManager.getInstance().saveAllDocuments();
                }
              });
              configureManifest(manifest, module, appType);
            }
          }
        };

        ApplicationManager.getApplication().runWriteAction(action);
      }

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

    String applicationId = MonkeyUtil.generateProjectId();
    String entryClassName = WordUtils.capitalize(module.getName()) + "App";

    manifest.getApplication().getId().setValue(applicationId);
    manifest.getApplication().getType().setValue(appType);
    manifest.getApplication().getName().setValue("AppName");
    // entry is a class which extends Toybox.Application.AppBase
    manifest.getApplication().getEntry().setValue(entryClassName);
    manifest.getApplication().getLauncherIcon().setValue("LauncherIcon");

    Products products = manifest.getApplication().getProducts();
    XmlTag productsRootTag = products.getXmlTag();
    XmlTag productTag = productsRootTag.createChildTag("product", productsRootTag.getNamespace(), "", false);
    productTag = productsRootTag.addSubTag(productTag, true);
    productTag.setAttribute("id", null, "square_watch");
    productTag.collapseIfEmpty();
    //productTag.setAttribute("id", null, "round_watch");

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
    return projectInfoFile != null ? loadDomElement(project, projectInfoFile, ProjectInfo.class) : null;
  }

  private static Manifest getManifest(Project project, VirtualFile contentRoot) {
    VirtualFile manifestFile = contentRoot.findChild(MANIFEST_XML);
    return manifestFile != null ? loadDomElement(project, manifestFile, Manifest.class) : null;
  }

  @Override
  public List<Pair<String, String>> getSourcePaths() {
    return ContainerUtil.emptyList();
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
}
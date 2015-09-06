package ee.edio.garmin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.intellij.compiler.CompilerWorkspaceConfiguration;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.Disposable;
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
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.ReadonlyStatusHandler;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.ExternalChangeAction;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.DocumentUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import ee.edio.garmin.dom.manifest.Manifest;
import ee.edio.garmin.dom.sdk.projectinfo.NewProjectFileMap;
import ee.edio.garmin.dom.sdk.projectinfo.ProjectInfo;
import ee.edio.garmin.module.MonkeyModuleWizardStep;
import ee.edio.garmin.sdk.MonkeySdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ee.edio.garmin.MonkeyUtil.loadDomElement;

public class MonkeyModuleBuilder extends JavaModuleBuilder implements SourcePathsBuilder, ModuleBuilderListener {
  private static final Logger LOG = Logger.getInstance("#ee.edio.garmin.MonkeyModuleBuilder");
  public static final String MANIFEST_XML = "manifest.xml";
  public static final String PROJECT_INFO_XML = "projectInfo.xml";
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

    final Sdk sdk = ModuleRootManager.getInstance(module).getSdk();
    final MonkeySdkType sdkType = MonkeySdkType.getInstance();
    final VirtualFile sdkBinDir = sdkType.getBinDir(sdk);
    createResourcesAndLibs(project, contentRoot, sdkBinDir);

    PsiDirectory sourceDir = sourceRoot != null ? PsiManager.getInstance(project).findDirectory(sourceRoot) : null;
    fillTemplates(project, module, contentRoot, sourceDir);
  }

  private void createResourcesAndLibs(final Project project, final VirtualFile rootDir, VirtualFile sdkBinDir) {
    ProjectInfo sdkProjectInfo = getSdkProjectInfo(project, sdkBinDir);
    List<NewProjectFileMap> newProjectFileMaps = sdkProjectInfo.getNewProjectFilesMaps().getNewProjectFileMaps();
    final NewProjectFileMap newProjectFileMap = Iterables.find(newProjectFileMaps, new Predicate<NewProjectFileMap>() {
      @Override
      public boolean apply(@Nullable NewProjectFileMap newProjectFileMap) {
        return newProjectFileMap != null && appType.equals(newProjectFileMap.getAppType().getStringValue());
      }
    });
    final GenericAttributeValue<String> baseDir = newProjectFileMap.getBaseDir(); // e.g templates/watch-app/simple

    try {
      for (ee.edio.garmin.dom.sdk.projectinfo.File file : newProjectFileMap.getFiles()) {
        final String relativeFilePath = file.getValue(); // e.g resources/images/launcher_icon.png
        final VirtualFile templateFile = sdkBinDir.findFileByRelativePath(baseDir + File.separator + relativeFilePath);
        //final File projectFilePath = new File(rootDir.getPath() + File.separator + relativeFilePath);
        //FileUtil.createParentDirs(projectFilePath);
        //final VirtualFile childDir = rootDir.findFileByRelativePath(projectFilePath.getParent());
        //final String fileName = projectFilePath.getName();

        // this handles creating child directories as well
        VfsUtil.copyFileRelative(project, templateFile, rootDir, relativeFilePath);
      }
    } catch (IOException e) {
      LOG.error(e);
    }

/*    for (String language : languages) {
      IFolder folder = project.getFolder("resources-" + language);
      if (!folder.exists()) {
        folder.create(true, true, null);
      }
    }*/

    //String sanitizedName = IQProjectSupport.sanitizeProjectName(name);
    String name = "okei";
    String sanitizedName = "okei";
    Map<String, String> substitutions = new HashMap<>();
    substitutions.put("appName", name);
    substitutions.put("appClassName", sanitizedName + "App");
    substitutions.put("viewClassName", sanitizedName + "View");
    substitutions.put("delegateClassName", sanitizedName + "Delegate");
    substitutions.put("menuDelegateClassName", sanitizedName + "MenuDelegate");
  }

  private void fillTemplates(final Project project, final Module module, final VirtualFile contentRoot, PsiDirectory sourceDir) {
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

  private static void configureManifest(Manifest manifest, Module module, String appType) {
    if (appType == null) {
      throw new RuntimeException("app type is null");
    }
    final PsiFile manifestFile = getValidatedPsiFile(manifest);
    if (manifestFile == null) {
      return;
    }

    final String applicationId = MonkeyUtil.generateProjectId();
    final String entryClassName = module.getName() + "App";

    manifest.getApplication().getId().setValue(applicationId);
    manifest.getApplication().getType().setValue(appType);
    manifest.getApplication().getName().setValue("AppName");
    // entry is a class which extends Toybox.Application.AppBase
    manifest.getApplication().getEntry().setValue(entryClassName);
    manifest.getApplication().getLauncherIcon().setValue("LauncherIcon");
    CodeStyleManager.getInstance(manifestFile.getProject()).reformat(manifestFile);
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

  @Nullable
  @Override
  public ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
    return null;
    //return new MonkeyModuleWizardStep(this, context, parentDisposable);
  }

  @Override
  public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext context, @NotNull ModulesProvider modulesProvider) {
    return new ModuleWizardStep[]{new MonkeyModuleWizardStep(this, context)};
  }


}

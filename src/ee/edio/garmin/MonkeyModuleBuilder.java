package ee.edio.garmin;

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
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.ReadonlyStatusHandler;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.ExternalChangeAction;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.DocumentUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomManager;
import ee.edio.garmin.dom.manifest.Manifest;
import ee.edio.garmin.ide.fileTemplates.MonkeyFileTemplateProvider;
import ee.edio.garmin.module.MonkeyModuleWizardStep;
import ee.edio.garmin.sdk.MonkeySdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static ee.edio.garmin.MonkeyUtil.createChildDirectoryIfNotExist;

public class MonkeyModuleBuilder extends JavaModuleBuilder implements SourcePathsBuilder, ModuleBuilderListener {
  private static final Logger LOG = Logger.getInstance("#ee.edio.garmin.MonkeyModuleBuilder");
  public static final String MANIFEST_XML = "manifest.xml";
  private final String type;

  public MonkeyModuleBuilder(String type) {
    this.type = type;
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
  public boolean isTemplate() {
    return true;
  }

  private void createProject(Project project, VirtualFile contentRoot, Module module) {
    VirtualFile sourceRoot = null;
    try {
      sourceRoot = contentRoot.createChildDirectory(this, "source");
    } catch (IOException e) {
      LOG.error(e);
    }
    createManifestFile(project, contentRoot);
    createResourcesAndLibs(project, contentRoot);

    PsiDirectory sourceDir = sourceRoot != null ? PsiManager.getInstance(project).findDirectory(sourceRoot) : null;
    createFilesAndSetupManifest(project, module, contentRoot, sourceDir);
  }

  private void createResourcesAndLibs(final Project project, final VirtualFile rootDir) {
    try {
      VirtualFile resourcesDir = createChildDirectoryIfNotExist(project, rootDir, "resources");
      createFileFromResource(project, resourcesDir, "resources.xml", "/icons/sdk.png");

      VirtualFile imagesDir = createChildDirectoryIfNotExist(project, resourcesDir, "images");
      createFileFromResource(project, imagesDir, "launcher_icon.png", "/icons/sdk.png");
      createFileFromResource(project, imagesDir, "monkey.png", "/icons/module.png");

      VirtualFile layoutsDir = createChildDirectoryIfNotExist(project, resourcesDir, "layouts");
      createFileFromResource(project, layoutsDir, "layout.xml", "/icons/module.png");

      VirtualFile menusDir = createChildDirectoryIfNotExist(project, resourcesDir, "menus");
      createFileFromResource(project, menusDir, "menu.xml", "/icons/module.png");

      VirtualFile sourceDir = createChildDirectoryIfNotExist(project, rootDir, "source");
      createFileFromResource(project, sourceDir, "app.mc", "/icons/module.png");
      createFileFromResource(project, sourceDir, "MenuDelegate.mc", "/icons/module.png");
      createFileFromResource(project, sourceDir, "View.mc", "/icons/module.png");

    } catch (IOException e) {
      LOG.error(e);
    }
  }

  private static void createFileFromResource(Project project, VirtualFile drawableDir, String name, String resourceFilePath)
      throws IOException {
    if (drawableDir.findChild(name) != null) {
      return;
    }
    VirtualFile resFile = drawableDir.createChildData(project, name);
    try (InputStream stream = MonkeyModuleBuilder.class.getResourceAsStream(resourceFilePath)) {
      byte[] bytes = FileUtil.adaptiveLoadBytes(stream);
      resFile.setBinaryContent(bytes);
    }
  }

  private static void createManifestFile(final Project project, VirtualFile contentRoot) {
    try {
      MonkeyFileTemplateProvider.createFromTemplate(project, contentRoot, MonkeyFileTemplateProvider.APP_MANIFEST_TEMPLATE, MANIFEST_XML);
    } catch (Exception e) {
      LOG.error(e);
    }
  }

  private void createFilesAndSetupManifest(final Project project, final Module module, final VirtualFile contentRoot, PsiDirectory sourceDir) {
    CommandProcessor.getInstance().executeCommand(project, new ExternalChangeAction() {
      @Override
      public void run() {
        Runnable action = new Runnable() {
          @Override
          public void run() {
            final Manifest manifest = getManifest(project, module, contentRoot);
            if (manifest != null) {
              StartupManager.getInstance(project).runWhenProjectIsInitialized(new Runnable() {
                @Override
                public void run() {
                  FileDocumentManager.getInstance().saveAllDocuments();
                }
              });

              configureManifest(manifest, module);
            }
          }
        };

        ApplicationManager.getApplication().runWriteAction(action);
      }

    }, "Create project", null);

  }


  private static void configureManifest(Manifest manifest, Module module) {
    final XmlTag manifestTag = manifest.getXmlTag();
    if (manifestTag == null) {
      return;
    }

    final PsiFile manifestFile = manifestTag.getContainingFile();
    if (manifestFile == null) {
      return;
    }

    final VirtualFile vManifestFile = manifestFile.getVirtualFile();
    if (vManifestFile == null ||
        !ReadonlyStatusHandler.ensureFilesWritable(manifestFile.getProject(), vManifestFile)) {
      return;
    }

    final String applicationId = MonkeyUtil.generateProjectId();
    manifest.getApplication().getId().setValue(applicationId);
    // main class is class which extends Toybox.Application.AppBase
    final String mainClassName = module.getName() + "App";
    manifest.getApplication().getEntry().setValue(mainClassName);

    // app type can be: watchface, datafield, widget, watch-app
    final String appType = "watch-app";
    manifest.getApplication().getType().setValue(appType);

/*
      usesSdkTag = manifestTag.addSubTag(usesSdkTag, true);
      usesSdkTag.setAttribute("minSdkVersion", SdkConstants.NS_RESOURCES, target.getVersion().getApiString());
    */
    CodeStyleManager.getInstance(manifestFile.getProject()).reformat(manifestFile);
  }

  private static Manifest getManifest(Project project, Module module, VirtualFile contentRoot) {
    VirtualFile manifestFile = getManifestFile(module, contentRoot);


    return manifestFile != null ? loadDomElement(project, manifestFile, Manifest.class) : null;
  }

  private static VirtualFile getManifestFile(Module module, VirtualFile contentRoot) {
    return contentRoot.findChild(MANIFEST_XML);
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

  @Nullable
  public static <T extends DomElement> T loadDomElement(@NotNull final Project project,
                                                        @NotNull final VirtualFile file,
                                                        @NotNull final Class<T> aClass) {
    return ApplicationManager.getApplication().runReadAction(new Computable<T>() {
      @Override
      @Nullable
      public T compute() {
        if (project.isDisposed()) return null;
        PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
        if (psiFile instanceof XmlFile) {
          return loadDomElementWithReadPermission(project, (XmlFile) psiFile, aClass);
        } else {
          return null;
        }
      }
    });
  }

  /**
   * This method should be called under a read action.
   */
  @Nullable
  public static <T extends DomElement> T loadDomElementWithReadPermission(@NotNull Project project,
                                                                          @NotNull XmlFile xmlFile,
                                                                          @NotNull Class<T> aClass) {
    ApplicationManager.getApplication().assertReadAccessAllowed();
    DomManager domManager = DomManager.getDomManager(project);
    DomFileElement<T> element = domManager.getFileElement(xmlFile, aClass);
    if (element == null) return null;
    return element.getRootElement();
  }
}

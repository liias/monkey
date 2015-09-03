package ee.edio.garmin;

import com.intellij.compiler.CompilerWorkspaceConfiguration;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.DumbAwareRunnable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.DocumentUtil;
import com.intellij.util.containers.ContainerUtil;
import ee.edio.garmin.ide.fileTemplates.MonkeyFileTemplateProvider;
import ee.edio.garmin.module.MonkeyModuleWizardStep;
import ee.edio.garmin.sdk.MonkeySdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MonkeyModuleBuilder extends JavaModuleBuilder implements SourcePathsBuilder, ModuleBuilderListener {
  private static final Logger LOG = Logger.getInstance("#ee.edio.garmin.MonkeyModuleBuilder");

  @Override
  public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
    addListener(this);
    super.setupRootModel(modifiableRootModel);

    final Project project = modifiableRootModel.getProject();
    VirtualFile[] files = modifiableRootModel.getContentRoots();
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
                  createManifestFile(project, contentRoot);
                }
              });
            }
          });
        }
      });
    }
  }

  public void createManifestFile(Project project, VirtualFile contentRoot) {
    try {
      MonkeyFileTemplateProvider.createFromTemplate(project, contentRoot, MonkeyFileTemplateProvider.APP_MANIFEST_TEMPLATE, "manifest.xml");
    } catch (Exception e) {
      LOG.error(e);
    }
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
    return new MonkeyModuleWizardStep(this, context, parentDisposable);
  }
}

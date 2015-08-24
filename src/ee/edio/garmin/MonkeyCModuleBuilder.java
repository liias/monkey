package ee.edio.garmin;

import com.intellij.compiler.CompilerWorkspaceConfiguration;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.Pair;
import com.intellij.remoteServer.impl.module.CloudModuleWizardStep;
import com.intellij.util.containers.ContainerUtil;
import ee.edio.garmin.module.MCModuleWizardStep;
import ee.edio.garmin.sdk.MCSdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MonkeyCModuleBuilder extends JavaModuleBuilder implements SourcePathsBuilder, ModuleBuilderListener {
  @Override
  public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
    addListener(this);
    super.setupRootModel(modifiableRootModel);
  }

  @Override
  public List<Pair<String, String>> getSourcePaths() {
    return ContainerUtil.emptyList();
  }

  @NotNull
  @Override
  public ModuleType getModuleType() {
    return MonkeyCModuleType.getInstance();
  }

  @Override
  public boolean isSuitableSdkType(SdkTypeId sdkType) {
    return sdkType == MCSdkType.getInstance();
  }

  @Override
  public void moduleCreated(@NotNull Module module) {
    CompilerWorkspaceConfiguration.getInstance(module.getProject()).CLEAR_OUTPUT_DIRECTORY = false;
  }

  @Nullable
  @Override
  public ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
    return new MCModuleWizardStep(this, context, parentDisposable);
  }
}

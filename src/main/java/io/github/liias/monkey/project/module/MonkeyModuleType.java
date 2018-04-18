package io.github.liias.monkey.project.module;

import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import icons.MonkeyIcons;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MonkeyModuleType extends ModuleType<MonkeyModuleBuilder> {
  public MonkeyModuleType() {
    super(MonkeyConstants.MODULE_TYPE_ID);
  }

  @NotNull
  public static MonkeyModuleType getInstance() {
    return (MonkeyModuleType) ModuleTypeManager.getInstance().findByID(MonkeyConstants.MODULE_TYPE_ID);
  }

  @NotNull
  @Override
  public MonkeyModuleBuilder createModuleBuilder() {
    return new MonkeyModuleBuilder(null);
  }

  @NotNull
  @Override
  public String getName() {
    return "Monkey C Module";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Monkey C modules are used for developing <b>Monkey C</b> applications.";
  }

  @Override
  public Icon getNodeIcon(@Deprecated boolean isOpened) {
    return MonkeyIcons.MODULE16;
  } // 16

  @NotNull
  @Override
  public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext,
                                              @NotNull MonkeyModuleBuilder moduleBuilder,
                                              @NotNull ModulesProvider modulesProvider) {

    final ProjectJdkForModuleStep projectJdkForModuleStep = new ProjectJdkForModuleStep(wizardContext, MonkeySdkType.getInstance());
    return new ModuleWizardStep[]{projectJdkForModuleStep};

  }

  @Nullable
  @Override
  public ModuleWizardStep modifyProjectTypeStep(@NotNull SettingsStep settingsStep, @NotNull ModuleBuilder moduleBuilder) {
    return super.modifyProjectTypeStep(settingsStep, moduleBuilder);
  }

  @Nullable
  @Override
  public ModuleWizardStep modifySettingsStep(@NotNull SettingsStep settingsStep, @NotNull ModuleBuilder moduleBuilder) {
    return super.modifySettingsStep(settingsStep, moduleBuilder);
  }
}

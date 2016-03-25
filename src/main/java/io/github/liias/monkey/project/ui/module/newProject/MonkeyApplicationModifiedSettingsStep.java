package io.github.liias.monkey.project.ui.module.newProject;

import com.intellij.ide.util.projectWizard.SdkSettingsStep;
import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.util.Condition;
import io.github.liias.monkey.project.module.MonkeyModuleBuilder;
import org.jetbrains.annotations.NotNull;

public class MonkeyApplicationModifiedSettingsStep extends SdkSettingsStep {
  protected MonkeyModuleBuilder myBuilder;

  public MonkeyApplicationModifiedSettingsStep(@NotNull final MonkeyModuleBuilder builder, @NotNull SettingsStep settingsStep) {
    super(settingsStep, builder, builder::isSuitableSdkType);
    myBuilder = builder;

    final String applicationName = builder.getName();
    if (applicationName != null && applicationName.length() > 0) {
      settingsStep.getModuleNameField().setText(applicationName);
    }
  }

  @Override
  public void updateDataModel() {
    super.updateDataModel();
  }
}

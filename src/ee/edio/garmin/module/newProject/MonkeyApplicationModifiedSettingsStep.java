package ee.edio.garmin.module.newProject;

import com.intellij.ide.util.projectWizard.SdkSettingsStep;
import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.util.Condition;
import ee.edio.garmin.MonkeyModuleBuilder;
import org.jetbrains.annotations.NotNull;

public class MonkeyApplicationModifiedSettingsStep extends SdkSettingsStep {
  protected MonkeyModuleBuilder myBuilder;

  public MonkeyApplicationModifiedSettingsStep(@NotNull final MonkeyModuleBuilder builder, @NotNull SettingsStep settingsStep) {
    super(settingsStep, builder, new Condition<SdkTypeId>() {
      @Override
      public boolean value(SdkTypeId sdkType) {
        return builder.isSuitableSdkType(sdkType);
      }
    });
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

package ee.edio.garmin.runconfig;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;

public class MonkeyModuleConfigurationProducer extends RunConfigurationProducer<MonkeyModuleBasedConfiguration> {
  public MonkeyModuleConfigurationProducer() {
    super(MonkeyConfigurationType.getInstance());
  }

  @Override
  protected boolean setupConfigurationFromContext(MonkeyModuleBasedConfiguration configuration,
                                                  ConfigurationContext context,
                                                  Ref<PsiElement> sourceElement) {
    return true;
  }

  @Override
  public boolean isConfigurationFromContext(MonkeyModuleBasedConfiguration configuration,
                                            ConfigurationContext context) {
    final PsiElement location = context.getPsiLocation();
    return false;
  }
}

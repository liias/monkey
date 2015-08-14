package ee.edio.garmin.runconfig;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;

public class MonkeyCApplicationConfigurationProducer extends RunConfigurationProducer<MonkeyCApplicationConfiguration> {
  protected MonkeyCApplicationConfigurationProducer() {
    super(MonkeyCApplicationConfigurationType.getInstance());
  }

  @Override
  protected boolean setupConfigurationFromContext(MonkeyCApplicationConfiguration configuration, ConfigurationContext context, Ref<PsiElement> sourceElement) {
    return false;
  }

  @Override
  public boolean isConfigurationFromContext(MonkeyCApplicationConfiguration configuration, ConfigurationContext context) {
    return false;
  }
}

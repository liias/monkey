package ee.edio.garmin.runconfig;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;

public class MonkeyCModuleConfigurationProducer extends RunConfigurationProducer<MonkeyCModuleBasedConfiguration> {
  public MonkeyCModuleConfigurationProducer() {
    super(MonkeyCConfigurationType.getInstance());
  }

  @Override
  protected boolean setupConfigurationFromContext(MonkeyCModuleBasedConfiguration configuration,
                                                  ConfigurationContext context,
                                                  Ref<PsiElement> sourceElement) {
    return true;
  }

  @Override
  public boolean isConfigurationFromContext(MonkeyCModuleBasedConfiguration configuration,
                                            ConfigurationContext context) {
    final PsiElement location = context.getPsiLocation();
    return false;
  }
}

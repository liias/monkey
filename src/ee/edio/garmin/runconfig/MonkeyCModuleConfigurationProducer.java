package ee.edio.garmin.runconfig;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;

public class MonkeyCModuleConfigurationProducer extends RunConfigurationProducer<MCModuleBasedConfiguration> {
  public MonkeyCModuleConfigurationProducer() {
    super(MCConfigurationType.getInstance());
  }

  @Override
  protected boolean setupConfigurationFromContext(MCModuleBasedConfiguration configuration,
                                                  ConfigurationContext context,
                                                  Ref<PsiElement> sourceElement) {
    return true;
  }

  @Override
  public boolean isConfigurationFromContext(MCModuleBasedConfiguration configuration,
                                            ConfigurationContext context) {
    final PsiElement location = context.getPsiLocation();
    return false;
  }
}

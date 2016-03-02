package io.github.liias.monkey.project.runconfig;

import com.intellij.execution.Location;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Comparing;
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
    final Location location = context.getLocation();

    if (location == null) {
      return false;
    }
    final Module contextModule = context.getModule();
    if (contextModule != null) {
      configuration.setModule(contextModule);
      configuration.setName(contextModule.getName());
    }

    configuration.setTargetDeviceId(TargetDevice.SQUARE_WATCH.getId());

    return true;
  }

  @Override
  public boolean isConfigurationFromContext(MonkeyModuleBasedConfiguration configuration,
                                            ConfigurationContext context) {
    final Module contextModule = context.getModule();
    if (contextModule == null) {
      return false;
    }
    final Module confModule = configuration.getConfigurationModule().getModule();

    return Comparing.equal(contextModule, confModule);
  }
}

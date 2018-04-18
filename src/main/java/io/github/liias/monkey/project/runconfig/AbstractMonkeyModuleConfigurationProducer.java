package io.github.liias.monkey.project.runconfig;

import com.intellij.execution.Location;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import io.github.liias.monkey.lang.MonkeyLanguage;

public abstract class AbstractMonkeyModuleConfigurationProducer extends RunConfigurationProducer<AbstractMonkeyModuleBasedConfiguration> {
  protected AbstractMonkeyModuleConfigurationProducer(ConfigurationType configurationType) {
    super(configurationType);
  }

  @Override
  protected boolean setupConfigurationFromContext(AbstractMonkeyModuleBasedConfiguration configuration, ConfigurationContext context, Ref<PsiElement> sourceElement) {
    final Location location = context.getLocation();

    if (location == null) {
      return false;
    }
    if (!(location.getPsiElement().getLanguage() instanceof MonkeyLanguage)) {
      return false;
    }
    final Module contextModule = context.getModule();
    if (contextModule != null) {
      configuration.setModule(contextModule);
      configuration.setName(contextModule.getName());
    }

    configuration.setTargetDeviceId(TargetDevice.DEFAULT_DEVICE.getId());

    return true;
  }

  @Override
  public boolean isConfigurationFromContext(AbstractMonkeyModuleBasedConfiguration configuration, ConfigurationContext context) {
    final Module contextModule = context.getModule();
    if (contextModule == null) {
      return false;
    }
    final Module confModule = configuration.getConfigurationModule().getModule();

    return Comparing.equal(contextModule, confModule);
  }


}

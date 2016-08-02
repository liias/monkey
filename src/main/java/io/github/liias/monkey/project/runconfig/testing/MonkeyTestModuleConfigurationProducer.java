package io.github.liias.monkey.project.runconfig.testing;

import io.github.liias.monkey.project.runconfig.AbstractMonkeyModuleConfigurationProducer;

public class MonkeyTestModuleConfigurationProducer extends AbstractMonkeyModuleConfigurationProducer {
  protected MonkeyTestModuleConfigurationProducer() {
    super(MonkeyTestConfigurationType.getInstance());
  }
}

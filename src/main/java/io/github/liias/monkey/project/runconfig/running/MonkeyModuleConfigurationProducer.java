package io.github.liias.monkey.project.runconfig.running;

import io.github.liias.monkey.project.runconfig.AbstractMonkeyModuleConfigurationProducer;

public class MonkeyModuleConfigurationProducer extends AbstractMonkeyModuleConfigurationProducer {
  public MonkeyModuleConfigurationProducer() {
    super(MonkeyConfigurationType.getInstance());
  }
}

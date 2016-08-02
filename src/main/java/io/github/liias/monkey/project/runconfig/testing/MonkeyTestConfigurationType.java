package io.github.liias.monkey.project.runconfig.testing;

import com.intellij.execution.configuration.ConfigurationFactoryEx;
import com.intellij.execution.configurations.*;
import com.intellij.openapi.project.Project;
import icons.MonkeyIcons;
import org.jetbrains.annotations.NotNull;

public class MonkeyTestConfigurationType extends ConfigurationTypeBase {
  private final ConfigurationFactoryEx factory;

  protected MonkeyTestConfigurationType() {
    super("MonkeyCApplicationTest", "Monkey C Application Test", "Configuration to run a Connect IQ unit tests", MonkeyIcons.MODULE_TEST_16);

    factory = new ConfigurationFactoryEx(this) {
      @Override
      public void onNewConfigurationCreated(@NotNull RunConfiguration configuration) {
        final ModuleBasedConfiguration moduleBasedConfiguration = (ModuleBasedConfiguration) configuration;
        moduleBasedConfiguration.onNewConfigurationCreated();
      }

      @NotNull
      public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new MonkeyTestModuleBasedConfiguration("", new MonkeyTestRunConfigurationModule(project), this);
      }
    };
    addFactory(factory);
  }

  public ConfigurationFactory getFactory() {
    return factory;
  }

  @NotNull
  public static MonkeyTestConfigurationType getInstance() {
    return ConfigurationTypeUtil.findConfigurationType(MonkeyTestConfigurationType.class);
  }
}

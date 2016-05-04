package io.github.liias.monkey.project.runconfig;

import com.intellij.execution.configuration.ConfigurationFactoryEx;
import com.intellij.execution.configurations.*;
import com.intellij.openapi.project.Project;
import icons.MonkeyIcons;
import org.jetbrains.annotations.NotNull;

public class MonkeyConfigurationType extends ConfigurationTypeBase {

  private final ConfigurationFactoryEx factory;

  protected MonkeyConfigurationType() {
    // icon is 16 (default for java is AllIcons.RunConfigurations.Application)
    super("MonkeyCApplication", "Monkey C Application", "Configuration to run a Connect IQ app with the simulator", MonkeyIcons.MODULE16);

    factory = new ConfigurationFactoryEx(this) {
      @Override
      public void onNewConfigurationCreated(@NotNull RunConfiguration configuration) {
        final ModuleBasedConfiguration moduleBasedConfiguration = (ModuleBasedConfiguration) configuration;
        moduleBasedConfiguration.onNewConfigurationCreated();
      }

      @NotNull
      public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new MonkeyModuleBasedConfiguration("", new MonkeyRunConfigurationModule(project), this);
      }
    };
    addFactory(factory);
  }

  public ConfigurationFactory getFactory() {
    return factory;
  }

  @NotNull
  public static MonkeyConfigurationType getInstance() {
    return ConfigurationTypeUtil.findConfigurationType(MonkeyConfigurationType.class);
    //return Extensions.findExtension(CONFIGURATION_TYPE_EP, MonkeyConfigurationType.class);
  }
}

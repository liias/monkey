package ee.edio.garmin.runconfig;

import com.intellij.execution.configuration.ConfigurationFactoryEx;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import ee.edio.garmin.MonkeyCIcons;
import org.jetbrains.annotations.NotNull;

public class MonkeyCApplicationConfigurationType extends ConfigurationTypeBase {
  protected MonkeyCApplicationConfigurationType() {
    // icon is 16 (default for java is AllIcons.RunConfigurations.Application)
    super("MonkeyCApplication", "Monkey C Application", "Configuration to run a Connect IQ app with the simulator", MonkeyCIcons.MODULE16);


    addFactory(new ConfigurationFactoryEx(this) {
      @Override
      public void onNewConfigurationCreated(@NotNull RunConfiguration configuration) {
        MonkeyCApplicationConfiguration monkeyCApplicationConfiguration = (MonkeyCApplicationConfiguration) configuration;
/*        if (StringUtil.isEmpty(monkeyCApplicationConfiguration.getWorkingDirectory())) {
          String baseDir = FileUtil.toSystemIndependentName(StringUtil.notNullize(configuration.getProject().getBasePath()));
          monkeyCApplicationConfiguration.setWorkingDirectory(baseDir);
        }*/
      }

      public RunConfiguration createTemplateConfiguration(Project project) {
        return new MonkeyCApplicationConfiguration(project, this, "");
      }
    });
  }

  @NotNull
  public static MonkeyCApplicationConfigurationType getInstance() {
    return Extensions.findExtension(CONFIGURATION_TYPE_EP, MonkeyCApplicationConfigurationType.class);
  }
}

package ee.edio.garmin.runconfig;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class MonkeyCRunConfigurationModule extends RunConfigurationModule {
  public MonkeyCRunConfigurationModule(@NotNull Project project) {
    super(project);
  }
}

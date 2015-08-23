package ee.edio.garmin.runconfig;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class MCRunConfigurationModule extends RunConfigurationModule {
  public MCRunConfigurationModule(@NotNull Project project) {
    super(project);
  }
}

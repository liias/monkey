package ee.edio.garmin.runconfig;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class MonkeyRunConfigurationModule extends RunConfigurationModule {
  public MonkeyRunConfigurationModule(@NotNull Project project) {
    super(project);
  }
}

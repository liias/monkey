package io.github.liias.monkey.project.runconfig.running;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;
import io.github.liias.monkey.project.runconfig.AbstractMonkeyRunConfigurationModule;
import org.jetbrains.annotations.NotNull;

public class MonkeyRunConfigurationModule extends AbstractMonkeyRunConfigurationModule {
  public MonkeyRunConfigurationModule(@NotNull Project project) {
    super(project);
  }
}

package io.github.liias.monkey.project.runconfig;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractMonkeyRunConfigurationModule extends RunConfigurationModule {
  public AbstractMonkeyRunConfigurationModule(@NotNull Project project) {
    super(project);
  }
}

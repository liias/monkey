package io.github.liias.monkey.project.runconfig.testing;

import com.intellij.openapi.project.Project;
import io.github.liias.monkey.project.runconfig.AbstractMonkeyRunConfigurationModule;
import org.jetbrains.annotations.NotNull;

public class MonkeyTestRunConfigurationModule extends AbstractMonkeyRunConfigurationModule {
  public MonkeyTestRunConfigurationModule(@NotNull Project project) {
    super(project);
  }
}
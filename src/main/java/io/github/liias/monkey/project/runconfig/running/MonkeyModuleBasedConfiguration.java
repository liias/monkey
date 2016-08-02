package io.github.liias.monkey.project.runconfig.running;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import io.github.liias.monkey.project.runconfig.AbstractMonkeyModuleBasedConfiguration;
import io.github.liias.monkey.project.runconfig.AbstractMonkeyRunConfigurationModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyModuleBasedConfiguration extends AbstractMonkeyModuleBasedConfiguration {

  public MonkeyModuleBasedConfiguration(String name, @NotNull AbstractMonkeyRunConfigurationModule configurationModule, @NotNull ConfigurationFactory factory) {
    super(name, configurationModule, factory);
  }

  @Nullable
  @Override
  public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
    return new MonkeyRunningState(environment);
  }
}
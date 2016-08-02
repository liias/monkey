package io.github.liias.monkey.project.runconfig.testing;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.testframework.sm.runner.SMRunnerConsolePropertiesProvider;
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties;
import io.github.liias.monkey.project.runconfig.AbstractMonkeyModuleBasedConfiguration;
import io.github.liias.monkey.project.runconfig.AbstractMonkeyRunConfigurationModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyTestModuleBasedConfiguration extends AbstractMonkeyModuleBasedConfiguration implements SMRunnerConsolePropertiesProvider {
  public static final String TEST_FRAMEWORK_NAME = "RunNoEvil";

  public MonkeyTestModuleBasedConfiguration(String name, @NotNull MonkeyTestRunConfigurationModule configurationModule, @NotNull ConfigurationFactory factory) {
    super(name, configurationModule, factory);
  }

  @Override
  public String suggestedName() {
    AbstractMonkeyRunConfigurationModule configurationModule = getConfigurationModule();
    return "All in " + configurationModule.getModuleName();
  }

  @Override
  public SMTRunnerConsoleProperties createTestConsoleProperties(Executor executor) {
    return new SMTRunnerConsoleProperties(this, TEST_FRAMEWORK_NAME, executor);
  }

  @Nullable
  @Override
  public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
    return new MonkeyTestRunningState(environment);
  }
}

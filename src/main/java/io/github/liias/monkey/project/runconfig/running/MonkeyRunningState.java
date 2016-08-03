package io.github.liias.monkey.project.runconfig.running;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.ui.ConsoleView;
import io.github.liias.monkey.project.runconfig.AbstractMonkeyRunningState;
import io.github.liias.monkey.project.runconfig.DeploymentTarget;
import org.jetbrains.annotations.NotNull;

// Starts app in simulatorHelper
public class MonkeyRunningState extends AbstractMonkeyRunningState {
  public MonkeyRunningState(ExecutionEnvironment environment) {
    super(environment, false);
  }

  @Override
  @NotNull
  public ExecutionResult execute(@NotNull final Executor executor, @NotNull final ProgramRunner runner) throws ExecutionException {
    ConsoleView console = createConsole(executor);

    if (DeploymentTarget.DEVICE.getId().equals(getConfiguration().getDeploymentTargetId())) {
      return runOnRealDevice(console);
    }

    runSimulator(console);

    return runOnSimulator(console, executor);
  }
}

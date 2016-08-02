package io.github.liias.monkey.project.runconfig.testing;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.ui.ConsoleView;
import io.github.liias.monkey.project.runconfig.AbstractMonkeyRunningState;
import org.jetbrains.annotations.NotNull;

public class MonkeyTestRunningState extends AbstractMonkeyRunningState {
  protected MonkeyTestRunningState(ExecutionEnvironment environment) {
    super(environment);
  }

  @Override
  @NotNull
  public ExecutionResult execute(@NotNull final Executor executor, @NotNull final ProgramRunner runner) throws ExecutionException {
    ConsoleView console = createConsole(executor);

    //final OSProcessHandler processHandler = new KillableColoredProcessHandler(createCommandLine());
    //ProcessTerminatedListener.attach(processHandler);
    //new MonkeySearchForTestsTask(null, null).attachTaskToProcess(processHandler);

    runSimulator(console);

    return runOnSimulator(console, executor);
  }
}

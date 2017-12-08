package io.github.liias.monkey.project.runconfig;

import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.DefaultProgramRunner;
import org.jetbrains.annotations.NotNull;

public class MonkeyProgramRunner extends DefaultProgramRunner {
  @NotNull
  @Override
  public String getRunnerId() {
    return "MonkeyCRunner";
  }

  @Override
  public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
    return profile instanceof AbstractMonkeyModuleBasedConfiguration && DefaultRunExecutor.EXECUTOR_ID.equals(executorId);
  }
}

package io.github.liias.monkey.project.runconfig.testing;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.testframework.SearchForTestsTask;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

import java.net.ServerSocket;

public class MonkeySearchForTestsTask extends SearchForTestsTask {
  public MonkeySearchForTestsTask(@Nullable Project project, ServerSocket socket) {
    super(project, socket);
  }

  @Override
  protected void search() throws ExecutionException {

  }

  @Override
  protected void onFound() {

  }
}

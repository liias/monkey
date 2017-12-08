package io.github.liias.monkey.project.runconfig.testing;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.testframework.TestConsoleProperties;
import com.intellij.execution.testframework.sm.SMTestRunnerConnectionUtil;
import com.intellij.execution.testframework.sm.ServiceMessageBuilder;
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.util.Key;
import io.github.liias.monkey.project.runconfig.AbstractMonkeyRunningState;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang.StringUtils.trimToEmpty;

public class MonkeyTestRunningState extends AbstractMonkeyRunningState {
  public static final Pattern PATTERN_TEST_NAME = Pattern.compile("Executing test (.*)\\.\\.\\.");

  protected MonkeyTestRunningState(ExecutionEnvironment environment) {
    super(environment, true);
  }

  @Override
  @NotNull
  public ExecutionResult execute(@NotNull Executor executor, @NotNull ProgramRunner runner) throws ExecutionException {
    ConsoleView simulatorConsole = createConsole(executor);
    runSimulator(simulatorConsole);

    ProcessHandler processHandler = startInSimulator();
    RunConfiguration runConfiguration = getConfiguration();
    TestConsoleProperties properties = new SMTRunnerConsoleProperties(runConfiguration, MonkeyTestModuleBasedConfiguration.TEST_FRAMEWORK_NAME, executor);

    ConsoleView console = SMTestRunnerConnectionUtil.
      createAndAttachConsole(MonkeyTestModuleBasedConfiguration.TEST_FRAMEWORK_NAME, processHandler, properties);
    //new MonkeySearchForTestsTask(null, null).attachTaskToProcess(processHandler);

    AtomicReference<String> lastTestName = new AtomicReference<>(null);
    AtomicReference<String> lastSuiteName = new AtomicReference<>(null);

    processHandler.addProcessListener(new ProcessAdapter() {
      @Override
      public void onTextAvailable(ProcessEvent event, Key outputType) {
        String line = event.getText();
        if (line.startsWith("Testing started at")) {
          processHandler.notifyTextAvailable(new ServiceMessageBuilder("enteredTheMatrix").toString() + "\n", ProcessOutputTypes.STDOUT);
        } else if (line.startsWith("RESULTS")) {
          String lastSuite = lastSuiteName.get();
          if (lastSuite != null) {
            processHandler.notifyTextAvailable(ServiceMessageBuilder.testSuiteFinished(lastSuite).toString() + "\n", ProcessOutputTypes.STDOUT);
          }
        } else if (line.startsWith("Executing test")) {
          Matcher matcher = PATTERN_TEST_NAME.matcher(line);
          matcher.find();
          String fullyQualifiedTestName = matcher.group(1);
          SuiteNameAndTestName suiteNameAndTestName = new SuiteNameAndTestName(fullyQualifiedTestName);
          String lastSuite = lastSuiteName.get();

          if (!trimToEmpty(lastSuite).equals(trimToEmpty(suiteNameAndTestName.suiteName))) {
            lastSuiteName.set(suiteNameAndTestName.suiteName);

            if (lastSuite != null) {
              processHandler.notifyTextAvailable(ServiceMessageBuilder.testSuiteFinished(lastSuite).toString() + "\n", ProcessOutputTypes.STDOUT);
            }
            if (suiteNameAndTestName.suiteName != null) {
              processHandler.notifyTextAvailable(ServiceMessageBuilder.testSuiteStarted(suiteNameAndTestName.suiteName).toString() + "\n", ProcessOutputTypes.STDOUT);
            }
          }

          lastTestName.set(suiteNameAndTestName.testName);
          processHandler.notifyTextAvailable(ServiceMessageBuilder.testStarted(lastTestName.get()).toString() + "\n", ProcessOutputTypes.STDOUT);
        } else if (line.equals("PASS\n") || line.equals("FAIL\n") || line.equals("ERROR\n")) {
          if (line.equals("FAIL\n") || line.equals("ERROR\n")) {
            processHandler.notifyTextAvailable(ServiceMessageBuilder.testFailed(lastTestName.get()).addAttribute("message", "Test failed").toString() + "\n", ProcessOutputTypes.STDOUT);
          }
          processHandler.notifyTextAvailable(ServiceMessageBuilder.testFinished(lastTestName.get()).toString() + "\n", ProcessOutputTypes.STDOUT);
        }
      }
    });

    return new DefaultExecutionResult(console, processHandler, createActions(console, processHandler, executor));
  }

  private static class SuiteNameAndTestName {
    public final String suiteName;
    public final String testName;

    public SuiteNameAndTestName(@NotNull String fullyQualifiedName) {
      String[] splitByDot = fullyQualifiedName.split("\\.", 2);
      this.suiteName = splitByDot.length > 1 ? splitByDot[0] : null;
      this.testName = splitByDot.length > 1 ? splitByDot[1] : splitByDot[0];
      requireNonNull(this.testName);
    }

    public boolean inSuite() {
      return suiteName != null;
    }
  }
}

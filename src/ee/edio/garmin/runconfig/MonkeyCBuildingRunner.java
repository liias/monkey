package ee.edio.garmin.runconfig;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.RunProfileStarter;
import com.intellij.execution.configurations.EncodingEnvironmentUtil;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.process.KillableColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.AsyncGenericProgramRunner;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.concurrency.Semaphore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.concurrency.AsyncPromise;
import org.jetbrains.concurrency.Promise;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MonkeyCBuildingRunner extends AsyncGenericProgramRunner {
  private static final String ID = "MonkeyCBuildingRunner";

  @NotNull
  @Override
  protected Promise<RunProfileStarter> prepare(@NotNull ExecutionEnvironment environment, @NotNull RunProfileState state)
      throws ExecutionException {
    final File outputFile;
    /*String outputDirectoryPath = ((MonkeyCRunningState)state).myConfiguration.getOutputFilePath();
    RunnerAndConfigurationSettings settings = environment.getRunnerAndConfigurationSettings();
    String configurationName = settings != null ? settings.getName() : "application";
    if (StringUtil.isEmpty(outputDirectoryPath)) {
      try {
        outputFile = FileUtil.createTempFile(configurationName, GoEnvironmentUtil.getBinaryFileNameForPath("go"), true);
      }
      catch (IOException e) {
        throw new ExecutionException("Cannot create temporary output file", e);
      }
    }
    else {
      File outputDirectory = new File(outputDirectoryPath);
      if (outputDirectory.isDirectory() || !outputDirectory.exists() && outputDirectory.mkdirs()) {
        outputFile = new File(outputDirectoryPath, GoEnvironmentUtil.getBinaryFileNameForPath(configurationName));
        try {
          if (!outputFile.exists() && !outputFile.createNewFile()) {
            throw new ExecutionException("Cannot create output file " + outputFile.getAbsolutePath());
          }
        }
        catch (IOException e) {
          throw new ExecutionException("Cannot create output file " + outputFile.getAbsolutePath());
        }
      }
      else {
        throw new ExecutionException("Cannot create output file in " + outputDirectory.getAbsolutePath());
      }
    }
    if (!prepareFile(outputFile)) {
      throw new ExecutionException("Cannot make temporary file executable " + outputFile.getAbsolutePath());
    }

    final AsyncPromise<RunProfileStarter> promise = new AsyncPromise<RunProfileStarter>();
    FileDocumentManager.getInstance().saveAllDocuments();

    final GoHistoryProcessListener historyProcessListener = new GoHistoryProcessListener();
    ((GoApplicationRunningState)state).createCommonExecutor()
        .withParameters("build")
        .withParameterString(((GoApplicationRunningState)state).getGoBuildParams())
        .withParameters("-o", outputFile.getAbsolutePath(), ((GoApplicationRunningState)state).getTarget())
        .showNotifications(true)
        .showOutputOnError()
        .disablePty()
        .withPresentableName("go build")
        .withProcessListener(historyProcessListener)
        .withProcessListener(new ProcessAdapter() {

          @Override
          public void processTerminated(ProcessEvent event) {
            super.processTerminated(event);
            if (event.getExitCode() == 0) {
              promise.setResult(new MyStarter(outputFile.getAbsolutePath(), historyProcessListener));
            }
            else {
              promise.setResult(null);
            }
          }
        }).executeWithProgress(false);
*/

    final AsyncPromise<RunProfileStarter> promise = new AsyncPromise<>();
    FileDocumentManager.getInstance().saveAllDocuments();

    GeneralCommandLine commandLine = createBuildCmd(environment);
    commandLine.withCharset(CharsetToolkit.UTF8_CHARSET);
    EncodingEnvironmentUtil.setLocaleEnvironmentIfMac(commandLine);
//    return commandLine;
    final KillableColoredProcessHandler killableColoredProcessHandler = new KillableColoredProcessHandler(commandLine);


    final Runnable cancelableExecutionProcess = createCancelableExecutionProcess(killableColoredProcessHandler, new com.intellij.util.Function<Object, Boolean>() {
      @Override
      public Boolean fun(Object o) {
        return null;
      }
    });
    final Task task = new Task.Backgroundable(environment.getProject(), "please wait", true) {
      @Override
      public void run(@NotNull final ProgressIndicator indicator) {
        cancelableExecutionProcess.run();
      }
    };
    ProgressManager.getInstance().run(task);
    return promise;
  }

  @NotNull
  @Override
  public String getRunnerId() {
    return ID;
  }

  @Override
  public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
    return DefaultRunExecutor.EXECUTOR_ID.equals(executorId) && profile instanceof MonkeyCApplicationConfiguration;
  }

  public GeneralCommandLine createBuildCmd(ExecutionEnvironment environment) {
    Project project = environment.getProject();
    String projectBasePath = project.getBasePath();
    String sdkPath = "C:\\Users\\Madis\\sdks\\connectiq-sdk-win-1.1.3";
    String sdkBinPath = sdkPath + File.separator + "bin" + File.separator;

    String outputName = project.getName() + ".prg";
    String outputDir = projectBasePath + File.separator + "bin" + File.separator;

    boolean hasResources = true;
    String sourcePath = projectBasePath + File.separator + "source" + File.separator;


    ImmutableList.Builder<String> parameters = ImmutableList.<String>builder()
        .add("-a", sdkBinPath + "api.db")
        .add("-i", sdkBinPath + "api.debug.xml")
        .add("-o", outputDir + outputName);
//        .add("-w") //debug info


    if (hasResources) {
      parameters.add("-z", projectBasePath + File.separator + "resources" + File.separator + "resources.xml")
          .add("-z", projectBasePath + File.separator + "resources" + File.separator + "menus" + File.separator + "menu.xml")
          .add("-z", projectBasePath + File.separator + "resources" + File.separator + "layouts" + File.separator + "layout.xml");
    }
    parameters.add("-m", projectBasePath + File.separator + "manifest.xml")
        .add("-u", sdkBinPath + "devices.xml")
        .add("-p", sdkBinPath + "projectInfo.xml"); // optional file


    VirtualFile sourceDir = project.getBaseDir().findChild("source");
    if (sourceDir == null) {
      throw new RuntimeException("source dir does not exist");
    }
    List<VirtualFile> sourceFiles = Arrays.asList(sourceDir.getChildren());

    ImmutableList<String> sourceFilePaths = FluentIterable.from(sourceFiles)
        .filter(new Predicate<VirtualFile>() {
          @Override
          public boolean apply(VirtualFile virtualFile) {
            return "mc".equals(virtualFile.getExtension());
          }
        }).transform(new Function<VirtualFile, String>() {
          @Override
          public String apply(VirtualFile virtualFile) {
            return virtualFile.getPath();
          }
        }).toList();

    parameters.addAll(sourceFilePaths);
    //parameters.add(sourcePath + "EsimeneView.mc", sourcePath + "EsimeneMenuDelegate.mc", sourcePath + "EsimeneApp.mc");

    parameters.add("-d", "vivoactive_sim");

    GeneralCommandLine commandLine = new GeneralCommandLine();
    commandLine.setExePath("java");
    commandLine.addParameters("-Dfile.encoding=UTF-8", "-Dapple.awt.UIElement=true");
    String classPath = "C:\\Program Files\\Java\\jre1.8.0_51\\lib\\tools.jar;";
    classPath += sdkBinPath + "monkeybrains.jar" + ";";
    commandLine.addParameters("-classpath", classPath);
    commandLine.addParameters("com.garmin.monkeybrains.Monkeybrains");

    commandLine.addParameters(parameters.build());
    return commandLine;
  }


  private static Runnable createCancelableExecutionProcess(final ProcessHandler processHandler,
                                                           final com.intellij.util.Function<Object, Boolean> cancelableFun) {
    return new Runnable() {
      private ProgressIndicator myProgressIndicator;
      private final Semaphore mySemaphore = new Semaphore();

      private final Runnable myWaitThread = new Runnable() {
        @Override
        public void run() {
          try {
            processHandler.waitFor();
          } finally {
            mySemaphore.up();
          }
        }
      };

      private final Runnable myCancelListener = new Runnable() {
        @Override
        public void run() {
          while (true) {
            if (myProgressIndicator == null) {

            }
            else if ((myProgressIndicator != null && (myProgressIndicator.isCanceled()
                || !myProgressIndicator.isRunning()))
                || (cancelableFun != null && cancelableFun.fun(null).booleanValue())
                || processHandler.isProcessTerminated()) {

              if (!processHandler.isProcessTerminated()) {
                try {
                  processHandler.destroyProcess();
                } finally {
                  mySemaphore.up();
                }
              }
              break;
            }
            try {
              synchronized (this) {
                wait(1000);
              }
            } catch (InterruptedException e) {
              //Do nothing
            }
          }
        }
      };

      @Override
      public void run() {
        myProgressIndicator = ProgressManager.getInstance().getProgressIndicator();
        if (myProgressIndicator != null && StringUtil.isEmpty(myProgressIndicator.getText())) {
          myProgressIndicator.setText("Please wait...");
        }

        mySemaphore.down();
        ApplicationManager.getApplication().executeOnPooledThread(myWaitThread);
        ApplicationManager.getApplication().executeOnPooledThread(myCancelListener);

        mySemaphore.waitFor();
      }
    };
  }
}

package ee.edio.garmin.runconfig;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.intellij.diagnostic.logging.LogConfigurationPanel;
import com.intellij.execution.*;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MCModuleBasedConfiguration extends ModuleBasedConfiguration<MCRunConfigurationModule> implements CommonProgramRunConfigurationParameters {
  private String PROGRAM_PARAMETERS;
  private String WORKING_DIRECTORY;
  private boolean PASS_PARENT_ENVS;
  private final Map<String, String> envs = new LinkedHashMap<>();
  private TargetDevice targetDevice;

  public MCModuleBasedConfiguration(String name, @NotNull MCRunConfigurationModule configurationModule, @NotNull ConfigurationFactory factory) {
    super(name, configurationModule, factory);
  }

  @Override
  public Collection<Module> getValidModules() {
    List<Module> allModules = Lists.newArrayList(ModuleManager.getInstance(getProject()).getModules());
    return Collections2.filter(allModules, new Predicate<Module>() {
      @Override
      public boolean apply(@Nullable Module module) {
        return module != null && !module.isDisposed();
      }
    });
  }

  @NotNull
  @Override
  public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
    SettingsEditorGroup<MCModuleBasedConfiguration> group = new SettingsEditorGroup<>();
    group.addEditor(ExecutionBundle.message("run.configuration.configuration.tab.title"), new MCSettingsEditor(getProject()));
    group.addEditor(ExecutionBundle.message("logs.tab.title"), new LogConfigurationPanel<MCModuleBasedConfiguration>());
    return group;
  }

  @Nullable
  @Override
  public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
    MCRunConfigurationModule configurationModule = getConfigurationModule();

    Module module = configurationModule.getModule();
    if (module == null) {
      Collection<Module> modules = getValidModules();
      if (modules.size() == 1) {
        module = ContainerUtil.getFirstItem(modules);
        getConfigurationModule().setModule(module);
      }
    }


    //for (ModuleBuildTarget target : chunk.getTargets()) {
    //  final File outputDir = target.getOutputDir();
    return new MCRunningState(environment);
  }

  @Override
  public void setProgramParameters(@Nullable String value) {
    PROGRAM_PARAMETERS = value;
  }

  @Nullable
  @Override
  public String getProgramParameters() {
    return PROGRAM_PARAMETERS;
  }

  @Override
  public void setWorkingDirectory(@Nullable String value) {
    WORKING_DIRECTORY = ExternalizablePath.urlValue(value);
  }

  @Nullable
  @Override
  public String getWorkingDirectory() {
    return ExternalizablePath.localPathValue(WORKING_DIRECTORY);
  }

  @Override
  public void setEnvs(@NotNull final Map<String, String> envs) {
    this.envs.clear();
    this.envs.putAll(envs);
  }

  @NotNull
  @Override
  public Map<String, String> getEnvs() {
    return this.envs;
  }

  @Override
  public void setPassParentEnvs(boolean passParentEnvs) {
    PASS_PARENT_ENVS = passParentEnvs;
  }

  @Override
  public boolean isPassParentEnvs() {
    return PASS_PARENT_ENVS;
  }

  public void setTargetDevice(TargetDevice targetDevice) {
    this.targetDevice = targetDevice;
  }

  public TargetDevice getTargetDevice() {
    return targetDevice;
  }
}

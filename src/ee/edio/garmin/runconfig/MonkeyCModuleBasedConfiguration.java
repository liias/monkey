package ee.edio.garmin.runconfig;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.intellij.diagnostic.logging.LogConfigurationPanel;
import com.intellij.execution.ExecutionBundle;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class MonkeyCModuleBasedConfiguration extends ModuleBasedConfiguration<MonkeyCRunConfigurationModule> {
  public MonkeyCModuleBasedConfiguration(String name, @NotNull MonkeyCRunConfigurationModule configurationModule, @NotNull ConfigurationFactory factory) {
    super(name, configurationModule, factory);
  }

  @Override
  public Collection<Module> getValidModules() {
    List<Module> allModules = Lists.newArrayList(ModuleManager.getInstance(getProject()).getModules());
    return Collections2.filter(allModules, new Predicate<Module>() {
      @Override
      public boolean apply(@Nullable Module module) {
        return module != null && module.isDisposed();
      }
    });
  }

  @NotNull
  @Override
  public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
    SettingsEditorGroup<MonkeyCModuleBasedConfiguration> group = new SettingsEditorGroup<>();
    group.addEditor(ExecutionBundle.message("run.configuration.configuration.tab.title"), new MonkeyCSettingsEditor(getProject()));
    group.addEditor(ExecutionBundle.message("logs.tab.title"), new LogConfigurationPanel<MonkeyCModuleBasedConfiguration>());
    return group;
  }

  @Nullable
  @Override
  public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
    MonkeyCRunConfigurationModule configurationModule = getConfigurationModule();
    return new MonkeyCRunningState(environment);
  }
}

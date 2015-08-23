package ee.edio.garmin.runconfig;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.intellij.diagnostic.logging.LogConfigurationPanel;
import com.intellij.execution.*;
import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.util.ProgramParametersUtil;
import com.intellij.openapi.components.PathMacroManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.SkipDefaultValuesSerializationFilters;
import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MCModuleBasedConfiguration extends ModuleBasedConfiguration<MCRunConfigurationModule> implements CommonProgramRunConfigurationParameters {
  private static final SkipDefaultValuesSerializationFilters SERIALIZATION_FILTERS = new SkipDefaultValuesSerializationFilters();
  private MCModuleBasedConfigurationBean bean = new MCModuleBasedConfigurationBean();
  private final Map<String, String> envs = new LinkedHashMap<>();

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
    return new MCRunningState(environment);
  }

  @Override
  public void setProgramParameters(@Nullable String value) {
    bean.PROGRAM_PARAMETERS = value;
  }

  @Nullable
  @Override
  public String getProgramParameters() {
    return bean.PROGRAM_PARAMETERS;
  }

  @Override
  public void setWorkingDirectory(@Nullable String value) {
    bean.WORKING_DIRECTORY = ExternalizablePath.urlValue(value);
  }

  @Nullable
  @Override
  public String getWorkingDirectory() {
    return ExternalizablePath.localPathValue(bean.WORKING_DIRECTORY);
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
    bean.PASS_PARENT_ENVS = passParentEnvs;
  }

  @Override
  public boolean isPassParentEnvs() {
    return bean.PASS_PARENT_ENVS;
  }

  public void setTargetDevice(TargetDevice targetDevice) {
    bean.targetDevice = targetDevice;
  }

  public TargetDevice getTargetDevice() {
    return bean.targetDevice;
  }

  @Override
  public void checkConfiguration() throws RuntimeConfigurationException {
    final MCRunConfigurationModule configurationModule = getConfigurationModule();
    ProgramParametersUtil.checkWorkingDirectoryExist(this, getProject(), configurationModule.getModule());
  }

  @Override
  public void readExternal(Element element) throws InvalidDataException {
    PathMacroManager.getInstance(getProject()).expandPaths(element);
    super.readExternal(element);
    XmlSerializer.deserializeInto(this.bean, element);
    EnvironmentVariablesComponent.readExternal(element, getEnvs());
    getConfigurationModule().readExternal(element);
  }

  @Override
  public void writeExternal(Element element) throws WriteExternalException {
    super.writeExternal(element);
    XmlSerializer.serializeInto(this.bean, element, SERIALIZATION_FILTERS);
    EnvironmentVariablesComponent.writeExternal(element, getEnvs());
    if (getConfigurationModule().getModule() != null) {
      getConfigurationModule().writeExternal(element);
    }
  }

  private static class MCModuleBasedConfigurationBean {
    public String PROGRAM_PARAMETERS = "";
    public String WORKING_DIRECTORY = "";
    public boolean PASS_PARENT_ENVS = true;
    private TargetDevice targetDevice;
  }
}

package io.github.liias.monkey.project.runconfig;

import com.intellij.diagnostic.logging.LogConfigurationPanel;
import com.intellij.execution.CommonProgramRunConfigurationParameters;
import com.intellij.execution.ExecutionBundle;
import com.intellij.execution.ExternalizablePath;
import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RuntimeConfigurationException;
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

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractMonkeyModuleBasedConfiguration extends ModuleBasedConfiguration<AbstractMonkeyRunConfigurationModule> implements CommonProgramRunConfigurationParameters {
  private static final SkipDefaultValuesSerializationFilters SERIALIZATION_FILTERS = new SkipDefaultValuesSerializationFilters();
  private AbstractMonkeyModuleBasedConfiguration.MonkeyModuleBasedConfigurationBean bean = new AbstractMonkeyModuleBasedConfiguration.MonkeyModuleBasedConfigurationBean();
  private final Map<String, String> envs = new LinkedHashMap<>();

  public AbstractMonkeyModuleBasedConfiguration(String name, @NotNull AbstractMonkeyRunConfigurationModule configurationModule, @NotNull ConfigurationFactory factory) {
    super(name, configurationModule, factory);
  }

  @Override
  public Collection<Module> getValidModules() {
    return Arrays.stream(ModuleManager.getInstance(getProject()).getModules())
      .filter(module -> module != null && !module.isDisposed())
      .collect(Collectors.toList());
  }

  @NotNull
  @Override
  public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
    SettingsEditorGroup<AbstractMonkeyModuleBasedConfiguration> group = new SettingsEditorGroup<>();
    Module module = getConfigurationModule().getModule();
    group.addEditor(ExecutionBundle.message("run.configuration.configuration.tab.title"), new MonkeySettingsEditor(getProject(), module));
    group.addEditor(ExecutionBundle.message("logs.tab.title"), new LogConfigurationPanel<>());
    return group;
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

  @Override
  public void checkConfiguration() throws RuntimeConfigurationException {
    final AbstractMonkeyRunConfigurationModule configurationModule = getConfigurationModule();
    ProgramParametersUtil.checkWorkingDirectoryExist(this, getProject(), configurationModule.getModule());
  }

  @Override
  public void readExternal(Element element) throws InvalidDataException {
    PathMacroManager.getInstance(getProject()).expandPaths(element);
    super.readExternal(element);
    XmlSerializer.deserializeInto(this.bean, element);
    EnvironmentVariablesComponent.readExternal(element, getEnvs());
    readModule(element);
  }

  @Override
  public void writeExternal(Element element) throws WriteExternalException {
    super.writeExternal(element);
    XmlSerializer.serializeInto(this.bean, element, SERIALIZATION_FILTERS);
    EnvironmentVariablesComponent.writeExternal(element, getEnvs());
    writeModule(element);
  }

  public String getTargetDeviceId() {
    return bean.TARGET_DEVICE_ID;
  }

  public void setTargetDeviceId(String targetDeviceId) {
    bean.TARGET_DEVICE_ID = targetDeviceId;
  }

  public String getDeploymentTargetId() {
    return bean.DEPLOYMENT_TARGET_ID;
  }

  public void setDeploymentTargetId(String deploymentTargetId) {
    bean.DEPLOYMENT_TARGET_ID = deploymentTargetId;
  }

  public String getDeviceDirectory() {
    return bean.DEVICE_DIRECTORY;
  }

  public void setDeviceDirectory(String deviceDirectory) {
    bean.DEVICE_DIRECTORY = deviceDirectory;
  }

  private static class MonkeyModuleBasedConfigurationBean {
    public String PROGRAM_PARAMETERS = "";
    public String WORKING_DIRECTORY = "";
    public boolean PASS_PARENT_ENVS = true;
    public String TARGET_DEVICE_ID = "";
    public String DEPLOYMENT_TARGET_ID = "";
    public String DEVICE_DIRECTORY = "";
  }
}



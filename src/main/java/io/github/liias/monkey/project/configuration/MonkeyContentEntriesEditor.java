package io.github.liias.monkey.project.configuration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ui.configuration.CommonContentEntriesEditor;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationState;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

import static io.github.liias.monkey.jps.model.JpsMonkeyModuleType.*;

public class MonkeyContentEntriesEditor extends CommonContentEntriesEditor {
  private TargetDeviceConfigurable targetDeviceConfigurable;

  public MonkeyContentEntriesEditor(String moduleName, ModuleConfigurationState state) {
    super(moduleName, state, MONKEY_SOURCE_ROOT_TYPE, MONKEY_TEST_SOURCE_ROOT_TYPE, MONKEY_RESOURCE_ROOT_TYPE, MONKEY_TEST_RESOURCE_ROOT_TYPE);
  }

  @Override
  public void disposeUIResources() {
    if (targetDeviceConfigurable != null) targetDeviceConfigurable.disposeUIResources();
    super.disposeUIResources();
  }

  @Override
  public boolean isModified() {
    return super.isModified() || targetDeviceConfigurable != null && targetDeviceConfigurable.isModified();
  }

  @Override
  protected void addAdditionalSettingsToPanel(JPanel mainPanel) {
    Sdk sdk = ModuleRootManager.getInstance(getModule()).getSdk();
    String binPath = sdk == null ? null : MonkeySdkType.getBinPath(sdk);

    targetDeviceConfigurable = new TargetDeviceConfigurable(myProject, binPath) {
      // overriding is needed because it would fail when Applying changes in module settings
      // though I guess I could give 'this' to TargetDeviceConfigurable so I could have getModule() reference instead of value
      @NotNull
      @Override
      public TargetDeviceModuleExtension getTargetDeviceModuleExtension() {
        return getModel().getModuleExtension(TargetDeviceModuleExtension.class);
      }
    };
    mainPanel.add(targetDeviceConfigurable.createComponent(), BorderLayout.NORTH);
    targetDeviceConfigurable.reset();
  }

  @Override
  public void apply() throws ConfigurationException {
    targetDeviceConfigurable.apply();
    super.apply();
  }
}

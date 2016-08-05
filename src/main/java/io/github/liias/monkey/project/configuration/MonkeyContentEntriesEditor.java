package io.github.liias.monkey.project.configuration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ui.configuration.CommonContentEntriesEditor;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationState;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootType;

import javax.swing.*;
import java.awt.*;

public class MonkeyContentEntriesEditor extends CommonContentEntriesEditor {
  private TargetDeviceConfigurable myTargetDeviceConfigurable;

  public MonkeyContentEntriesEditor(String moduleName, ModuleConfigurationState state) {
    super(moduleName, state, JavaSourceRootType.SOURCE, JavaSourceRootType.TEST_SOURCE, JavaResourceRootType.RESOURCE, JavaResourceRootType.TEST_RESOURCE);
  }

  @Override
  public void disposeUIResources() {
    if (myTargetDeviceConfigurable != null) myTargetDeviceConfigurable.disposeUIResources();
    super.disposeUIResources();
  }

  @Override
  public boolean isModified() {
    return super.isModified() || myTargetDeviceConfigurable != null && myTargetDeviceConfigurable.isModified();
  }

  @Override
  protected void addAdditionalSettingsToPanel(JPanel mainPanel) {
    Sdk sdk = ModuleRootManager.getInstance(getModule()).getSdk();
    if (sdk == null) {
      return;
    }
    //checkNotNull(sdk);
    String binPath = MonkeySdkType.getBinPath(sdk);

    myTargetDeviceConfigurable = new TargetDeviceConfigurable(myProject, binPath) {
      // overriding is needed because it would fail when Applying changes in module settings
      // though I guess I could give 'this' to TargetDeviceConfigurable so I could have getModule() reference instead of value
      @NotNull
      @Override
      public TargetDeviceModuleExtension getTargetDeviceModuleExtension() {
        return getModel().getModuleExtension(TargetDeviceModuleExtension.class);
      }
    };
    mainPanel.add(myTargetDeviceConfigurable.createComponent(), BorderLayout.NORTH);
    myTargetDeviceConfigurable.reset();
  }

  @Override
  public void apply() throws ConfigurationException {
    myTargetDeviceConfigurable.apply();
    super.apply();
  }
}

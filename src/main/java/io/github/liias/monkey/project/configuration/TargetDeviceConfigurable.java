package io.github.liias.monkey.project.configuration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.UnnamedConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import io.github.liias.monkey.project.runconfig.TargetDevice;
import io.github.liias.monkey.project.sdk.devices.DevicesReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class TargetDeviceConfigurable implements UnnamedConfigurable {
  private JComboBox targetDeviceComboBox;
  private JPanel panel = new JPanel(new GridBagLayout());

  public TargetDeviceConfigurable(Project project, @Nullable String sdkBinPath) {
    targetDeviceComboBox = new ComboBox();

    DevicesReader devicesReader = new DevicesReader(sdkBinPath);
    List<TargetDevice> targetDevices = devicesReader.parseDevicesXml();

    for (TargetDevice device : targetDevices) {
      //noinspection unchecked
      targetDeviceComboBox.addItem(device);
    }

    targetDeviceComboBox.addActionListener(e -> {
      final Object selectedItem = targetDeviceComboBox.getSelectedItem();
      final TargetDevice targetDevice = selectedItem instanceof TargetDevice ? (TargetDevice) selectedItem : null;
      getTargetDeviceModuleExtension().setTargetDevice(targetDevice);
    });

    JLabel label = new JLabel("Target Device");
    label.setLabelFor(targetDeviceComboBox);
    panel.add(label,
      new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(12, 6, 12, 0), 0, 0));
    panel.add(targetDeviceComboBox,
      new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(6, 6, 12, 0), 0, 0));
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    return panel;
  }

  @Override
  public boolean isModified() {
    return getTargetDeviceModuleExtension().isChanged();
  }

  @Override
  public void apply() throws ConfigurationException {
    getTargetDeviceModuleExtension().commit();
  }

  @Override
  public void reset() {
    targetDeviceComboBox.setSelectedItem(getTargetDeviceModuleExtension().getTargetDevice());
  }

  @Override
  public void disposeUIResources() {
    panel = null;
    targetDeviceComboBox = null;
  }

  @NotNull
  public abstract TargetDeviceModuleExtension getTargetDeviceModuleExtension();
}

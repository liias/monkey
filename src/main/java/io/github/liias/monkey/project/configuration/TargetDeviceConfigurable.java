package io.github.liias.monkey.project.configuration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.UnnamedConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import io.github.liias.monkey.project.runconfig.TargetDevice;
import io.github.liias.monkey.project.runconfig.TargetSdkVersion;
import io.github.liias.monkey.project.sdk.devices.DevicesReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class TargetDeviceConfigurable implements UnnamedConfigurable {
  private ComboBox<TargetDevice> targetDeviceComboBox;
  private ComboBox<TargetSdkVersion> targetSdkVersionComboBox;
  private JPanel panel = new JPanel(new GridBagLayout());

  public TargetDeviceConfigurable(Project project, @Nullable String sdkBinPath) {
    DevicesReader devicesReader = new DevicesReader(sdkBinPath);

    addTargetDevice(devicesReader);
    addTargetSdkVersion(devicesReader);
  }

  private void addTargetDevice(DevicesReader devicesReader) {
    List<TargetDevice> targetDevices = devicesReader.parseDevicesXml();

    targetDeviceComboBox = new ComboBox<>();
    for (TargetDevice device : targetDevices) {
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

  private void addTargetSdkVersion(DevicesReader devicesReader) {
    List<TargetSdkVersion> targetSdkVersions = devicesReader.getTargetSdkVersions();

    targetSdkVersionComboBox = new ComboBox<>();
    for (TargetSdkVersion targetSdkVersion : targetSdkVersions) {
      targetSdkVersionComboBox.addItem(targetSdkVersion);
    }

    targetSdkVersionComboBox.addActionListener(e -> {
      final Object selectedItem = targetSdkVersionComboBox.getSelectedItem();
      final TargetSdkVersion targetSdkVersion = selectedItem instanceof TargetSdkVersion ? (TargetSdkVersion) selectedItem : null;
      getTargetDeviceModuleExtension().setTargetSdkVersion(targetSdkVersion);
    });

    JLabel label = new JLabel("Target SDK version");
    label.setLabelFor(targetSdkVersionComboBox);
    panel.add(label,
      new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(12, 6, 12, 0), 0, 0));
    panel.add(targetSdkVersionComboBox,
      new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(6, 6, 12, 0), 0, 0));
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
    targetSdkVersionComboBox.setSelectedItem(getTargetDeviceModuleExtension().getTargetSdkVersion());
  }

  @Override
  public void disposeUIResources() {
    panel = null;
    targetDeviceComboBox = null;
    targetSdkVersionComboBox = null;
  }

  @NotNull
  public abstract TargetDeviceModuleExtension getTargetDeviceModuleExtension();
}

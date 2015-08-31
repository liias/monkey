package ee.edio.garmin.configuration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.UnnamedConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import ee.edio.garmin.runconfig.MonkeySettingsEditor;
import ee.edio.garmin.runconfig.TargetDevice;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class TargetDeviceConfigurable implements UnnamedConfigurable {
  private final Project myProject;
  private ComboBox myComboBox;
  private JPanel myPanel = new JPanel(new GridBagLayout());

  public TargetDeviceConfigurable(Project project) {
    myProject = project;
    myComboBox = new ComboBox();

    for (TargetDevice device : MonkeySettingsEditor.ALL_DEVICES) {
      //noinspection unchecked
      myComboBox.addItem(device);
    }

    myComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final Object selectedItem = myComboBox.getSelectedItem();
        final TargetDevice targetDevice = selectedItem instanceof TargetDevice ? (TargetDevice) selectedItem : null;
        getTargetDeviceModuleExtension().setTargetDevice(targetDevice);
      }
    });

    JLabel label = new JLabel("Target Device");
    label.setLabelFor(myComboBox);
    myPanel.add(label,
        new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(12, 6, 12, 0), 0, 0));
    myPanel.add(myComboBox,
        new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(6, 6, 12, 0), 0, 0));
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    return myPanel;
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
    myComboBox.setSelectedItem(getTargetDeviceModuleExtension().getTargetDevice());
  }

  @Override
  public void disposeUIResources() {
    myPanel = null;
    myComboBox = null;
  }

  @NotNull
  public abstract TargetDeviceModuleExtension getTargetDeviceModuleExtension();
}

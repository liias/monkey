package ee.edio.garmin.configuration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.UnnamedConfigurable;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationState;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TargetDeviceConfigurable implements UnnamedConfigurable {
  public static final Key<String> TARGET_DEVICE = new Key<>("TARGET_DEVICE");
  private final ModuleConfigurationState moduleConfigurationState;
  private ComboBox myComboBox;
  private JPanel myPanel = new JPanel(new GridBagLayout());
  private String targetDevice;

  public TargetDeviceConfigurable(final ModuleConfigurationState moduleConfigurationState) {
    this.moduleConfigurationState = moduleConfigurationState;
    myComboBox = new ComboBox();
    myComboBox.addItem("vivoactive");

    myComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final Object selectedItem = myComboBox.getSelectedItem();
        targetDevice = selectedItem.toString();
        moduleConfigurationState.putUserData(TARGET_DEVICE, targetDevice);
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
    final String userData = moduleConfigurationState.getUserData(TARGET_DEVICE);
    if (userData == null || targetDevice == null) {
      return true;
    }
    return !userData.equals(targetDevice);
  }

  @Override
  public void apply() throws ConfigurationException {

  }

  @Override
  public void reset() {
    myComboBox.setSelectedIndex(0);
  }

  @Override
  public void disposeUIResources() {
    myPanel = null;
    myComboBox = null;
  }
}

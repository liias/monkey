package io.github.liias.monkey.project.runconfig;

import com.google.common.collect.ImmutableList;
import com.intellij.application.options.ModulesComboBox;
import com.intellij.execution.ui.CommonProgramParametersPanel;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.ListCellRendererWrapper;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.util.ui.UIUtil;
import io.github.liias.monkey.project.module.MonkeyModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class MonkeySettingsEditor extends SettingsEditor<MonkeyModuleBasedConfiguration> implements PanelWithAnchor {

  // TODO: populate from sdk devices.xml
  public static final List<TargetDevice> ALL_DEVICES = new ImmutableList.Builder<TargetDevice>()
    .add(TargetDevice.SQUARE_WATCH)
    .add(TargetDevice.ROUND_WATCH)
    .add(TargetDevice.SEMI_ROUND_WATCH)
    .add(TargetDevice.TALL_WATCH)
    .add(new TargetDevice("d2bravo", "D2™ Bravo"))
    .add(new TargetDevice("edge_520", "Edge 520"))
    .add(new TargetDevice("edge_1000", "Edge 1000 / Explore"))
    .add(new TargetDevice("epix", "epix®"))
    .add(new TargetDevice("fenix3", "fēnix™ 3 / tactix™ Bravo / quatix™ 3"))
    .add(new TargetDevice("fenix3_hr", "fēnix™ 3 HR"))
    .add(new TargetDevice("fr230", "Forerunner® 230"))
    .add(new TargetDevice("fr630", "Forerunner® 630"))
    .add(new TargetDevice("fr920xt", "Forerunner® 920XT"))
    .add(new TargetDevice("vivoactive", "vívoactive"))
    .add(new TargetDevice("vivoactive_hr", "vívoactive HR"))
    .build();

  private final Project project;
  private LabeledComponent<JComboBox<TargetDevice>> targetDevice;
  private JComponent anchor;
  private CommonProgramParametersPanel commonProgramParameters;
  private LabeledComponent<ModulesComboBox> moduleComponent;
  private JComponent wholePanel;

  public MonkeySettingsEditor(final Project project) {
    this.project = project;
    this.anchor = UIUtil.mergeComponentsWithAnchor(commonProgramParameters);
    ModulesComboBox modulesComboBox = moduleComponent.getComponent();
    modulesComboBox.fillModules(project, MonkeyModuleType.getInstance());
    fillTargetDevices();
  }

  public List<TargetDevice> getAllDevices() {
    return ALL_DEVICES;
  }

  private static class TargetDeviceListRenderer extends ListCellRendererWrapper<TargetDevice> {
    public TargetDeviceListRenderer() {
      super();
    }

    @Override
    public void customize(JList list, TargetDevice device, int index, boolean selected, boolean hasFocus) {
      if (device != null) {
        //setIcon(ModuleType.get(value).getIcon());
        setText(device.getName());
      } else {
        setText("<unspecified>");
        setIcon(null);
      }
    }
  }

  private void fillTargetDevices() {
    final JComboBox<TargetDevice> comboBox = targetDevice.getComponent();
    comboBox.removeAllItems();
    //noinspection unchecked
    comboBox.setRenderer(new TargetDeviceListRenderer());

    getAllDevices().forEach(comboBox::addItem);

    comboBox.addActionListener(e -> {
      final TargetDevice selectedItem = (TargetDevice) comboBox.getSelectedItem();
      // TODO: needs rebuilding module or something else?
    });
  }

  @Override
  public JComponent getAnchor() {
    return this.anchor;
  }

  @Override
  public void setAnchor(@Nullable JComponent anchor) {
    this.anchor = anchor;
    commonProgramParameters.setAnchor(anchor);
    targetDevice.setAnchor(anchor);
  }

  @Override
  protected void resetEditorFrom(MonkeyModuleBasedConfiguration configuration) {
    commonProgramParameters.reset(configuration);
    moduleComponent.getComponent().setSelectedModule(configuration.getConfigurationModule().getModule());

    final String targetDeviceId = configuration.getTargetDeviceId();
    if (targetDeviceId != null) {
      final TargetDevice selectedTargetDevice = new TargetDevice();
      selectedTargetDevice.setId(targetDeviceId);
      this.targetDevice.getComponent().setSelectedItem(selectedTargetDevice);
    } else {
      this.targetDevice.getComponent().setSelectedIndex(0);
    }
  }

  @Override
  protected void applyEditorTo(MonkeyModuleBasedConfiguration configuration) throws ConfigurationException {
    commonProgramParameters.applyTo(configuration);
    configuration.setModule(moduleComponent.getComponent().getSelectedModule());
    final TargetDevice selectedTargetDevice = (TargetDevice) this.targetDevice.getComponent().getSelectedItem();
    configuration.setTargetDeviceId(selectedTargetDevice.getId());
  }

  @NotNull
  @Override
  protected JComponent createEditor() {
    return wholePanel;
  }
}

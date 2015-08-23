package ee.edio.garmin.runconfig;

import com.intellij.application.options.ModulesComboBox;
import com.intellij.execution.ui.CommonProgramParametersPanel;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.util.ui.UIUtil;
import ee.edio.garmin.MonkeyCModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MCSettingsEditor extends SettingsEditor<MCModuleBasedConfiguration> implements PanelWithAnchor {
  private final Project project;
  private LabeledComponent<JComboBox> targetDevice;
  private JComponent anchor;

  private CommonProgramParametersPanel commonProgramParameters;
  //private LabeledComponent<TextFieldWithBrowseButton> myJarPathComponent;
  private LabeledComponent<ModulesComboBox> moduleComponent;
  private JComponent wholePanel;

  public MCSettingsEditor(final Project project) {
    this.project = project;
    //this.moduleSelector = new MCConfigurationModuleSelector(project, this.module.getComponent());
    //this.commonProgramParameters.setModuleContext(moduleSelector.getModule());
    /*this.module.getComponent().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        commonProgramParameters.setModuleContext(moduleSelector.getModule());
      }
    });*/
    //myAnchor = UIUtil.mergeComponentsWithAnchor(myMainClass, myCommonProgramParameters, myAlternativeJREPanel, myModule);
    this.anchor = UIUtil.mergeComponentsWithAnchor(commonProgramParameters);
    ModulesComboBox modulesComboBox = moduleComponent.getComponent();
    //modulesComboBox.allowEmptySelection("<whole project>");
    modulesComboBox.fillModules(project, MonkeyCModuleType.getInstance());
  }

/*  private void createUIComponents() {
    myJarPathComponent = new LabeledComponent<>();
    TextFieldWithBrowseButton textFieldWithBrowseButton = new TextFieldWithBrowseButton();
    textFieldWithBrowseButton.addBrowseFolderListener("Choose JAR File", null, this.project,
        new FileChooserDescriptor(false, false, true, true, false, false));
    myJarPathComponent.setComponent(textFieldWithBrowseButton);
  }*/

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
  protected void resetEditorFrom(MCModuleBasedConfiguration configuration) {
    commonProgramParameters.reset(configuration);
    moduleComponent.getComponent().setSelectedModule(configuration.getConfigurationModule().getModule());

    // TODO: target device? from configuration.getTargetDevice()
    //targetDevice.setText("");
    //targetDevice.getComponent().setSelectedIndex(0);
  }

  @Override
  protected void applyEditorTo(MCModuleBasedConfiguration configuration) throws ConfigurationException {
    commonProgramParameters.applyTo(configuration);
    configuration.setModule(moduleComponent.getComponent().getSelectedModule());

    final Object selectedItem = this.targetDevice.getComponent().getSelectedItem();
    String targetDeviceId = "fenix3";
    if (selectedItem != null) {
      targetDeviceId = selectedItem.toString();
    }
    final TargetDevice targetDevice = new TargetDevice();
    targetDevice.setId(targetDeviceId);

    configuration.setTargetDevice(targetDevice);
    //moduleSelector.applyTo(configuration);
    //final String className = getMainClassField().getText();
    //final PsiClass aClass = myModuleSelector.findClass(className);
    //configuration.MAIN_CLASS_NAME = aClass != null ? JavaExecutionUtil.getRuntimeQualifiedName(aClass) : className;
    //configuration.ALTERNATIVE_JRE_PATH = myAlternativeJREPanel.getPath();
    //configuration.ALTERNATIVE_JRE_PATH_ENABLED = myAlternativeJREPanel.isPathEnabled();
    //configuration.ENABLE_SWING_INSPECTOR = (myVersionDetector.isJre50Configured(configuration) || myVersionDetector.isModuleJre50Configured(configuration)) && myShowSwingInspectorCheckbox.isSelected();

/*    commonProgramParameters.applyTo(configuration);
    configuration.setAlternativeJrePath(myAlternativeJREPanel.getPath());
    configuration.setAlternativeJrePathEnabled(myAlternativeJREPanel.isPathEnabled());
    configuration.setJarPath(FileUtil.toSystemIndependentName(myJarPathComponent.getComponent().getText()));
    configuration.setModule(moduleComponent.getComponent().getSelectedModule());*/
  }

  @NotNull
  @Override
  protected JComponent createEditor() {
    return wholePanel;
  }
}

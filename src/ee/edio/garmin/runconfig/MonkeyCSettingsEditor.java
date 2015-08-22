package ee.edio.garmin.runconfig;

import com.intellij.execution.ui.AlternativeJREPanel;
import com.intellij.execution.ui.CommonProgramParametersPanel;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MonkeyCSettingsEditor extends SettingsEditor<MCModuleBasedConfiguration> implements PanelWithAnchor {
  private final Project project;
  private JComponent anchor;

  private CommonProgramParametersPanel myCommonProgramParameters;
  private LabeledComponent<TextFieldWithBrowseButton> myJarPathComponent;
//  private LabeledComponent<ModulesComboBox> myModuleComponent;
//  private JPanel myWholePanel;

  private AlternativeJREPanel myAlternativeJREPanel;

  public MonkeyCSettingsEditor(final Project project) {
    this.project = project;
    this.anchor = UIUtil.mergeComponentsWithAnchor(myJarPathComponent, myCommonProgramParameters, myAlternativeJREPanel);
/*    ModulesComboBox modulesComboBox = myModuleComponent.getComponent();
    modulesComboBox.allowEmptySelection("<whole project>");
    modulesComboBox.fillModules(project);*/
  }

  private void createUIComponents() {
    myJarPathComponent = new LabeledComponent<>();
    TextFieldWithBrowseButton textFieldWithBrowseButton = new TextFieldWithBrowseButton();
    textFieldWithBrowseButton.addBrowseFolderListener("Choose JAR File", null, this.project,
        new FileChooserDescriptor(false, false, true, true, false, false));
    myJarPathComponent.setComponent(textFieldWithBrowseButton);
  }

  @Override
  public JComponent getAnchor() {
    return this.anchor;
  }

  @Override
  public void setAnchor(JComponent anchor) {
    this.anchor = anchor;
    myCommonProgramParameters.setAnchor(anchor);
    myAlternativeJREPanel.setAnchor(anchor);
    myJarPathComponent.setAnchor(anchor);
  }

  @Override
  protected void resetEditorFrom(MCModuleBasedConfiguration configuration) {
/*    myCommonProgramParameters.reset(configuration);
    myJarPathComponent.getComponent().setText(FileUtil.toSystemDependentName(configuration.getJarPath()));
    myAlternativeJREPanel.init(configuration.getAlternativeJrePath(), configuration.isAlternativeJrePathEnabled());
    myModuleComponent.getComponent().setSelectedModule(configuration.getModule());*/
  }

  @Override
  protected void applyEditorTo(MCModuleBasedConfiguration configuration) throws ConfigurationException {
/*    myCommonProgramParameters.applyTo(configuration);
    configuration.setAlternativeJrePath(myAlternativeJREPanel.getPath());
    configuration.setAlternativeJrePathEnabled(myAlternativeJREPanel.isPathEnabled());
    configuration.setJarPath(FileUtil.toSystemIndependentName(myJarPathComponent.getComponent().getText()));
    configuration.setModule(myModuleComponent.getComponent().getSelectedModule());*/
  }

  @NotNull
  @Override
  protected JComponent createEditor() {
//    return myWholePanel;
    return new JPanel();
  }
}

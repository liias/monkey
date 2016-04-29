package io.github.liias.monkey.ide.actions.appsettings;

import com.google.common.base.Objects;
import com.intellij.application.options.ModulesComboBox;
import com.intellij.openapi.compiler.CompilerPaths;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.components.OnOffButton;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import io.github.liias.monkey.ide.actions.appsettings.AppSettingsManager.SettingsAndLanguages.Setting;
import io.github.liias.monkey.project.module.MonkeyModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.liias.monkey.ide.actions.appsettings.AppSettingsManager.SettingsAndLanguages.Setting.Type;

public class AppSettingsForm {
  private JPanel panel;
  private JPanel settingsPanel;
  private ModulesComboBox modulesComboBox;

  @Nullable
  private final Project project;
  private AppSettingsDialog appSettingsDialog;
  private AppSettingsManager appSettingsManager;

  private Map<String, JComponent> fieldsBySettingKey;

  protected AppSettingsForm(@Nullable Project project, @NotNull AppSettingsDialog appSettingsDialog) {
    this.project = project;
    this.appSettingsDialog = appSettingsDialog;
    this.fieldsBySettingKey = new HashMap<>();
  }

  // TODO: place custom component creation code here
  private void createUIComponents() {
    modulesComboBox = new ModulesComboBox();
    if (project != null) {
      modulesComboBox.fillModules(project, MonkeyModuleType.getInstance());
    }

    modulesComboBox.addActionListener(e -> {
      final Module selectedModule = modulesComboBox.getSelectedModule();
      if (selectedModule != null) {
        VirtualFile moduleOutputDir = CompilerPaths.getModuleOutputDirectory(selectedModule, false);
        String projectName = selectedModule.getProject().getName();
        String settingsFilename = projectName + "-settings.json";

        VirtualFile settingsFile = moduleOutputDir.findChild(settingsFilename);
        this.appSettingsManager = new AppSettingsManager(selectedModule, settingsFile);
        AppSettingsManager.SettingsAndLanguages settingsAndLanguages = appSettingsManager.getSettingsAndLanguages();
        fillSettings(settingsAndLanguages.getSettings(), settingsAndLanguages.getLanguages());
      }
    });
  }

  private void fillSettings(List<Setting> settings, Map<String, Map<String, String>> languages) {
    GridLayoutManager layout = new GridLayoutManager(settings.size() + 1, 2);
    settingsPanel.setLayout(layout);
    GridConstraints gc = new GridConstraints();
    gc.setAnchor(GridConstraints.ANCHOR_WEST);
    gc.setVSizePolicy(GridConstraints.SIZEPOLICY_FIXED);

    Map<String, String> translations = languages.get("valyrian");

    for (Setting setting : settings) {
      gc.setColumn(0);
      gc.setFill(GridConstraints.FILL_NONE);
      gc.setHSizePolicy(GridConstraints.SIZEPOLICY_FIXED);
      String displayName = Objects.firstNonNull(translations.get(setting.configTitle), setting.configTitle);
      JBLabel label = new JBLabel(displayName);
      settingsPanel.add(label, gc);

      gc.setColumn(1);
      int fill = setting.configType == Type.BOOLEAN ? GridConstraints.FILL_NONE : GridConstraints.FILL_HORIZONTAL;
      gc.setFill(fill);
      gc.setHSizePolicy(GridConstraints.SIZEPOLICY_WANT_GROW);

      JComponent settingValueComponent = getSettingComponent(setting);
      settingsPanel.add(settingValueComponent, gc);
      label.setLabelFor(settingValueComponent);
      fieldsBySettingKey.put(setting.getKey(), settingValueComponent);

      gc.setRow(gc.getRow() + 1);
    }
    gc.setColumn(0);
    gc.setVSizePolicy(GridConstraints.FILL_VERTICAL);
    gc.setHSizePolicy(GridConstraints.SIZEPOLICY_FIXED);
    settingsPanel.add(new JBLabel(""), gc);

    appSettingsDialog.pack();
  }

  // TODO: AppSettingsManager.getComponentValue() needs to support these types - do something else
  // Also, currently this doesn't set correct type for some values using text field, i.e floats
  private JComponent getSettingComponent(Setting setting) {
    Object defaultValue = setting.defaultValue;
    String value = defaultValue != null ? defaultValue.toString() : "";
    switch (setting.configType) {
      case ALPHA_NUMERIC:
        return new JBTextField(value);
      case NUMERIC:
        Setting.ValueType valueType = setting.valueType;
        if (valueType == Setting.ValueType.NUMBER) {
          JSpinner jSpinner = new JSpinner();
          jSpinner.setValue(Integer.valueOf(value));
          return jSpinner;
        } else {
          return new JBTextField(value);
        }
      case BOOLEAN:
        OnOffButton onOffButton = new OnOffButton();
        onOffButton.setSelected(Boolean.valueOf(value));
        return onOffButton;
      case DATE:
        return new JBTextField(value);
      case EMAIL:
        return new JBTextField(value);
      case LIST:
        return new JBTextField(value);
      case PASSWORD:
        JBPasswordField jbPasswordField = new JBPasswordField();
        jbPasswordField.setText(value);
        return jbPasswordField;
      case PHONE:
        return new JBTextField(value);
      case URL:
        return new JBTextField(value);
      default:
        return new JBTextField(value);
    }
  }

  public JPanel getPanel() {
    return panel;
  }

  public void sendSettingsToSimulator() {
    if (appSettingsManager != null) {
      appSettingsManager.sendToSim(fieldsBySettingKey);
    }
  }
}

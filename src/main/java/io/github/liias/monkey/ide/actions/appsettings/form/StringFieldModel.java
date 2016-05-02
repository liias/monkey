package io.github.liias.monkey.ide.actions.appsettings.form;

import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import javax.swing.*;

// config type: alphaNumeric, phone, email, url, password
public class StringFieldModel implements FieldModel<String> {
  private Setting.ConfigType configType;
  private final JTextField component;

  public StringFieldModel(Setting.ConfigType configType, String value) {
    this.configType = configType;
    this.component = createComponent(value);
  }

  public JTextField createComponent(String value) {
    if (configType == Setting.ConfigType.PASSWORD) {
      JBPasswordField jbPasswordField = new JBPasswordField();
      jbPasswordField.setText(value);
      return jbPasswordField;
    }
    return new JBTextField(value);
  }

  @Override
  public JComponent getComponent() {
    return component;
  }

  @Override
  public void setValue(String value) {
    component.setText(value);
  }

  @Override
  public String getValue() {
    return component.getText();
  }
}

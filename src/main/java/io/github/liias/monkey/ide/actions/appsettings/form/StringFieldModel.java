package io.github.liias.monkey.ide.actions.appsettings.form;

import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import javax.swing.*;
import java.util.Map;

// config type: alphaNumeric, phone, email, url, password
public class StringFieldModel extends FieldModel<String> {
  private final JTextField component;

  public StringFieldModel(Setting setting, Map<String, String> translations) {
    super(setting, translations);
    this.component = createComponent();
  }

  public JTextField createComponent() {
    Setting.ConfigType configType = setting.getConfigType();
    String value = setting.getValueAsString();
    JTextField jTextField;
    if (configType == Setting.ConfigType.PASSWORD) {
      JBPasswordField jbPasswordField = new JBPasswordField();
      jbPasswordField.setText(value);
      jTextField = jbPasswordField;
    } else {
      JBTextField jbTextField = new JBTextField(value);
      jTextField = jbTextField;
    }

    applyGenericProperties(jTextField);
    return jTextField;
  }

  @Override
  public JComponent getComponent() {
    return component;
  }

  @Override
  public String getValue() {
    return component.getText();
  }
}

package io.github.liias.monkey.ide.actions.appsettings.form;

import com.intellij.ui.components.JBTextField;
import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import javax.swing.*;
import java.util.Map;
import java.util.Objects;

// config type: numeric
public class FloatFieldModel extends FieldModel<Float> {
  private final JBTextField component;

  public FloatFieldModel(Setting setting, Map<String, String> translations) {
    super(setting, translations);
    this.component = createComponent();
  }

  public JBTextField createComponent() {
    String valueAsString = Objects.toString(setting.getValueAsFloat(), null);
    JBTextField jbTextField = new JBTextField(valueAsString);

    applyGenericProperties(jbTextField);
    return jbTextField;
  }

  @Override
  public JComponent getComponent() {
    return component;
  }

  @Override
  public Float getValue() {
    return Float.valueOf(component.getText());
  }
}

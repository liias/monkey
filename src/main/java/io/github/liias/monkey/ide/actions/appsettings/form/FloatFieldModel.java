package io.github.liias.monkey.ide.actions.appsettings.form;

import com.intellij.ui.components.JBTextField;
import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import javax.swing.*;
import java.util.Objects;

// config type: numeric
public class FloatFieldModel implements FieldModel<Float> {
  private final Setting.ConfigType configType;
  private final JBTextField component;

  public FloatFieldModel(Setting.ConfigType configType, Float value) {
    this.configType = configType;
    this.component = createComponent(value);
  }

  public JBTextField createComponent(Float value) {
    String valueAsString = Objects.toString(value, null);
    return new JBTextField(valueAsString);
  }

  @Override
  public JComponent getComponent() {
    return component;
  }

  @Override
  public void setValue(Float value) {
    component.setText(value.toString());
  }

  @Override
  public Float getValue() {
    String value = component.getText();
    return Float.valueOf(value);
  }
}

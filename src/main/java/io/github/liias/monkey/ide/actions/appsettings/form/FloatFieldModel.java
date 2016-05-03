package io.github.liias.monkey.ide.actions.appsettings.form;

import com.intellij.ui.components.JBTextField;
import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import javax.swing.*;
import java.util.Objects;

// config type: numeric
public class FloatFieldModel implements FieldModel<Float> {
  private final Setting.ConfigType configType;
  private final JBTextField component;

  public FloatFieldModel(Setting setting) {
    this.configType = setting.getConfigType();
    this.component = createComponent(setting.getValueAsFloat());
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
  }

  @Override
  public Float getValue() {
    return Float.valueOf(component.getText());
  }
}

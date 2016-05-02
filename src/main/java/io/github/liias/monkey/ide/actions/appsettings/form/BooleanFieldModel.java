package io.github.liias.monkey.ide.actions.appsettings.form;

import com.intellij.ui.components.OnOffButton;
import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import javax.swing.*;

// config type: boolean
public class BooleanFieldModel implements FieldModel<Boolean> {

  private final Setting.ConfigType configType;
  private final OnOffButton component;

  public BooleanFieldModel(Setting.ConfigType configType, Boolean value) {
    this.configType = configType;
    this.component = createComponent(value);
  }

  public OnOffButton createComponent(boolean value) {
    OnOffButton onOffButton = new OnOffButton();
    onOffButton.setSelected(value);
    return onOffButton;
  }

  @Override
  public JComponent getComponent() {
    return component;
  }

  @Override
  public void setValue(Boolean value) {

  }

  @Override
  public Boolean getValue() {
    return component.isSelected();
  }
}

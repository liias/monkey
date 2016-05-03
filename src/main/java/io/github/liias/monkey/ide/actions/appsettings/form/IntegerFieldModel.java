package io.github.liias.monkey.ide.actions.appsettings.form;

import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import javax.swing.*;

// config type: numeric, list, date
public class IntegerFieldModel implements FieldModel<Integer> {

  private Setting.ConfigType configType;
  private JComponent component;

  public IntegerFieldModel(Setting setting) {
    this.configType = setting.getConfigType();
    this.component = createComponent(setting.getValueAsInteger());
  }

  public JSpinner createComponent(Integer value) {
    if (value == null) {
      value = 0; // can't support null here - can it be null for integers??
    }
    if (configType == Setting.ConfigType.NUMERIC) {
      return new JSpinner(new SpinnerNumberModel(value, null, null, 1));
    } else if (configType == Setting.ConfigType.DATE) {
      // TODO: use SpinnerDateModel
      return new JSpinner(new SpinnerNumberModel(value, null, null, 1));
    } else if (configType == Setting.ConfigType.LIST) {
      // TODO: use SpinnerListModel
      return new JSpinner(new SpinnerNumberModel(value, null, null, 1));
    }
    throw new IllegalArgumentException("unknown config type " + configType);
  }

  @Override
  public JComponent getComponent() {
    return component;
  }

  @Override
  public void setValue(Integer value) {

  }

  @Override
  public Integer getValue() {
    if (configType == Setting.ConfigType.NUMERIC) {
      JSpinner jSpinner = (JSpinner) component;
      SpinnerNumberModel model = (SpinnerNumberModel) jSpinner.getModel();
      return model.getNumber().intValue();
    } else if (configType == Setting.ConfigType.DATE) {
      JSpinner jSpinner = (JSpinner) component;
      SpinnerNumberModel model = (SpinnerNumberModel) jSpinner.getModel();
      return model.getNumber().intValue();
    } else if (configType == Setting.ConfigType.LIST) {
      JSpinner jSpinner = (JSpinner) component;
      SpinnerNumberModel model = (SpinnerNumberModel) jSpinner.getModel();
      return model.getNumber().intValue();
    }
    throw new IllegalArgumentException("unknown config type " + configType);
  }
}

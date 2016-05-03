package io.github.liias.monkey.ide.actions.appsettings.form;

import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import javax.swing.*;
import java.util.Map;

// config type: numeric, list, date
public class IntegerFieldModel extends FieldModel<Integer> {
  private JComponent component;

  public IntegerFieldModel(Setting setting, Map<String, String> translations) {
    super(setting, translations);
    this.component = createComponent();
  }

  public JSpinner createComponent() {
    Integer value = setting.getValueAsInteger();
    if (value == null) {
      value = 0; // can't support null here - can it be null for integers??
    }
    Setting.ConfigType configType = setting.getConfigType();

    JSpinner jSpinner = new JSpinner(new SpinnerNumberModel(value, null, null, 1));
    applyGenericProperties(jSpinner);

    if (configType == Setting.ConfigType.NUMERIC) {
      return jSpinner;
    } else if (configType == Setting.ConfigType.DATE) {
      // TODO: use SpinnerDateModel
      return jSpinner;
    } else if (configType == Setting.ConfigType.LIST) {
      // TODO: use SpinnerListModel
      return jSpinner;
    }
    throw new IllegalArgumentException("unknown config type " + configType);
  }

  @Override
  public JComponent getComponent() {
    return component;
  }

  @Override
  public Integer getValue() {
    Setting.ConfigType configType = setting.getConfigType();

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

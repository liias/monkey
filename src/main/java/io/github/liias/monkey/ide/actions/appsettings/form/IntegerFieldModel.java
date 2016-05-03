package io.github.liias.monkey.ide.actions.appsettings.form;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.CollectionComboBoxModel;
import com.intellij.util.text.DateFormatUtil;
import com.michaelbaranov.microba.calendar.DatePicker;
import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import javax.swing.*;
import java.text.DateFormat;
import java.time.ZoneOffset;
import java.util.*;

// config type: numeric, list, date
public class IntegerFieldModel extends FieldModel<Integer> {
  private JComponent component;

  public IntegerFieldModel(Setting setting, Map<String, String> translations) {
    super(setting, translations);
    this.component = createComponent();
  }

  public JComponent createComponent() {
    Integer value = setting.getValueAsInteger();
    if (value == null) {
      value = 0; // can't support null here - can it be null for integers??
    }
    Setting.ConfigType configType = setting.getConfigType();

    JComponent component;

    if (configType == Setting.ConfigType.NUMERIC) {
      component = new JSpinner(new SpinnerNumberModel(value, null, null, 1));
    } else if (configType == Setting.ConfigType.DATE) {
      // using long, as int wouldn't fit after multiplying with 1000
      Date date = new Date(value.longValue() * 1000L);
      DateFormat dateFormat = DateFormatUtil.getDateFormat().getDelegate();
      TimeZone timeZoneUtc = TimeZone.getTimeZone(ZoneOffset.UTC);
      dateFormat.setTimeZone(timeZoneUtc);
      DatePicker datePicker = new DatePicker(date, dateFormat, Locale.getDefault(), timeZoneUtc);
      datePicker.setKeepTime(false);
      component = datePicker;
      // or use JSpinner with SpinnerDateModel
    } else if (configType == Setting.ConfigType.LIST) {
      List<Setting.Option> configOptions = setting.getConfigOptions();
      List<ComboOption> comboOptions = new ArrayList<>();
      for (Setting.Option configOption : configOptions) {
        String optionLabel = getTranslated(configOption.getDisplay());
        int optionValue = configOption.getValue();
        ComboOption comboOption = new ComboOption(optionValue, optionLabel);
        comboOptions.add(comboOption);
      }
      Optional<ComboOption> selectedOption = Optional.empty();
      if (setting.getValueAsInteger() != null) {
        selectedOption = comboOptions.stream().filter(o -> setting.getValueAsInteger().equals(o.getValue())).findFirst();
      }

      CollectionComboBoxModel<ComboOption> comboBoxModel = new CollectionComboBoxModel<>(comboOptions);
      if (selectedOption.isPresent()) {
        comboBoxModel.setSelectedItem(selectedOption.get());
      }

      component = new ComboBox(comboBoxModel);
    } else {
      throw new IllegalArgumentException("unknown config type " + configType);
    }

    applyGenericProperties(component);
    return component;
  }

  public static class ComboOption {
    private final int value;
    private final String translatedLabel;

    public ComboOption(int value, String translatedLabel) {
      this.value = value;
      this.translatedLabel = translatedLabel;
    }

    public int getValue() {
      return value;
    }

    public String getTranslatedLabel() {
      return translatedLabel;
    }

    @Override
    public String toString() {
      return translatedLabel;
    }
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
      DatePicker datePicker = (DatePicker) component;
      Date date = datePicker.getDate();
      Long ms = date.getTime() / 1000;
      return ms.intValue();
    } else if (configType == Setting.ConfigType.LIST) {
      ComboBox comboBox = (ComboBox) component;
      ComboOption comboOption = (ComboOption) comboBox.getModel().getSelectedItem();
      if (comboOption == null) {
        return null;
      }
      return comboOption.getValue();
    }
    throw new IllegalArgumentException("unknown config type " + configType);
  }
}

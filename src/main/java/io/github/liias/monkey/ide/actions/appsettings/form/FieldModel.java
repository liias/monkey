package io.github.liias.monkey.ide.actions.appsettings.form;

import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import javax.swing.*;

/*
valueType configType
--------------------
string	  alphaNumeric, phone, email, url, password
number	  numeric, list, date
float	    numeric
boolean	  boolean
*/
public interface FieldModel<V> {

  static FieldModel create(Setting setting) {
    Setting.ConfigType configType = setting.getConfigType();
    switch (setting.getValueType()) {
      case BOOLEAN:
        // config type: boolean
        return new BooleanFieldModel(configType, setting.getValueAsBoolean());
      case FLOAT:
        // config type: numeric
        return new FloatFieldModel(configType, setting.getValueAsFloat());
      case NUMBER:
        // config type: numeric, list, date
        return new IntegerFieldModel(configType, setting.getValueAsInteger());
      case STRING:
        // config type: alphaNumeric, phone, email, url, password
        return new StringFieldModel(configType, setting.getValueAsString());
    }
    throw new IllegalArgumentException("unknown setting value type: " + setting.getValueType());
  }

  JComponent getComponent();

  //TODO: perhaps not needed
  void setValue(V value);

  V getValue();
}

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
    switch (setting.getValueType()) {
      case BOOLEAN:
        return new BooleanFieldModel(setting);
      case FLOAT:
        return new FloatFieldModel(setting);
      case NUMBER:
        return new IntegerFieldModel(setting);
      case STRING:
        return new StringFieldModel(setting);
    }
    throw new IllegalArgumentException("unknown setting value type: " + setting.getValueType());
  }

  JComponent getComponent();

  //TODO: perhaps not needed
  void setValue(V value);

  V getValue();
}

package io.github.liias.monkey.ide.actions.appsettings;

import com.google.gson.*;
import io.github.liias.monkey.ide.actions.appsettings.json.Setting;

import java.lang.reflect.Type;

public class SettingDeserializer implements JsonDeserializer<Setting> {

  @Override
  public Setting deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    Setting setting = new Gson().fromJson(json, Setting.class); // use default Gson object

    final JsonObject jsonObject = json.getAsJsonObject();
    JsonElement defaultValueElement = jsonObject.get("defaultValue");
    Object defaultValue = getDefaultValueConsideringValueType(setting.getValueType(), defaultValueElement);
    setting.setDefaultValue(defaultValue);
    setting.setValue(defaultValue);
    return setting;
  }

  private static Object getDefaultValueConsideringValueType(Setting.ValueType valueType, JsonElement defaultValueElement) {
    switch (valueType) {
      case BOOLEAN:
        return defaultValueElement.getAsBoolean();
      case STRING:
        return defaultValueElement.getAsString();
      case NUMBER:
        return defaultValueElement.getAsInt();
      case FLOAT:
        return defaultValueElement.getAsFloat();
    }
    throw new IllegalArgumentException("unknown valueType");
  }
}
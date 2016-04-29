package io.github.liias.monkey.ide.actions.appsettings.json;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Setting {
  @NotNull
  String key;                  // "number_prop",

  @NotNull
  ValueType valueType;         // "number",

  @Nullable
  Object defaultValue;         // 2,

  @NotNull
  String configTitle;          // "number_title",

  @Nullable
  String configPrompt;         // null,

  @Nullable
  String configError;          // null,

  @NotNull
  ConfigType configType;       // "numeric",

  boolean configReadonly;      // false,

  boolean configRequired;      // false,

  @Nullable
  List<Option> configOptions;  // null,

  @Nullable
  Number configMin;            // null,

  @Nullable
  Number configMax;            // null,

  @Nullable
  Integer configMaxLength;     // null

  public String getKey() {
    return key;
  }

  public ValueType getValueType() {
    return valueType;
  }

  public Object getDefaultValue() {
    return defaultValue;
  }

  public String getConfigTitle() {
    return configTitle;
  }

  public String getConfigPrompt() {
    return configPrompt;
  }

  public String getConfigError() {
    return configError;
  }

  public ConfigType getConfigType() {
    return configType;
  }

  public boolean isConfigReadonly() {
    return configReadonly;
  }

  public boolean isConfigRequired() {
    return configRequired;
  }

  public List<Option> getConfigOptions() {
    return configOptions;
  }

  public Number getConfigMin() {
    return configMin;
  }

  public Number getConfigMax() {
    return configMax;
  }

  public Integer getConfigMaxLength() {
    return configMaxLength;
  }

  public void setDefaultValue(Object defaultValue) {
    this.defaultValue = defaultValue;
  }

  public void setValue(Object value) {
    defaultValue = value;
  }

  public Object getValue() {
    return defaultValue;
  }

  public enum ValueType {
    @SerializedName("string")
    STRING,

    // integer really
    @SerializedName("number")
    NUMBER,

    @SerializedName("float")
    FLOAT,

    @SerializedName("boolean")
    BOOLEAN
  }

  /*
  valueType configType
  --------------------
  string	  alphaNumeric, phone, email, url, password
  number	  list, numeric, date
  float	    numeric
  boolean	  boolean
*/
  public enum ConfigType {
    @SerializedName("alphaNumeric")
    ALPHA_NUMERIC, // value type: STRING

    @SerializedName("boolean")
    BOOLEAN, // value type: BOOLEAN

    @SerializedName("date")
    DATE, // value type: NUMBER

    @SerializedName("email")
    EMAIL, // value type: STRING

    @SerializedName("list")
    LIST, // value type: NUMBER

    @SerializedName("numeric")
    NUMERIC, // value type: NUMBER

    @SerializedName("password")
    PASSWORD, // value type: STRING

    @SerializedName("phone")
    PHONE, // value type: STRING

    @SerializedName("url")
    URL // value type: STRING
  }

  public static class Option {
    String display;
    int value;
  }
}

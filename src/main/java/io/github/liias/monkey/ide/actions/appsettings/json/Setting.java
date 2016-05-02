package io.github.liias.monkey.ide.actions.appsettings.json;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings({"unused", "NullableProblems"})
public class Setting {
  @NotNull
  private String key;

  @NotNull
  private ValueType valueType;

  @Nullable
  private Object defaultValue;

  // not coming from Json
  @Nullable
  private Object value;

  // translatable string id
  @NotNull
  private String configTitle;

  // message to display when prompting the user to set the value. Not used for e.g readonly and boolean settings.
  @Nullable
  private String configPrompt;

  @Nullable
  private String configError;

  @NotNull
  private ConfigType configType;

  // This attribute is valid for all types except list and password. Default: false
  private boolean configReadonly;

  private boolean configRequired;

  @Nullable
  private List<Option> configOptions;

  @Nullable
  private Number configMin;

  @Nullable
  private Number configMax;

  @Nullable
  private Integer configMaxLength;

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
    this.value = value;
  }

  public Object getValue() {
    return value;
  }

  public Boolean getValueAsBoolean() {
    return getValueAs(Boolean.class);
  }

  public Float getValueAsFloat() {
    return getValueAs(Float.class);
  }

  public Integer getValueAsInteger() {
    return getValueAs(Integer.class);
  }

  public String getValueAsString() {
    return getValueAs(String.class);
  }

  @SuppressWarnings("unchecked")
  public <T> T getValueAs(Class<T> clazz) {
    return (T) getValue();
  }

  @SuppressWarnings("unchecked")
  public <T> T getDefaultValueAs(Class<T> clazz) {
    return (T) getDefaultValue();
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
    NUMERIC, // value type: NUMBER(integer really) or FLOAT

    @SerializedName("password")
    PASSWORD, // value type: STRING

    @SerializedName("phone")
    PHONE, // value type: STRING

    @SerializedName("url")
    URL // value type: STRING
  }

  public static class Option {
    // translatable string id
    String display;

    int value;
  }
}

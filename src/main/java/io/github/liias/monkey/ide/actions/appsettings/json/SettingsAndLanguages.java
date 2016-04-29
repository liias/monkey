package io.github.liias.monkey.ide.actions.appsettings.json;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class SettingsAndLanguages {
  private List<Setting> settings;
  private Map<String, Map<String, String>> languages;

  public List<Setting> getSettings() {
    return settings;
  }

  // language name ("valyrian" for default), translation key, translated value
  public Map<String, Map<String, String>> getLanguages() {
    return languages;
  }

}

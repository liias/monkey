package io.github.liias.monkey.ide.actions.appsettings.json;

import java.util.List;
import java.util.Map;

public class SettingsAndLanguages {
  List<Setting> settings;
  Map<String, Map<String, String>> languages;

  public List<Setting> getSettings() {
    return settings;
  }

  public Map<String, Map<String, String>> getLanguages() {
    return languages;
  }

}

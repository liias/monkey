package io.github.liias.monkey.ide.actions.appsettings;

import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import io.github.liias.monkey.deserializer.Serializer;
import io.github.liias.monkey.deserializer.type.MonkeyType;
import io.github.liias.monkey.deserializer.type.MonkeyTypeHash;
import io.github.liias.monkey.deserializer.type.MonkeyTypeString;
import io.github.liias.monkey.ide.actions.appsettings.form.FieldModel;
import io.github.liias.monkey.ide.actions.appsettings.json.Setting;
import io.github.liias.monkey.ide.actions.appsettings.json.SettingsAndLanguages;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppSettingsManager {
  private final Module module;

  private SimulatorCommunication simulatorCommunication;
  private List<Setting> settings;
  private Map<String, Map<String, String>> languages;

  public AppSettingsManager(Module module, VirtualFile settingsFile) {
    this.module = module;

    initSettingsAndLanguages(settingsFile);
  }

  public void initSettingsAndLanguages(VirtualFile settingsFile) {
    try {
      SettingsAndLanguages settingsAndLanguages = parseSettingsJson(settingsFile);
      simulatorCommunication = new SimulatorCommunication(module);
      settings = updateValuesFromSimulator(settingsAndLanguages.getSettings(), simulatorCommunication);
      languages = settingsAndLanguages.getLanguages();
    } catch (IOException e) {
      Throwables.propagate(e);
    }
  }

  private static List<Setting> updateValuesFromSimulator(List<Setting> settings, SimulatorCommunication simulatorCommunication) throws IOException {
    Map<String, MonkeyType> settingValueBySettingKey = receiveSettingValuesFromSimulator(simulatorCommunication);
    setSettingValues(settings, settingValueBySettingKey);
    return settings;
  }

  private static Map<String, MonkeyType> receiveSettingValuesFromSimulator(SimulatorCommunication simulatorCommunication) {
    try {
      Map<MonkeyType, MonkeyType> remoteSettings = simulatorCommunication.parseFromSim();
      return remoteSettings.entrySet().stream()
        .filter(e -> e.getKey() instanceof MonkeyTypeString)
        .collect(Collectors.toMap(e -> ((MonkeyTypeString) e.getKey()).getValue(), Map.Entry::getValue));
    } catch (IOException | ExecutionException e) {
      Throwables.propagate(e);
    }
    return new HashMap<>();
  }

  private static void setSettingValues(List<Setting> settings, Map<String, MonkeyType> remoteSettingsByKey) {
    Map<String, Setting> settingsByKey = settings.stream()
      .collect(Collectors.toMap(Setting::getKey, Function.identity()));

    remoteSettingsByKey.forEach((key, value) -> {
      Setting setting = settingsByKey.get(key);
      if (setting != null) {
        setting.setValueAs(value.getValue(), setting.getValueType());
      }
    });
  }

  private static SettingsAndLanguages parseSettingsJson(VirtualFile settingsFile) throws IOException {
    InputStreamReader reader = new InputStreamReader(settingsFile.getInputStream(), StandardCharsets.UTF_8);

    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(Setting.class, new SettingDeserializer());
    Gson gson = gsonBuilder.create();
    return gson.fromJson(reader, SettingsAndLanguages.class);
  }


  private MonkeyTypeHash convertSettingsToMonkeyTypeHash() {
    Map<Object, Object> settingValuesByKey = getSettings().stream()
      .collect(Collectors.toMap(Setting::getKey, Setting::getValue));
    return new MonkeyTypeHash(settingValuesByKey);
  }

  public boolean sendToSimulator(Map<String, FieldModel> fieldsBySettingKey) {
    updateSettingValuesFromForm(fieldsBySettingKey);

    MonkeyTypeHash monkeyTypeHash = convertSettingsToMonkeyTypeHash();
    Serializer serializer = new Serializer(monkeyTypeHash);
    byte[] serialize = serializer.serialize();

    return simulatorCommunication.sendToSimulator(serialize);
  }

  private void updateSettingValuesFromForm(Map<String, FieldModel> fieldsBySettingKey) {
    for (Setting setting : getSettings()) {
      FieldModel fieldModel = fieldsBySettingKey.get(setting.getKey());
      setting.setValue(fieldModel.getValue());
    }
  }

  public List<Setting> getSettings() {
    return settings;
  }

  public Map<String, Map<String, String>> getLanguages() {
    return languages;
  }
}

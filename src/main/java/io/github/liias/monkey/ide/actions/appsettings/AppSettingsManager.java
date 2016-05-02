package io.github.liias.monkey.ide.actions.appsettings;

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
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppSettingsManager {
  private SimulatorCommunication simulatorCommunication;
  SettingsAndLanguages settingsAndLanguages;

  public AppSettingsManager(Module module, VirtualFile settingsFile) {
    try {
      InputStreamReader reader = new InputStreamReader(settingsFile.getInputStream(), settingsFile.getCharset());

      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeAdapter(Setting.class, new SettingDeserializer());
      Gson gson = gsonBuilder.create();
      this.settingsAndLanguages = gson.fromJson(reader, SettingsAndLanguages.class);

      this.simulatorCommunication = new SimulatorCommunication(module);

      Map<String, Setting> settingsByKey = this.settingsAndLanguages.getSettings().stream()
        .collect(Collectors.toMap(Setting::getKey, Function.identity()));

      Map<MonkeyType, MonkeyType> remoteSettings = simulatorCommunication.parseFromSim();

      Map<String, MonkeyType> remoteSettingsByKey = remoteSettings.entrySet().stream()
        .filter(e -> e.getKey() instanceof MonkeyTypeString)
        .collect(Collectors.toMap(e -> ((MonkeyTypeString) e.getKey()).getValue(), Map.Entry::getValue));

      remoteSettingsByKey.forEach((key, value) -> {
        Setting setting = settingsByKey.get(key);
        if (setting != null) {
          setting.setValueAs(value.getValue(), setting.getValueType());
        }
      });
    } catch (IOException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  public SettingsAndLanguages getSettingsAndLanguages() {
    return settingsAndLanguages;
  }


  public List<Setting> getSettings() {
    return getSettingsAndLanguages().getSettings();
  }

  private MonkeyTypeHash convertSettingsToMonkeyTypeHash() {
    Map<Object, Object> settingValuesByKey = getSettings().stream()
      .collect(Collectors.toMap(Setting::getKey, Setting::getValue));
    return new MonkeyTypeHash(settingValuesByKey);
  }

  public void sendToSim(Map<String, FieldModel> fieldsBySettingKey) {
    updateSettingValuesFromForm(fieldsBySettingKey);

    MonkeyTypeHash monkeyTypeHash = convertSettingsToMonkeyTypeHash();
    Serializer serializer = new Serializer(monkeyTypeHash);
    byte[] serialize = serializer.serialize();

    boolean ok = simulatorCommunication.sendToSimulator(serialize);
  }

  private void updateSettingValuesFromForm(Map<String, FieldModel> fieldsBySettingKey) {
    for (Setting setting : getSettings()) {
      FieldModel fieldModel = fieldsBySettingKey.get(setting.getKey());
      setting.setValue(fieldModel.getValue());
    }
  }

}

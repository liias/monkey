package io.github.liias.monkey.ide.actions.appsettings;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class AppSettingsManager {
  SettingsAndLanguages settingsAndLanguages;

  public AppSettingsManager(Module module, VirtualFile settingsFile) {
    try {
      InputStreamReader reader = new InputStreamReader(settingsFile.getInputStream(), settingsFile.getCharset());

      Gson gson = new Gson();
      this.settingsAndLanguages = gson.fromJson(reader, SettingsAndLanguages.class);
      System.out.println("ok");

      SimulatorCommunication simulatorCommunication = new SimulatorCommunication(module);
      SettingsAndLanguages settingsAndLanguages = simulatorCommunication.parseFromSim();

    } catch (IOException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  public SettingsAndLanguages getSettingsAndLanguages() {
    return settingsAndLanguages;
  }


  public static class SettingsAndLanguages {
    List<Setting> settings;
    Map languages;

    public List<Setting> getSettings() {
      return settings;
    }

    public Map getLanguages() {
      return languages;
    }

    public static class Setting {
      String key;                  // ": "number_prop",
      ValueType valueType;            //       ": "number",
      String defaultValue;         //          ": 2,
      String configTitle;          //         ": "number_title",
      String configPrompt;         //          ": null,
      String configError;          //         ": null,
      Type configType;           //        ": "numeric",
      boolean configReadonly;          //         ": false,
      boolean configRequired;      //             ": false,
      List<Option> configOptions;        //           ": null,
      Number configMin;           //        ": null,
      Number configMax;           //        ": null,
      Integer configMaxLength;     //              ": null

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

      public enum Type {
        @SerializedName("alphaNumeric")
        ALPHA_NUMERIC,
        @SerializedName("boolean")
        BOOLEAN,
        @SerializedName("date")
        DATE,
        @SerializedName("email")
        EMAIL,
        @SerializedName("list")
        LIST,
        @SerializedName("numeric")
        NUMERIC,
        @SerializedName("password")
        PASSWORD,
        @SerializedName("phone")
        PHONE,
        @SerializedName("url")
        URL
      }

      public static class Option {
        String display;
        int value;
      }
    }

  }


}

package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class MonkeyIcons {
  private static Icon load(String path) {
    return IconLoader.getIcon(path, MonkeyIcons.class);
  }

  public static final Icon FILE = load("/icons/mc_file.png"); // 16

  public static final Icon SDK = load("/icons/sdk.png"); // 16
  public static final Icon ADD_SDK = load("/icons/addsdk.png"); // 16
  public static final Icon MODULE24 = load("/icons/module.png"); // 24
  public static final Icon MODULE16 = load("/icons/sdk.png"); // 16

  public static final Icon APP_SETTINGS13 = load("/icons/app_settings_13.png"); // 13
}

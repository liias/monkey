package io.github.liias.monkey.project.sdk;

import com.intellij.openapi.projectRoots.*;
import com.intellij.openapi.vfs.VirtualFile;
import io.github.liias.monkey.icons.MonkeyIcons;
import io.github.liias.monkey.project.module.MonkeyConstants;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

public class MonkeySdkType extends SdkType {
  public MonkeySdkType() {
    super(MonkeyConstants.SDK_TYPE_ID);
  }

  @NotNull
  public static MonkeySdkType getInstance() {
    final MonkeySdkType instance = SdkType.findInstance(MonkeySdkType.class);
    assert instance != null;
    return instance;
  }

  @Nullable
  @Override
  public String suggestHomePath() {
    return null;
  }

  // 16x16
  @Override
  public Icon getIcon() {
    return MonkeyIcons.SDK;
  }

  // 16x16
  @Override
  public Icon getIconForAddAction() {
    return MonkeyIcons.ADD_SDK;
  }

  @Override
  public boolean isValidSdkHome(String path) {
    return true;
  }

  @Override
  public String suggestSdkName(String currentSdkName, String sdkHome) {
    return getVersionString(sdkHome);
  }

  @Nullable
  @Override
  public String getVersionString(String sdkHome) {
    return "1.1.3";
  }

  @Nullable
  @Override
  public AdditionalDataConfigurable createAdditionalDataConfigurable(SdkModel sdkModel, SdkModificator sdkModificator) {
    return null;
  }

  @Override
  public String getPresentableName() {
    return "Connect IQ SDK";
  }

  @Override
  public void saveAdditionalData(@NotNull SdkAdditionalData additionalData, @NotNull Element additional) {
  }

  public String getMonkeydoBatPath(@NotNull Sdk sdk) {
    return getBinPath(sdk) + "monkeydo.bat";
  }

  public String getMonkeybrainsJarPath(@NotNull Sdk sdk) {
    return getBinPath(sdk) + "monkeybrains.jar";
  }

  public VirtualFile getBinDir(@NotNull Sdk sdk) {
    return sdk.getHomeDirectory().findChild("bin");
  }

  public static String getBinPath(@NotNull Sdk sdk) {
    return getConvertedHomePath(sdk) + "bin" + File.separator;
  }

  private static String getConvertedHomePath(@NotNull Sdk sdk) {
    String path = sdk.getHomePath().replace('/', File.separatorChar);
    if (!path.endsWith(File.separator)) {
      path += File.separator;
    }
    return path;
  }
}

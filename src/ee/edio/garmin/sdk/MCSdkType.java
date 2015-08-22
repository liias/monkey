package ee.edio.garmin.sdk;

import com.intellij.openapi.projectRoots.*;
import ee.edio.garmin.MCConstants;
import ee.edio.garmin.MonkeyCIcons;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

public class MCSdkType extends SdkType {
  public MCSdkType() {
    super(MCConstants.SDK_TYPE_ID);
  }

  @NotNull
  public static MCSdkType getInstance() {
    final MCSdkType instance = SdkType.findInstance(MCSdkType.class);
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
    return MonkeyCIcons.SDK;
  }

  // 16x16
  @Override
  public Icon getIconForAddAction() {
    return MonkeyCIcons.ADD_SDK;
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
    return getBinPath(sdk) + File.separator + "monkeydo.bat";
  }

  public String getMonkeybrainsJarPath(@NotNull Sdk sdk) {
    return getBinPath(sdk) + File.separator + "monkeybrains.jar";
  }

  public String getBinPath(@NotNull Sdk sdk) {
    return getConvertedHomePath(sdk) + "bin";
  }

  private static String getConvertedHomePath(@NotNull Sdk sdk) {
    String path = sdk.getHomePath().replace('/', File.separatorChar);
    if (!path.endsWith(File.separator)) {
      path += File.separator;
    }
    return path;
  }

}

package io.github.liias.monkey.jps.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.JpsElementTypeWithDefaultProperties;
import org.jetbrains.jps.model.library.sdk.JpsSdk;
import org.jetbrains.jps.model.library.sdk.JpsSdkType;

public class JpsMonkeySdkType extends JpsSdkType<JpsDummyElement> implements JpsElementTypeWithDefaultProperties<JpsDummyElement> {
  public static final JpsMonkeySdkType INSTANCE = new JpsMonkeySdkType();

  @NotNull
  @Override
  public JpsDummyElement createDefaultProperties() {
    return JpsElementFactory.getInstance().createDummyElement();
  }

  public static class SdkVersion {
    int major;
    int minor;
    int micro;

    public SdkVersion(int major, int minor, int micro) {
      this.major = major;
      this.minor = minor;
      this.micro = micro;
    }
  }


  public static SdkVersion getSdkVersion(@NotNull JpsSdk sdk) {
    String versionString = sdk.getVersionString();
    if (versionString == null) {
      System.out.println("Can't parse SDK version. Try setting SDK for the whole project instead of only module");
      return null;
    }

    String version = versionString.split("-")[0];

    if (!version.matches("\\d+[.]\\d{1,2}[.]\\d{1,2}")) {
      throw new IllegalArgumentException("weird version string format for version: " + version);
    }

    String[] number = version.split("\\.");
    int major = Short.parseShort(number[0]);
    int minor = Short.parseShort(number[1]);
    int micro = Short.parseShort(number[2]);

    return new SdkVersion(major, minor, micro);
  }


  public static boolean hasAppSigningSupport(SdkVersion sdkVersion) {
    if (sdkVersion == null) {
      return false;
    }
    if (sdkVersion.major <= 1 && sdkVersion.minor >= 3) {
      return true;
    }

    if (sdkVersion.major > 2 ||
      (sdkVersion.major == 2 && sdkVersion.minor >= 1)) {
      return true;
    }
    return false;
  }
}
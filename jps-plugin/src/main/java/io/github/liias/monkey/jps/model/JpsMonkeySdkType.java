package io.github.liias.monkey.jps.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    public static SdkVersion fromVersionString(@Nullable String versionString) {
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
  }

  public static SdkVersion getSdkVersion(@NotNull JpsSdk sdk) {
    return SdkVersion.fromVersionString(sdk.getVersionString());
  }

  public static boolean hasUnitTestsSupport(SdkVersion sdkVersion) {
    if (sdkVersion == null) {
      return false;
    }

    return versionSince(sdkVersion, 1, 3, 0);
  }

  public static boolean hasAppSigningSupport(SdkVersion sdkVersion) {
    if (sdkVersion == null) {
      return false;
    }

    return versionSince(sdkVersion, 1, 3, 0);
  }

  public static boolean hasCharSupport(SdkVersion sdkVersion) {
    if (sdkVersion == null) {
      return false;
    }

    return versionSince(sdkVersion, 1, 3, 0);
  }

  public static boolean hasOptionalSdkArgumentsSupport(SdkVersion sdkVersion) {
    if (sdkVersion == null) {
      return false;
    }

    return versionSince(sdkVersion, 2, 1, 0);
  }

  public static boolean hasMinSdkVersionSupport(SdkVersion sdkVersion) {
    if (sdkVersion == null) {
      return false;
    }

    return versionSince(sdkVersion, 1, 3, 0);
  }

  public static boolean hasSdkVersionBuildOptionSupport(SdkVersion sdkVersion) {
    if (sdkVersion == null) {
      return false;
    }

    return versionSince(sdkVersion, 2, 1, 3);
  }

  // if any part (in order major, minor, micro) is more than wanted, then returns true
  private static boolean versionSince(SdkVersion sdkVersion, int major, int minor, int micro) {
    if (sdkVersion == null) {
      return false;
    }

    if (sdkVersion.major > major) {
      return true;
    } else if (sdkVersion.major < major) {
      return false;
    }

    if (sdkVersion.minor > minor) {
      return true;
    } else if (sdkVersion.minor < minor) {
      return false;
    }

    if (sdkVersion.micro > micro) {
      return true;
    } else if (sdkVersion.micro < micro) {
      return false;
    }

    // all version parts are equal
    return true;
  }
}
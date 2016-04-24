package io.github.liias.monkey;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.SystemInfoRt;

public class Utils {
  public static GeneralCommandLine createGeneralCommandLine(String workDirectory, String exePath) {
    if (SystemInfo.isLinux) {
      return new GeneralCommandLine()
          .withWorkDirectory(workDirectory)
          .withExePath("wine")
          .withParameters(exePath);
    }

    if (SystemInfo.isMac) {
      return new GeneralCommandLine()
          .withWorkDirectory(workDirectory)
          .withExePath("open")
          .withParameters(exePath);
    }

    return new GeneralCommandLine()
        .withWorkDirectory(workDirectory)
        .withExePath(exePath);
  }

  public static String getForWinLinOrMac(String winLin, String mac) {
    if (SystemInfoRt.isWindows || SystemInfoRt.isLinux) {
      return winLin;
    } else if (SystemInfoRt.isMac) {
      return mac;
    }
    throw new UnsupportedOperationException("OS is unsupported: " + SystemInfoRt.OS_NAME);
  }
}

package io.github.liias.monkey;

import com.google.common.collect.ImmutableList;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.SystemInfoRt;
import com.intellij.openapi.vfs.CharsetToolkit;

import java.util.ArrayList;
import java.util.List;

public class Utils {
  public static GeneralCommandLine createGeneralCommandLine(String workDirectory, String exePath) {
    if (SystemInfo.isLinux) {
      // wine bla.exe
      // wine cmd /c bla.bat
      ImmutableList.Builder<String> paramsBuilder = ImmutableList.builder();
      if (exePath.endsWith(".bat")) {
        paramsBuilder.add("cmd", "/c");
      }
      paramsBuilder.add(exePath);
      return new GeneralCommandLine()
        .withWorkDirectory(workDirectory)
        .withExePath("wine")
        .withParameters(paramsBuilder.build())
        .withCharset(CharsetToolkit.UTF8_CHARSET);
    }

    if (SystemInfo.isMac) {
      String macExePath = exePath;
      List<String> parameters = new ArrayList<>();

      // .app files are opened with open command?
      if (exePath.endsWith(".app")) {
        macExePath = "open";
        parameters.add(exePath);
        parameters.add("--args");
      }

      return new GeneralCommandLine()
        .withWorkDirectory(workDirectory)
        .withExePath(macExePath)
        .withParameters(parameters)
        .withCharset(CharsetToolkit.UTF8_CHARSET);
    }

    return new GeneralCommandLine()
      .withWorkDirectory(workDirectory)
      .withExePath(exePath)
      .withCharset(CharsetToolkit.UTF8_CHARSET);
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

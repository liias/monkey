package io.github.liias.monkey.project.sdk;

import io.github.liias.monkey.project.runconfig.TargetDevice;
import io.github.liias.monkey.project.sdk.devices.DevicesReader;
import org.junit.Test;

import java.util.List;

public class TestLoadingDevicesList {

  @Test
  public void testSomething() {
    String sdkBinPath = "D:\\connectiq\\connectiq-sdk-win-1.2.9\\bin\\";
    DevicesReader devicesReader = new DevicesReader(sdkBinPath);
    List<TargetDevice> targetDevices = devicesReader.parseDevicesXml();
    System.out.println(targetDevices);
  }

}

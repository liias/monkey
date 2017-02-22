package io.github.liias.monkey.project.sdk.devices;

import com.intellij.openapi.diagnostic.Logger;
import io.github.liias.monkey.project.runconfig.TargetDevice;
import io.github.liias.monkey.project.runconfig.TargetSdkVersion;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DevicesReader {
  protected static final Logger LOG = Logger.getInstance("#" + DevicesReader.class.getName());

  public static final String DEVICES_XML = "devices.xml";

  private static String TAG_DEVICES = "devices";
  private static String TAG_DEVICE = "device";

  public static final String DEVICE_ATTRIBUTE_ID = "id";
  public static final String DEVICE_ATTRIBUTE_NAME = "name";

  @Nullable
  private final String sdkBinPath;
  private List<TargetDevice> devices;

  public DevicesReader(@Nullable String sdkBinPath) {
    this.sdkBinPath = sdkBinPath;
  }

  public List<TargetSdkVersion> getTargetSdkVersions() {
    return Arrays.asList(
      TargetSdkVersion.VERSION_1_2_X,
      TargetSdkVersion.VERSION_1_3_X,
      TargetSdkVersion.VERSION_2_1_X,
      TargetSdkVersion.VERSION_2_2_X
    );
  }

  public List<TargetDevice> parseDevicesXml() {
    this.devices = new ArrayList<>();

    if (sdkBinPath == null) {
      return this.devices;
    }
    String devicesXmlPath = sdkBinPath + DEVICES_XML;
    File devicesXml = new File(devicesXmlPath);
    if (!devicesXml.exists()) {
      return this.devices;
    }

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(devicesXml);
      this.devices = importDevices(doc);

    } catch (SAXException | IOException | ParserConfigurationException e) {
      // this could be if some wrong SDK is set
      LOG.warn("could not find " + devicesXmlPath + "or some other problem: " + e.getMessage());
    }
    return this.devices;
  }

  private List<TargetDevice> importDevices(Document doc) {
    Element rootElement = doc.getDocumentElement();
    Element devicesRoot = (Element) rootElement.getElementsByTagName(TAG_DEVICES).item(0);
    NodeList devices = devicesRoot.getElementsByTagName(TAG_DEVICE);

    List<TargetDevice> devicesList = new ArrayList<>();
    for (int i = 0; i < devices.getLength(); i++) {
      Element device = (Element) devices.item(i);
      NamedNodeMap attributes = device.getAttributes();
      String deviceId = attributes.getNamedItem(DEVICE_ATTRIBUTE_ID).getNodeValue();
      if (deviceId.endsWith("_sim")) {
        continue;
      }
      String deviceName = attributes.getNamedItem(DEVICE_ATTRIBUTE_NAME).getNodeValue();
      TargetDevice targetDevice = new TargetDevice(deviceId, deviceName);

      devicesList.add(targetDevice);
    }

    return devicesList;
  }
}

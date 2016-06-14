package io.github.liias.monkey.project.sdk;

import com.intellij.openapi.projectRoots.*;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.HashMap;
import icons.MonkeyIcons;
import io.github.liias.monkey.project.module.MonkeyConstants;
import io.github.liias.monkey.project.sdk.skeleton.SdkSkeleton;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static io.github.liias.monkey.Utils.getForWinLinOrMac;

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

  @Override
  public void setupSdkPaths(@NotNull Sdk sdk) {
    SdkSkeleton.updateSdk(sdk);
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
  @NotNull
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

  private final Map<String, String> myCachedVersionStrings = Collections.synchronizedMap(new HashMap<>());

  @Nullable
  @Override
  public String getVersionString(String sdkHome) {
    String versionString = myCachedVersionStrings.get(sdkHome);
    if (versionString == null) {
      versionString = detectCompilerVersion(sdkHome);
      if (!StringUtil.isEmpty(versionString)) {
        myCachedVersionStrings.put(sdkHome, versionString);
      }
    }

    if (versionString == null) {
      return "Unknown version";
    }
    return versionString;
  }

  private String detectCompilerVersion(@NotNull String homePath) {
    final File compilerInfoXml = new File(homePath, "bin/compilerInfo.xml");

    String version = null;
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(compilerInfoXml);
      //optional, but recommended
      //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
      doc.getDocumentElement().normalize();

      Node versionNode = doc.getElementsByTagName("version").item(0);
      version = versionNode.getTextContent();
    } catch (SAXException | ParserConfigurationException | IOException e) {
      e.printStackTrace();
    }

    return version;
  }


  @Nullable
  @Override
  public AdditionalDataConfigurable createAdditionalDataConfigurable(@NotNull SdkModel sdkModel, @NotNull SdkModificator sdkModificator) {
    return null;
  }

  @NotNull
  @Override
  public String getPresentableName() {
    return "Connect IQ SDK";
  }

  @Override
  public void saveAdditionalData(@NotNull SdkAdditionalData additionalData, @NotNull Element additional) {
  }

  public static String getMonkeydoBatPath(@NotNull Sdk sdk) {
    String monkeydo = getForWinLinOrMac("monkeydo.bat", "monkeydo");
    return getBinPath(sdk) + monkeydo;
  }

  public VirtualFile getBinDir(@NotNull Sdk sdk) {
    return sdk.getHomeDirectory().findChild("bin");
  }

  public static String getBinPath(@NotNull Sdk sdk) {
    return getConvertedHomePath(sdk) + "bin" + File.separator;
  }

  public static String getApiDocPath(@NotNull Sdk sdk) {
    return getConvertedHomePath(sdk) + "doc" + File.separator;
  }

  private static String getConvertedHomePath(@NotNull Sdk sdk) {
    String path = sdk.getHomePath().replace('/', File.separatorChar);
    if (!path.endsWith(File.separator)) {
      path += File.separator;
    }
    return path;
  }
}

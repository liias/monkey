package ee.edio.garmin.dom.sdk.projectinfo;

import com.intellij.util.xml.GenericAttributeValue;

import java.util.List;

public interface NewProjectFileMap extends ProjectInfoDomElement {
  GenericAttributeValue<String> getAppType();

  GenericAttributeValue<String> getName();

  GenericAttributeValue<String> getBaseDir();

  GenericAttributeValue<String> getDescription();

  List<File> getFiles();
}

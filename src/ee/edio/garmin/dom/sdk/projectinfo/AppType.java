package ee.edio.garmin.dom.sdk.projectinfo;

import com.intellij.util.xml.GenericAttributeValue;

public interface AppType extends ProjectInfoDomElement {
  GenericAttributeValue<String> getId();

  GenericAttributeValue<String> getValue();

  GenericAttributeValue<String> getName();

  GenericAttributeValue<String> getDescription();
}
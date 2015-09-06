package ee.edio.garmin.dom.sdk.projectinfo;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.GenericAttributeValue;

public interface Permission extends ProjectInfoDomElement {
  GenericAttributeValue<String> getId();

  GenericAttributeValue<String> getName();

  GenericAttributeValue<String> getDescription();

  @Attribute("module")
  GenericAttributeValue<String> getModuleName();
}

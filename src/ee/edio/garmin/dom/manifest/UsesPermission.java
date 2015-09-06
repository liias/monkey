package ee.edio.garmin.dom.manifest;

import com.intellij.util.xml.GenericAttributeValue;

public interface UsesPermission extends ManifestDomElement {
  GenericAttributeValue<String> getId();
}
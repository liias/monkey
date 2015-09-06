package ee.edio.garmin.dom.manifest;

import com.intellij.util.xml.GenericAttributeValue;

public interface Product extends ManifestDomElement {
  GenericAttributeValue<String> getId();
}
package io.github.liias.monkey.project.dom.manifest;

import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;

public interface Product extends ManifestDomElement {
  @NameValue
  GenericAttributeValue<String> getId();
}
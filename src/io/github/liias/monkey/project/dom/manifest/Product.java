package io.github.liias.monkey.project.dom.manifest;

import com.intellij.util.xml.GenericAttributeValue;

public interface Product extends ManifestDomElement {
  GenericAttributeValue<String> getId();
}
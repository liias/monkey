package io.github.liias.monkey.project.dom.manifest;

import com.intellij.util.xml.GenericAttributeValue;

public interface UsesPermission extends ManifestDomElement {
  GenericAttributeValue<String> getId();
}
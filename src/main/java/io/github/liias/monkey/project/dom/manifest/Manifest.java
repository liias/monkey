package io.github.liias.monkey.project.dom.manifest;

import com.intellij.util.xml.GenericAttributeValue;

public interface Manifest extends ManifestDomElement {
  GenericAttributeValue<String> getVersion();

  Application getApplication();
}




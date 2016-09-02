package io.github.liias.monkey.project.dom.manifest;

public interface Manifest extends ManifestDomElement {
  NoNamespaceAttributeValue<Integer> getVersion();

  Application getApplication();
}
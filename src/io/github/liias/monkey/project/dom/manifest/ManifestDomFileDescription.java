package io.github.liias.monkey.project.dom.manifest;

import com.intellij.util.xml.DomFileDescription;

public class ManifestDomFileDescription extends DomFileDescription<Manifest> {
  public ManifestDomFileDescription() {
    super(Manifest.class, "manifest", ManifestDomElement.IQ_NAMESPACE_PREFIX);
  }

  @Override
  protected void initializeFileDescription() {
    registerNamespacePolicy(ManifestDomElement.IQ_NAMESPACE_PREFIX, ManifestDomElement.IQ_NAMESPACE);
  }
}
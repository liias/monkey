package io.github.liias.monkey.project.dom.manifest;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.Iconable;
import com.intellij.util.xml.DomFileDescription;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ManifestDomFileDescription extends DomFileDescription<Manifest> {
  public ManifestDomFileDescription() {
    super(Manifest.class, "manifest", ManifestDomElement.IQ_NAMESPACE_PREFIX);
  }

  @Override
  protected void initializeFileDescription() {
    registerNamespacePolicy(ManifestDomElement.IQ_NAMESPACE_PREFIX, ManifestDomElement.IQ_NAMESPACE);
  }

  @Nullable
  @Override
  public Icon getFileIcon(@Iconable.IconFlags int flags) {
    return AllIcons.FileTypes.Manifest;
  }
}
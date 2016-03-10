package io.github.liias.monkey.project.dom.manifest;

import com.intellij.util.xml.highlighting.BasicDomElementsInspection;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class ManifestDomInspection extends BasicDomElementsInspection<ManifestDomElement> {
  public ManifestDomInspection() {
    super(ManifestDomElement.class);
  }

  @Override
  @Nls
  @NotNull
  public String getDisplayName() {
    return "Unresolved References";
  }
}

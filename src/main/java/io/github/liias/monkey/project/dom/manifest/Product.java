package io.github.liias.monkey.project.dom.manifest;

import com.intellij.spellchecker.xml.NoSpellchecking;
import com.intellij.util.xml.NameValue;
import com.intellij.util.xml.Required;

public interface Product extends ManifestDomElement {
  @NameValue
  @NoSpellchecking
  @Required
  NoNamespaceAttributeValue<String> getId();

  // pattern "[0-9a-zA-Z]{3}-[0-9a-zA-Z]{5}-[0-9a-zA-Z]{2}"
  NoNamespaceAttributeValue<String> getPartNumber();

  NoNamespaceAttributeValue<String> getFilename();

  NoNamespaceAttributeValue<Integer> getMinFirmwareVersion();

  NoNamespaceAttributeValue<Integer> getMaxFirmwareVersion();

  NoNamespaceAttributeValue<String> getSig();

  NoNamespaceAttributeValue<String> getSigFormat();
}
package io.github.liias.monkey.project.dom.manifest;

import com.intellij.spellchecker.xml.NoSpellchecking;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;
import com.intellij.util.xml.Required;

public interface Product extends ManifestDomElement {
  @NameValue
  @NoSpellchecking
  @Required
  GenericAttributeValue<String> getId();

  // pattern "[0-9a-zA-Z]{3}-[0-9a-zA-Z]{5}-[0-9a-zA-Z]{2}"
  GenericAttributeValue<String> getPartNumber();

  GenericAttributeValue<String> getFilename();

  GenericAttributeValue<Integer> getMinFirmwareVersion();

  GenericAttributeValue<Integer> getMaxFirmwareVersion();

  GenericAttributeValue<String> getSig();

  GenericAttributeValue<String> getSigFormat();
}
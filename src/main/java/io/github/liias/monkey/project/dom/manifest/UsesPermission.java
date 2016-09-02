package io.github.liias.monkey.project.dom.manifest;

import com.intellij.spellchecker.xml.NoSpellchecking;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;
import com.intellij.util.xml.Required;

public interface UsesPermission extends ManifestDomElement {
  @NameValue
  @NoSpellchecking
  @Required
  GenericAttributeValue<String> getId();
}
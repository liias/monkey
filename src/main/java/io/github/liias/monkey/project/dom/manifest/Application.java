package io.github.liias.monkey.project.dom.manifest;

import com.intellij.spellchecker.xml.NoSpellchecking;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;
import com.intellij.util.xml.Required;

public interface Application extends ManifestDomElement {
  @Required
  GenericAttributeValue<String> getEntry();

  // min length 32
  @Required
  GenericAttributeValue<String> getId();

  @Required
  GenericAttributeValue<String> getLauncherIcon();

  // actually, if the attribute is not set, then some default is used,
  // but can't be empty. Makes sense to set anyway
  @Required(false)
  GenericAttributeValue<String> getMinSdkVersion();

  @NameValue
  @Required
  @NoSpellchecking
  GenericAttributeValue<String> getName();

  @Required
  GenericAttributeValue<String> getType();

  Products getProducts();

  Permissions getPermissions();

  Languages getLanguages();
}
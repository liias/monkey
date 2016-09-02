package io.github.liias.monkey.project.dom.manifest;

import com.intellij.spellchecker.xml.NoSpellchecking;
import com.intellij.util.xml.NameValue;
import com.intellij.util.xml.Required;

public interface Application extends ManifestDomElement {
  @Required
  NoNamespaceAttributeValue<String> getEntry();

  // min length 32
  @Required
  NoNamespaceAttributeValue<String> getId();

  @Required
  NoNamespaceAttributeValue<String> getLauncherIcon();

  // actually, if the attribute is not set, then some default is used,
  // but can't be empty. Makes sense to set anyway
  @Required(false)
  NoNamespaceAttributeValue<String> getMinSdkVersion();

  @NameValue
  @Required
  @NoSpellchecking
  NoNamespaceAttributeValue<String> getName();

  @Required
  NoNamespaceAttributeValue<String> getType();

  Products getProducts();

  Permissions getPermissions();

  Languages getLanguages();
}
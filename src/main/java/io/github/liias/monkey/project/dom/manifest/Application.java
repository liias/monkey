package io.github.liias.monkey.project.dom.manifest;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;
import com.intellij.util.xml.Required;

public interface Application extends ManifestDomElement {
  @Required
  GenericAttributeValue<String> getEntry();

  GenericAttributeValue<String> getId();

  @Attribute("launcherIcon")
  GenericAttributeValue<String> getLauncherIcon();

  // actually, if the attribute is not set, then some default is used,
  // but can't be empty. Makes sense to set anyway
  @Required
  GenericAttributeValue<String> getMinSdkVersion();

  @NameValue
  GenericAttributeValue<String> getName();

  GenericAttributeValue<String> getType();

  Products getProducts();

  Permissions getPermissions();

  Languages getLanguages();
}
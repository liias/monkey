package io.github.liias.monkey.project.dom.manifest;

import com.intellij.util.xml.*;

@DefinesXml
@Namespace(ManifestDomElement.IQ_NAMESPACE_PREFIX)
@NameStrategy(JavaNameStrategy.class)
public interface ManifestDomElement extends DomElement {
  String IQ_NAMESPACE_PREFIX = "iq";
  String IQ_NAMESPACE = "http://www.garmin.com/xml/connectiq";
}

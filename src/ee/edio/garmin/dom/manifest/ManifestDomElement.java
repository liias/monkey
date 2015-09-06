package ee.edio.garmin.dom.manifest;

import com.intellij.util.xml.DefinesXml;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.JavaNameStrategy;
import com.intellij.util.xml.NameStrategy;
import com.intellij.util.xml.Namespace;

@DefinesXml
@Namespace(ManifestDomElement.IQ_NAMESPACE_PREFIX)
@NameStrategy(JavaNameStrategy.class)
public interface ManifestDomElement extends DomElement {
  String IQ_NAMESPACE_PREFIX = "iq";
  String IQ_NAMESPACE = "http://www.garmin.com/xml/connectiq";
}

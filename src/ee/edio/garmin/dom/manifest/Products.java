package ee.edio.garmin.dom.manifest;

import java.util.List;

public interface Products extends ManifestDomElement {
  List<Product> getProducts();
}
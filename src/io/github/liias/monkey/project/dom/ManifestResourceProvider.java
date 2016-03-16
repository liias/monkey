package io.github.liias.monkey.project.dom;

import com.intellij.javaee.ResourceRegistrar;
import com.intellij.javaee.StandardResourceProvider;

public class ManifestResourceProvider implements StandardResourceProvider {
  public static final String CONNECTIQ_NAMESPACE = "http://www.garmin.com/xml/connectiq";

  @Override
  public void registerResources(ResourceRegistrar registrar) {
    // so that IDEA wouldn't whine about unregistered schema
    registrar.addIgnoredResource(CONNECTIQ_NAMESPACE);
  }
}

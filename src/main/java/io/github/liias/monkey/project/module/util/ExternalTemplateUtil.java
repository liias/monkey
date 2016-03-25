package io.github.liias.monkey.project.module.util;

import com.intellij.openapi.vfs.CharsetToolkit;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;

public class ExternalTemplateUtil {
  private static VelocityEngine newVelocityEngine() {
    VelocityEngine engine = new VelocityEngine();
    engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, new NullLogChute());
    engine.setProperty(RuntimeConstants.INPUT_ENCODING, CharsetToolkit.UTF8);
    engine.setProperty(RuntimeConstants.PARSER_POOL_SIZE, 3);
    engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "includes");
    engine.init();
    return engine;
  }

  private static final VelocityEngine engine = newVelocityEngine();

  public static VelocityEngine getEngine() {
    return engine;
  }
}

package ee.edio.garmin.jps.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.JpsElementTypeWithDefaultProperties;
import org.jetbrains.jps.model.JpsSimpleElement;
import org.jetbrains.jps.model.ex.JpsElementTypeBase;
import org.jetbrains.jps.model.module.JpsModuleType;

public class JpsMCModuleType extends JpsElementTypeBase<JpsSimpleElement<JpsMCModuleProperties>>
    implements JpsModuleType<JpsSimpleElement<JpsMCModuleProperties>>,
    JpsElementTypeWithDefaultProperties<JpsSimpleElement<JpsMCModuleProperties>> {

  public static final JpsMCModuleType INSTANCE = new JpsMCModuleType();

  @NotNull
  @Override
  public JpsSimpleElement<JpsMCModuleProperties> createDefaultProperties() {
    return JpsElementFactory.getInstance().createSimpleElement(new JpsMCModuleProperties());
  }
}

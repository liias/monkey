package ee.edio.garmin.jps.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.JpsElementTypeWithDefaultProperties;
import org.jetbrains.jps.model.JpsSimpleElement;
import org.jetbrains.jps.model.ex.JpsElementTypeBase;
import org.jetbrains.jps.model.module.JpsModuleType;

public class JpsMonkeyModuleType extends JpsElementTypeBase<JpsSimpleElement<JpsMonkeyModuleProperties>>
    implements JpsModuleType<JpsSimpleElement<JpsMonkeyModuleProperties>>,
    JpsElementTypeWithDefaultProperties<JpsSimpleElement<JpsMonkeyModuleProperties>> {

  public static final JpsMonkeyModuleType INSTANCE = new JpsMonkeyModuleType();

  @NotNull
  @Override
  public JpsSimpleElement<JpsMonkeyModuleProperties> createDefaultProperties() {
    return JpsElementFactory.getInstance().createSimpleElement(new JpsMonkeyModuleProperties());
  }
}

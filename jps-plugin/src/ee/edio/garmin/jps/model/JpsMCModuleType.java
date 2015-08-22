package ee.edio.garmin.jps.model;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.JpsElementTypeWithDefaultProperties;
import org.jetbrains.jps.model.JpsSimpleElement;
import org.jetbrains.jps.model.ex.JpsElementTypeBase;
import org.jetbrains.jps.model.module.JpsModuleType;
import org.jetbrains.jps.model.serialization.module.JpsModulePropertiesSerializer;

public class JpsMCModuleType extends JpsElementTypeBase<JpsSimpleElement<JpsMCModuleProperties>>
    implements JpsModuleType<JpsSimpleElement<JpsMCModuleProperties>>,
    JpsElementTypeWithDefaultProperties<JpsSimpleElement<JpsMCModuleProperties>> {

  public static final JpsMCModuleType INSTANCE = new JpsMCModuleType();
  private static final String MODULE_TYPE_ID = "MONKEY_C_MODULE";

  @NotNull
  @Override
  public JpsSimpleElement<JpsMCModuleProperties> createDefaultProperties() {
    return JpsElementFactory.getInstance().createSimpleElement(new JpsMCModuleProperties());
  }

  @NotNull
  public static JpsModulePropertiesSerializer<JpsSimpleElement<JpsMCModuleProperties>> createModulePropertiesSerializer() {
    return new JpsModulePropertiesSerializer<JpsSimpleElement<JpsMCModuleProperties>>(INSTANCE, MODULE_TYPE_ID, null) {
      @NotNull
      @Override
      public JpsSimpleElement<JpsMCModuleProperties> loadProperties(@Nullable Element componentElement) {
        return INSTANCE.createDefaultProperties();
      }

      @Override
      public void saveProperties(@NotNull JpsSimpleElement<JpsMCModuleProperties> properties, @NotNull Element componentElement) {
      }
    };
  }

}

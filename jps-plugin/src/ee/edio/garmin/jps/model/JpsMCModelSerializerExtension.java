package ee.edio.garmin.jps.model;

import ee.edio.garmin.MCConstants;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.JpsSimpleElement;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;
import org.jetbrains.jps.model.serialization.library.JpsSdkPropertiesSerializer;
import org.jetbrains.jps.model.serialization.module.JpsModulePropertiesSerializer;

import java.util.Collections;
import java.util.List;

public class JpsMCModelSerializerExtension extends JpsModelSerializerExtension {
  public static final String MODULE_TARGET_DEVICE_ID_ATTRIBUTE = "target-device";

  @Override
  public void loadRootModel(@NotNull JpsModule module, @NotNull Element rootModel) {
    final JpsSimpleElement modulePropertiesElement = (JpsSimpleElement) module.getProperties();
    final JpsMCModuleProperties moduleProperties = (JpsMCModuleProperties) modulePropertiesElement.getData();
    moduleProperties.TARGET_DEVICE_ID = rootModel.getAttributeValue(MODULE_TARGET_DEVICE_ID_ATTRIBUTE);
  }

/*  @Override
  public void loadModuleOptions(@NotNull JpsModule module, @NotNull Element rootElement) {
    final JpsSimpleElement modulePropertiesElement = (JpsSimpleElement) module.getProperties();
    final JpsMCModuleProperties moduleProperties = (JpsMCModuleProperties) modulePropertiesElement.getData();
    moduleProperties.TARGET_DEVICE_ID = rootElement.getAttributeValue(MODULE_TARGET_DEVICE_ID_ATTRIBUTE);
  }*/

  @NotNull
  @Override
  public List<? extends JpsModulePropertiesSerializer<?>> getModulePropertiesSerializers() {
    return Collections.singletonList(new JpsMonkeyModulePropertiesSerializer());
  }

  @NotNull
  @Override
  public List<? extends JpsSdkPropertiesSerializer<?>> getSdkPropertiesSerializers() {
    return Collections.singletonList(new JpsMonkeySdkPropertiesSerializer());
  }

  private static class JpsMonkeySdkPropertiesSerializer extends JpsSdkPropertiesSerializer<JpsDummyElement> {
    protected JpsMonkeySdkPropertiesSerializer() {
      super(MCConstants.SDK_TYPE_ID, JpsMCSdkType.INSTANCE);
    }

    @NotNull
    @Override
    public JpsDummyElement loadProperties(@Nullable Element propertiesElement) {
      return JpsElementFactory.getInstance().createDummyElement();
    }

    @Override
    public void saveProperties(@NotNull JpsDummyElement properties, @NotNull Element element) {
    }
  }


  private static class JpsMonkeyModulePropertiesSerializer extends JpsModulePropertiesSerializer<JpsSimpleElement<JpsMCModuleProperties>> {
    private JpsMonkeyModulePropertiesSerializer() {
      super(JpsMCModuleType.INSTANCE, MCConstants.MODULE_TYPE_ID, null);
    }

    @Override
    public JpsSimpleElement<JpsMCModuleProperties> loadProperties(@Nullable Element componentElement) {
      return JpsElementFactory.getInstance().createSimpleElement(new JpsMCModuleProperties());
    }

    @Override
    public void saveProperties(@NotNull JpsSimpleElement<JpsMCModuleProperties> element, @NotNull Element componentElement) {
    }
  }

}

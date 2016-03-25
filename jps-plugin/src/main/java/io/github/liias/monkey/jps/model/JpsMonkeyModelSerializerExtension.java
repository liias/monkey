package io.github.liias.monkey.jps.model;

import io.github.liias.monkey.jps.MonkeyConstants;
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

public class JpsMonkeyModelSerializerExtension extends JpsModelSerializerExtension {
  public static final String MODULE_TARGET_DEVICE_ID_ATTRIBUTE = "target-device";

  @Override
  public void loadRootModel(@NotNull JpsModule module, @NotNull Element rootModel) {
    final JpsSimpleElement modulePropertiesElement = (JpsSimpleElement) module.getProperties();
    final JpsMonkeyModuleProperties moduleProperties = (JpsMonkeyModuleProperties) modulePropertiesElement.getData();
    moduleProperties.TARGET_DEVICE_ID = rootModel.getAttributeValue(MODULE_TARGET_DEVICE_ID_ATTRIBUTE);
  }

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
      super(MonkeyConstants.SDK_TYPE_ID, JpsMonkeySdkType.INSTANCE);
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

  private static class JpsMonkeyModulePropertiesSerializer extends JpsModulePropertiesSerializer<JpsSimpleElement<JpsMonkeyModuleProperties>> {
    private JpsMonkeyModulePropertiesSerializer() {
      super(JpsMonkeyModuleType.INSTANCE, MonkeyConstants.MODULE_TYPE_ID, null);
    }

    @Override
    public JpsSimpleElement<JpsMonkeyModuleProperties> loadProperties(@Nullable Element componentElement) {
      return JpsElementFactory.getInstance().createSimpleElement(new JpsMonkeyModuleProperties());
    }

    @Override
    public void saveProperties(@NotNull JpsSimpleElement<JpsMonkeyModuleProperties> element, @NotNull Element componentElement) {
    }
  }
}
package io.github.liias.monkey.jps.model;

import com.intellij.openapi.util.JDOMExternalizerUtil;
import io.github.liias.monkey.jps.MonkeyConstants;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.JpsGlobal;
import org.jetbrains.jps.model.JpsSimpleElement;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.serialization.JpsGlobalExtensionSerializer;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;
import org.jetbrains.jps.model.serialization.library.JpsSdkPropertiesSerializer;
import org.jetbrains.jps.model.serialization.module.JpsModulePropertiesSerializer;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class JpsMonkeyModelSerializerExtension extends JpsModelSerializerExtension {
  public static final JpsElementChildRoleBase<JpsMonkeyGlobalProperties> MONKEY_GLOBAL_CONFIG_ROLE =
    JpsElementChildRoleBase.create("monkey global config");

  public static final String MODULE_TARGET_DEVICE_ID_ATTRIBUTE = "target-device";

  @Override
  public void loadRootModel(@NotNull JpsModule module, @NotNull Element rootModel) {
    if (module.getModuleType() != JpsMonkeyModuleType.INSTANCE) {
      return;
    }

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

  @NotNull
  @Override
  public List<? extends JpsGlobalExtensionSerializer> getGlobalExtensionSerializers() {
    return Collections.singletonList(new JpsMonkeyGlobalExtensionSerializer());
  }


  private static class JpsMonkeyGlobalExtensionSerializer extends JpsGlobalExtensionSerializer {

    /**
     * // see io.github.liias.monkey.ide.conf.MonkeyOptions
     */
    protected JpsMonkeyGlobalExtensionSerializer() {
      super("monkey.options.xml", "MonkeyOptions");
    }

    @Override
    public void loadExtension(@NotNull JpsGlobal jpsGlobal, @NotNull Element componentTag) {
      String keyPath = JDOMExternalizerUtil.readField(componentTag, "keyPath");
      File keyPathFile = keyPath != null ? new File(keyPath) : null;
      JpsMonkeyGlobalProperties jpsMonkeyGlobalProperties = new JpsMonkeyGlobalProperties(keyPathFile);
      jpsGlobal.getContainer().setChild(MONKEY_GLOBAL_CONFIG_ROLE, jpsMonkeyGlobalProperties);
    }

    @Override
    public void saveExtension(@NotNull JpsGlobal jpsGlobal, @NotNull Element componentTag) {
    }
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
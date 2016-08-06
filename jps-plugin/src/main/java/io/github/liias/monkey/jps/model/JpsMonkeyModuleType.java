package io.github.liias.monkey.jps.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.JpsElementTypeWithDefaultProperties;
import org.jetbrains.jps.model.JpsSimpleElement;
import org.jetbrains.jps.model.ex.JpsElementTypeBase;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.module.JpsModuleType;

public class JpsMonkeyModuleType extends JpsElementTypeBase<JpsSimpleElement<JpsMonkeyModuleProperties>>
  implements JpsModuleType<JpsSimpleElement<JpsMonkeyModuleProperties>>,
  JpsElementTypeWithDefaultProperties<JpsSimpleElement<JpsMonkeyModuleProperties>> {

  public static final JavaSourceRootType MONKEY_SOURCE_ROOT_TYPE = JavaSourceRootType.SOURCE;
  public static final JavaSourceRootType MONKEY_TEST_SOURCE_ROOT_TYPE = JavaSourceRootType.TEST_SOURCE;
  public static final JavaResourceRootType MONKEY_RESOURCE_ROOT_TYPE = JavaResourceRootType.RESOURCE;
  public static final JavaResourceRootType MONKEY_TEST_RESOURCE_ROOT_TYPE = JavaResourceRootType.TEST_RESOURCE;

  public static final JpsMonkeyModuleType INSTANCE = new JpsMonkeyModuleType();

  @NotNull
  @Override
  public JpsSimpleElement<JpsMonkeyModuleProperties> createDefaultProperties() {
    return JpsElementFactory.getInstance().createSimpleElement(new JpsMonkeyModuleProperties());
  }
}

package io.github.liias.monkey.project.configuration;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleExtension;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import io.github.liias.monkey.project.runconfig.TargetDevice;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TargetDeviceModuleExtension extends ModuleExtension<TargetDeviceModuleExtension> {
  // must be same as fields in JpsMonkeyModuleProperties
  //JpsMonkeyModelSerializerExtension.MODULE_TARGET_DEVICE_ID_ATTRIBUTE
  private static final String TARGET_DEVICE_ID_ATTRIBUTE = "target-device";

  private Module module;
  private final boolean writable;
  private static final Logger LOG = Logger.getInstance("#" + TargetDeviceModuleExtension.class.getName());

  private TargetDevice targetDevice;
  private final TargetDeviceModuleExtension source;

  public static TargetDeviceModuleExtension getInstance(final Module module) {
    return ModuleRootManager.getInstance(module).getModuleExtension(TargetDeviceModuleExtension.class);
  }

  @SuppressWarnings("unused")
  public TargetDeviceModuleExtension(Module module) {
    this.module = module;
    this.source = null;
    this.writable = false;
  }

  public TargetDeviceModuleExtension(final TargetDeviceModuleExtension source, boolean writable) {
    this.writable = writable;
    this.module = source.module;
    this.targetDevice = source.targetDevice;
    this.source = source;
  }

  @Nullable
  public TargetDevice getTargetDevice() {
    return targetDevice;
  }

  public void setTargetDevice(final TargetDevice targetDevice) {
    LOG.assertTrue(writable, "Writable model can be retrieved from writable ModifiableRootModel");
    this.targetDevice = targetDevice;
  }

  @Override
  public void readExternal(@NotNull Element element) throws InvalidDataException {
    final String targetDeviceId = element.getAttributeValue(TARGET_DEVICE_ID_ATTRIBUTE);
    if (targetDeviceId != null) {
      try {
        targetDevice = TargetDevice.fromId(targetDeviceId);
      } catch (IllegalArgumentException e) {
        //bad value was stored
      }
    } else {
      targetDevice = null;
    }
  }

  @Override
  public void writeExternal(final Element element) throws WriteExternalException {
    if (targetDevice != null) {
      element.setAttribute(TARGET_DEVICE_ID_ATTRIBUTE, targetDevice.getId());
    }
  }

  @Override
  public ModuleExtension getModifiableModel(final boolean writable) {
    return new TargetDeviceModuleExtension(this, writable);
  }

  @Override
  public void commit() {
    if (source != null && source.targetDevice != targetDevice) {
      source.targetDevice = targetDevice;
    }
  }

  @Override
  public boolean isChanged() {
    return source != null && source.targetDevice != targetDevice;
  }

  @Override
  public void dispose() {
    module = null;
    targetDevice = null;
  }
}

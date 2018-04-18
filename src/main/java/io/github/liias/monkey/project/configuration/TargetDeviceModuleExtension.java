package io.github.liias.monkey.project.configuration;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleExtension;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import io.github.liias.monkey.jps.model.JpsMonkeyModelSerializerExtension;
import io.github.liias.monkey.project.runconfig.TargetDevice;
import io.github.liias.monkey.project.runconfig.TargetSdkVersion;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TargetDeviceModuleExtension extends ModuleExtension {
  // must be same as fields in JpsMonkeyModuleProperties
  //JpsMonkeyModelSerializerExtension.MODULE_TARGET_DEVICE_ID_ATTRIBUTE
  private static final String TARGET_DEVICE_ID_ATTRIBUTE = JpsMonkeyModelSerializerExtension.MODULE_TARGET_DEVICE_ID_ATTRIBUTE;
  private static final String TARGET_SDK_VERSION_ATTRIBUTE = JpsMonkeyModelSerializerExtension.MODULE_TARGET_SDK_VERSION_ATTRIBUTE;

  private Module module;
  private final boolean writable;
  private static final Logger LOG = Logger.getInstance("#" + TargetDeviceModuleExtension.class.getName());

  private TargetDevice targetDevice;
  private TargetSdkVersion targetSdkVersion;
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
    this.targetSdkVersion = source.targetSdkVersion;
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

  @Nullable
  public TargetSdkVersion getTargetSdkVersion() {
    return targetSdkVersion;
  }

  public void setTargetSdkVersion(final TargetSdkVersion targetSdkVersion) {
    LOG.assertTrue(writable, "Writable model can be retrieved from writable ModifiableRootModel");
    this.targetSdkVersion = targetSdkVersion;
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

    final String targetSdkVersionId = element.getAttributeValue(TARGET_SDK_VERSION_ATTRIBUTE);
    if (targetSdkVersionId != null) {
      try {
        targetSdkVersion = TargetSdkVersion.fromId(targetSdkVersionId);
      } catch (IllegalArgumentException e) {
        //bad value was stored
      }
    } else {
      targetSdkVersion = null;
    }
  }

  @Override
  public void writeExternal(final Element element) throws WriteExternalException {
    if (targetDevice != null) {
      element.setAttribute(TARGET_DEVICE_ID_ATTRIBUTE, targetDevice.getId());
    }
    if (targetSdkVersion != null) {
      element.setAttribute(TARGET_SDK_VERSION_ATTRIBUTE, targetSdkVersion.getId());
    }
  }

  @Override
  public ModuleExtension getModifiableModel(final boolean writable) {
    return new TargetDeviceModuleExtension(this, writable);
  }

  @Override
  public void commit() {
    if (source.targetDevice != targetDevice) {
      source.targetDevice = targetDevice;
    }
    if (source.targetSdkVersion != targetSdkVersion) {
      source.targetSdkVersion = targetSdkVersion;
    }
  }

  @Override
  public boolean isChanged() {
    return source.targetDevice != targetDevice ||
      source.targetSdkVersion != targetSdkVersion;
  }

  @Override
  public void dispose() {
    module = null;
    targetDevice = null;
    targetSdkVersion = null;
  }

}

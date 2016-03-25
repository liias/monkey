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

  private Module myModule;
  private final boolean myWritable;
  private static final Logger LOG = Logger.getInstance("#" + TargetDeviceModuleExtension.class.getName());

  private TargetDevice myTargetDevice;
  private final TargetDeviceModuleExtension mySource;

  public static TargetDeviceModuleExtension getInstance(final Module module) {
    return ModuleRootManager.getInstance(module).getModuleExtension(TargetDeviceModuleExtension.class);
  }

  public TargetDeviceModuleExtension(Module module) {
    myModule = module;
    mySource = null;
    myWritable = false;
  }

  public TargetDeviceModuleExtension(final TargetDeviceModuleExtension source, boolean writable) {
    myWritable = writable;
    myModule = source.myModule;
    myTargetDevice = source.myTargetDevice;
    mySource = source;
  }

  @Nullable
  public TargetDevice getTargetDevice() {
    return myTargetDevice;
  }

  public void setTargetDevice(final TargetDevice targetDevice) {
    LOG.assertTrue(myWritable, "Writable model can be retrieved from writable ModifiableRootModel");
    myTargetDevice = targetDevice;
  }

  @Override
  public void readExternal(@NotNull Element element) throws InvalidDataException {
    final String targetDeviceId = element.getAttributeValue(TARGET_DEVICE_ID_ATTRIBUTE);
    if (targetDeviceId != null) {
      try {
        myTargetDevice = TargetDevice.fromId(targetDeviceId);
      } catch (IllegalArgumentException e) {
        //bad value was stored
      }
    } else {
      myTargetDevice = null;
    }
  }

  @Override
  public void writeExternal(final Element element) throws WriteExternalException {
    if (myTargetDevice != null) {
      element.setAttribute(TARGET_DEVICE_ID_ATTRIBUTE, myTargetDevice.getId());
    }
  }

  @Override
  public ModuleExtension getModifiableModel(final boolean writable) {
    return new TargetDeviceModuleExtension(this, writable);
  }

  @Override
  public void commit() {
    if (mySource != null && mySource.myTargetDevice != myTargetDevice) {
      mySource.myTargetDevice = myTargetDevice;
    }
  }

  @Override
  public boolean isChanged() {
    return mySource != null && mySource.myTargetDevice != myTargetDevice;
  }

  @Override
  public void dispose() {
    myModule = null;
    myTargetDevice = null;
  }
}

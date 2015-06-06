package ee.edio.garmin.psi;

import com.intellij.psi.tree.IElementType;
import ee.edio.garmin.MonkeyCLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MonkeyCElementType extends IElementType {
  public MonkeyCElementType(@NotNull @NonNls String debugName) {
    super(debugName, MonkeyCLanguage.INSTANCE);
  }
}

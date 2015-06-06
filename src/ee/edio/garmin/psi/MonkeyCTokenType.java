package ee.edio.garmin.psi;

import com.intellij.psi.tree.IElementType;
import ee.edio.garmin.MonkeyCLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MonkeyCTokenType extends IElementType {

  public MonkeyCTokenType(@NotNull @NonNls String debugName) {
    super(debugName, MonkeyCLanguage.INSTANCE);
  }

  @Override
  public String toString() {
    return "MonkeyCTokenType." + super.toString();
  }
}

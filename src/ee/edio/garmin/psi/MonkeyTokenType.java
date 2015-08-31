package ee.edio.garmin.psi;

import com.intellij.psi.tree.IElementType;
import ee.edio.garmin.MonkeyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MonkeyTokenType extends IElementType {

  public MonkeyTokenType(@NotNull @NonNls String debugName) {
    super(debugName, MonkeyLanguage.INSTANCE);
  }

  @Override
  public String toString() {
    return "MonkeyTokenType." + super.toString();
  }
}

package ee.edio.garmin.psi;

import com.intellij.psi.tree.IElementType;
import ee.edio.garmin.MonkeyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MonkeyElementType extends IElementType {
  public MonkeyElementType(@NotNull @NonNls String debugName) {
    super(debugName, MonkeyLanguage.INSTANCE);
  }
}

package io.github.liias.monkey.lang.psi;

import com.intellij.psi.tree.IElementType;
import io.github.liias.monkey.lang.MonkeyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MonkeyElementType extends IElementType {
  public MonkeyElementType(@NotNull @NonNls String debugName) {
    super(debugName, MonkeyLanguage.INSTANCE);
  }
}

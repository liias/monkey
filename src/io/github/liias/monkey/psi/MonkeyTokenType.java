package io.github.liias.monkey.psi;

import com.intellij.psi.tree.IElementType;
import io.github.liias.monkey.MonkeyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MonkeyTokenType extends IElementType {

  public MonkeyTokenType(@NotNull @NonNls String debugName) {
    super(debugName, MonkeyLanguage.INSTANCE);
  }

}

// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyKeyValueInitializer extends MonkeyPsiCompositeElement {

  @Nullable
  MonkeyCreator getCreator();

  @NotNull
  List<MonkeyExpression> getExpressionList();

  @Nullable
  MonkeyIdentifierSuffix getIdentifierSuffix();

  @Nullable
  MonkeyLiteral getLiteral();

  @Nullable
  MonkeySymbol getSymbol();

}

// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyForStatement extends MonkeyPsiCompositeElement {

  @Nullable
  MonkeyExpression getExpression();

  @Nullable
  MonkeyExpressionList getExpressionList();

  @Nullable
  MonkeyForInit getForInit();

  @NotNull
  MonkeyStatement getStatement();

}

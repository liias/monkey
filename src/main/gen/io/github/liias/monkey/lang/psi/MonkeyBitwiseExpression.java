// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyBitwiseExpression extends MonkeyExpression {

  @NotNull
  List<MonkeyBitwiseOperator> getBitwiseOperatorList();

  @NotNull
  List<MonkeyExpression> getExpressionList();

}

// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ee.edio.garmin.psi.MonkeyCTypes.*;
import ee.edio.garmin.psi.*;

public class MonkeyCHasExpressionImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCHasExpression {

  public MonkeyCHasExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitHasExpression(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MonkeyCInstanceOfExpression getInstanceOfExpression() {
    return findNotNullChildByClass(MonkeyCInstanceOfExpression.class);
  }

  @Override
  @Nullable
  public MonkeyCSymbol getSymbol() {
    return findChildByClass(MonkeyCSymbol.class);
  }

}

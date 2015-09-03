// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ee.edio.garmin.psi.MonkeyTypes.*;
import ee.edio.garmin.psi.*;

public class MonkeyUnaryExpressionImpl extends MonkeyExpressionImpl implements MonkeyUnaryExpression {

  public MonkeyUnaryExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitUnaryExpression(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyUnaryExpression getUnaryExpression() {
    return findChildByClass(MonkeyUnaryExpression.class);
  }

  @Override
  @Nullable
  public MonkeyUnaryExpressionNotPlusMinus getUnaryExpressionNotPlusMinus() {
    return findChildByClass(MonkeyUnaryExpressionNotPlusMinus.class);
  }

}

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

public class MonkeyCConditionalExpressionImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCConditionalExpression {

  public MonkeyCConditionalExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitConditionalExpression(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCConditionalExpression getConditionalExpression() {
    return findChildByClass(MonkeyCConditionalExpression.class);
  }

  @Override
  @NotNull
  public MonkeyCConditionalOrExpression getConditionalOrExpression() {
    return findNotNullChildByClass(MonkeyCConditionalOrExpression.class);
  }

  @Override
  @Nullable
  public MonkeyCExpression getExpression() {
    return findChildByClass(MonkeyCExpression.class);
  }

}

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

public class MonkeyCExpressionImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCExpression {

  public MonkeyCExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitExpression(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCAssignmentOperator getAssignmentOperator() {
    return findChildByClass(MonkeyCAssignmentOperator.class);
  }

  @Override
  @NotNull
  public MonkeyCConditionalExpression getConditionalExpression() {
    return findNotNullChildByClass(MonkeyCConditionalExpression.class);
  }

  @Override
  @Nullable
  public MonkeyCExpression getExpression() {
    return findChildByClass(MonkeyCExpression.class);
  }

}

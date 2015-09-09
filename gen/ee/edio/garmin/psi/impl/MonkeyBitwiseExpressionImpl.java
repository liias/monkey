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

public class MonkeyBitwiseExpressionImpl extends MonkeyExpressionImpl implements MonkeyBitwiseExpression {

  public MonkeyBitwiseExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitBitwiseExpression(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MonkeyBitwiseOperator> getBitwiseOperatorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyBitwiseOperator.class);
  }

  @Override
  @NotNull
  public List<MonkeyUnaryExpression> getUnaryExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyUnaryExpression.class);
  }

}

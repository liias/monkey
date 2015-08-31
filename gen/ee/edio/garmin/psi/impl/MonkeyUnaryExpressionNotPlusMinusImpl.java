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

public class MonkeyUnaryExpressionNotPlusMinusImpl extends MonkeyPsiCompositeElementImpl implements MonkeyUnaryExpressionNotPlusMinus {

  public MonkeyUnaryExpressionNotPlusMinusImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitUnaryExpressionNotPlusMinus(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyPrimary getPrimary() {
    return findChildByClass(MonkeyPrimary.class);
  }

  @Override
  @NotNull
  public List<MonkeySelector> getSelectorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeySelector.class);
  }

  @Override
  @Nullable
  public MonkeyUnaryExpression getUnaryExpression() {
    return findChildByClass(MonkeyUnaryExpression.class);
  }

}

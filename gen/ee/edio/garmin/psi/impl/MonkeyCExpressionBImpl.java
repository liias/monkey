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

public class MonkeyCExpressionBImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCExpressionB {

  public MonkeyCExpressionBImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitExpressionB(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCNewDefinition getNewDefinition() {
    return findChildByClass(MonkeyCNewDefinition.class);
  }

  @Override
  @Nullable
  public MonkeyCOpExpression getOpExpression() {
    return findChildByClass(MonkeyCOpExpression.class);
  }

}

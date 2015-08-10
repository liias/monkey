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

public class MonkeyCForStatementImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCForStatement {

  public MonkeyCForStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitForStatement(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCExpression getExpression() {
    return findChildByClass(MonkeyCExpression.class);
  }

  @Override
  @Nullable
  public MonkeyCExpressionList getExpressionList() {
    return findChildByClass(MonkeyCExpressionList.class);
  }

  @Override
  @Nullable
  public MonkeyCForInit getForInit() {
    return findChildByClass(MonkeyCForInit.class);
  }

  @Override
  @NotNull
  public MonkeyCStatement getStatement() {
    return findNotNullChildByClass(MonkeyCStatement.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}

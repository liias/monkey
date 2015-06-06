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

public class MonkeyCFunctionMembersImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCFunctionMembers {

  public MonkeyCFunctionMembersImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitFunctionMembers(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCCallExpression getCallExpression() {
    return findChildByClass(MonkeyCCallExpression.class);
  }

  @Override
  @Nullable
  public MonkeyCIfStatement getIfStatement() {
    return findChildByClass(MonkeyCIfStatement.class);
  }

  @Override
  @Nullable
  public MonkeyCReturnStatement getReturnStatement() {
    return findChildByClass(MonkeyCReturnStatement.class);
  }

  @Override
  @Nullable
  public MonkeyCVariableDefinition getVariableDefinition() {
    return findChildByClass(MonkeyCVariableDefinition.class);
  }

}

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

public class MonkeyCStatementImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCStatement {

  public MonkeyCStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitStatement(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCBlock getBlock() {
    return findChildByClass(MonkeyCBlock.class);
  }

  @Override
  @Nullable
  public MonkeyCExpression getExpression() {
    return findChildByClass(MonkeyCExpression.class);
  }

  @Override
  @Nullable
  public MonkeyCForStatement getForStatement() {
    return findChildByClass(MonkeyCForStatement.class);
  }

  @Override
  @Nullable
  public MonkeyCParExpression getParExpression() {
    return findChildByClass(MonkeyCParExpression.class);
  }

  @Override
  @NotNull
  public List<MonkeyCStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyCStatement.class);
  }

  @Override
  @Nullable
  public MonkeyCSwitchBlockStatementGroups getSwitchBlockStatementGroups() {
    return findChildByClass(MonkeyCSwitchBlockStatementGroups.class);
  }

  @Override
  @Nullable
  public MonkeyCTryStatement getTryStatement() {
    return findChildByClass(MonkeyCTryStatement.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}

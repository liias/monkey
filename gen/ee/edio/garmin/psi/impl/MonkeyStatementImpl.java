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

public class MonkeyStatementImpl extends MonkeyPsiCompositeElementImpl implements MonkeyStatement {

  public MonkeyStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitStatement(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyBlock getBlock() {
    return findChildByClass(MonkeyBlock.class);
  }

  @Override
  @Nullable
  public MonkeyExpression getExpression() {
    return findChildByClass(MonkeyExpression.class);
  }

  @Override
  @Nullable
  public MonkeyForStatement getForStatement() {
    return findChildByClass(MonkeyForStatement.class);
  }

  @Override
  @Nullable
  public MonkeyParExpression getParExpression() {
    return findChildByClass(MonkeyParExpression.class);
  }

  @Override
  @NotNull
  public List<MonkeyStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyStatement.class);
  }

  @Override
  @Nullable
  public MonkeySwitchBlockStatementGroups getSwitchBlockStatementGroups() {
    return findChildByClass(MonkeySwitchBlockStatementGroups.class);
  }

  @Override
  @Nullable
  public MonkeyTryStatement getTryStatement() {
    return findChildByClass(MonkeyTryStatement.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}

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

public class MonkeyCBlockStatementImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCBlockStatement {

  public MonkeyCBlockStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitBlockStatement(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCClassDeclaration getClassDeclaration() {
    return findChildByClass(MonkeyCClassDeclaration.class);
  }

  @Override
  @Nullable
  public MonkeyCLocalVariableDeclarationStatement getLocalVariableDeclarationStatement() {
    return findChildByClass(MonkeyCLocalVariableDeclarationStatement.class);
  }

  @Override
  @Nullable
  public MonkeyCStatement getStatement() {
    return findChildByClass(MonkeyCStatement.class);
  }

}

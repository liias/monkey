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

public class MonkeyCFunctionDeclarationImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCFunctionDeclaration {

  public MonkeyCFunctionDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitFunctionDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MonkeyCBlockStatement> getBlockStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyCBlockStatement.class);
  }

  @Override
  @Nullable
  public MonkeyCExplicitConstructorInvocation getExplicitConstructorInvocation() {
    return findChildByClass(MonkeyCExplicitConstructorInvocation.class);
  }

  @Override
  @NotNull
  public MonkeyCFormalParameters getFormalParameters() {
    return findNotNullChildByClass(MonkeyCFormalParameters.class);
  }

  @Override
  @NotNull
  public MonkeyCModifiers getModifiers() {
    return findNotNullChildByClass(MonkeyCModifiers.class);
  }

  @Override
  @Nullable
  public MonkeyCQualifiedNameList getQualifiedNameList() {
    return findChildByClass(MonkeyCQualifiedNameList.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}

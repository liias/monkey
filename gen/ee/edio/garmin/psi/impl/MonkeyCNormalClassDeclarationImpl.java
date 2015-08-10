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

public class MonkeyCNormalClassDeclarationImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCNormalClassDeclaration {

  public MonkeyCNormalClassDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitNormalClassDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MonkeyCClassBody getClassBody() {
    return findNotNullChildByClass(MonkeyCClassBody.class);
  }

  @Override
  @NotNull
  public MonkeyCModifiers getModifiers() {
    return findNotNullChildByClass(MonkeyCModifiers.class);
  }

  @Override
  @Nullable
  public MonkeyCType getType() {
    return findChildByClass(MonkeyCType.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}

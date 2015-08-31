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

public class MonkeyNormalClassDeclarationImpl extends MonkeyPsiCompositeElementImpl implements MonkeyNormalClassDeclaration {

  public MonkeyNormalClassDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitNormalClassDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MonkeyClassBody getClassBody() {
    return findNotNullChildByClass(MonkeyClassBody.class);
  }

  @Override
  @NotNull
  public MonkeyModifiers getModifiers() {
    return findNotNullChildByClass(MonkeyModifiers.class);
  }

  @Override
  @Nullable
  public MonkeyType getType() {
    return findChildByClass(MonkeyType.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}

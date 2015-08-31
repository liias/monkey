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

public class MonkeyCClassBodyDeclarationImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCClassBodyDeclaration {

  public MonkeyCClassBodyDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitClassBodyDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCBlock getBlock() {
    return findChildByClass(MonkeyCBlock.class);
  }

  @Override
  @Nullable
  public MonkeyCMemberDecl getMemberDecl() {
    return findChildByClass(MonkeyCMemberDecl.class);
  }

  @Override
  @Nullable
  public MonkeyCUsingDeclaration getUsingDeclaration() {
    return findChildByClass(MonkeyCUsingDeclaration.class);
  }

}

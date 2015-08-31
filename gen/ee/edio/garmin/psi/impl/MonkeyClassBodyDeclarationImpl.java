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

public class MonkeyClassBodyDeclarationImpl extends MonkeyPsiCompositeElementImpl implements MonkeyClassBodyDeclaration {

  public MonkeyClassBodyDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitClassBodyDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyBlock getBlock() {
    return findChildByClass(MonkeyBlock.class);
  }

  @Override
  @Nullable
  public MonkeyMemberDecl getMemberDecl() {
    return findChildByClass(MonkeyMemberDecl.class);
  }

  @Override
  @Nullable
  public MonkeyUsingDeclaration getUsingDeclaration() {
    return findChildByClass(MonkeyUsingDeclaration.class);
  }

}

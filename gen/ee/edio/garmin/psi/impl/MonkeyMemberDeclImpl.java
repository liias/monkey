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

public class MonkeyMemberDeclImpl extends MonkeyPsiCompositeElementImpl implements MonkeyMemberDecl {

  public MonkeyMemberDeclImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitMemberDecl(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyClassDeclaration getClassDeclaration() {
    return findChildByClass(MonkeyClassDeclaration.class);
  }

  @Override
  @Nullable
  public MonkeyConstDeclaration getConstDeclaration() {
    return findChildByClass(MonkeyConstDeclaration.class);
  }

  @Override
  @Nullable
  public MonkeyFieldDeclaration getFieldDeclaration() {
    return findChildByClass(MonkeyFieldDeclaration.class);
  }

  @Override
  @Nullable
  public MonkeyFunctionDeclaration getFunctionDeclaration() {
    return findChildByClass(MonkeyFunctionDeclaration.class);
  }

}

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

public class MonkeyCTypeDeclarationImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCTypeDeclaration {

  public MonkeyCTypeDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitTypeDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCClassDeclaration getClassDeclaration() {
    return findChildByClass(MonkeyCClassDeclaration.class);
  }

  @Override
  @Nullable
  public MonkeyCConstDeclaration getConstDeclaration() {
    return findChildByClass(MonkeyCConstDeclaration.class);
  }

  @Override
  @Nullable
  public MonkeyCFieldDeclaration getFieldDeclaration() {
    return findChildByClass(MonkeyCFieldDeclaration.class);
  }

  @Override
  @Nullable
  public MonkeyCModuleDeclaration getModuleDeclaration() {
    return findChildByClass(MonkeyCModuleDeclaration.class);
  }

}

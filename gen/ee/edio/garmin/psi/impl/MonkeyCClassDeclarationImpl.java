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

public class MonkeyCClassDeclarationImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCClassDeclaration {

  public MonkeyCClassDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitClassDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCEnumDeclaration getEnumDeclaration() {
    return findChildByClass(MonkeyCEnumDeclaration.class);
  }

  @Override
  @Nullable
  public MonkeyCNormalClassDeclaration getNormalClassDeclaration() {
    return findChildByClass(MonkeyCNormalClassDeclaration.class);
  }

}

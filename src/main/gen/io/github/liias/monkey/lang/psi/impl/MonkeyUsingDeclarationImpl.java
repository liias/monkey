// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.liias.monkey.lang.psi.MonkeyTypes.*;
import io.github.liias.monkey.lang.psi.*;

public class MonkeyUsingDeclarationImpl extends MonkeyPsiCompositeElementImpl implements MonkeyUsingDeclaration {

  public MonkeyUsingDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitUsingDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyComponentName getComponentName() {
    return findChildByClass(MonkeyComponentName.class);
  }

  @Override
  @NotNull
  public MonkeyQualifiedName getQualifiedName() {
    return findNotNullChildByClass(MonkeyQualifiedName.class);
  }

}

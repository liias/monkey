// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.liias.monkey.psi.MonkeyTypes.*;
import io.github.liias.monkey.psi.*;

public class MonkeyClassDeclarationImpl extends AbstractMonkeyPsiClass implements MonkeyClassDeclaration {

  public MonkeyClassDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitClassDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyClassBody getClassBody() {
    return findChildByClass(MonkeyClassBody.class);
  }

  @Override
  @NotNull
  public MonkeyComponentName getComponentName() {
    return findNotNullChildByClass(MonkeyComponentName.class);
  }

}

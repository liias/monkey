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

public class MonkeyArrayCreatorImpl extends MonkeyPsiCompositeElementImpl implements MonkeyArrayCreator {

  public MonkeyArrayCreatorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitArrayCreator(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyDictionaryCreator getDictionaryCreator() {
    return findChildByClass(MonkeyDictionaryCreator.class);
  }

  @Override
  @Nullable
  public MonkeyExpression getExpression() {
    return findChildByClass(MonkeyExpression.class);
  }

}

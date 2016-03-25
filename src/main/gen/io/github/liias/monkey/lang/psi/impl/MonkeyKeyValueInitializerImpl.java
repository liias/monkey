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

public class MonkeyKeyValueInitializerImpl extends MonkeyPsiCompositeElementImpl implements MonkeyKeyValueInitializer {

  public MonkeyKeyValueInitializerImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitKeyValueInitializer(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCreator getCreator() {
    return findChildByClass(MonkeyCreator.class);
  }

  @Override
  @NotNull
  public List<MonkeyExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyExpression.class);
  }

  @Override
  @Nullable
  public MonkeyIdentifierSuffix getIdentifierSuffix() {
    return findChildByClass(MonkeyIdentifierSuffix.class);
  }

  @Override
  @Nullable
  public MonkeyLiteral getLiteral() {
    return findChildByClass(MonkeyLiteral.class);
  }

  @Override
  @Nullable
  public MonkeySymbol getSymbol() {
    return findChildByClass(MonkeySymbol.class);
  }

}

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

public class MonkeyUnaryExpressionImpl extends MonkeyExpressionImpl implements MonkeyUnaryExpression {

  public MonkeyUnaryExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitUnaryExpression(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCreator getCreator() {
    return findChildByClass(MonkeyCreator.class);
  }

  @Override
  @Nullable
  public MonkeyExpression getExpression() {
    return findChildByClass(MonkeyExpression.class);
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
  @NotNull
  public List<MonkeySelector> getSelectorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeySelector.class);
  }

  @Override
  @Nullable
  public MonkeySymbol getSymbol() {
    return findChildByClass(MonkeySymbol.class);
  }

}

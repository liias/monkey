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

public class MonkeyIdentifierSuffixImpl extends MonkeyPsiCompositeElementImpl implements MonkeyIdentifierSuffix {

  public MonkeyIdentifierSuffixImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitIdentifierSuffix(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyArguments getArguments() {
    return findChildByClass(MonkeyArguments.class);
  }

  @Override
  @NotNull
  public List<MonkeyExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyExpression.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}

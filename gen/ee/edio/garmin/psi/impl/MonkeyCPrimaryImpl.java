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

public class MonkeyCPrimaryImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCPrimary {

  public MonkeyCPrimaryImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitPrimary(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCCreator getCreator() {
    return findChildByClass(MonkeyCCreator.class);
  }

  @Override
  @Nullable
  public MonkeyCIdentifierSuffix getIdentifierSuffix() {
    return findChildByClass(MonkeyCIdentifierSuffix.class);
  }

  @Override
  @Nullable
  public MonkeyCLiteral getLiteral() {
    return findChildByClass(MonkeyCLiteral.class);
  }

  @Override
  @Nullable
  public MonkeyCParExpression getParExpression() {
    return findChildByClass(MonkeyCParExpression.class);
  }

  @Override
  @Nullable
  public MonkeyCSymbol getSymbol() {
    return findChildByClass(MonkeyCSymbol.class);
  }

}

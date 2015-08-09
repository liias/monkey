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

public class MonkeyCLiteralImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCLiteral {

  public MonkeyCLiteralImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitLiteral(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCDoubleliteral getDoubleliteral() {
    return findChildByClass(MonkeyCDoubleliteral.class);
  }

  @Override
  @Nullable
  public MonkeyCFloatliteral getFloatliteral() {
    return findChildByClass(MonkeyCFloatliteral.class);
  }

  @Override
  @Nullable
  public MonkeyCIntliteral getIntliteral() {
    return findChildByClass(MonkeyCIntliteral.class);
  }

  @Override
  @Nullable
  public MonkeyCLongliteral getLongliteral() {
    return findChildByClass(MonkeyCLongliteral.class);
  }

  @Override
  @Nullable
  public PsiElement getCharliteral() {
    return findChildByType(CHARLITERAL);
  }

  @Override
  @Nullable
  public PsiElement getStringliteral() {
    return findChildByType(STRINGLITERAL);
  }

}

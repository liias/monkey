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

public class MonkeyCVariableDeclaratorImpl extends MonkeyCNamedElementImpl implements MonkeyCVariableDeclarator {

  public MonkeyCVariableDeclaratorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitVariableDeclarator(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCVariableInitializer getVariableInitializer() {
    return findChildByClass(MonkeyCVariableInitializer.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

  public String getKey() {
    return MonkeyCPsiImplUtil.getKey(this);
  }

  public String getValue() {
    return MonkeyCPsiImplUtil.getValue(this);
  }

}

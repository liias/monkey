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

public class MonkeyVariableDeclaratorImpl extends MonkeyNamedElementImpl implements MonkeyVariableDeclarator {

  public MonkeyVariableDeclaratorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitVariableDeclarator(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyVariableInitializer getVariableInitializer() {
    return findChildByClass(MonkeyVariableInitializer.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

  public String getKey() {
    return MonkeyPsiImplUtil.getKey(this);
  }

  public String getValue() {
    return MonkeyPsiImplUtil.getValue(this);
  }

  public PsiElement setName(String newName) {
    return MonkeyPsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return MonkeyPsiImplUtil.getNameIdentifier(this);
  }

}

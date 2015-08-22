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

public class MonkeyCArrayCreatorImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCArrayCreator {

  public MonkeyCArrayCreatorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitArrayCreator(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCArrayInitializer getArrayInitializer() {
    return findChildByClass(MonkeyCArrayInitializer.class);
  }

  @Override
  @Nullable
  public MonkeyCCreatedName getCreatedName() {
    return findChildByClass(MonkeyCCreatedName.class);
  }

  @Override
  @NotNull
  public List<MonkeyCExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyCExpression.class);
  }

  @Override
  @Nullable
  public MonkeyCNewArrayInitializer getNewArrayInitializer() {
    return findChildByClass(MonkeyCNewArrayInitializer.class);
  }

  @Override
  @Nullable
  public MonkeyCNewDictionaryInitializer getNewDictionaryInitializer() {
    return findChildByClass(MonkeyCNewDictionaryInitializer.class);
  }

}

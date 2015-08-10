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

public class MonkeyCCreatorImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCCreator {

  public MonkeyCCreatorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitCreator(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCArrayCreator getArrayCreator() {
    return findChildByClass(MonkeyCArrayCreator.class);
  }

  @Override
  @Nullable
  public MonkeyCClassCreatorRest getClassCreatorRest() {
    return findChildByClass(MonkeyCClassCreatorRest.class);
  }

  @Override
  @Nullable
  public MonkeyCClassOrInterfaceType getClassOrInterfaceType() {
    return findChildByClass(MonkeyCClassOrInterfaceType.class);
  }

}

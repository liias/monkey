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

public class MonkeyCExpImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCExp {

  public MonkeyCExpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitExp(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCNewDefinition getNewDefinition() {
    return findChildByClass(MonkeyCNewDefinition.class);
  }

  @Override
  @Nullable
  public MonkeyCVariableName getVariableName() {
    return findChildByClass(MonkeyCVariableName.class);
  }

}

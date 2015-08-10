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

public class MonkeyCExplicitConstructorInvocationImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCExplicitConstructorInvocation {

  public MonkeyCExplicitConstructorInvocationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitExplicitConstructorInvocation(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MonkeyCArguments getArguments() {
    return findNotNullChildByClass(MonkeyCArguments.class);
  }

  @Override
  @Nullable
  public MonkeyCPrimary getPrimary() {
    return findChildByClass(MonkeyCPrimary.class);
  }

}

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

public class MonkeyCForInitImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCForInit {

  public MonkeyCForInitImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitForInit(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCExpressionList getExpressionList() {
    return findChildByClass(MonkeyCExpressionList.class);
  }

  @Override
  @Nullable
  public MonkeyCLocalVariableDeclaration getLocalVariableDeclaration() {
    return findChildByClass(MonkeyCLocalVariableDeclaration.class);
  }

}

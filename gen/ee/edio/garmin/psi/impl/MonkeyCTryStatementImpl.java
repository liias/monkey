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

public class MonkeyCTryStatementImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCTryStatement {

  public MonkeyCTryStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitTryStatement(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MonkeyCBlock> getBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyCBlock.class);
  }

  @Override
  @Nullable
  public MonkeyCCatches getCatches() {
    return findChildByClass(MonkeyCCatches.class);
  }

}

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

public class MonkeyCClassMembersImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCClassMembers {

  public MonkeyCClassMembersImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitClassMembers(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCFunctionDefinition getFunctionDefinition() {
    return findChildByClass(MonkeyCFunctionDefinition.class);
  }

  @Override
  @Nullable
  public MonkeyCVariableDefinition getVariableDefinition() {
    return findChildByClass(MonkeyCVariableDefinition.class);
  }

}

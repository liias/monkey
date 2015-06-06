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

public class MonkeyCFunctionDefinitionImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCFunctionDefinition {

  public MonkeyCFunctionDefinitionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitFunctionDefinition(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MonkeyCFunctionArgs getFunctionArgs() {
    return findChildByClass(MonkeyCFunctionArgs.class);
  }

  @Override
  @Nullable
  public MonkeyCFunctionBody getFunctionBody() {
    return findChildByClass(MonkeyCFunctionBody.class);
  }

  @Override
  @Nullable
  public MonkeyCFunctionName getFunctionName() {
    return findChildByClass(MonkeyCFunctionName.class);
  }

}

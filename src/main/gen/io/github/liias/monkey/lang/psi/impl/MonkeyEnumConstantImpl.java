// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.liias.monkey.lang.psi.MonkeyTypes.*;
import io.github.liias.monkey.lang.psi.*;

public class MonkeyEnumConstantImpl extends AbstractMonkeyComponentImpl implements MonkeyEnumConstant {

  public MonkeyEnumConstantImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitEnumConstant(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MonkeyComponentName getComponentName() {
    return findNotNullChildByClass(MonkeyComponentName.class);
  }

  @Override
  @Nullable
  public PsiElement getIntliteral() {
    return findChildByType(INTLITERAL);
  }

}

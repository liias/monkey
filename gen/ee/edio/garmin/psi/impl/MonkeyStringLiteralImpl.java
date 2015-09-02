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
import ee.edio.garmin.MonkeyStringLiteralEscaper;

public class MonkeyStringLiteralImpl extends MonkeyPsiCompositeElementImpl implements MonkeyStringLiteral {

  public MonkeyStringLiteralImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) ((MonkeyVisitor)visitor).visitStringLiteral(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getString() {
    return findNotNullChildByType(STRING);
  }

  public boolean isValidHost() {
    return MonkeyPsiImplUtil.isValidHost(this);
  }

  @NotNull
  public MonkeyStringLiteral updateText(String text) {
    return MonkeyPsiImplUtil.updateText(this, text);
  }

  @NotNull
  public MonkeyStringLiteralEscaper createLiteralTextEscaper() {
    return MonkeyPsiImplUtil.createLiteralTextEscaper(this);
  }

}

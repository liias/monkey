package ee.edio.garmin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import ee.edio.garmin.psi.MonkeyNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class MonkeyNamedElementImpl extends ASTWrapperPsiElement implements MonkeyNamedElement {

  public MonkeyNamedElementImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public String getName() {
    return getNameIdentifier().getText();
  }

  /*
  @Nullable
  @Override
  public PsiElement getNameIdentifier() {
    return null;
  }

  @Override
  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    return null;
  }
  */
}

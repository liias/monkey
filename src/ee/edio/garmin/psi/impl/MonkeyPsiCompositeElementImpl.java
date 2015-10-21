package ee.edio.garmin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import ee.edio.garmin.psi.MonkeyPsiCompositeElement;
import org.jetbrains.annotations.NotNull;

public class MonkeyPsiCompositeElementImpl extends ASTWrapperPsiElement implements MonkeyPsiCompositeElement {

  public MonkeyPsiCompositeElementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public IElementType getTokenType() {
    return getNode().getElementType();
  }

  @Override
  public String toString() {
    return getTokenType().toString();
  }
}

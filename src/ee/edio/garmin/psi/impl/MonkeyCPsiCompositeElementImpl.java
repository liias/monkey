package ee.edio.garmin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import ee.edio.garmin.psi.MonkeyCPsiCompositeElement;
import org.jetbrains.annotations.NotNull;

public class MonkeyCPsiCompositeElementImpl extends ASTWrapperPsiElement implements MonkeyCPsiCompositeElement {

  public MonkeyCPsiCompositeElementImpl(@NotNull ASTNode node) {
    super(node);
  }

}

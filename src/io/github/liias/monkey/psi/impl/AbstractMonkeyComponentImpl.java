package io.github.liias.monkey.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import io.github.liias.monkey.psi.MonkeyComponent;
import io.github.liias.monkey.psi.MonkeyComponentName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

abstract public class AbstractMonkeyComponentImpl extends MonkeyPsiCompositeElementImpl implements MonkeyComponent {
  public AbstractMonkeyComponentImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public String getName() {
    final MonkeyComponentName name = getComponentName();
    if (name != null) {
      return name.getText();
    }
    return super.getName();
  }

  @Override
  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    final MonkeyComponentName componentName = getComponentName();
    if (componentName != null) {
      componentName.setName(name);
    }
    return this;
  }

  @Nullable
  @Override
  public PsiElement getNameIdentifier() {
    return getComponentName();
  }

  @Override
  public ItemPresentation getPresentation() {
    return new ItemPresentation() {
      @Nullable
      @Override
      public String getPresentableText() {
        return getName();
      }

      @Nullable
      @Override
      public String getLocationString() {
        return null;
      }

      @Nullable
      @Override
      public Icon getIcon(boolean unused) {
        return AbstractMonkeyComponentImpl.this.getIcon(0);
      }

    };
  }

  @Override
  public int getTextOffset() {
    final MonkeyComponentName name = getComponentName();
    return name != null ? name.getTextOffset() : super.getTextOffset();
  }
}

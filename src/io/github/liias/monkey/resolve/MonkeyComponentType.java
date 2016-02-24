package io.github.liias.monkey.resolve;

import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import io.github.liias.monkey.psi.MonkeyClass;
import io.github.liias.monkey.psi.MonkeyComponentName;
import io.github.liias.monkey.psi.MonkeyFieldDeclaration;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public enum MonkeyComponentType {
  CLASS(AllIcons.Nodes.Class),
  FIELD(AllIcons.Nodes.Field);

  private final Icon myIcon;

  MonkeyComponentType(final Icon icon) {
    myIcon = icon;
  }

  @Nullable
  public static MonkeyComponentType typeOf(@Nullable PsiElement element) {
    if (element instanceof MonkeyComponentName) {
      return typeOf(element.getParent());
    }
    if (element instanceof MonkeyClass) {
      return CLASS;
    }
    if (element instanceof MonkeyFieldDeclaration) {
      return FIELD;
    }
    return null;
  }

}

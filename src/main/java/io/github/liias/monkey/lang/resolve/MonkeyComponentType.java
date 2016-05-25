package io.github.liias.monkey.lang.resolve;

import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import io.github.liias.monkey.lang.psi.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public enum MonkeyComponentType {
  MODULE(AllIcons.Nodes.Package),
  CLASS(AllIcons.Nodes.Class),
  FUNCTION(AllIcons.Nodes.Function),
  PARAMETER(AllIcons.Nodes.Parameter),
  FIELD(AllIcons.Nodes.Field),
  CONSTANT(AllIcons.Nodes.Field),
  ENUM(AllIcons.Nodes.Enum),
  VARIABLE(AllIcons.Nodes.Variable);

  private final Icon myIcon;

  MonkeyComponentType(final Icon icon) {
    myIcon = icon;
  }

  @Nullable
  public static MonkeyComponentType typeOf(@Nullable PsiElement element) {
    if (element instanceof MonkeyComponentName) {
      return typeOf(element.getParent());
    }
    if (element instanceof MonkeyModuleDeclaration) {
      return MODULE;
    }
    if (element instanceof MonkeyClass) {
      return CLASS;
    }
    if (element instanceof MonkeyFunctionDeclaration) {
      return FUNCTION;
    }
    if (element instanceof MonkeyFormalParameterDeclarations) {
      return PARAMETER;
    }
    if (element instanceof MonkeyFieldDeclaration) {
      return FIELD;
    }
    if (element instanceof MonkeyConstDeclaration) {
      return CONSTANT;
    }
    if (element instanceof MonkeyEnumConstant) {
      return ENUM;
    }
    if (element instanceof MonkeyVariableDeclaration) {
      return VARIABLE;
    }
    return null;
  }

}

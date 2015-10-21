package ee.edio.garmin;

import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import ee.edio.garmin.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public enum MonkeyComponentType {

  CLASS(AllIcons.Nodes.Class) {
    @Override
    public Icon getIcon(@NotNull MonkeyComponent component) {
      return component instanceof MonkeyEnumBodyDeclarations ? AllIcons.Nodes.Enum
          : getIcon();
    }
  },
  FUNCTION(AllIcons.Nodes.Function),
  METHOD(AllIcons.Nodes.Method),
  VARIABLE(AllIcons.Nodes.Variable),
  FIELD(AllIcons.Nodes.Field),
  PARAMETER(AllIcons.Nodes.Parameter),
  TYPEDEF(AllIcons.Nodes.Annotationtype),
  CONSTRUCTOR(AllIcons.Nodes.Method),
  OPERATOR(AllIcons.Nodes.ClassInitializer),
  LABEL(AllIcons.Nodes.Variable);


  private final Icon myIcon;

  MonkeyComponentType(final Icon icon) {
    myIcon = icon;
  }

  public int getKey() {
    return ordinal();
  }

  public Icon getIcon() {
    return myIcon;
  }

  public Icon getIcon(@NotNull MonkeyComponent component) {
    // Overridden in appropriate subclasses
    return getIcon();
  }

  @Nullable
  public static MonkeyComponentType valueOf(int key) {
    return key >= 0 && key < values().length ? values()[key] : null;
  }

  @Nullable
  public static MonkeyComponentType typeOf(@Nullable PsiElement element) {
    if (element instanceof MonkeyComponentName) {
      return typeOf(element.getParent());
    }
    if ((element instanceof MonkeyComponent && PsiTreeUtil.getParentOfType(element, MonkeyFormalParameterDecls.class, false) != null)) {
      return PARAMETER;
    }
    if (element instanceof MonkeyClassDeclaration) {
      return CLASS;
    }
    if (element instanceof MonkeyEnumBodyDeclarations) {
      return FIELD;
    }

    if (element instanceof MonkeyFunctionDeclaration) {
      return FUNCTION;
    }
    if (element instanceof MonkeyVariableDeclarator) {
      return VARIABLE;
    }
    return null;
  }

}

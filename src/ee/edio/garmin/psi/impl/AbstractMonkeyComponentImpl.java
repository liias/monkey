package ee.edio.garmin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import ee.edio.garmin.psi.MonkeyComponent;
import ee.edio.garmin.psi.MonkeyComponentName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

  public String toString(){
    return "MonkeyComponent:" + getText();
  }
}

package ee.edio.garmin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import ee.edio.garmin.psi.MonkeyId;
import ee.edio.garmin.psi.MonkeyNamedElement;
import ee.edio.garmin.util.MonkeyElementGenerator;
import org.jetbrains.annotations.NotNull;

public abstract class MonkeyNamedElementImpl extends ASTWrapperPsiElement implements MonkeyNamedElement {

  public MonkeyNamedElementImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public String getName() {
    return getId().getText();
  }

  @Override
  public PsiElement setName(@NotNull String newElementName) throws IncorrectOperationException {
    final MonkeyId identifier = getId();
    final MonkeyId identifierNew = MonkeyElementGenerator.createIdentifierFromText(getProject(), newElementName);

    if (identifierNew != null) {
      getNode().replaceChild(identifier.getNode(), identifierNew.getNode());
    }
    return this;
  }

  @Override
  public PsiElement getNameIdentifier() {
    return this;
  }

  @NotNull
  @Override
  public MonkeyId getId() {
    /*if (PsiTreeUtil.instanceOf(this, MonkeyId.class)) {
      return (MonkeyId) this;
    }*/
    return PsiTreeUtil.getChildOfType(this, MonkeyId.class);
  }
}

package ee.edio.garmin.psi.impl;


import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import ee.edio.garmin.psi.MonkeyNamedElement;
import ee.edio.garmin.psi.MonkeyTypes;

public class MonkeyPsiImplUtil {

  public static String getName(PsiNamedElement element) {
    return getKey(element);
  }

  public static PsiElement setName(PsiNamedElement element, String newName) {
    /*ASTNode keyNode = element.getNode().findChildByType(MonkeyTypes.IDENTIFIER);
    if (keyNode != null) {
      MonkeyNamedElement namedEl = MonkeyElementFactory.createProperty(element.getProject(), newName);
      ASTNode newKeyNode = namedEl.getFirstChild().getNode();
      element.getNode().replaceChild(keyNode, newKeyNode);
    }*/
    return element;
  }

  public static PsiElement getNameIdentifier(MonkeyNamedElement element) {
    ASTNode keyNode = element.getNode().findChildByType(MonkeyTypes.IDENTIFIER);
    if (keyNode != null) {
      return keyNode.getPsi();
    } else {
      return null;
    }
  }


  public static String getKey(PsiNamedElement element) {
    ASTNode keyNode = element.getNode().findChildByType(MonkeyTypes.IDENTIFIER);
    if (keyNode != null) {
      return keyNode.getText();
    } else {
      return "";
    }
  }

  public static String getValue(PsiNamedElement element) {
    ASTNode valueNode = element.getNode().findChildByType(MonkeyTypes.IDENTIFIER);
    if (valueNode != null) {
      return valueNode.getText();
    } else {
      return null;
    }
  }
}

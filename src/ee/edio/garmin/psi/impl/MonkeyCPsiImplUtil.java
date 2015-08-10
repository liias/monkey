package ee.edio.garmin.psi.impl;


import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiNamedElement;
import ee.edio.garmin.psi.MonkeyCTypes;

public class MonkeyCPsiImplUtil {
  public static String getKey(PsiNamedElement element) {
    ASTNode keyNode = element.getNode().findChildByType(MonkeyCTypes.IDENTIFIER);
    if (keyNode != null) {
      return keyNode.getText();
    } else {
      return null;
    }
  }

  public static String getValue(PsiNamedElement element) {
    ASTNode valueNode = element.getNode().findChildByType(MonkeyCTypes.IDENTIFIER);
    if (valueNode != null) {
      return valueNode.getText();
    } else {
      return null;
    }
  }
}

package ee.edio.garmin.psi.impl;


import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import ee.edio.garmin.MonkeyStringLiteralEscaper;
import ee.edio.garmin.psi.MonkeyStringLiteral;
import ee.edio.garmin.psi.MonkeyTypes;
import org.jetbrains.annotations.NotNull;

public class MonkeyPsiImplUtil {

  public static String getName(PsiNamedElement element) {
    return getKey(element);
  }

  public static String getKey(PsiNamedElement element) {
    ASTNode keyNode = element.getNode().findChildByType(MonkeyTypes.IDENTIFIER);
    if (keyNode != null) {
      return keyNode.getText();
    } else {
      return "";
    }
  }


  // to satisfy PsiLanguageInjectionHost
  @SuppressWarnings("UnusedParameters")
  public static boolean isValidHost(@NotNull MonkeyStringLiteral o) {
    return true;
  }

  // to satisfy PsiLanguageInjectionHost
  @NotNull
  public static MonkeyStringLiteral updateText(@NotNull MonkeyStringLiteral o, @NotNull String text) {
    if (text.length() > 2) {
      StringBuilder outChars = new StringBuilder();
      MonkeyStringLiteralEscaper.escapeString(text.substring(1, text.length() - 1), outChars);
      outChars.insert(0, '"');
      outChars.append('"');
      text = outChars.toString();
    }

    ASTNode valueNode = o.getNode().getFirstChildNode();
    assert valueNode instanceof LeafElement;

    ((LeafElement) valueNode).replaceWithText(text);
    return o;
  }

  // to satisfy PsiLanguageInjectionHost
  @NotNull
  public static MonkeyStringLiteralEscaper createLiteralTextEscaper(@NotNull MonkeyStringLiteral o) {
    return new MonkeyStringLiteralEscaper<>(o);
  }
}

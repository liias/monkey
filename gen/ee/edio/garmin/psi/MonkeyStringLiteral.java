// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import ee.edio.garmin.MonkeyStringLiteralEscaper;

public interface MonkeyStringLiteral extends PsiLanguageInjectionHost {

  @NotNull
  PsiElement getString();

  boolean isValidHost();

  @NotNull
  MonkeyStringLiteral updateText(String text);

  @NotNull
  MonkeyStringLiteralEscaper createLiteralTextEscaper();

}

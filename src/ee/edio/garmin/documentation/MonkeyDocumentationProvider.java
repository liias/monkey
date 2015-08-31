package ee.edio.garmin.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.psi.PsiElement;

public class MonkeyDocumentationProvider extends AbstractDocumentationProvider {
  @Override
  public String generateDoc(PsiElement element, PsiElement originalElement) {
    return "<div class=\"description\"> Parameterized version of moment. Given all parameters as integers,<br/> return back a Moment object of the given date.<br/> @since 1.0.0<br/></div>";
  }
}
package ee.edio.garmin;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class MonkeyCReferenceContributor extends PsiReferenceContributor {
  @Override
  public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {

    registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class),
        new PsiReferenceProvider() {
          @NotNull
          @Override
          public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
            String text = (String) literalExpression.getValue();
            if (text != null) {
              return new PsiReference[]{new MonkeyCReference(element, new TextRange(8, text.length() + 1))};
            }
            //if (text != null && text.startsWith("simple:")) {
            //  return new PsiReference[]{new MonkeyCReference(element, new TextRange(8, text.length() + 1))};
            //}
            return new PsiReference[0];
          }
        });


    //registrar.registerReferenceProvider(PATTERN, new JavaReflectionReferenceProvider());

  }
}

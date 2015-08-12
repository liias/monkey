package ee.edio.garmin;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import ee.edio.garmin.psi.MonkeyCNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MonkeyCReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
  private String key;

  public MonkeyCReference(@NotNull PsiElement element, TextRange rangeInElement) {
    super(element, rangeInElement);
    key = element.getText().substring(rangeInElement.getStartOffset(), rangeInElement.getEndOffset());
  }

  @NotNull
  @Override
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    Project project = myElement.getProject();
    List<ResolveResult> results = new ArrayList<>();
    final List<MonkeyCNamedElement> namedElements = MonkeyCUtil.findProperties(project, key);
    for (MonkeyCNamedElement namedEl : namedElements) {
      results.add(new PsiElementResolveResult(namedEl));
    }
    return results.toArray(new ResolveResult[results.size()]);
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    ResolveResult[] resolveResults = multiResolve(false);
    return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    Project project = myElement.getProject();
    List<MonkeyCNamedElement> namedElements = MonkeyCUtil.findProperties(project);
    List<LookupElement> variants = new ArrayList<>();
    for (final MonkeyCNamedElement namedEl : namedElements) {
      if (namedEl.getName() != null && namedEl.getName().length() > 0) {
        variants.add(LookupElementBuilder.create(namedEl).
                withIcon(MonkeyCIcons.FILE).
                withTypeText(namedEl.getContainingFile().getName())
        );
      }
    }
    return variants.toArray();
  }

}

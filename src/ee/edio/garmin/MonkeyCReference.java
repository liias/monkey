package ee.edio.garmin;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import ee.edio.garmin.psi.MonkeyCVariableDeclarator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MonkeyCReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
  private String key;

  public MonkeyCReference(PsiElement element, TextRange rangeInElement) {
    super(element, rangeInElement);
    key = element.getText().substring(rangeInElement.getStartOffset(), rangeInElement.getEndOffset());
  }

  @NotNull
  @Override
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    Project project = myElement.getProject();
    List<ResolveResult> results = new ArrayList<>();
    final List<MonkeyCVariableDeclarator> properties = MonkeyCUtil.findProperties(project, key);
    for (MonkeyCVariableDeclarator property : properties) {
      results.add(new PsiElementResolveResult(property));
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
    List<MonkeyCVariableDeclarator> properties = MonkeyCUtil.findProperties(project);
    List<LookupElement> variants = new ArrayList<>();
    for (final MonkeyCVariableDeclarator property : properties) {
      if (property.getName() != null && property.getName().length() > 0) {
        variants.add(LookupElementBuilder.create(property).
                withIcon(MonkeyCIcons.FILE).
                withTypeText(property.getContainingFile().getName())
        );
      }
    }
    return variants.toArray();
  }

}

package io.github.liias.monkey.lang.ide.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import gnu.trove.THashSet;
import io.github.liias.monkey.lang.psi.MonkeyClass;
import io.github.liias.monkey.lang.psi.MonkeyComponentName;
import io.github.liias.monkey.lang.psi.MonkeyReference;
import io.github.liias.monkey.lang.resolve.ComponentNameScopeProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;

public class MonkeyCompletionProvider extends CompletionProvider<CompletionParameters> {
  @Override
  protected void addCompletions(@NotNull CompletionParameters parameters, final ProcessingContext context, @NotNull CompletionResultSet result) {
    MonkeyReference reference = PsiTreeUtil.getParentOfType(parameters.getPosition(), MonkeyReference.class);
    if (reference != null) {
      THashSet<MonkeyComponentName> variants = new THashSet<>();

      ComponentNameScopeProcessor processor = new ComponentNameScopeProcessor(variants);
      PsiTreeUtil.treeWalkUp(processor, reference, null, ResolveState.initial());
      MonkeyClass monkeyClass = PsiTreeUtil.getParentOfType(reference, MonkeyClass.class);

      appendVariantsToCompletionSet(result, variants);
    }
  }


  public static Set<String> appendVariantsToCompletionSet(@NotNull final CompletionResultSet result,
                                                          @NotNull final Collection<MonkeyComponentName> variants) {
    final Set<String> addedNames = new THashSet<>();
    for (MonkeyComponentName componentName : variants) {
      LookupElement lookupElement = LookupElementBuilder.create(componentName);
      //.withTypeText("int");

      if (addedNames.add(lookupElement.getLookupString())) {
        result.addElement(lookupElement);
      }
    }
    return addedNames;
  }
}

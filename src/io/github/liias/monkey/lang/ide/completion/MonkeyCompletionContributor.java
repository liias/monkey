package io.github.liias.monkey.lang.ide.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.intellij.util.containers.ContainerUtil;
import io.github.liias.monkey.lang.MonkeyLanguage;
import io.github.liias.monkey.lang.psi.MonkeyId;
import io.github.liias.monkey.lang.psi.MonkeyReference;
import io.github.liias.monkey.lang.psi.MonkeyTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class MonkeyCompletionContributor extends CompletionContributor {

  private static final Set<String> keywords = ContainerUtil.immutableSet(
      "and", "as", "class", "const", "do", "else", "enum", "extends", "false", "for", "function", "has", "hidden",
      "if", "instanceof", "module", "native", "new", "null", "or", "return", "static", "true", "using", "var", "while");

  public MonkeyCompletionContributor() {
    PsiElementPattern.Capture<PsiElement> idInReference = psiElement()
        .withLanguage(MonkeyLanguage.INSTANCE)
        .withSuperParent(1, MonkeyId.class)
        .withSuperParent(2, MonkeyReference.class);

    extend(CompletionType.BASIC, idInReference, new MonkeyCompletionProvider());

    extend(CompletionType.BASIC, psiElement(MonkeyTypes.IDENTIFIER).withLanguage(MonkeyLanguage.INSTANCE),
        new CompletionProvider<CompletionParameters>() {
          @Override
          protected void addCompletions(@NotNull CompletionParameters parameters, final ProcessingContext context, @NotNull CompletionResultSet result) {
            for (String keyword : keywords) {
              result.addElement(LookupElementBuilder.create(keyword));
            }
          }
        });
  }
}

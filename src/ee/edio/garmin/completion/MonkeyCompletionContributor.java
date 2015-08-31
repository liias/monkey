package ee.edio.garmin.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import ee.edio.garmin.MonkeyUtil;
import ee.edio.garmin.psi.MonkeyTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class MonkeyCompletionContributor extends CompletionContributor {


  public MonkeyCompletionContributor() {
    extend(CompletionType.BASIC, psiElement().andOr(psiElement(MonkeyTypes.IDENTIFIER)), new CompletionProvider<CompletionParameters>() {
      @Override
      protected void addCompletions(@NotNull CompletionParameters parameters, final ProcessingContext context, @NotNull CompletionResultSet result) {
        Set<String> suggestedKeywords = MonkeyUtil.getKeywords();
        for (String keyword : suggestedKeywords) {
          result.addElement(LookupElementBuilder.create(keyword));
        }
      }
    });
  }
}

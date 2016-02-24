package io.github.liias.monkey.ide.refactoring;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.LocalSearchScope;
import io.github.liias.monkey.resolve.MonkeyComponentType;
import io.github.liias.monkey.psi.MonkeyNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyRefactoringSupportProvider extends RefactoringSupportProvider {
  @Override
  public boolean isInplaceRenameAvailable(@NotNull PsiElement elementToRename, PsiElement nameSuggestionContext) {
    return elementToRename instanceof MonkeyNamedElement &&
        elementToRename.getUseScope() instanceof LocalSearchScope;
  }

  @Override
  public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement elementToRename, @Nullable PsiElement context) {
    return MonkeyComponentType.typeOf(elementToRename) == MonkeyComponentType.FIELD;
  }
}

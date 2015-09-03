package ee.edio.garmin;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import ee.edio.garmin.psi.MonkeyNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyRefactoringSupportProvider extends RefactoringSupportProvider {
  @Override
  public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, @Nullable PsiElement context) {
    return element instanceof MonkeyNamedElement;
  }

  @Override
  public boolean isInplaceRenameAvailable(@NotNull final PsiElement elementToRename, final PsiElement nameSuggestionContext) {
    return elementToRename instanceof MonkeyNamedElement;
    //&& elementToRename.getUseScope() instanceof LocalSearchScope;
  }


}

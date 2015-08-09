package ee.edio.garmin;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import ee.edio.garmin.psi.MonkeyCFunctionDeclaration;
import org.jetbrains.annotations.NotNull;

public class MonkeyCRefactoringSupportProvider extends RefactoringSupportProvider {
  @Override
  public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
    return element instanceof MonkeyCFunctionDeclaration;
  }
}

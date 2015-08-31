package ee.edio.garmin;

import com.intellij.lang.refactoring.RefactoringSupportProvider;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import ee.edio.garmin.psi.MonkeyFormalParameters;
import ee.edio.garmin.psi.MonkeyLocalVariableDeclaration;
import ee.edio.garmin.psi.MonkeyMemberDecl;
import ee.edio.garmin.psi.MonkeyVariableDeclarator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyRefactoringSupportProvider extends RefactoringSupportProvider {
  @Override
  public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, @Nullable PsiElement context) {
    return element instanceof MonkeyMemberDecl;
  }

  @Override
  public boolean isInplaceRenameAvailable(@NotNull final PsiElement elementToRename, final PsiElement nameSuggestionContext) {
    if (nameSuggestionContext != null && nameSuggestionContext.getContainingFile() != elementToRename.getContainingFile())
      return false;
    if (!(elementToRename instanceof MonkeyLocalVariableDeclaration) &&
        !(elementToRename instanceof MonkeyFormalParameters) &&
        !(elementToRename instanceof MonkeyVariableDeclarator)) {
      return false;
    }
    SearchScope useScope = PsiSearchHelper.SERVICE.getInstance(elementToRename.getProject()).getUseScope(elementToRename);
    if (!(useScope instanceof LocalSearchScope)) return false;
    PsiElement[] scopeElements = ((LocalSearchScope) useScope).getScope();
    PsiFile containingFile = elementToRename.getContainingFile();
    return PsiTreeUtil.isAncestor(containingFile, scopeElements[0], false);
  }



}

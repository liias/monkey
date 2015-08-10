package ee.edio.garmin;

import com.intellij.lang.refactoring.RefactoringSupportProvider;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import ee.edio.garmin.psi.MonkeyCFormalParameters;
import ee.edio.garmin.psi.MonkeyCLocalVariableDeclaration;
import ee.edio.garmin.psi.MonkeyCMemberDecl;
import ee.edio.garmin.psi.MonkeyCVariableDeclarator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyCRefactoringSupportProvider extends RefactoringSupportProvider {
  @Override
  public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, @Nullable PsiElement context) {
    return element instanceof MonkeyCMemberDecl;
  }

  @Override
  public boolean isInplaceRenameAvailable(@NotNull final PsiElement elementToRename, final PsiElement nameSuggestionContext) {
    if (nameSuggestionContext != null && nameSuggestionContext.getContainingFile() != elementToRename.getContainingFile())
      return false;
    if (!(elementToRename instanceof MonkeyCLocalVariableDeclaration) &&
        !(elementToRename instanceof MonkeyCFormalParameters) &&
        !(elementToRename instanceof MonkeyCVariableDeclarator)) {
      return false;
    }
    SearchScope useScope = PsiSearchHelper.SERVICE.getInstance(elementToRename.getProject()).getUseScope(elementToRename);
    if (!(useScope instanceof LocalSearchScope)) return false;
    PsiElement[] scopeElements = ((LocalSearchScope) useScope).getScope();
    PsiFile containingFile = elementToRename.getContainingFile();
    return PsiTreeUtil.isAncestor(containingFile, scopeElements[0], false);
  }



}

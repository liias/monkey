package io.github.liias.monkey.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import io.github.liias.monkey.MonkeyLanguage;
import io.github.liias.monkey.file.MonkeyFileType;
import io.github.liias.monkey.psi.impl.MonkeyPsiCompositeElementImpl;
import org.jetbrains.annotations.NotNull;

public class MonkeyFile extends PsiFileBase {
  public MonkeyFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, MonkeyLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return MonkeyFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "Monkey C File";
  }

  @Override
  public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
    return MonkeyPsiCompositeElementImpl.processDeclarationsImpl(this, processor, state)
        && super.processDeclarations(processor, state, lastParent, place);
  }

}

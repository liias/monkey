package io.github.liias.monkey.lang.ide.navigation;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.ResolveState;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import gnu.trove.THashSet;
import io.github.liias.monkey.lang.file.MonkeyFileType;
import io.github.liias.monkey.lang.psi.MonkeyComponentName;
import io.github.liias.monkey.lang.psi.MonkeyFile;
import io.github.liias.monkey.lang.resolve.ComponentNameScopeProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ContributorHelper {
  public static List<MonkeyComponentName> getVariants(Project project, boolean includeNonProjectItems) {
    final List<MonkeyComponentName> result = new ArrayList<>();

    final GlobalSearchScope scope = includeNonProjectItems ? GlobalSearchScope.allScope(project) : GlobalSearchScope.projectScope(project);
    Collection<VirtualFile> sourceFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, MonkeyFileType.INSTANCE, scope);
    for (VirtualFile sourceFile : sourceFiles) {
      MonkeyFile monkeyFile = (MonkeyFile) PsiManager.getInstance(project).findFile(sourceFile);
      if (monkeyFile != null) {
        THashSet<MonkeyComponentName> variants = new THashSet<>();
        ComponentNameScopeProcessor processor = new ComponentNameScopeProcessor(variants);
        PsiTreeUtil.treeWalkUp(processor, monkeyFile, null, ResolveState.initial());
        result.addAll(variants);
      }
    }
    return result.stream()
      .filter(c -> c.getPresentation() != null)
      .collect(Collectors.toList());
  }
}

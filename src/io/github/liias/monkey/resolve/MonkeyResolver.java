package io.github.liias.monkey.resolve;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.liias.monkey.psi.MonkeyComponentName;
import io.github.liias.monkey.psi.MonkeyReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MonkeyResolver implements ResolveCache.AbstractResolver<MonkeyReference, List<? extends PsiElement>> {
  public static final MonkeyResolver INSTANCE = new MonkeyResolver();

  @Nullable
  @Override
  public List<? extends PsiElement> resolve(@NotNull MonkeyReference reference, boolean incompleteCode) {
    MonkeyReference[] references = PsiTreeUtil.getChildrenOfType(reference, MonkeyReference.class);
    if (references == null) {
      return resolveSimpleReference(reference, reference.getCanonicalText());
    }
    return null;
  }

  @NotNull
  public static List<? extends PsiElement> resolveSimpleReference(@NotNull final PsiElement scopeElement, @NotNull final String name) {
    final List<MonkeyComponentName> result = new ArrayList<>();
    // local
    final MonkeyResolveProcessor resolveProcessor = new MonkeyResolveProcessor(result, name);
    PsiTreeUtil.treeWalkUp(resolveProcessor, scopeElement, null, ResolveState.initial());

    // todo: add super, global, etc (check monkey c docs for order)
    return result;
  }
}

package io.github.liias.monkey.lang.resolve;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.liias.monkey.lang.psi.MonkeyComponentName;
import io.github.liias.monkey.lang.psi.MonkeyReference;
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

    /*
    Scoping
    Monkey C is a message-passed language. When a function is called, the virtual machine does a look up operation
    at runtime to find the function being called. Here is the hierarchy that it will search:

    1. Instance members of the class
    2. Members of the superclass
    3. Static members of the class
    4. Members of the parent module, and the parent modules up to the global namespace
    5. Members of the superclass’s parent module up to the global namespace
    */

    return result;
  }
}

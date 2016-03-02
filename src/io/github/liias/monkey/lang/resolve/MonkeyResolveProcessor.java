package io.github.liias.monkey.lang.resolve;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.BaseScopeProcessor;
import com.intellij.psi.scope.PsiScopeProcessor;
import io.github.liias.monkey.lang.psi.MonkeyComponentName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Gathers declarations for reference
 */
public class MonkeyResolveProcessor extends BaseScopeProcessor implements PsiScopeProcessor {
  private final List<MonkeyComponentName> myResult;
  private final String myName;

  public MonkeyResolveProcessor(List<MonkeyComponentName> result, String name) {
    this.myResult = result;
    this.myName = name;
  }

  @Override
  public boolean execute(@NotNull PsiElement element, @NotNull ResolveState state) {
    if (!(element instanceof MonkeyComponentName)) return true;
    MonkeyComponentName componentName = (MonkeyComponentName) element;
    final PsiElement parentElement = componentName.getParent();

    if (this.myName.equals(componentName.getName())
      //&& !isMember(MonkeyComponentType.typeOf(parentElement))
        ) {
      myResult.add(componentName);
      return false;
    }

    return true;
  }

  private static boolean isMember(final @Nullable MonkeyComponentType componentType) {
    return componentType == MonkeyComponentType.FIELD;
  }
}

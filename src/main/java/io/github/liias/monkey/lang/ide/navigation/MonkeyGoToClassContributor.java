package io.github.liias.monkey.lang.ide.navigation;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import io.github.liias.monkey.lang.psi.MonkeyClass;
import io.github.liias.monkey.lang.psi.MonkeyClassDeclaration;
import io.github.liias.monkey.lang.psi.MonkeyComponentName;
import io.github.liias.monkey.lang.psi.MonkeyModuleDeclaration;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class MonkeyGoToClassContributor implements ChooseByNameContributor {
  @NotNull
  @Override
  public String[] getNames(Project project, boolean includeNonProjectItems) {
    List<String> collect = getClassVariants(project, includeNonProjectItems).stream()
      .map(MonkeyComponentName::getName)
      .collect(Collectors.toList());
    return collect.stream().toArray(String[]::new);
  }

  @NotNull
  @Override
  public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
    return getClassVariants(project, includeNonProjectItems).stream()
      .filter(i -> i.getName().equals(name))
      .toArray(NavigationItem[]::new);
  }

  private static List<MonkeyComponentName> getClassVariants(Project project, boolean includeNonProjectItems) {
    return ContributorHelper.getVariants(project, includeNonProjectItems).stream()
      .filter(i -> {
        PsiElement parent = i.getParent();
        return parent != null && (parent instanceof MonkeyClass || parent instanceof MonkeyModuleDeclaration);
      })
      .collect(Collectors.toList());
  }
}
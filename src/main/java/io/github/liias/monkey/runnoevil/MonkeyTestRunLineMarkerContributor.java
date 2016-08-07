package io.github.liias.monkey.runnoevil;

import com.intellij.execution.lineMarker.ExecutorAction;
import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.Function;
import io.github.liias.monkey.lang.psi.MonkeyClass;
import io.github.liias.monkey.lang.psi.MonkeyFunctionDeclaration;
import io.github.liias.monkey.lang.psi.MonkeyId;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MonkeyTestRunLineMarkerContributor extends RunLineMarkerContributor {
  private static final Function<PsiElement, String> TOOLTIP_PROVIDER = (element) -> "Run Test";

  @Nullable
  @Override
  public Info getInfo(PsiElement element) {
    MonkeyTestFramework monkeyTestFramework = new MonkeyTestFramework();

    if (element instanceof MonkeyId) {
      PsiElement parent = element.getParent();
      if (parent != null && parent instanceof MonkeyClass) {
        if (monkeyTestFramework.isTestClass(parent)) {
          MonkeyClass monkeyClass = (MonkeyClass) parent;
          String framework1 = "java:suite://" + monkeyClass.getName();

          return getInfo(framework1, element.getProject(), true);
        }
      }
    }

    if (element instanceof MonkeyFunctionDeclaration) {
      if (monkeyTestFramework.isTestMethod(element)) {
        MonkeyFunctionDeclaration functionDeclaration = (MonkeyFunctionDeclaration) element;

        String url = "java:test://" + functionDeclaration.getName();
        return getInfo(url, element.getProject(), false);
      }
    }

    return null;
  }

  private static Info getInfo(String url, Project project, boolean isClass) {
    Icon icon = getTestStateIcon(url, project, isClass);
    return new Info(icon, TOOLTIP_PROVIDER, ExecutorAction.getActions(1));
  }

  private static Icon getTestStateIcon(String url, Project project, boolean isClass) {
    return isClass ? AllIcons.RunConfigurations.TestState.Run_run : AllIcons.RunConfigurations.TestState.Run;
  }
}

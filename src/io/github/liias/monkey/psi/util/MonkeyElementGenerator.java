package io.github.liias.monkey.psi.util;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.PsiFileFactoryImpl;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.LightVirtualFile;
import io.github.liias.monkey.MonkeyLanguage;
import io.github.liias.monkey.file.MonkeyFileType;
import io.github.liias.monkey.psi.MonkeyComponent;
import io.github.liias.monkey.psi.MonkeyComponentName;
import io.github.liias.monkey.psi.MonkeyId;
import org.jetbrains.annotations.Nullable;

public class MonkeyElementGenerator {

  // Creates MonkeyId by creating a random element (i.e class) which has name
  @Nullable
  public static MonkeyId createIdentifierFromText(Project myProject, String name) {
    final PsiFile dummyFile = createDummyFile(myProject, "class " + name + " {}");

    final MonkeyComponent monkeyComponent = PsiTreeUtil.getChildOfType(dummyFile, MonkeyComponent.class);
    final MonkeyComponentName componentName = monkeyComponent == null ? null : monkeyComponent.getComponentName();
    return componentName == null ? null : componentName.getId();
  }

  private static PsiFile createDummyFile(Project myProject, String text) {
    final PsiFileFactory factory = PsiFileFactory.getInstance(myProject);
    final String name = "dummy." + MonkeyFileType.INSTANCE.getDefaultExtension();
    final LightVirtualFile virtualFile = new LightVirtualFile(name, MonkeyFileType.INSTANCE, text);
    final PsiFile psiFile = ((PsiFileFactoryImpl) factory).trySetupPsiForFile(virtualFile, MonkeyLanguage.INSTANCE, false, true);
    assert psiFile != null;
    return psiFile;
  }
}

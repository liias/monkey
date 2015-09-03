package ee.edio.garmin.util;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.PsiFileFactoryImpl;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.LightVirtualFile;
import ee.edio.garmin.MonkeyFileType;
import ee.edio.garmin.MonkeyLanguage;
import ee.edio.garmin.psi.MonkeyComponent;
import ee.edio.garmin.psi.MonkeyComponentName;
import ee.edio.garmin.psi.MonkeyId;
import org.jetbrains.annotations.Nullable;

public class MonkeyElementGenerator {
  @Nullable
  public static MonkeyId createIdentifierFromText(Project myProject, String name) {
    final PsiFile dummyFile = createDummyFile(myProject, "function " + name + "(){}");

    final MonkeyComponent monkeyComponent = PsiTreeUtil.getChildOfType(dummyFile, MonkeyComponent.class);
    //final MonkeyComponent monkeyComponent = PsiTreeUtil.getChildOfType(dummyFile, MonkeyComponent.class);
    final MonkeyComponentName componentName = monkeyComponent == null ? null : monkeyComponent.getComponentName();
    return componentName == null ? null : componentName.getId();
  }

  public static PsiFile createDummyFile(Project myProject, String text) {
    final PsiFileFactory factory = PsiFileFactory.getInstance(myProject);
    final String name = "dummy." + MonkeyFileType.INSTANCE.getDefaultExtension();
    final LightVirtualFile virtualFile = new LightVirtualFile(name, MonkeyFileType.INSTANCE, text);
    final PsiFile psiFile = ((PsiFileFactoryImpl) factory).trySetupPsiForFile(virtualFile, MonkeyLanguage.INSTANCE, false, true);
    assert psiFile != null;
    return psiFile;
  }
}

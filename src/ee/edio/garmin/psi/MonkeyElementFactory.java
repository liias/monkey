package ee.edio.garmin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import ee.edio.garmin.MonkeyFileType;

public class MonkeyElementFactory {
/*  public static SimpleProperty createProperty(Project project, String name, String value) {
    final SimpleFile file = createFile(project, name + " = " + value);
    return (SimpleProperty) file.getFirstChild();
  }*/

  public static MonkeyNamedElement createProperty(Project project, String name) {

    final MonkeyFile file = createFile(project, name);
    return (MonkeyNamedElement) file.getFirstChild();
  }

/*  public static PsiElement createCRLF(Project project) {
    final SimpleFile file = createFile(project, "\n");
    return file.getFirstChild();
  }*/

  public static MonkeyFile createFile(Project project, String text) {
    String name = "dummy.simple";
    return (MonkeyFile) PsiFileFactory.getInstance(project).
        createFileFromText(name, MonkeyFileType.INSTANCE, text);
  }
}

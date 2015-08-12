package ee.edio.garmin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import ee.edio.garmin.MonkeyCFileType;

public class MonkeyCElementFactory {
/*  public static SimpleProperty createProperty(Project project, String name, String value) {
    final SimpleFile file = createFile(project, name + " = " + value);
    return (SimpleProperty) file.getFirstChild();
  }*/

  public static MonkeyCNamedElement createProperty(Project project, String name) {

    final MonkeyCFile file = createFile(project, name);
    return (MonkeyCNamedElement) file.getFirstChild();
  }

/*  public static PsiElement createCRLF(Project project) {
    final SimpleFile file = createFile(project, "\n");
    return file.getFirstChild();
  }*/

  public static MonkeyCFile createFile(Project project, String text) {
    String name = "dummy.simple";
    return (MonkeyCFile) PsiFileFactory.getInstance(project).
        createFileFromText(name, MonkeyCFileType.INSTANCE, text);
  }
}

package ee.edio.garmin.ide.fileTemplates;

import com.intellij.ide.fileTemplates.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import ee.edio.garmin.MonkeyIcons;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

public class MonkeyFileTemplateProvider implements FileTemplateGroupDescriptorFactory {
  @Override
  public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
    return new FileTemplateGroupDescriptor("Connect IQ", MonkeyIcons.SDK);
  }

  public static PsiElement createFromTemplate(@NotNull Project project, @NotNull VirtualFile rootDir, @NotNull String templateName, @NotNull String fileName) throws Exception {
    Properties properties = FileTemplateManager.getInstance(project).getDefaultProperties();
    rootDir.refresh(false, false);
    PsiDirectory directory = PsiManager.getInstance(project).findDirectory(rootDir);
    if (directory != null) {
      FileTemplateManager manager = FileTemplateManager.getInstance(directory.getProject());
      FileTemplate template = manager.getInternalTemplate(templateName);
      return FileTemplateUtil.createFromTemplate(template, fileName, properties, directory);
    }
    return null;
  }
}

package ee.edio.garmin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import ee.edio.garmin.psi.MonkeyCFile;
import ee.edio.garmin.psi.MonkeyCVariableDeclarator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MonkeyCUtil {

  public static List<MonkeyCVariableDeclarator> findProperties(Project project, String key) {
    List<MonkeyCVariableDeclarator> result = null;
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, MonkeyCFileType.INSTANCE,
        GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      MonkeyCFile monkeyFile = (MonkeyCFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (monkeyFile != null) {
        MonkeyCVariableDeclarator[] properties = PsiTreeUtil.getChildrenOfType(monkeyFile, MonkeyCVariableDeclarator.class);
        if (properties != null) {
          for (MonkeyCVariableDeclarator property : properties) {
            if (key.equals(property.getName())) {
              if (result == null) {
                result = new ArrayList<>();
              }
              result.add(property);
            }
          }
        }
      }
    }
    return result != null ? result : Collections.<MonkeyCVariableDeclarator>emptyList();
  }

  public static List<MonkeyCVariableDeclarator> findProperties(Project project) {
    List<MonkeyCVariableDeclarator> result = new ArrayList<>();
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, MonkeyCFileType.INSTANCE,
        GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      MonkeyCFile simpleFile = (MonkeyCFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (simpleFile != null) {
        MonkeyCVariableDeclarator[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, MonkeyCVariableDeclarator.class);
        if (properties != null) {
          Collections.addAll(result, properties);
        }
      }
    }
    return result;
  }

}

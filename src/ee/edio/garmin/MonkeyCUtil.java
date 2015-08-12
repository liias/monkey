package ee.edio.garmin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.FileBasedIndex;
import ee.edio.garmin.psi.MonkeyCFile;
import ee.edio.garmin.psi.MonkeyCNamedElement;

import java.util.*;

public class MonkeyCUtil {

  public static List<MonkeyCNamedElement> findProperties(Project project, String key) {
    List<MonkeyCNamedElement> result = null;
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, MonkeyCFileType.INSTANCE,
        GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      MonkeyCFile monkeyFile = (MonkeyCFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (monkeyFile != null) {
        MonkeyCNamedElement[] properties = PsiTreeUtil.getChildrenOfType(monkeyFile, MonkeyCNamedElement.class);
        if (properties != null) {
          for (MonkeyCNamedElement property : properties) {
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
    return result != null ? result : Collections.<MonkeyCNamedElement>emptyList();
  }

  public static List<MonkeyCNamedElement> findProperties(Project project) {
    List<MonkeyCNamedElement> result = new ArrayList<>();
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, MonkeyCFileType.INSTANCE,
        GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      MonkeyCFile simpleFile = (MonkeyCFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (simpleFile != null) {
        MonkeyCNamedElement[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, MonkeyCNamedElement.class);
        if (properties != null) {
          Collections.addAll(result, properties);
        }
      }
    }
    return result;
  }


  private static final Set<String> keywords = ContainerUtil.immutableSet(
      "and", "as", "class", "const", "do", "else", "enum", "extends", "false", "for", "function", "has", "hidden",
      "if", "instanceof", "module", "native", "new", "null", "or", "return", "static", "true", "using", "var", "while");

  public static Set<String> getKeywords() {
    return keywords;
  }
}

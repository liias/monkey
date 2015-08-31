package ee.edio.garmin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.FileBasedIndex;
import ee.edio.garmin.psi.MonkeyFile;
import ee.edio.garmin.psi.MonkeyNamedElement;

import java.util.*;

public class MonkeyUtil {

  public static List<MonkeyNamedElement> findProperties(Project project, String key) {
    List<MonkeyNamedElement> result = null;
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, MonkeyFileType.INSTANCE,
        GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      MonkeyFile monkeyFile = (MonkeyFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (monkeyFile != null) {
        MonkeyNamedElement[] properties = PsiTreeUtil.getChildrenOfType(monkeyFile, MonkeyNamedElement.class);
        if (properties != null) {
          for (MonkeyNamedElement property : properties) {
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
    return result != null ? result : Collections.<MonkeyNamedElement>emptyList();
  }

  public static List<MonkeyNamedElement> findProperties(Project project) {
    List<MonkeyNamedElement> result = new ArrayList<>();
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, MonkeyFileType.INSTANCE,
        GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      MonkeyFile simpleFile = (MonkeyFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (simpleFile != null) {
        MonkeyNamedElement[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, MonkeyNamedElement.class);
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

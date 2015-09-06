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
import ee.edio.garmin.psi.MonkeyReference;
import ee.edio.garmin.psi.MonkeyReferenceExpression;

import java.io.IOException;
import java.util.*;

public class MonkeyUtil {

  public static List<MonkeyNamedElement> findReferences(Project project, MonkeyReference monkeyReference) {
    final MonkeyReferenceExpression referenceExpression = (MonkeyReferenceExpression) monkeyReference;

    final String name = referenceExpression.getComponentName().getName();
    if (name == null) {
      return new ArrayList<>();
    }
    List<MonkeyNamedElement> result = new ArrayList<>();
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, MonkeyFileType.INSTANCE,
        GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      MonkeyFile monkeyFile = (MonkeyFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (monkeyFile != null) {
        MonkeyNamedElement[] references = PsiTreeUtil.getChildrenOfType(monkeyFile.getFirstChild(), MonkeyNamedElement.class);
        if (references != null) {
          for (MonkeyNamedElement reference : references) {
            if (name.equals(reference.getName())) {
              result.add(reference);
            }
          }

          //Collections.addAll(result, references);
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

  public static boolean isRealDevice(String deviceId) {
    return !deviceId.equalsIgnoreCase("round_watch") &&
        !deviceId.equalsIgnoreCase("square_watch") &&
        !deviceId.equalsIgnoreCase("semi_round_watch");
  }

  public static String generateProjectId() {
    UUID id = UUID.randomUUID();
    return String.format("%016X%016X", id.getMostSignificantBits(), id.getLeastSignificantBits());
  }

  public static VirtualFile createChildDirectoryIfNotExist(Project project, VirtualFile parent, String name) throws IOException {
    final VirtualFile child = parent.findChild(name);
    return child == null ? parent.createChildDirectory(project, name) : child;
  }
}

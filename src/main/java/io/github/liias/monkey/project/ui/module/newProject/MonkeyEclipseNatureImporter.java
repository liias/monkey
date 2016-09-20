package io.github.liias.monkey.project.ui.module.newProject;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import io.github.liias.monkey.project.module.MonkeyModuleBuilder;
import io.github.liias.monkey.project.module.MonkeyModuleType;
import io.github.liias.monkey.project.module.util.MonkeyModuleUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.idea.eclipse.importWizard.EclipseNatureImporter;

import java.util.*;

import static io.github.liias.monkey.jps.model.JpsMonkeyModuleType.MONKEY_SOURCE_ROOT_TYPE;

public class MonkeyEclipseNatureImporter extends EclipseNatureImporter {
  private static final Logger LOG = Logger.getInstance("#io.github.liias.monkey.project.ui.module.newProject.MonkeyEclipseNatureImporter");

  public static final String NATURE_NAME = "connectiq.projectNature";

  @NotNull
  @Override
  public String getNatureName() {
    return NATURE_NAME;
  }

  @Override
  public Set<String> getProvidedCons() {
    return Collections.emptySet();
  }

  @Override
  public void doImport(@NotNull Project project, @NotNull List<Module> modules) {
    for (Module module : modules) {
      if (module.isDisposed()) {
        // e.g if importing was canceled by user
        continue;
      }

      final VirtualFile contentRoot = chooseMainContentRoot(module);

      if (contentRoot == null) {
        reportImportErrorToEventLog("Cannot find content root containing " +
            MonkeyModuleUtil.MANIFEST_XML + " file",
          module.getName(),
          project);
        continue;
      }

      MonkeyModuleType monkeyModuleType = MonkeyModuleType.getInstance();

      // Eclipse importer set type to java, so we overwrite it
      module.setOption(Module.ELEMENT_TYPE, monkeyModuleType.getId());

      final ModifiableRootModel modifiableModel = ModuleRootManager.getInstance(module).getModifiableModel();
      MonkeyModuleBuilder moduleBuilder = monkeyModuleType.createModuleBuilder();
      try {
        // mark source folder as Source if exists
        Optional<ContentEntry> existingContentEntry = Arrays.stream(modifiableModel.getContentEntries()).findFirst();
        if (existingContentEntry.isPresent()) {
          ContentEntry contentEntry = existingContentEntry.get();
          Optional.ofNullable(contentEntry.getFile())
            .map(f -> f.findChild("source"))
            .ifPresent(sourcePath -> contentEntry.addSourceFolder(sourcePath, MONKEY_SOURCE_ROOT_TYPE));
        }

        moduleBuilder.setupRootModel(modifiableModel);
      } catch (ConfigurationException e) {
        reportImportErrorToEventLog(e.getMessage(), module.getName(), project);
        continue;
      }

      ApplicationManager.getApplication().runWriteAction(modifiableModel::commit);
    }
  }

  private static void reportImportErrorToEventLog(String message, String moduleName, Project project) {
    Notifications.Bus.notify(new Notification("Importing Error",
      "Error when importing module '" + moduleName + "'",
      message, NotificationType.ERROR, null), project);
    LOG.debug(message);
  }

  @Nullable
  private static VirtualFile chooseMainContentRoot(@NotNull Module module) {
    final VirtualFile[] roots = ModuleRootManager.getInstance(module).getContentRoots();

    if (roots.length == 0) {
      return null;
    }
    if (roots.length == 1) {
      return roots[0];
    }

    for (VirtualFile root : roots) {
      if (root.findChild(MonkeyModuleUtil.MANIFEST_XML) != null) {
        return root;
      }
    }
    return null;
  }
}

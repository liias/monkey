package io.github.liias.monkey.project;

import com.intellij.ProjectTopics;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.ModuleAdapter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.util.messages.MessageBusConnection;
import icons.MonkeyIcons;
import io.github.liias.monkey.ide.actions.appsettings.ui.AppSettingsToolWindowFactory;
import org.jetbrains.annotations.NotNull;

public class MonkeyProjectListener extends AbstractProjectComponent {

  public static final String APP_SETTINGS_TOOL_WINDOW_ID = "App Settings";

  protected MonkeyProjectListener(Project project) {
    super(project);
  }

  @Override
  public void projectOpened() {
    MessageBusConnection connection = myProject.getMessageBus().connect(myProject);

    createOrRemoveToolWindow(myProject);

    connection.subscribe(ProjectTopics.MODULES, new ModuleAdapter() {
      @Override
      public void moduleAdded(@NotNull Project project, @NotNull Module module) {
        moduleAddedOrRemovedForProject(project);
      }

      @Override
      public void moduleRemoved(@NotNull Project project, @NotNull Module module) {
        moduleAddedOrRemovedForProject(project);
      }
    });
  }

  private static void moduleAddedOrRemovedForProject(@NotNull Project project) {
    ApplicationManager.getApplication().invokeLater(() -> {
      if (project.isDisposed()) {
        return;
      }
      createOrRemoveToolWindow(project);
    });
  }

  private static void createOrRemoveToolWindow(Project project) {
    final ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
    toolWindowManager.invokeLater(() -> {
      final ToolWindow existingToolWindow = toolWindowManager.getToolWindow(APP_SETTINGS_TOOL_WINDOW_ID);
      boolean shouldShowToolWindow = AppSettingsToolWindowFactory.hasMonkeyModule(project);

      if (existingToolWindow == null && shouldShowToolWindow) {
        createAppSettingsToolWindow(project, toolWindowManager);
      }
      if (existingToolWindow != null && !shouldShowToolWindow) {
        existingToolWindow.hide(null);
      }
    });
  }

  private static ToolWindow createAppSettingsToolWindow(Project project, ToolWindowManager toolWindowManager) {
    ToolWindow toolWindow = toolWindowManager.registerToolWindow(APP_SETTINGS_TOOL_WINDOW_ID, false, ToolWindowAnchor.RIGHT);
    toolWindow.setIcon(MonkeyIcons.APP_SETTINGS13);
    AppSettingsToolWindowFactory appSettingsToolWindowFactory = new AppSettingsToolWindowFactory();
    if (appSettingsToolWindowFactory.value(project)) {
      appSettingsToolWindowFactory.createToolWindowContent(project, toolWindow);
    }
    return toolWindow;
  }

}

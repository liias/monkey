package io.github.liias.monkey.ide.actions.appsettings.ui;

import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import io.github.liias.monkey.project.module.MonkeyModuleType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class AppSettingsToolWindowFactory implements ToolWindowFactory, Condition<Project>, DumbAware {

  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    AppSettingsToolWindowPanel appSettingsToolWindowPanel = new AppSettingsToolWindowPanel(project);

    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
    Content content = contentFactory.createContent(appSettingsToolWindowPanel, null, false);
    toolWindow.getContentManager().addContent(content);

    //Disposer.register(project, appSettingsToolWindowPanel);
  }

  @Override
  public boolean value(Project project) {
    return hasMonkeyModule(project);
  }

  public static boolean hasMonkeyModule(Project project) {
    return Arrays.stream(ModuleManager.getInstance(project).getModules())
      .anyMatch(module -> ModuleType.is(module, MonkeyModuleType.getInstance()));
  }
}

package io.github.liias.monkey.ide.actions.appsettings.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class AppSettingsToolWindowFactory implements ToolWindowFactory {

  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    AppSettingsToolWindowPanel appSettingsToolWindowPanel = new AppSettingsToolWindowPanel(project);

    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
    Content content = contentFactory.createContent(appSettingsToolWindowPanel, null, false);
    toolWindow.getContentManager().addContent(content);

    //Disposer.register(project, appSettingsToolWindowPanel);
  }
}

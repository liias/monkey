package io.github.liias.monkey.ide.actions.appsettings;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;

public class AppSettingsAction extends AnAction implements DumbAware {
  public AppSettingsAction() {
    super("App Settings Editor");
  }

  @Override
  public void actionPerformed(AnActionEvent e) {
    AppSettingsDialog dialog = new AppSettingsDialog(e.getProject());
    dialog.show();
  }
}

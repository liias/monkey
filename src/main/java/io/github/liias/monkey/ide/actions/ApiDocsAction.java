package io.github.liias.monkey.ide.actions;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import io.github.liias.monkey.project.sdk.MonkeySdkType;

public class ApiDocsAction extends AnAction implements DumbAware {
  public ApiDocsAction() {
    super("Browse API Documentation");
  }

  @Override
  public void actionPerformed(AnActionEvent e) {
    Project project = e.getProject();
    Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
    if (!(projectSdk.getSdkType() instanceof MonkeySdkType)) {
      return;
    }
    String apiDocPath = MonkeySdkType.getApiDocPath(projectSdk);
    String framesHtmlPath = apiDocPath + "frames.html";
    BrowserUtil.browse(framesHtmlPath);
  }
}

package io.github.liias.monkey.ide.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import io.github.liias.monkey.icons.MonkeyIcons;

public class CreateMonkeyFileAction extends CreateFileFromTemplateAction {
  public CreateMonkeyFileAction() {
    super("Monkey C File", "Monkey C File", MonkeyIcons.FILE);
  }

  @Override
  protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
    builder
        .setTitle("New Monkey C File")
        .addKind("Monkey C File", MonkeyIcons.FILE, "Monkey C File.mc");
  }

  @Override
  protected String getActionName(PsiDirectory directory, String newName, String templateName) {
    return "Create Monkey C file {newName}";
  }
}

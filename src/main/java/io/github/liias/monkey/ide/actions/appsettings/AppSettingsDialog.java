package io.github.liias.monkey.ide.actions.appsettings;

import com.intellij.CommonBundle;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.stream.Stream;

public class AppSettingsDialog extends DialogWrapper {
  private final AppSettingsForm appSettingsForm;

  protected AppSettingsDialog(@Nullable Project project) {
    super(project, true, IdeModalityType.MODELESS);

    setTitle("App Settings Editor");
    setCancelButtonText(CommonBundle.getCloseButtonText());
    appSettingsForm = new AppSettingsForm(project, this);
    init();
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
    return appSettingsForm.getPanel();
  }

  @NotNull
  @Override
  protected Action[] createActions() {
    DialogWrapperAction sendAction = new DialogWrapperAction("Send Settings") {
      {
        putValue(DEFAULT_ACTION, Boolean.TRUE);
      }

      @Override
      protected void doAction(ActionEvent e) {
        appSettingsForm.sendSettingsToSimulator();
      }
    };

    return Stream.of(sendAction, getCancelAction())
        .toArray(Action[]::new);
  }
}

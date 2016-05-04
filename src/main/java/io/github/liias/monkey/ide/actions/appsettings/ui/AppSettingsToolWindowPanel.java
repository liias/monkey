package io.github.liias.monkey.ide.actions.appsettings.ui;

import com.intellij.ide.actions.ContextHelpAction;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.util.IconUtil;
import com.intellij.util.ui.JBUI;
import io.github.liias.monkey.ide.actions.appsettings.AppSettingsForm;

import javax.swing.*;

public class AppSettingsToolWindowPanel extends SimpleToolWindowPanel {
  private final Project project;
  private final AppSettingsForm appSettingsForm;

  public AppSettingsToolWindowPanel(Project project) {
    super(true, true);
    this.project = project;

    setToolbar(createToolbarPanel());
    appSettingsForm = new AppSettingsForm(project, null);
    JPanel panel = appSettingsForm.getPanel();
    setContent(ScrollPaneFactory.createScrollPane(panel));
  }

  private JPanel createToolbarPanel() {
    final DefaultActionGroup group = new DefaultActionGroup();
    group.add(new SendAction());
    group.add(new ReceiveAction());

    group.addSeparator();
    //group.add(new ContextHelpAction(HelpID.ANT));

    /*group.add(new RemoveAction());
    group.add(new RunAction());
    group.add(new ShowAllTargetsAction());
    AnAction action = CommonActionsManager.getInstance().createExpandAllAction(myTreeExpander, this);
    action.getTemplatePresentation().setDescription(AntBundle.message("ant.explorer.expand.all.nodes.action.description"));
    group.add(action);
    action = CommonActionsManager.getInstance().createCollapseAllAction(myTreeExpander, this);
    action.getTemplatePresentation().setDescription(AntBundle.message("ant.explorer.collapse.all.nodes.action.description"));
    group.add(action);
    group.add(myAntBuildFilePropertiesAction);
    group.add(new ContextHelpAction(HelpID.ANT));*/

    final ActionToolbar actionToolBar = ActionManager.getInstance().createActionToolbar(ActionPlaces.ANT_EXPLORER_TOOLBAR, group, true);
    return JBUI.Panels.simplePanel(actionToolBar.getComponent());
  }

  private final class SendAction extends AnAction {
    public SendAction() {
      super("Send", "Send to Simulator", IconUtil.getMoveUpIcon());
    }

    public void actionPerformed(AnActionEvent e) {
      appSettingsForm.sendSettingsToSimulator();
    }
  }

  private final class ReceiveAction extends AnAction {
    public ReceiveAction() {
      super("Receive", "Receive from Simulator", IconUtil.getMoveDownIcon());
    }

    public void actionPerformed(AnActionEvent e) {
      appSettingsForm.receiveSettings();
    }
  }
}

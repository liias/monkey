package ee.edio.garmin.module;


import com.intellij.openapi.roots.ui.configuration.ModulesProvider;

import javax.swing.*;

public class MonkeyAppPropertiesEditor {
  private JPanel myContentPanel;
  private JTextPane inFutureReleasesSomeTextPane;

  private final ModulesProvider myModulesProvider;

  public MonkeyAppPropertiesEditor(String moduleName, ModulesProvider modulesProvider) {
    myModulesProvider = modulesProvider;
  }

  public JPanel getContentPanel() {
    return myContentPanel;
  }
}

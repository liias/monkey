package io.github.liias.monkey.ide.actions.appsettings.ui;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.Condition;

public class AppSettingsToolWindowCondition implements Condition, DumbAware {
  @Override
  public boolean value(Object o) {
    return true;
  }
}

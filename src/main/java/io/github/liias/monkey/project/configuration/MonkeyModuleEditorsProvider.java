package io.github.liias.monkey.project.configuration;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleConfigurationEditor;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ClasspathEditor;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationEditorProvider;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationState;
import com.intellij.openapi.roots.ui.configuration.OutputEditor;
import io.github.liias.monkey.project.module.MonkeyModuleType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MonkeyModuleEditorsProvider implements ModuleConfigurationEditorProvider {
  public ModuleConfigurationEditor[] createEditors(@NotNull ModuleConfigurationState state) {
    ModifiableRootModel rootModel = state.getRootModel();
    Module module = rootModel.getModule();
    if (!(ModuleType.get(module) instanceof MonkeyModuleType)) {
      return ModuleConfigurationEditor.EMPTY;
    }

    String moduleName = module.getName();
    List<ModuleConfigurationEditor> editors = new ArrayList<>();
    editors.add(new MonkeyContentEntriesEditor(moduleName, state));
    editors.add(new OutputEditorEx(state));
    editors.add(new ClasspathEditor(state));
    return editors.toArray(new ModuleConfigurationEditor[editors.size()]);
  }

  static class OutputEditorEx extends OutputEditor {
    protected OutputEditorEx(ModuleConfigurationState state) {
      super(state);
    }

    protected JComponent createComponentImpl() {
      JComponent component = super.createComponentImpl();
      component.remove(1); // todo: looks ugly
      return component;
    }
  }
}

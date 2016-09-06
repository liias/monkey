package io.github.liias.monkey.project.ui.module;

import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.ui.ComboBox;
import io.github.liias.monkey.project.module.MonkeyModuleBuilder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MonkeyTemplateChooser {
  private JPanel myContentPanel;
  private JTextPane chooseTemplateTextPane;
  private ComboBox templateCombobox;

  private final WizardContext wizardContext;
  private final MonkeyModuleBuilder moduleBuilder;

  public MonkeyTemplateChooser(MonkeyModuleBuilder moduleBuilder, WizardContext wizardContext) {
    this.wizardContext = wizardContext;
    this.moduleBuilder = moduleBuilder;
  }

  private void createUIComponents() {
    /*
    Project fakeProject = ProjectManager.getInstance().getDefaultProject();
    VirtualFile sdkBinDir = LocalFileSystem.getInstance().refreshAndFindFileByPath("D:\\connectiq\\connectiq-sdk-win-2.1.3\\bin");
    ProjectInfo sdkProjectInfo = MonkeyModuleBuilder.getSdkProjectInfo(fakeProject, sdkBinDir);

    List<NewProjectFileMap> candidates = sdkProjectInfo.getNewProjectFilesMaps().getNewProjectFileMaps().stream()
      .filter(fileMap -> fileMap != null && moduleBuilder.getAppType().equals(fileMap.getAppType().getStringValue()))
      .collect(Collectors.toList());

    List<String> names = candidates.stream().map(c -> c.getName().getStringValue()).collect(Collectors.toList());*/

    List<String> names = new ArrayList<>();
    names.add("simple");
    templateCombobox = new ComboBox<String>();

    for (String name : names) {
      templateCombobox.addItem(name);
    }


/*
    templateCombobox.addActionListener(e -> {
      selectModule(templateCombobox.getSelectedIndex());
    });*/

  }

  public JPanel getContentPanel() {
    return myContentPanel;
  }
}
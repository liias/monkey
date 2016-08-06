package io.github.liias.monkey.project;

import com.google.common.base.Throwables;
import com.intellij.ide.util.importProject.ModuleDescriptor;
import com.intellij.ide.util.importProject.ProjectDescriptor;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.ProjectJdkForModuleStep;
import com.intellij.ide.util.projectWizard.importSources.DetectedProjectRoot;
import com.intellij.ide.util.projectWizard.importSources.DetectedSourceRoot;
import com.intellij.ide.util.projectWizard.importSources.ProjectFromSourcesBuilder;
import com.intellij.ide.util.projectWizard.importSources.ProjectStructureDetector;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.io.FileUtilRt;
import io.github.liias.monkey.lang.file.MonkeyFileType;
import io.github.liias.monkey.project.module.MonkeyModuleBuilder;
import io.github.liias.monkey.project.module.MonkeyModuleType;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

/**
 * Handles New project from existing sources
 */
public class MonkeyProjectStructureDetector extends ProjectStructureDetector {

  public static final String MONKEY_SOURCE_FILE_EXTENSION = MonkeyFileType.INSTANCE.getDefaultExtension();

  /*
      Detects source roots instead of module content root.
      see com.intellij.ide.util.importProject.RootDetectionProcessor.mergeContentRoots(), content root is ignored if there are source roots
       */
  @NotNull
  @Override
  public DirectoryProcessingResult detectRoots(@NotNull File dir,
                                               @NotNull File[] children,
                                               @NotNull File base,
                                               @NotNull List<DetectedProjectRoot> result) {
    for (File child : children) {
      if (child.isFile() && FileUtilRt.extensionEquals(child.getName(), MONKEY_SOURCE_FILE_EXTENSION)) {
        result.add(new MonkeyDetectedSourceRoot(dir));
        // alternatively could add one module root instead of many source roots:
        //result.add(new DetectedContentRoot(dir, "MonkeyC", MonkeyModuleType.getInstance(), WebModuleType.getInstance()));
        return DirectoryProcessingResult.SKIP_CHILDREN;
      }
    }

    return DirectoryProcessingResult.PROCESS_CHILDREN;
  }

  /*
  Collect all source roots and add a new module with them
   */
  @Override
  public void setupProjectStructure(@NotNull Collection<DetectedProjectRoot> roots,
                                    @NotNull ProjectDescriptor projectDescriptor,
                                    @NotNull ProjectFromSourcesBuilder builder) {

    if (!builder.hasRootsFromOtherDetectors(this)) {
      builder.setupModulesByContentRoots(projectDescriptor, roots);

      if (!roots.isEmpty()) {
        List<ModuleDescriptor> modules = projectDescriptor.getModules();
        if (modules.isEmpty()) {
          roots.stream()
            .filter(r -> r instanceof MonkeyDetectedSourceRoot)
            .map(r -> (MonkeyDetectedSourceRoot) r)
            .collect(groupingBy(sr -> sr.getDirectory().getParentFile(), mapping(Function.identity(), toSet())))
            .forEach((moduleContentRoot, moduleSourceRoots) -> {
                ModuleDescriptor moduleDescriptor = new ModuleDescriptor(moduleContentRoot, MonkeyModuleType.getInstance(), moduleSourceRoots);
                moduleDescriptor.addConfigurationUpdater(new MonkeyModuleConfigurationUpdater());
                modules.add(moduleDescriptor);
              }
            );
        }
      }
    }
  }

  public static class MonkeyModuleConfigurationUpdater extends ModuleBuilder.ModuleConfigurationUpdater {
    @Override
    public void update(@NotNull Module module, @NotNull ModifiableRootModel rootModel) {
      MonkeyModuleType monkeyModuleType = MonkeyModuleType.getInstance();
      MonkeyModuleBuilder moduleBuilder = monkeyModuleType.createModuleBuilder();
      try {
        moduleBuilder.setupRootModel(rootModel);
      } catch (ConfigurationException e) {
        Throwables.propagate(e);
      }
    }
  }

  @NotNull
  @Override
  public List<ModuleWizardStep> createWizardSteps(@NotNull ProjectFromSourcesBuilder builder, ProjectDescriptor projectDescriptor, Icon stepIcon) {
    ProjectJdkForModuleStep projectJdkForModuleStep = new ProjectJdkForModuleStep(builder.getContext(), MonkeySdkType.getInstance());
    return Collections.singletonList(projectJdkForModuleStep);
  }

  private static class MonkeyDetectedSourceRoot extends DetectedSourceRoot {
    public MonkeyDetectedSourceRoot(File directory) {
      super(directory, null);
    }

    @NotNull
    @Override
    public String getRootTypeName() {
      return "MonkeyC";
    }
  }
}

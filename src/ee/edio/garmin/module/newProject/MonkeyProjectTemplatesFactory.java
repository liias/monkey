package ee.edio.garmin.module.newProject;

import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.platform.ProjectTemplate;
import com.intellij.platform.ProjectTemplatesFactory;
import com.intellij.platform.templates.BuilderBasedTemplate;
import ee.edio.garmin.MonkeyIcons;
import ee.edio.garmin.MonkeyModuleBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MonkeyProjectTemplatesFactory extends ProjectTemplatesFactory {

  public static final String WATCH_FACE = "Watch Face";
  public static final String DATA_FIELD = "Data field";
  public static final String WIDGET = "Widget";
  public static final String WATCH_APP = "Application";


  @NotNull
  @Override
  public String[] getGroups() {
    return new String[]{"Monkey C "};
  }

  @Override
  public String getParentGroup(String group) {
    return null;
  }

  @Override
  public Icon getGroupIcon(String group) {
    return MonkeyIcons.MODULE16;
  }

  @NotNull
  @Override
  public ProjectTemplate[] createTemplates(@Nullable String group, WizardContext context) {
    ProjectTemplate[] templates = {
        new MonkeyProjectTemplate("app", "long description is here", new MonkeyModuleBuilder(WATCH_APP)),
        new MonkeyProjectTemplate("watch face", "long description is here", new MonkeyModuleBuilder(WATCH_FACE)),
        new MonkeyProjectTemplate(DATA_FIELD, "long description is here", new MonkeyModuleBuilder(DATA_FIELD)),
    };

    return templates;
  }

  private static class MonkeyProjectTemplate extends BuilderBasedTemplate {

    private final String myName;
    private final String myDescription;

    private MonkeyProjectTemplate(String name, String description, MonkeyModuleBuilder builder) {
      super(builder);
      myName = name;
      myDescription = description;
    }

    @NotNull
    @Override
    public String getName() {
      return myName;
    }

    @Nullable
    @Override
    public String getDescription() {
      return myDescription;
    }
  }
}

package ee.edio.garmin.dom.sdk.projectinfo;

import com.intellij.util.xml.DomFileDescription;

public class ProjectInfoDomFileDescription extends DomFileDescription<ProjectInfo> {
  public ProjectInfoDomFileDescription() {
    super(ProjectInfo.class, "monkeybrains");
  }
}
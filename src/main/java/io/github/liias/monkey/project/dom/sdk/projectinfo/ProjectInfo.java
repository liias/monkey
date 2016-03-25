package io.github.liias.monkey.project.dom.sdk.projectinfo;

public interface ProjectInfo extends ProjectInfoDomElement {
  AppPermissions getAppPermissions();

  AppTypes getAppTypes();

  PermissionMaps getPermissionMaps();

  NewProjectFilesMaps getNewProjectFilesMaps();
}

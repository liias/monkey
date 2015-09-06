package ee.edio.garmin.dom.sdk.projectinfo;

import java.util.List;

public interface Permissions extends ProjectInfoDomElement {
  List<Permission> getPermissions();
}

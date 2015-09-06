package ee.edio.garmin.dom.manifest;

import java.util.List;

public interface Permissions extends ManifestDomElement {
  List<UsesPermission> getUsesPermissions();
}
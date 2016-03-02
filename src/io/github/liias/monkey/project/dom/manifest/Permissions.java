package io.github.liias.monkey.project.dom.manifest;

import java.util.List;

public interface Permissions extends ManifestDomElement {
  List<UsesPermission> getUsesPermissions();
}
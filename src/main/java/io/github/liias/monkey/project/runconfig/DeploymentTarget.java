package io.github.liias.monkey.project.runconfig;

public class DeploymentTarget {
  private String id;
  private String name;

  public DeploymentTarget(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return getName();
  }

  // only id matters
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DeploymentTarget that = (DeploymentTarget) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}

package io.github.liias.monkey.jps.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.ex.JpsElementBase;

import java.io.File;

public class JpsMonkeyGlobalProperties extends JpsElementBase<JpsMonkeyGlobalProperties> implements JpsElement {
  private File developerKeyPath;

  public JpsMonkeyGlobalProperties(File developerKeyPath) {
    this.developerKeyPath = developerKeyPath;
  }

  public File getDeveloperKeyPath() {
    return developerKeyPath;
  }

  @NotNull
  @Override
  public JpsMonkeyGlobalProperties createCopy() {
    return new JpsMonkeyGlobalProperties(developerKeyPath);
  }

  @Override
  public void applyChanges(@NotNull JpsMonkeyGlobalProperties modified) {
  }
}
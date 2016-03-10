package io.github.liias.monkey.jps.builder;

import com.intellij.openapi.util.io.FileUtilRt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildRootDescriptor;
import org.jetbrains.jps.builders.BuildTarget;

import java.io.File;
import java.io.FileFilter;

public class MonkeySourceRootDescriptor extends BuildRootDescriptor {
  private final File root;
  private final MonkeyBuildTarget monkeyBuildTarget;

  public MonkeySourceRootDescriptor(File root, MonkeyBuildTarget monkeyBuildTarget) {
    this.root = root;
    this.monkeyBuildTarget = monkeyBuildTarget;
  }

  @NotNull
  @Override
  public String getRootId() {
    return root.getAbsolutePath();
  }

  @Override
  public File getRootFile() {
    return root;
  }

  @Override
  public BuildTarget<?> getTarget() {
    return monkeyBuildTarget;
  }

  @NotNull
  @Override
  public FileFilter createFileFilter() {
    return file -> FileUtilRt.extensionEquals(file.getName(), "mc");
  }
}

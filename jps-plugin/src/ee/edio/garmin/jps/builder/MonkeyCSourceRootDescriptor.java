package ee.edio.garmin.jps.builder;

import com.intellij.openapi.util.io.FileUtilRt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildRootDescriptor;
import org.jetbrains.jps.builders.BuildTarget;

import java.io.File;
import java.io.FileFilter;

public class MonkeyCSourceRootDescriptor extends BuildRootDescriptor {
  private final File root;
  private final MonkeyCTarget monkeyCTarget;

  public MonkeyCSourceRootDescriptor(File root, MonkeyCTarget monkeyCTarget) {
    this.root = root;
    this.monkeyCTarget = monkeyCTarget;
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
    return monkeyCTarget;
  }

  @NotNull
  @Override
  public FileFilter createFileFilter() {
    return new FileFilter() {
      @Override
      public boolean accept(@NotNull File file) {
        return FileUtilRt.extensionEquals(file.getName(), "mc");
      }
    };
  }
}

package ee.edio.garmin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MonkeyFileType extends LanguageFileType {
  public static final MonkeyFileType INSTANCE = new MonkeyFileType();

  private MonkeyFileType() {
    super(MonkeyLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "Monkey C";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Monkey C source file";
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "mc";
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return MonkeyIcons.FILE;
  }
}

package io.github.liias.monkey.lang.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import io.github.liias.monkey.icons.MonkeyIcons;
import io.github.liias.monkey.lang.MonkeyLanguage;
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
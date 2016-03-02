package io.github.liias.monkey.lang;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;

public class MonkeyLanguage extends Language {
  public static final MonkeyLanguage INSTANCE = new MonkeyLanguage();

  private MonkeyLanguage() {
    super("MonkeyC");
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "Monkey C";
  }

  @Override
  public boolean isCaseSensitive() {
    return true;
  }
}

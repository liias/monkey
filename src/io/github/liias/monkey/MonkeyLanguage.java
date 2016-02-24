package io.github.liias.monkey;

import com.intellij.lang.Language;

public class MonkeyLanguage extends Language {
  public static final MonkeyLanguage INSTANCE = new MonkeyLanguage();

  private MonkeyLanguage() {
    super("MonkeyC");
  }
}

package ee.edio.garmin;

import com.intellij.lang.Language;

public class MonkeyLanguage extends Language {
  public static final MonkeyLanguage INSTANCE = new MonkeyLanguage();

  private MonkeyLanguage() {
    super("MonkeyC");
  }
}

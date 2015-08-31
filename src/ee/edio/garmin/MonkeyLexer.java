package ee.edio.garmin;

import com.intellij.lexer.FlexAdapter;

public class MonkeyLexer extends FlexAdapter {
  public MonkeyLexer() {
    super(new _MonkeyLexer());
  }
}

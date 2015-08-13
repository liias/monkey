package ee.edio.garmin;

import com.intellij.lexer.FlexAdapter;

public class MonkeyCLexer extends FlexAdapter {
  public MonkeyCLexer() {
    super(new _MonkeyCLexer());
  }
}

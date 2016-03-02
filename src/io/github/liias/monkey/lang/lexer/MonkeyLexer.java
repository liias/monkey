package io.github.liias.monkey.lang.lexer;

import com.intellij.lexer.FlexAdapter;

public class MonkeyLexer extends FlexAdapter {
  public MonkeyLexer() {
    super(new _MonkeyLexer());
  }
}

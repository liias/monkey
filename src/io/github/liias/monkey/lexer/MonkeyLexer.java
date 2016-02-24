package io.github.liias.monkey.lexer;

import com.intellij.lexer.FlexAdapter;

public class MonkeyLexer extends FlexAdapter {
  public MonkeyLexer() {
    super(new _MonkeyLexer());
  }
}

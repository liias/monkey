package ee.edio.garmin;

import com.intellij.psi.tree.TokenSet;
import ee.edio.garmin.psi.MonkeyTypes;

public interface MonkeyTokenTypesSets {
  TokenSet STRINGS = TokenSet.create(MonkeyTypes.STRING);

  TokenSet BUILT_IN_IDENTIFIERS = TokenSet.create(
      MonkeyTypes.AND,
      MonkeyTypes.AS,
      MonkeyTypes.CLASS,
      MonkeyTypes.CONST,
      MonkeyTypes.DO,
      MonkeyTypes.ELSE,
      MonkeyTypes.ENUM,
      MonkeyTypes.EXTENDS,
      MonkeyTypes.FALSE,
      MonkeyTypes.FOR,
      MonkeyTypes.FUNCTION,
      MonkeyTypes.HAS,
      MonkeyTypes.HIDDEN,
      MonkeyTypes.IF,
      MonkeyTypes.INSTANCEOF,
      MonkeyTypes.MODULE,
      MonkeyTypes.NATIVE,
      MonkeyTypes.NEW,
      MonkeyTypes.NULL,
      MonkeyTypes.OR,
      MonkeyTypes.RETURN,
      MonkeyTypes.STATIC,
      MonkeyTypes.TRUE,
      MonkeyTypes.USING,
      MonkeyTypes.VAR,
      MonkeyTypes.WHILE
  );


}

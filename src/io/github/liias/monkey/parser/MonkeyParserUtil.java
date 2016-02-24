package io.github.liias.monkey.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import io.github.liias.monkey.psi.MonkeyTypes;

public class MonkeyParserUtil extends GeneratedParserUtilBase {
  public static boolean nonStrictID(PsiBuilder builder_, int level_) {
    final PsiBuilder.Marker marker_ = builder_.mark();
    final boolean result_ = consumeToken(builder_, MonkeyTypes.IDENTIFIER);
    if (result_) {
      marker_.done(MonkeyTypes.ID);
      return true;
    } else if (MonkeyTokenTypesSets.BUILT_IN_IDENTIFIERS.contains(builder_.getTokenType())) {
      builder_.advanceLexer();
      marker_.done(MonkeyTypes.ID);
      return true;
    }
    marker_.rollbackTo();
    return false;
  }
}

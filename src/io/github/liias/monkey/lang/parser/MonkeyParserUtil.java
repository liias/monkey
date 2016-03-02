package io.github.liias.monkey.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import io.github.liias.monkey.lang.psi.MonkeyTypes;

public class MonkeyParserUtil extends GeneratedParserUtilBase {
  public static boolean strictID(PsiBuilder builder_, int level_) {
    final PsiBuilder.Marker marker_ = builder_.mark();
    final boolean result_ = consumeToken(builder_, MonkeyTypes.IDENTIFIER);
    if (result_) {
      marker_.done(MonkeyTypes.ID);
      return true;
    }
    marker_.rollbackTo();
    return false;
  }
}

package ee.edio.garmin.findUsages;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.psi.tree.TokenSet;
import ee.edio.garmin.MonkeyLexer;
import ee.edio.garmin.psi.MonkeyTypes;

public class MonkeyWordsScanner extends DefaultWordsScanner {
  public MonkeyWordsScanner() {
    super(new MonkeyLexer(), TokenSet.create(MonkeyTypes.IDENTIFIER),
        TokenSet.create(MonkeyTypes.BLOCK_COMMENT, MonkeyTypes.SINGLE_LINE_COMMENT), TokenSet.create(MonkeyTypes.LITERAL));
    //setMayHaveFileRefsInLiterals(true);
  }
}

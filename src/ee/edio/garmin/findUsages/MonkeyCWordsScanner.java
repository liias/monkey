package ee.edio.garmin.findUsages;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.psi.tree.TokenSet;
import ee.edio.garmin.MonkeyCLexer;
import ee.edio.garmin.psi.MonkeyCTypes;

public class MonkeyCWordsScanner extends DefaultWordsScanner {
  public MonkeyCWordsScanner() {
    super(new MonkeyCLexer(), TokenSet.create(MonkeyCTypes.IDENTIFIER),
        TokenSet.create(MonkeyCTypes.BLOCK_COMMENT, MonkeyCTypes.SINGLE_LINE_COMMENT), TokenSet.create(MonkeyCTypes.LITERAL));
    //setMayHaveFileRefsInLiterals(true);
  }
}

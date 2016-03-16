package io.github.liias.monkey.lang.ide;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import io.github.liias.monkey.lang.psi.MonkeyTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyBraceMatcher implements PairedBraceMatcher {
  private static final BracePair[] BRACE_PAIRS = new BracePair[]{
      new BracePair(MonkeyTypes.LPAREN, MonkeyTypes.RPAREN, false),
      new BracePair(MonkeyTypes.LBRACE, MonkeyTypes.RBRACE, true),
      new BracePair(MonkeyTypes.LBRACKET, MonkeyTypes.RBRACKET, false),
      new BracePair(MonkeyTypes.LT, MonkeyTypes.GT, false)
  };


  @Override
  public BracePair[] getPairs() {
    return BRACE_PAIRS;
  }

  @Override
  public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
    return true;
  }

  @Override
  public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
    return openingBraceOffset;
  }
}

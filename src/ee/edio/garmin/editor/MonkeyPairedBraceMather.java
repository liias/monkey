package ee.edio.garmin.editor;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import ee.edio.garmin.MonkeyParserDefinition;
import ee.edio.garmin.psi.MonkeyElementType;
import ee.edio.garmin.psi.MonkeyTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyPairedBraceMather implements PairedBraceMatcher {
  private final BracePair[] pairs = new BracePair[]{
      new BracePair(MonkeyTypes.LPAREN, MonkeyTypes.RPAREN, false),
      new BracePair(MonkeyTypes.LBRACE, MonkeyTypes.RBRACE, true),
      new BracePair(MonkeyTypes.LBRACKET, MonkeyTypes.RBRACKET, false),
      new BracePair(MonkeyTypes.LT, MonkeyTypes.GT, false)
  };

  @Override
  public BracePair[] getPairs() {
    return pairs;
  }

  @Override
  public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
    if (contextType instanceof MonkeyElementType) {
      return isPairedBracesAllowedBeforeTypeInMonkeyC(contextType);
    }
    return true;
  }


  private static boolean isPairedBracesAllowedBeforeTypeInMonkeyC(final IElementType tokenType) {
    TokenSet COMMENTS_OR_WHITESPACES = TokenSet.orSet(MonkeyParserDefinition.COMMENTS, MonkeyParserDefinition.WHITE_SPACES);
    return COMMENTS_OR_WHITESPACES.contains(tokenType)
        || tokenType == MonkeyTypes.SEMI
        || tokenType == MonkeyTypes.COMMA
        || tokenType == MonkeyTypes.RPAREN
        || tokenType == MonkeyTypes.RBRACKET
        || tokenType == MonkeyTypes.RBRACE
        || tokenType == MonkeyTypes.LBRACE;
  }

  @Override
  public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
    /*PsiElement element = file.findElementAt(openingBraceOffset);
    if (element == null || element instanceof PsiFile) return openingBraceOffset;
    PsiElement parent = element.getParent();
    if (parent instanceof MonkeyBlock) {
      parent = parent.getParent();
      if (parent instanceof MonkeyFunctionDeclaration) {
        TextRange range = DeclarationRangeUtil.getDeclarationRange(parent);
        return range.getStartOffset();
      } else if (parent instanceof MonkeyStatement) {
        if (parent instanceof MonkeyBlockStatement && parent.getParent() instanceof MonkeyStatement) {
          parent = parent.getParent();
        }
        return parent.getTextRange().getStartOffset();
      }
    } else if (parent instanceof MonkeyClassDeclaration) {
      TextRange range = DeclarationRangeUtil.getDeclarationRange(parent);
      return range.getStartOffset();
    }*/

    return openingBraceOffset;
  }
}

package ee.edio.garmin.editor;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import ee.edio.garmin.MonkeyCParserDefinition;
import ee.edio.garmin.psi.MonkeyCElementType;
import ee.edio.garmin.psi.MonkeyCTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MCPairedBraceMather implements PairedBraceMatcher {
  private final BracePair[] pairs = new BracePair[]{
      new BracePair(MonkeyCTypes.LPAREN, MonkeyCTypes.RPAREN, false),
      new BracePair(MonkeyCTypes.LBRACE, MonkeyCTypes.RBRACE, true),
      new BracePair(MonkeyCTypes.LBRACKET, MonkeyCTypes.RBRACKET, false),
      new BracePair(MonkeyCTypes.LT, MonkeyCTypes.GT, false)
  };

  @Override
  public BracePair[] getPairs() {
    return pairs;
  }

  @Override
  public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
    if (contextType instanceof MonkeyCElementType) {
      return isPairedBracesAllowedBeforeTypeInMonkeyC(contextType);
    }
    return true;
  }


  private static boolean isPairedBracesAllowedBeforeTypeInMonkeyC(final IElementType tokenType) {
    TokenSet COMMENTS_OR_WHITESPACES = TokenSet.orSet(MonkeyCParserDefinition.COMMENTS, MonkeyCParserDefinition.WHITE_SPACES);
    return COMMENTS_OR_WHITESPACES.contains(tokenType)
        || tokenType == MonkeyCTypes.SEMI
        || tokenType == MonkeyCTypes.COMMA
        || tokenType == MonkeyCTypes.RPAREN
        || tokenType == MonkeyCTypes.RBRACKET
        || tokenType == MonkeyCTypes.RBRACE
        || tokenType == MonkeyCTypes.LBRACE;
  }

  @Override
  public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
    /*PsiElement element = file.findElementAt(openingBraceOffset);
    if (element == null || element instanceof PsiFile) return openingBraceOffset;
    PsiElement parent = element.getParent();
    if (parent instanceof MonkeyCBlock) {
      parent = parent.getParent();
      if (parent instanceof MonkeyCFunctionDeclaration) {
        TextRange range = DeclarationRangeUtil.getDeclarationRange(parent);
        return range.getStartOffset();
      } else if (parent instanceof MonkeyCStatement) {
        if (parent instanceof MonkeyCBlockStatement && parent.getParent() instanceof MonkeyCStatement) {
          parent = parent.getParent();
        }
        return parent.getTextRange().getStartOffset();
      }
    } else if (parent instanceof MonkeyCClassDeclaration) {
      TextRange range = DeclarationRangeUtil.getDeclarationRange(parent);
      return range.getStartOffset();
    }*/

    return openingBraceOffset;
  }
}

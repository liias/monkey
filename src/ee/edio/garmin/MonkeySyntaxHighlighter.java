package ee.edio.garmin;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import ee.edio.garmin.psi.MonkeyTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*;
import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class MonkeySyntaxHighlighter extends SyntaxHighlighterBase {

  public static final TextAttributesKey MC_LINE_COMMENT = TextAttributesKey.createTextAttributesKey("MC.LINE_COMMENT", LINE_COMMENT);
  public static final TextAttributesKey MC_BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey("MC.BLOCK_COMMENT", BLOCK_COMMENT);
  public static final TextAttributesKey MC_CLASS_NAME = createTextAttributesKey("MC.CLASS_NAME", CLASS_NAME);
  public static final TextAttributesKey MC_FUNCTION_DECLARATION = createTextAttributesKey("MC.FUNCTION_DECLARATION", FUNCTION_DECLARATION);
  public static final TextAttributesKey MC_KEYWORD = createTextAttributesKey("MC.KEYWORD", KEYWORD);
  public static final TextAttributesKey MC_OPERATOR = createTextAttributesKey("MC.OPERATOR", OPERATION_SIGN);
  public static final TextAttributesKey MC_STRING = createTextAttributesKey("MC.STRING", STRING);
  public static final TextAttributesKey MC_NUMBER = createTextAttributesKey("MC.NUMBER", NUMBER);

  private static final TokenSet KEYWORD_TOKENS = TokenSet.create(
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

  private static final TokenSet OPERATOR_TOKENS = TokenSet.create(
      MonkeyTypes.PLUS,
      MonkeyTypes.SUB,
      MonkeyTypes.STAR,
      MonkeyTypes.SLASH
  );

  private static final Map<IElementType, TextAttributesKey> TYPE_KEY_MAP = createTypeKeyMap();

  private static Map<IElementType, TextAttributesKey> createTypeKeyMap() {
    Map<IElementType, TextAttributesKey> aMap = new HashMap<>();
    fillMap(aMap, KEYWORD_TOKENS, MC_KEYWORD);
    fillMap(aMap, OPERATOR_TOKENS, MC_OPERATOR);
    fillMap(aMap, MonkeyTokenTypesSets.STRINGS, MC_STRING);
    aMap.put(MonkeyTypes.SINGLE_LINE_COMMENT, MC_LINE_COMMENT);
    aMap.put(MonkeyTypes.BLOCK_COMMENT, MC_BLOCK_COMMENT);
    aMap.put(MonkeyTypes.INTLITERAL, MC_NUMBER);
    aMap.put(MonkeyTypes.LONGLITERAL, MC_NUMBER);
    aMap.put(MonkeyTypes.FLOATLITERAL, MC_NUMBER);
    aMap.put(MonkeyTypes.DOUBLELITERAL, MC_NUMBER);

    return Collections.unmodifiableMap(aMap);
  }

  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new FlexAdapter(new _MonkeyLexer(null));
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(TYPE_KEY_MAP.get(tokenType));
  }
}

package ee.edio.garmin;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import ee.edio.garmin.psi.MonkeyCTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*;
import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class MonkeyCSyntaxHighlighter extends SyntaxHighlighterBase {

  public static final TextAttributesKey MC_LINE_COMMENT = TextAttributesKey.createTextAttributesKey("MC.LINE_COMMENT", LINE_COMMENT);
  public static final TextAttributesKey MC_BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey("MC.BLOCK_COMMENT", BLOCK_COMMENT);
  public static final TextAttributesKey MC_CLASS_NAME = createTextAttributesKey("MC.CLASS_NAME", CLASS_NAME);
  public static final TextAttributesKey MC_FUNCTION_DECLARATION = createTextAttributesKey("MC.FUNCTION_DECLARATION", FUNCTION_DECLARATION);
  public static final TextAttributesKey MC_KEYWORD = createTextAttributesKey("MC.KEYWORD", KEYWORD);
  public static final TextAttributesKey MC_OPERATOR = createTextAttributesKey("MC.OPERATOR", OPERATION_SIGN);
  public static final TextAttributesKey MC_STRING = createTextAttributesKey("MC.STRING", STRING);
  public static final TextAttributesKey MC_NUMBER = createTextAttributesKey("MC.NUMBER", NUMBER);

  private static final TokenSet KEYWORD_TOKENS = TokenSet.create(
      MonkeyCTypes.AND,
      MonkeyCTypes.AS,
      MonkeyCTypes.CLASS,
      MonkeyCTypes.CONST,
      MonkeyCTypes.DO,
      MonkeyCTypes.ELSE,
      MonkeyCTypes.ENUM,
      MonkeyCTypes.EXTENDS,
      MonkeyCTypes.FALSE,
      MonkeyCTypes.FOR,
      MonkeyCTypes.FUNCTION,
      MonkeyCTypes.HAS,
      MonkeyCTypes.HIDDEN,
      MonkeyCTypes.IF,
      MonkeyCTypes.INSTANCEOF,
      MonkeyCTypes.MODULE,
      MonkeyCTypes.NATIVE,
      MonkeyCTypes.NEW,
      MonkeyCTypes.NULL,
      MonkeyCTypes.OR,
      MonkeyCTypes.RETURN,
      MonkeyCTypes.STATIC,
      MonkeyCTypes.TRUE,
      MonkeyCTypes.USING,
      MonkeyCTypes.VAR,
      MonkeyCTypes.WHILE
  );

  private static final TokenSet OPERATOR_TOKENS = TokenSet.create(
      MonkeyCTypes.OP_PLUS,
      MonkeyCTypes.OP_MINUS,
      MonkeyCTypes.OP_MULTIPLY,
      MonkeyCTypes.OP_DIVIDE
  );

  private static final Map<IElementType, TextAttributesKey> TYPE_KEY_MAP = createTypeKeyMap();

  private static Map<IElementType, TextAttributesKey> createTypeKeyMap() {
    Map<IElementType, TextAttributesKey> aMap = new HashMap<>();
    fillMap(aMap, KEYWORD_TOKENS, MC_KEYWORD);
    fillMap(aMap, OPERATOR_TOKENS, MC_OPERATOR);
    aMap.put(MonkeyCTypes.SINGLE_LINE_COMMENT, MC_LINE_COMMENT);
    aMap.put(MonkeyCTypes.BLOCK_COMMENT, MC_BLOCK_COMMENT);
    aMap.put(MonkeyCTypes.STRING, MC_STRING);
    aMap.put(MonkeyCTypes.NUMBER, MC_NUMBER);

    return Collections.unmodifiableMap(aMap);
  }

  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new FlexAdapter(new _MonkeyCLexer(null));
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(TYPE_KEY_MAP.get(tokenType));
  }
}

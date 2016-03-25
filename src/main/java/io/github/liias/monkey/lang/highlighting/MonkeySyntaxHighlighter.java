package io.github.liias.monkey.lang.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import io.github.liias.monkey.lang.lexer.MonkeyLexer;
import io.github.liias.monkey.lang.parser.MonkeyTokenTypesSets;
import io.github.liias.monkey.lang.psi.MonkeyTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*;
import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class MonkeySyntaxHighlighter extends SyntaxHighlighterBase {
  public static final TextAttributesKey MC_LINE_COMMENT = TextAttributesKey.createTextAttributesKey("MC.LINE_COMMENT", LINE_COMMENT);
  public static final TextAttributesKey MC_LINE_DOC_COMMENT = TextAttributesKey.createTextAttributesKey("MC.LINE_DOC_COMMENT", DOC_COMMENT);
  public static final TextAttributesKey MC_BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey("MC.BLOCK_COMMENT", BLOCK_COMMENT);
  public static final TextAttributesKey MC_CLASS_NAME = createTextAttributesKey("MC.CLASS_NAME", CLASS_NAME);
  public static final TextAttributesKey MC_FUNCTION_DECLARATION = createTextAttributesKey("MC.FUNCTION_DECLARATION", FUNCTION_DECLARATION);
  public static final TextAttributesKey MC_KEYWORD = createTextAttributesKey("MC.KEYWORD", KEYWORD);
  public static final TextAttributesKey MC_OPERATOR = createTextAttributesKey("MC.OPERATOR", OPERATION_SIGN);
  public static final TextAttributesKey MC_STRING = createTextAttributesKey("MC.STRING", STRING);
  public static final TextAttributesKey MC_NUMBER = createTextAttributesKey("MC.NUMBER", NUMBER);

  private static final TokenSet OPERATOR_TOKENS = TokenSet.create(
      MonkeyTypes.PLUS,
      MonkeyTypes.SUB,
      MonkeyTypes.STAR,
      MonkeyTypes.SLASH
  );

  private static final TokenSet NUMBER_LITERALS = TokenSet.create(
      MonkeyTypes.INTLITERAL,
      MonkeyTypes.LONGLITERAL,
      MonkeyTypes.FLOATLITERAL,
      MonkeyTypes.DOUBLELITERAL,
      MonkeyTypes.HEX_LITERAL
  );

  private static final Map<IElementType, TextAttributesKey> TYPE_KEY_MAP = createTypeKeyMap();

  private static Map<IElementType, TextAttributesKey> createTypeKeyMap() {
    Map<IElementType, TextAttributesKey> aMap = new HashMap<>();
    fillMap(aMap, MonkeyTokenTypesSets.BUILT_IN_IDENTIFIERS, MC_KEYWORD);
    fillMap(aMap, OPERATOR_TOKENS, MC_OPERATOR);
    fillMap(aMap, MonkeyTokenTypesSets.STRINGS, MC_STRING);
    fillMap(aMap, NUMBER_LITERALS, MC_NUMBER);
    aMap.put(MonkeyTypes.SINGLE_LINE_COMMENT, MC_LINE_COMMENT);
    aMap.put(MonkeyTypes.SINGLE_LINE_DOC_COMMENT, MC_LINE_DOC_COMMENT);
    aMap.put(MonkeyTypes.BLOCK_COMMENT, MC_BLOCK_COMMENT);

    return Collections.unmodifiableMap(aMap);
  }

  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new MonkeyLexer();
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(TYPE_KEY_MAP.get(tokenType));
  }
}
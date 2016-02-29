// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.github.liias.monkey.psi.MonkeyTypes.*;
import static io.github.liias.monkey.parser.MonkeyParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class MonkeyParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ANNOTATION) {
      r = annotation(b, 0);
    }
    else if (t == CLASS_BODY) {
      r = classBody(b, 0);
    }
    else if (t == CLASS_BODY_MEMBERS) {
      r = classBodyMembers(b, 0);
    }
    else if (t == CLASS_DECLARATION) {
      r = classDeclaration(b, 0);
    }
    else if (t == COMPONENT_NAME) {
      r = componentName(b, 0);
    }
    else if (t == EXPRESSION) {
      r = expression(b, 0);
    }
    else if (t == FIELD_DECLARATION) {
      r = fieldDeclaration(b, 0);
    }
    else if (t == ID) {
      r = id(b, 0);
    }
    else if (t == LITERAL_EXPRESSION) {
      r = literalExpression(b, 0);
    }
    else if (t == MODIFIERS) {
      r = modifiers(b, 0);
    }
    else if (t == QUALIFIED_NAME) {
      r = qualifiedName(b, 0);
    }
    else if (t == REFERENCE_EXPRESSION) {
      r = referenceExpression(b, 0);
    }
    else if (t == SYMBOL) {
      r = symbol(b, 0);
    }
    else if (t == USING_DECLARATION) {
      r = usingDeclaration(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return monkeyCFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(EXPRESSION, LITERAL_EXPRESSION, REFERENCE_EXPRESSION),
  };

  /* ********************************************************** */
  // LPAREN symbol RPAREN
  public static boolean annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && symbol(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ANNOTATION, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE classBodyMembers RBRACE
  public static boolean classBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, classBodyMembers(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, CLASS_BODY, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // fieldDeclaration
  static boolean classBodyMember(PsiBuilder b, int l) {
    return fieldDeclaration(b, l + 1);
  }

  /* ********************************************************** */
  // classBodyMember*
  public static boolean classBodyMembers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBodyMembers")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<class body members>");
    int c = current_position_(b);
    while (true) {
      if (!classBodyMember(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "classBodyMembers", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, CLASS_BODY_MEMBERS, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // modifiers CLASS componentName (EXTENDS qualifiedName)? classBody
  public static boolean classDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<class declaration>");
    r = modifiers(b, l + 1);
    r = r && consumeToken(b, CLASS);
    p = r; // pin = 2
    r = r && report_error_(b, componentName(b, l + 1));
    r = p && report_error_(b, classDeclaration_3(b, l + 1)) && r;
    r = p && classBody(b, l + 1) && r;
    exit_section_(b, l, m, CLASS_DECLARATION, r, p, null);
    return r || p;
  }

  // (EXTENDS qualifiedName)?
  private static boolean classDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_3")) return false;
    classDeclaration_3_0(b, l + 1);
    return true;
  }

  // EXTENDS qualifiedName
  private static boolean classDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXTENDS);
    r = r && qualifiedName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // usingDeclaration | classDeclaration
  static boolean compilationUnit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compilationUnit")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = usingDeclaration(b, l + 1);
    if (!r) r = classDeclaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // << strictID >>
  public static boolean componentName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "componentName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<component name>");
    r = strictID(b, l + 1);
    exit_section_(b, l, m, COMPONENT_NAME, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // primary
  public static boolean expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<expression>");
    r = primary(b, l + 1);
    exit_section_(b, l, m, EXPRESSION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // modifiers VAR componentName (EQ expression)? SEMI
  public static boolean fieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<field declaration>");
    r = modifiers(b, l + 1);
    r = r && consumeToken(b, VAR);
    p = r; // pin = 2
    r = r && report_error_(b, componentName(b, l + 1));
    r = p && report_error_(b, fieldDeclaration_3(b, l + 1)) && r;
    r = p && consumeToken(b, SEMI) && r;
    exit_section_(b, l, m, FIELD_DECLARATION, r, p, null);
    return r || p;
  }

  // (EQ expression)?
  private static boolean fieldDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_3")) return false;
    fieldDeclaration_3_0(b, l + 1);
    return true;
  }

  // EQ expression
  private static boolean fieldDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQ);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "id")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, ID, r);
    return r;
  }

  /* ********************************************************** */
  // INTLITERAL
  public static boolean literalExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalExpression")) return false;
    if (!nextTokenIs(b, INTLITERAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INTLITERAL);
    exit_section_(b, m, LITERAL_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // annotation? STATIC? HIDDEN?
  public static boolean modifiers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "modifiers")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<modifiers>");
    r = modifiers_0(b, l + 1);
    r = r && modifiers_1(b, l + 1);
    r = r && modifiers_2(b, l + 1);
    exit_section_(b, l, m, MODIFIERS, r, false, null);
    return r;
  }

  // annotation?
  private static boolean modifiers_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "modifiers_0")) return false;
    annotation(b, l + 1);
    return true;
  }

  // STATIC?
  private static boolean modifiers_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "modifiers_1")) return false;
    consumeToken(b, STATIC);
    return true;
  }

  // HIDDEN?
  private static boolean modifiers_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "modifiers_2")) return false;
    consumeToken(b, HIDDEN);
    return true;
  }

  /* ********************************************************** */
  // compilationUnit*
  static boolean monkeyCFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "monkeyCFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!compilationUnit(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "monkeyCFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // literalExpression | referenceExpression
  static boolean primary(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = literalExpression(b, l + 1);
    if (!r) r = referenceExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // componentName (DOT componentName)*
  public static boolean qualifiedName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<qualified name>");
    r = componentName(b, l + 1);
    r = r && qualifiedName_1(b, l + 1);
    exit_section_(b, l, m, QUALIFIED_NAME, r, false, null);
    return r;
  }

  // (DOT componentName)*
  private static boolean qualifiedName_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!qualifiedName_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "qualifiedName_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // DOT componentName
  private static boolean qualifiedName_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && componentName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // << strictID >>
  public static boolean referenceExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<reference expression>");
    r = strictID(b, l + 1);
    exit_section_(b, l, m, REFERENCE_EXPRESSION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // COLON << strictID >>
  public static boolean symbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "symbol")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, COLON);
    p = r; // pin = 1
    r = r && strictID(b, l + 1);
    exit_section_(b, l, m, SYMBOL, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // USING qualifiedName (AS componentName)? SEMI
  public static boolean usingDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingDeclaration")) return false;
    if (!nextTokenIs(b, USING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, USING);
    r = r && qualifiedName(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, usingDeclaration_2(b, l + 1));
    r = p && consumeToken(b, SEMI) && r;
    exit_section_(b, l, m, USING_DECLARATION, r, p, null);
    return r || p;
  }

  // (AS componentName)?
  private static boolean usingDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingDeclaration_2")) return false;
    usingDeclaration_2_0(b, l + 1);
    return true;
  }

  // AS componentName
  private static boolean usingDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingDeclaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AS);
    r = r && componentName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}

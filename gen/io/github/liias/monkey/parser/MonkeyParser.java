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
    if (t == CLASS_BODY) {
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
    else if (t == REFERENCE_EXPRESSION) {
      r = referenceExpression(b, 0);
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
  // CLASS componentName classBody
  public static boolean classDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration")) return false;
    if (!nextTokenIs(b, CLASS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, CLASS);
    r = r && componentName(b, l + 1);
    p = r; // pin = 2
    r = r && classBody(b, l + 1);
    exit_section_(b, l, m, CLASS_DECLARATION, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // classDeclaration
  static boolean compilationUnit(PsiBuilder b, int l) {
    return classDeclaration(b, l + 1);
  }

  /* ********************************************************** */
  // << nonStrictID >>
  public static boolean componentName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "componentName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<component name>");
    r = nonStrictID(b, l + 1);
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
  // VAR componentName (EQ expression)? SEMI
  public static boolean fieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, VAR);
    r = r && componentName(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, fieldDeclaration_2(b, l + 1));
    r = p && consumeToken(b, SEMI) && r;
    exit_section_(b, l, m, FIELD_DECLARATION, r, p, null);
    return r || p;
  }

  // (EQ expression)?
  private static boolean fieldDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_2")) return false;
    fieldDeclaration_2_0(b, l + 1);
    return true;
  }

  // EQ expression
  private static boolean fieldDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_2_0")) return false;
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
  // << nonStrictID >>
  public static boolean referenceExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<reference expression>");
    r = nonStrictID(b, l + 1);
    exit_section_(b, l, m, REFERENCE_EXPRESSION, r, false, null);
    return r;
  }

}

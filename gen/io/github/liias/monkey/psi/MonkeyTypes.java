// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.github.liias.monkey.psi.impl.*;

public interface MonkeyTypes {

  IElementType CLASS_BODY = new MonkeyElementType("CLASS_BODY");
  IElementType CLASS_BODY_MEMBERS = new MonkeyElementType("CLASS_BODY_MEMBERS");
  IElementType CLASS_DECLARATION = new MonkeyElementType("CLASS_DECLARATION");
  IElementType COMPONENT_NAME = new MonkeyElementType("COMPONENT_NAME");
  IElementType EXPRESSION = new MonkeyElementType("EXPRESSION");
  IElementType FIELD_DECLARATION = new MonkeyElementType("FIELD_DECLARATION");
  IElementType ID = new MonkeyElementType("ID");
  IElementType LITERAL_EXPRESSION = new MonkeyElementType("LITERAL_EXPRESSION");
  IElementType REFERENCE_EXPRESSION = new MonkeyElementType("REFERENCE_EXPRESSION");

  IElementType AMP = new MonkeyTokenType("&");
  IElementType AMPAMP = new MonkeyTokenType("&&");
  IElementType AMPEQ = new MonkeyTokenType("&=");
  IElementType AND = new MonkeyTokenType("and");
  IElementType AS = new MonkeyTokenType("as");
  IElementType BANG = new MonkeyTokenType("!");
  IElementType BANGEQ = new MonkeyTokenType("!=");
  IElementType BAR = new MonkeyTokenType("|");
  IElementType BARBAR = new MonkeyTokenType("||");
  IElementType BLOCK_COMMENT = new MonkeyTokenType("BLOCK_COMMENT");
  IElementType BREAK = new MonkeyTokenType("break");
  IElementType CARET = new MonkeyTokenType("^");
  IElementType CARETEQ = new MonkeyTokenType("|=");
  IElementType CASE = new MonkeyTokenType("case");
  IElementType CATCH = new MonkeyTokenType("catch");
  IElementType CHARLITERAL = new MonkeyTokenType("CHARLITERAL");
  IElementType CLASS = new MonkeyTokenType("class");
  IElementType COLON = new MonkeyTokenType(":");
  IElementType COMMA = new MonkeyTokenType(",");
  IElementType CONST = new MonkeyTokenType("const");
  IElementType CONTINUE = new MonkeyTokenType("continue");
  IElementType DEFAULT = new MonkeyTokenType("default");
  IElementType DO = new MonkeyTokenType("do");
  IElementType DOT = new MonkeyTokenType(".");
  IElementType DOUBLELITERAL = new MonkeyTokenType("DOUBLELITERAL");
  IElementType ELSE = new MonkeyTokenType("else");
  IElementType ENUM = new MonkeyTokenType("enum");
  IElementType EQ = new MonkeyTokenType("=");
  IElementType EQEQ = new MonkeyTokenType("==");
  IElementType EQGT = new MonkeyTokenType("=>");
  IElementType EXTENDS = new MonkeyTokenType("extends");
  IElementType FALSE = new MonkeyTokenType("false");
  IElementType FINALLY = new MonkeyTokenType("finally");
  IElementType FLOATLITERAL = new MonkeyTokenType("FLOATLITERAL");
  IElementType FOR = new MonkeyTokenType("for");
  IElementType FUNCTION = new MonkeyTokenType("function");
  IElementType GT = new MonkeyTokenType(">");
  IElementType HAS = new MonkeyTokenType("has");
  IElementType HEX_LITERAL = new MonkeyTokenType("HEX_LITERAL");
  IElementType HIDDEN = new MonkeyTokenType("hidden");
  IElementType IDENTIFIER = new MonkeyTokenType("IDENTIFIER");
  IElementType IF = new MonkeyTokenType("if");
  IElementType INSTANCEOF = new MonkeyTokenType("instanceof");
  IElementType INTLITERAL = new MonkeyTokenType("INTLITERAL");
  IElementType LBRACE = new MonkeyTokenType("{");
  IElementType LBRACKET = new MonkeyTokenType("[");
  IElementType LONGLITERAL = new MonkeyTokenType("LONGLITERAL");
  IElementType LPAREN = new MonkeyTokenType("(");
  IElementType LT = new MonkeyTokenType("<");
  IElementType MODULE = new MonkeyTokenType("module");
  IElementType MULTI_LINE_COMMENT_END = new MonkeyTokenType("*/");
  IElementType MULTI_LINE_COMMENT_START = new MonkeyTokenType("/*");
  IElementType NATIVE = new MonkeyTokenType("native");
  IElementType NEW = new MonkeyTokenType("new");
  IElementType NULL = new MonkeyTokenType("null");
  IElementType OR = new MonkeyTokenType("or");
  IElementType PERCENT = new MonkeyTokenType("%");
  IElementType PERCENTEQ = new MonkeyTokenType("%=");
  IElementType PLUS = new MonkeyTokenType("+");
  IElementType PLUSEQ = new MonkeyTokenType("+=");
  IElementType PLUSPLUS = new MonkeyTokenType("++");
  IElementType QUES = new MonkeyTokenType("?");
  IElementType RBRACE = new MonkeyTokenType("}");
  IElementType RBRACKET = new MonkeyTokenType("]");
  IElementType RETURN = new MonkeyTokenType("return");
  IElementType RPAREN = new MonkeyTokenType(")");
  IElementType SEMI = new MonkeyTokenType(";");
  IElementType SINGLE_LINE_COMMENT = new MonkeyTokenType("SINGLE_LINE_COMMENT");
  IElementType SLASH = new MonkeyTokenType("/");
  IElementType SLASHEQ = new MonkeyTokenType("/=");
  IElementType STAR = new MonkeyTokenType("*");
  IElementType STAREQ = new MonkeyTokenType("*=");
  IElementType STATIC = new MonkeyTokenType("static");
  IElementType STRING = new MonkeyTokenType("string");
  IElementType STRING_A = new MonkeyTokenType("\"");
  IElementType STRING_B = new MonkeyTokenType("'");
  IElementType SUB = new MonkeyTokenType("-");
  IElementType SUBEQ = new MonkeyTokenType("-=");
  IElementType SUBSUB = new MonkeyTokenType("--");
  IElementType SWITCH = new MonkeyTokenType("switch");
  IElementType THROW = new MonkeyTokenType("throw");
  IElementType TILDE = new MonkeyTokenType("~");
  IElementType TRUE = new MonkeyTokenType("true");
  IElementType TRY = new MonkeyTokenType("try");
  IElementType USING = new MonkeyTokenType("using");
  IElementType VAR = new MonkeyTokenType("var");
  IElementType WHILE = new MonkeyTokenType("while");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == CLASS_BODY) {
        return new MonkeyClassBodyImpl(node);
      }
      else if (type == CLASS_BODY_MEMBERS) {
        return new MonkeyClassBodyMembersImpl(node);
      }
      else if (type == CLASS_DECLARATION) {
        return new MonkeyClassDeclarationImpl(node);
      }
      else if (type == COMPONENT_NAME) {
        return new MonkeyComponentNameImpl(node);
      }
      else if (type == EXPRESSION) {
        return new MonkeyExpressionImpl(node);
      }
      else if (type == FIELD_DECLARATION) {
        return new MonkeyFieldDeclarationImpl(node);
      }
      else if (type == ID) {
        return new MonkeyIdImpl(node);
      }
      else if (type == LITERAL_EXPRESSION) {
        return new MonkeyLiteralExpressionImpl(node);
      }
      else if (type == REFERENCE_EXPRESSION) {
        return new MonkeyReferenceExpressionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

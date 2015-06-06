// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import ee.edio.garmin.psi.impl.*;

public interface MonkeyCTypes {

  IElementType ARGUMENT_LIST = new MonkeyCElementType("ARGUMENT_LIST");
  IElementType ARRAY = new MonkeyCElementType("ARRAY");
  IElementType BOOLEAN_EXPRESSION = new MonkeyCElementType("BOOLEAN_EXPRESSION");
  IElementType BOOLEAN_OPERATOR = new MonkeyCElementType("BOOLEAN_OPERATOR");
  IElementType CALL_ARGS = new MonkeyCElementType("CALL_ARGS");
  IElementType CALL_EXPRESSION = new MonkeyCElementType("CALL_EXPRESSION");
  IElementType CLASS_BODY = new MonkeyCElementType("CLASS_BODY");
  IElementType CLASS_DEFINITION = new MonkeyCElementType("CLASS_DEFINITION");
  IElementType CLASS_MEMBERS = new MonkeyCElementType("CLASS_MEMBERS");
  IElementType CLASS_NAME = new MonkeyCElementType("CLASS_NAME");
  IElementType CLASS_TYPE = new MonkeyCElementType("CLASS_TYPE");
  IElementType EQUALITY_OPERATOR = new MonkeyCElementType("EQUALITY_OPERATOR");
  IElementType EXP = new MonkeyCElementType("EXP");
  IElementType EXPRESSION_B = new MonkeyCElementType("EXPRESSION_B");
  IElementType FUNCTION_ARGS = new MonkeyCElementType("FUNCTION_ARGS");
  IElementType FUNCTION_BODY = new MonkeyCElementType("FUNCTION_BODY");
  IElementType FUNCTION_DEFINITION = new MonkeyCElementType("FUNCTION_DEFINITION");
  IElementType FUNCTION_MEMBERS = new MonkeyCElementType("FUNCTION_MEMBERS");
  IElementType FUNCTION_NAME = new MonkeyCElementType("FUNCTION_NAME");
  IElementType ID = new MonkeyCElementType("ID");
  IElementType IF_STATEMENT = new MonkeyCElementType("IF_STATEMENT");
  IElementType LITERAL = new MonkeyCElementType("LITERAL");
  IElementType NEW_DEFINITION = new MonkeyCElementType("NEW_DEFINITION");
  IElementType OPERATOR = new MonkeyCElementType("OPERATOR");
  IElementType OP_EXPRESSION = new MonkeyCElementType("OP_EXPRESSION");
  IElementType PACKAGE_NAME = new MonkeyCElementType("PACKAGE_NAME");
  IElementType POSSIBLE_ARGS = new MonkeyCElementType("POSSIBLE_ARGS");
  IElementType RELATIONAL_OPERATOR = new MonkeyCElementType("RELATIONAL_OPERATOR");
  IElementType RETURN_STATEMENT = new MonkeyCElementType("RETURN_STATEMENT");
  IElementType USING_STATEMENT = new MonkeyCElementType("USING_STATEMENT");
  IElementType VARIABLE_DEFINITION = new MonkeyCElementType("VARIABLE_DEFINITION");
  IElementType VARIABLE_NAME = new MonkeyCElementType("VARIABLE_NAME");
  IElementType WHATEVER = new MonkeyCElementType("WHATEVER");
  IElementType WITH_EXTENDS = new MonkeyCElementType("WITH_EXTENDS");

  IElementType ALPHANUM = new MonkeyCTokenType("alphanum");
  IElementType AND = new MonkeyCTokenType("and");
  IElementType AS = new MonkeyCTokenType("as");
  IElementType BLOCK_COMMENT = new MonkeyCTokenType("BLOCK_COMMENT");
  IElementType BLOCK_END = new MonkeyCTokenType("}");
  IElementType BLOCK_START = new MonkeyCTokenType("{");
  IElementType BREAK = new MonkeyCTokenType("break");
  IElementType CASE = new MonkeyCTokenType("case");
  IElementType CATCH = new MonkeyCTokenType("catch");
  IElementType CLASS = new MonkeyCTokenType("class");
  IElementType COMMA = new MonkeyCTokenType(",");
  IElementType CONST = new MonkeyCTokenType("const");
  IElementType CONTINUE = new MonkeyCTokenType("continue");
  IElementType DEFAULT = new MonkeyCTokenType("default");
  IElementType DO = new MonkeyCTokenType("do");
  IElementType ELSE = new MonkeyCTokenType("else");
  IElementType ENUM = new MonkeyCTokenType("enum");
  IElementType EXTENDS = new MonkeyCTokenType("extends");
  IElementType FALSE = new MonkeyCTokenType("false");
  IElementType FINALLY = new MonkeyCTokenType("finally");
  IElementType FOR = new MonkeyCTokenType("for");
  IElementType FUNCTION = new MonkeyCTokenType("function");
  IElementType HAS = new MonkeyCTokenType("has");
  IElementType HIDDEN = new MonkeyCTokenType("hidden");
  IElementType IF = new MonkeyCTokenType("if");
  IElementType INSTANCEOF = new MonkeyCTokenType("instanceof");
  IElementType LPAREN = new MonkeyCTokenType("(");
  IElementType MODULE = new MonkeyCTokenType("module");
  IElementType MULTI_LINE_COMMENT_END = new MonkeyCTokenType("*/");
  IElementType MULTI_LINE_COMMENT_START = new MonkeyCTokenType("/*");
  IElementType NATIVE = new MonkeyCTokenType("native");
  IElementType NEW = new MonkeyCTokenType("new");
  IElementType NULL = new MonkeyCTokenType("null");
  IElementType NUMBER = new MonkeyCTokenType("number");
  IElementType OP_DIVIDE = new MonkeyCTokenType("/");
  IElementType OP_MINUS = new MonkeyCTokenType("-");
  IElementType OP_MULTIPLY = new MonkeyCTokenType("*");
  IElementType OP_PLUS = new MonkeyCTokenType("+");
  IElementType OR = new MonkeyCTokenType("or");
  IElementType RETURN = new MonkeyCTokenType("return");
  IElementType RPAREN = new MonkeyCTokenType(")");
  IElementType SINGLE_LINE_COMMENT = new MonkeyCTokenType("SINGLE_LINE_COMMENT");
  IElementType STATEMENT_END = new MonkeyCTokenType(";");
  IElementType STATIC = new MonkeyCTokenType("static");
  IElementType STRING = new MonkeyCTokenType("string");
  IElementType STRING_A = new MonkeyCTokenType("\"");
  IElementType STRING_B = new MonkeyCTokenType("'");
  IElementType SWITCH = new MonkeyCTokenType("switch");
  IElementType SYMBOL = new MonkeyCTokenType("symbol");
  IElementType THROW = new MonkeyCTokenType("throw");
  IElementType TRUE = new MonkeyCTokenType("true");
  IElementType TRY = new MonkeyCTokenType("try");
  IElementType USING = new MonkeyCTokenType("using");
  IElementType VAR = new MonkeyCTokenType("var");
  IElementType WHILE = new MonkeyCTokenType("while");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ARGUMENT_LIST) {
        return new MonkeyCArgumentListImpl(node);
      }
      else if (type == ARRAY) {
        return new MonkeyCArrayImpl(node);
      }
      else if (type == BOOLEAN_EXPRESSION) {
        return new MonkeyCBooleanExpressionImpl(node);
      }
      else if (type == BOOLEAN_OPERATOR) {
        return new MonkeyCBooleanOperatorImpl(node);
      }
      else if (type == CALL_ARGS) {
        return new MonkeyCCallArgsImpl(node);
      }
      else if (type == CALL_EXPRESSION) {
        return new MonkeyCCallExpressionImpl(node);
      }
      else if (type == CLASS_BODY) {
        return new MonkeyCClassBodyImpl(node);
      }
      else if (type == CLASS_DEFINITION) {
        return new MonkeyCClassDefinitionImpl(node);
      }
      else if (type == CLASS_MEMBERS) {
        return new MonkeyCClassMembersImpl(node);
      }
      else if (type == CLASS_NAME) {
        return new MonkeyCClassNameImpl(node);
      }
      else if (type == CLASS_TYPE) {
        return new MonkeyCClassTypeImpl(node);
      }
      else if (type == EQUALITY_OPERATOR) {
        return new MonkeyCEqualityOperatorImpl(node);
      }
      else if (type == EXP) {
        return new MonkeyCExpImpl(node);
      }
      else if (type == EXPRESSION_B) {
        return new MonkeyCExpressionBImpl(node);
      }
      else if (type == FUNCTION_ARGS) {
        return new MonkeyCFunctionArgsImpl(node);
      }
      else if (type == FUNCTION_BODY) {
        return new MonkeyCFunctionBodyImpl(node);
      }
      else if (type == FUNCTION_DEFINITION) {
        return new MonkeyCFunctionDefinitionImpl(node);
      }
      else if (type == FUNCTION_MEMBERS) {
        return new MonkeyCFunctionMembersImpl(node);
      }
      else if (type == FUNCTION_NAME) {
        return new MonkeyCFunctionNameImpl(node);
      }
      else if (type == ID) {
        return new MonkeyCIdImpl(node);
      }
      else if (type == IF_STATEMENT) {
        return new MonkeyCIfStatementImpl(node);
      }
      else if (type == LITERAL) {
        return new MonkeyCLiteralImpl(node);
      }
      else if (type == NEW_DEFINITION) {
        return new MonkeyCNewDefinitionImpl(node);
      }
      else if (type == OPERATOR) {
        return new MonkeyCOperatorImpl(node);
      }
      else if (type == OP_EXPRESSION) {
        return new MonkeyCOpExpressionImpl(node);
      }
      else if (type == PACKAGE_NAME) {
        return new MonkeyCPackageNameImpl(node);
      }
      else if (type == POSSIBLE_ARGS) {
        return new MonkeyCPossibleArgsImpl(node);
      }
      else if (type == RELATIONAL_OPERATOR) {
        return new MonkeyCRelationalOperatorImpl(node);
      }
      else if (type == RETURN_STATEMENT) {
        return new MonkeyCReturnStatementImpl(node);
      }
      else if (type == USING_STATEMENT) {
        return new MonkeyCUsingStatementImpl(node);
      }
      else if (type == VARIABLE_DEFINITION) {
        return new MonkeyCVariableDefinitionImpl(node);
      }
      else if (type == VARIABLE_NAME) {
        return new MonkeyCVariableNameImpl(node);
      }
      else if (type == WHATEVER) {
        return new MonkeyCWhateverImpl(node);
      }
      else if (type == WITH_EXTENDS) {
        return new MonkeyCWithExtendsImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static ee.edio.garmin.psi.MonkeyCTypes.*;
import static ee.edio.garmin.parser.MonkeyCParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class MonkeyCParser implements PsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ARGUMENT_LIST) {
      r = argumentList(b, 0);
    }
    else if (t == ARRAY) {
      r = array(b, 0);
    }
    else if (t == BOOLEAN_EXPRESSION) {
      r = booleanExpression(b, 0);
    }
    else if (t == BOOLEAN_OPERATOR) {
      r = booleanOperator(b, 0);
    }
    else if (t == CALL_ARGS) {
      r = callArgs(b, 0);
    }
    else if (t == CALL_EXPRESSION) {
      r = callExpression(b, 0);
    }
    else if (t == CLASS_BODY) {
      r = classBody(b, 0);
    }
    else if (t == CLASS_DEFINITION) {
      r = classDefinition(b, 0);
    }
    else if (t == CLASS_MEMBERS) {
      r = classMembers(b, 0);
    }
    else if (t == CLASS_NAME) {
      r = className(b, 0);
    }
    else if (t == CLASS_TYPE) {
      r = classType(b, 0);
    }
    else if (t == EQUALITY_OPERATOR) {
      r = equalityOperator(b, 0);
    }
    else if (t == EXP) {
      r = exp(b, 0);
    }
    else if (t == EXPRESSION_B) {
      r = expressionB(b, 0);
    }
    else if (t == FUNCTION_ARGS) {
      r = functionArgs(b, 0);
    }
    else if (t == FUNCTION_BODY) {
      r = functionBody(b, 0);
    }
    else if (t == FUNCTION_DEFINITION) {
      r = functionDefinition(b, 0);
    }
    else if (t == FUNCTION_MEMBERS) {
      r = functionMembers(b, 0);
    }
    else if (t == FUNCTION_NAME) {
      r = functionName(b, 0);
    }
    else if (t == ID) {
      r = id(b, 0);
    }
    else if (t == IF_STATEMENT) {
      r = ifStatement(b, 0);
    }
    else if (t == LITERAL) {
      r = literal(b, 0);
    }
    else if (t == NEW_DEFINITION) {
      r = newDefinition(b, 0);
    }
    else if (t == OP_EXPRESSION) {
      r = opExpression(b, 0);
    }
    else if (t == OPERATOR) {
      r = operator(b, 0);
    }
    else if (t == PACKAGE_NAME) {
      r = packageName(b, 0);
    }
    else if (t == POSSIBLE_ARGS) {
      r = possibleArgs(b, 0);
    }
    else if (t == RELATIONAL_OPERATOR) {
      r = relationalOperator(b, 0);
    }
    else if (t == RETURN_STATEMENT) {
      r = returnStatement(b, 0);
    }
    else if (t == USING_STATEMENT) {
      r = usingStatement(b, 0);
    }
    else if (t == VARIABLE_DEFINITION) {
      r = variableDefinition(b, 0);
    }
    else if (t == VARIABLE_NAME) {
      r = variableName(b, 0);
    }
    else if (t == WHATEVER) {
      r = whatever(b, 0);
    }
    else if (t == WITH_EXTENDS) {
      r = withExtends(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return monkeyCFile(b, l + 1);
  }

  /* ********************************************************** */
  // variableName (COMMA variableName)*
  public static boolean argumentList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentList")) return false;
    if (!nextTokenIs(b, ALPHANUM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variableName(b, l + 1);
    r = r && argumentList_1(b, l + 1);
    exit_section_(b, m, ARGUMENT_LIST, r);
    return r;
  }

  // (COMMA variableName)*
  private static boolean argumentList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentList_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!argumentList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "argumentList_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA variableName
  private static boolean argumentList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && variableName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '[' exp (COMMA exp)* ']'
  public static boolean array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<array>");
    r = consumeToken(b, "[");
    r = r && exp(b, l + 1);
    r = r && array_2(b, l + 1);
    r = r && consumeToken(b, "]");
    exit_section_(b, l, m, ARRAY, r, false, null);
    return r;
  }

  // (COMMA exp)*
  private static boolean array_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!array_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "array_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA exp
  private static boolean array_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && exp(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // literal booleanOperator literal
  public static boolean booleanExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<boolean expression>");
    r = literal(b, l + 1);
    r = r && booleanOperator(b, l + 1);
    r = r && literal(b, l + 1);
    exit_section_(b, l, m, BOOLEAN_EXPRESSION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // relationalOperator | equalityOperator
  public static boolean booleanOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanOperator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<boolean operator>");
    r = relationalOperator(b, l + 1);
    if (!r) r = equalityOperator(b, l + 1);
    exit_section_(b, l, m, BOOLEAN_OPERATOR, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // possibleArgs (COMMA possibleArgs)?
  public static boolean callArgs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callArgs")) return false;
    if (!nextTokenIs(b, "<call args>", ALPHANUM, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<call args>");
    r = possibleArgs(b, l + 1);
    r = r && callArgs_1(b, l + 1);
    exit_section_(b, l, m, CALL_ARGS, r, false, null);
    return r;
  }

  // (COMMA possibleArgs)?
  private static boolean callArgs_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callArgs_1")) return false;
    callArgs_1_0(b, l + 1);
    return true;
  }

  // COMMA possibleArgs
  private static boolean callArgs_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callArgs_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && possibleArgs(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // classType LPAREN callArgs? RPAREN STATEMENT_END
  public static boolean callExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callExpression")) return false;
    if (!nextTokenIs(b, ALPHANUM)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = classType(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    p = r; // pin = 2
    r = r && report_error_(b, callExpression_2(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, RPAREN, STATEMENT_END)) && r;
    exit_section_(b, l, m, CALL_EXPRESSION, r, p, null);
    return r || p;
  }

  // callArgs?
  private static boolean callExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callExpression_2")) return false;
    callArgs(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // BLOCK_START classMembers* BLOCK_END
  public static boolean classBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody")) return false;
    if (!nextTokenIs(b, BLOCK_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, BLOCK_START);
    p = r; // pin = 1
    r = r && report_error_(b, classBody_1(b, l + 1));
    r = p && consumeToken(b, BLOCK_END) && r;
    exit_section_(b, l, m, CLASS_BODY, r, p, null);
    return r || p;
  }

  // classMembers*
  private static boolean classBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!classMembers(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "classBody_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // CLASS className withExtends? classBody
  public static boolean classDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDefinition")) return false;
    if (!nextTokenIs(b, CLASS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CLASS);
    r = r && className(b, l + 1);
    r = r && classDefinition_2(b, l + 1);
    r = r && classBody(b, l + 1);
    exit_section_(b, m, CLASS_DEFINITION, r);
    return r;
  }

  // withExtends?
  private static boolean classDefinition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDefinition_2")) return false;
    withExtends(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // variableDefinition | functionDefinition
  public static boolean classMembers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classMembers")) return false;
    if (!nextTokenIs(b, "<class members>", FUNCTION, VAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<class members>");
    r = variableDefinition(b, l + 1);
    if (!r) r = functionDefinition(b, l + 1);
    exit_section_(b, l, m, CLASS_MEMBERS, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // id
  public static boolean className(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "className")) return false;
    if (!nextTokenIs(b, ALPHANUM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = id(b, l + 1);
    exit_section_(b, m, CLASS_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // id '.' id | id
  public static boolean classType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classType")) return false;
    if (!nextTokenIs(b, ALPHANUM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = classType_0(b, l + 1);
    if (!r) r = id(b, l + 1);
    exit_section_(b, m, CLASS_TYPE, r);
    return r;
  }

  // id '.' id
  private static boolean classType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classType_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = id(b, l + 1);
    r = r && consumeToken(b, ".");
    r = r && id(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '==' | '!='
  public static boolean equalityOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityOperator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<equality operator>");
    r = consumeToken(b, "==");
    if (!r) r = consumeToken(b, "!=");
    exit_section_(b, l, m, EQUALITY_OPERATOR, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // variableName | newDefinition
  public static boolean exp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exp")) return false;
    if (!nextTokenIs(b, "<exp>", NEW, ALPHANUM)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<exp>");
    r = variableName(b, l + 1);
    if (!r) r = newDefinition(b, l + 1);
    exit_section_(b, l, m, EXP, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // newDefinition | opExpression
  public static boolean expressionB(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionB")) return false;
    if (!nextTokenIs(b, "<expression b>", NEW, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<expression b>");
    r = newDefinition(b, l + 1);
    if (!r) r = opExpression(b, l + 1);
    exit_section_(b, l, m, EXPRESSION_B, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // variableName COMMA variableName | variableName
  public static boolean functionArgs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionArgs")) return false;
    if (!nextTokenIs(b, ALPHANUM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = functionArgs_0(b, l + 1);
    if (!r) r = variableName(b, l + 1);
    exit_section_(b, m, FUNCTION_ARGS, r);
    return r;
  }

  // variableName COMMA variableName
  private static boolean functionArgs_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionArgs_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variableName(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && variableName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BLOCK_START functionMembers* BLOCK_END
  public static boolean functionBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionBody")) return false;
    if (!nextTokenIs(b, BLOCK_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, BLOCK_START);
    p = r; // pin = 1
    r = r && report_error_(b, functionBody_1(b, l + 1));
    r = p && consumeToken(b, BLOCK_END) && r;
    exit_section_(b, l, m, FUNCTION_BODY, r, p, null);
    return r || p;
  }

  // functionMembers*
  private static boolean functionBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionBody_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!functionMembers(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "functionBody_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // FUNCTION functionName LPAREN functionArgs? RPAREN functionBody
  public static boolean functionDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDefinition")) return false;
    if (!nextTokenIs(b, FUNCTION)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, FUNCTION);
    p = r; // pin = 1
    r = r && report_error_(b, functionName(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LPAREN)) && r;
    r = p && report_error_(b, functionDefinition_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    r = p && functionBody(b, l + 1) && r;
    exit_section_(b, l, m, FUNCTION_DEFINITION, r, p, null);
    return r || p;
  }

  // functionArgs?
  private static boolean functionDefinition_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDefinition_3")) return false;
    functionArgs(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // variableDefinition | callExpression | ifStatement |returnStatement
  public static boolean functionMembers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionMembers")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<function members>");
    r = variableDefinition(b, l + 1);
    if (!r) r = callExpression(b, l + 1);
    if (!r) r = ifStatement(b, l + 1);
    if (!r) r = returnStatement(b, l + 1);
    exit_section_(b, l, m, FUNCTION_MEMBERS, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // id
  public static boolean functionName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionName")) return false;
    if (!nextTokenIs(b, ALPHANUM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = id(b, l + 1);
    exit_section_(b, m, FUNCTION_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // alphanum
  public static boolean id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "id")) return false;
    if (!nextTokenIs(b, ALPHANUM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ALPHANUM);
    exit_section_(b, m, ID, r);
    return r;
  }

  /* ********************************************************** */
  // IF LPAREN booleanExpression RPAREN
  public static boolean ifStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifStatement")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IF, LPAREN);
    r = r && booleanExpression(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, IF_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // string | number | id
  public static boolean literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<literal>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = id(b, l + 1);
    exit_section_(b, l, m, LITERAL, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // topLevelDefinition*
  static boolean monkeyCFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "monkeyCFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!topLevelDefinition(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "monkeyCFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // NEW className LPAREN argumentList? RPAREN
  public static boolean newDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newDefinition")) return false;
    if (!nextTokenIs(b, NEW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, NEW);
    p = r; // pin = 1
    r = r && report_error_(b, className(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LPAREN)) && r;
    r = p && report_error_(b, newDefinition_3(b, l + 1)) && r;
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, NEW_DEFINITION, r, p, null);
    return r || p;
  }

  // argumentList?
  private static boolean newDefinition_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newDefinition_3")) return false;
    argumentList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // number operator number
  public static boolean opExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opExpression")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    r = r && operator(b, l + 1);
    r = r && consumeToken(b, NUMBER);
    exit_section_(b, m, OP_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // OP_PLUS | OP_MINUS | OP_MULTIPLY | OP_DIVIDE
  public static boolean operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<operator>");
    r = consumeToken(b, OP_PLUS);
    if (!r) r = consumeToken(b, OP_MINUS);
    if (!r) r = consumeToken(b, OP_MULTIPLY);
    if (!r) r = consumeToken(b, OP_DIVIDE);
    exit_section_(b, l, m, OPERATOR, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // id '.' id | id
  public static boolean packageName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "packageName")) return false;
    if (!nextTokenIs(b, ALPHANUM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = packageName_0(b, l + 1);
    if (!r) r = id(b, l + 1);
    exit_section_(b, m, PACKAGE_NAME, r);
    return r;
  }

  // id '.' id
  private static boolean packageName_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "packageName_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = id(b, l + 1);
    r = r && consumeToken(b, ".");
    r = r && id(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // alphanum | string
  public static boolean possibleArgs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "possibleArgs")) return false;
    if (!nextTokenIs(b, "<possible args>", ALPHANUM, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<possible args>");
    r = consumeToken(b, ALPHANUM);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, l, m, POSSIBLE_ARGS, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '>=' | '>' | '<=' | '<'
  public static boolean relationalOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationalOperator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<relational operator>");
    r = consumeToken(b, ">=");
    if (!r) r = consumeToken(b, ">");
    if (!r) r = consumeToken(b, "<=");
    if (!r) r = consumeToken(b, "<");
    exit_section_(b, l, m, RELATIONAL_OPERATOR, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // RETURN whatever STATEMENT_END
  public static boolean returnStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnStatement")) return false;
    if (!nextTokenIs(b, RETURN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, RETURN);
    p = r; // pin = 1
    r = r && report_error_(b, whatever(b, l + 1));
    r = p && consumeToken(b, STATEMENT_END) && r;
    exit_section_(b, l, m, RETURN_STATEMENT, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // usingStatement
  //                              | classDefinition
  static boolean topLevelDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "topLevelDefinition")) return false;
    if (!nextTokenIs(b, "", CLASS, USING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = usingStatement(b, l + 1);
    if (!r) r = classDefinition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // USING packageName (AS id)? STATEMENT_END
  public static boolean usingStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingStatement")) return false;
    if (!nextTokenIs(b, USING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, USING);
    p = r; // pin = 1
    r = r && report_error_(b, packageName(b, l + 1));
    r = p && report_error_(b, usingStatement_2(b, l + 1)) && r;
    r = p && consumeToken(b, STATEMENT_END) && r;
    exit_section_(b, l, m, USING_STATEMENT, r, p, null);
    return r || p;
  }

  // (AS id)?
  private static boolean usingStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingStatement_2")) return false;
    usingStatement_2_0(b, l + 1);
    return true;
  }

  // AS id
  private static boolean usingStatement_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingStatement_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AS);
    r = r && id(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // VAR variableName ('=' expressionB)? STATEMENT_END
  public static boolean variableDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDefinition")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, VAR);
    p = r; // pin = 1
    r = r && report_error_(b, variableName(b, l + 1));
    r = p && report_error_(b, variableDefinition_2(b, l + 1)) && r;
    r = p && consumeToken(b, STATEMENT_END) && r;
    exit_section_(b, l, m, VARIABLE_DEFINITION, r, p, null);
    return r || p;
  }

  // ('=' expressionB)?
  private static boolean variableDefinition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDefinition_2")) return false;
    variableDefinition_2_0(b, l + 1);
    return true;
  }

  // '=' expressionB
  private static boolean variableDefinition_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDefinition_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "=");
    r = r && expressionB(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // id
  public static boolean variableName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableName")) return false;
    if (!nextTokenIs(b, ALPHANUM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = id(b, l + 1);
    exit_section_(b, m, VARIABLE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // array
  public static boolean whatever(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "whatever")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<whatever>");
    r = array(b, l + 1);
    exit_section_(b, l, m, WHATEVER, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // EXTENDS classType
  public static boolean withExtends(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "withExtends")) return false;
    if (!nextTokenIs(b, EXTENDS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXTENDS);
    r = r && classType(b, l + 1);
    exit_section_(b, m, WITH_EXTENDS, r);
    return r;
  }

}

// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.github.liias.monkey.lang.psi.MonkeyTypes.*;
import static io.github.liias.monkey.lang.parser.MonkeyParserUtil.*;
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
    if (t == ADDITIVE_EXPRESSION) {
      r = additiveExpression(b, 0);
    }
    else if (t == AND_EXPRESSION) {
      r = andExpression(b, 0);
    }
    else if (t == ANNOTATION) {
      r = annotation(b, 0);
    }
    else if (t == ARGUMENTS) {
      r = arguments(b, 0);
    }
    else if (t == ARRAY_CREATOR) {
      r = arrayCreator(b, 0);
    }
    else if (t == ARRAY_INITIALIZER) {
      r = arrayInitializer(b, 0);
    }
    else if (t == ASSIGNMENT_OPERATOR) {
      r = assignmentOperator(b, 0);
    }
    else if (t == BITWISE_EXPRESSION) {
      r = bitwiseExpression(b, 0);
    }
    else if (t == BITWISE_OPERATOR) {
      r = bitwiseOperator(b, 0);
    }
    else if (t == BLING_EXPRESSION) {
      r = blingExpression(b, 0);
    }
    else if (t == BLOCK) {
      r = block(b, 0);
    }
    else if (t == BLOCK_STATEMENT) {
      r = blockStatement(b, 0);
    }
    else if (t == CATCH_CLAUSE) {
      r = catchClause(b, 0);
    }
    else if (t == CATCH_PARAMETER) {
      r = catchParameter(b, 0);
    }
    else if (t == CATCHES) {
      r = catches(b, 0);
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
    else if (t == CONDITIONAL_AND_EXPRESSION) {
      r = conditionalAndExpression(b, 0);
    }
    else if (t == CONDITIONAL_EXPRESSION) {
      r = conditionalExpression(b, 0);
    }
    else if (t == CONDITIONAL_OR_EXPRESSION) {
      r = conditionalOrExpression(b, 0);
    }
    else if (t == CONST_DECLARATION) {
      r = constDeclaration(b, 0);
    }
    else if (t == CREATOR) {
      r = creator(b, 0);
    }
    else if (t == DICTIONARY_CREATOR) {
      r = dictionaryCreator(b, 0);
    }
    else if (t == ENUM_CONSTANT) {
      r = enumConstant(b, 0);
    }
    else if (t == ENUM_DECLARATION) {
      r = enumDeclaration(b, 0);
    }
    else if (t == EQUALITY_EXPRESSION) {
      r = equalityExpression(b, 0);
    }
    else if (t == EXCLUSIVE_OR_EXPRESSION) {
      r = exclusiveOrExpression(b, 0);
    }
    else if (t == EXPRESSION) {
      r = expression(b, 0);
    }
    else if (t == EXPRESSION_LIST) {
      r = expressionList(b, 0);
    }
    else if (t == FIELD_DECLARATION) {
      r = fieldDeclaration(b, 0);
    }
    else if (t == FIELD_DECLARATION_LIST) {
      r = fieldDeclarationList(b, 0);
    }
    else if (t == FOR_INIT) {
      r = forInit(b, 0);
    }
    else if (t == FOR_STATEMENT) {
      r = forStatement(b, 0);
    }
    else if (t == FORMAL_PARAMETER_DECLARATIONS) {
      r = formalParameterDeclarations(b, 0);
    }
    else if (t == FUNCTION_DECLARATION) {
      r = functionDeclaration(b, 0);
    }
    else if (t == HAS_EXPRESSION) {
      r = hasExpression(b, 0);
    }
    else if (t == ID) {
      r = id(b, 0);
    }
    else if (t == IDENTIFIER_SUFFIX) {
      r = identifierSuffix(b, 0);
    }
    else if (t == INCLUSIVE_OR_EXPRESSION) {
      r = inclusiveOrExpression(b, 0);
    }
    else if (t == INSTANCE_OF_EXPRESSION) {
      r = instanceOfExpression(b, 0);
    }
    else if (t == KEY_VALUE_INITIALIZER) {
      r = keyValueInitializer(b, 0);
    }
    else if (t == LITERAL) {
      r = literal(b, 0);
    }
    else if (t == MODIFIERS) {
      r = modifiers(b, 0);
    }
    else if (t == MODULE_BODY) {
      r = moduleBody(b, 0);
    }
    else if (t == MODULE_BODY_MEMBERS) {
      r = moduleBodyMembers(b, 0);
    }
    else if (t == MODULE_DECLARATION) {
      r = moduleDeclaration(b, 0);
    }
    else if (t == MULTIPLICATIVE_EXPRESSION) {
      r = multiplicativeExpression(b, 0);
    }
    else if (t == OBJECT_CREATOR) {
      r = objectCreator(b, 0);
    }
    else if (t == PAR_EXPRESSION) {
      r = parExpression(b, 0);
    }
    else if (t == QUALIFIED_NAME) {
      r = qualifiedName(b, 0);
    }
    else if (t == REFERENCE_EXPRESSION) {
      r = referenceExpression(b, 0);
    }
    else if (t == RELATIONAL_EXPRESSION) {
      r = relationalExpression(b, 0);
    }
    else if (t == RELATIONAL_OP) {
      r = relationalOp(b, 0);
    }
    else if (t == SELECTOR) {
      r = selector(b, 0);
    }
    else if (t == SHIFT_EXPRESSION) {
      r = shiftExpression(b, 0);
    }
    else if (t == SHIFT_OP) {
      r = shiftOp(b, 0);
    }
    else if (t == STATEMENT) {
      r = statement(b, 0);
    }
    else if (t == SWITCH_BLOCK_STATEMENT_GROUP) {
      r = switchBlockStatementGroup(b, 0);
    }
    else if (t == SWITCH_BLOCK_STATEMENT_GROUPS) {
      r = switchBlockStatementGroups(b, 0);
    }
    else if (t == SWITCH_LABEL) {
      r = switchLabel(b, 0);
    }
    else if (t == SYMBOL) {
      r = symbol(b, 0);
    }
    else if (t == THIS_EXPRESSION) {
      r = thisExpression(b, 0);
    }
    else if (t == TRY_STATEMENT) {
      r = tryStatement(b, 0);
    }
    else if (t == UNARY_EXPRESSION) {
      r = unaryExpression(b, 0);
    }
    else if (t == USING_DECLARATION) {
      r = usingDeclaration(b, 0);
    }
    else if (t == VARIABLE_DECLARATION) {
      r = variableDeclaration(b, 0);
    }
    else if (t == VARIABLE_DECLARATION_LIST) {
      r = variableDeclarationList(b, 0);
    }
    else if (t == VARIABLE_INITIALIZER) {
      r = variableInitializer(b, 0);
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
    create_token_set_(ADDITIVE_EXPRESSION, AND_EXPRESSION, BITWISE_EXPRESSION, BLING_EXPRESSION,
      CONDITIONAL_AND_EXPRESSION, CONDITIONAL_EXPRESSION, CONDITIONAL_OR_EXPRESSION, EQUALITY_EXPRESSION,
      EXCLUSIVE_OR_EXPRESSION, EXPRESSION, HAS_EXPRESSION, INCLUSIVE_OR_EXPRESSION,
      INSTANCE_OF_EXPRESSION, MULTIPLICATIVE_EXPRESSION, PAR_EXPRESSION, REFERENCE_EXPRESSION,
      RELATIONAL_EXPRESSION, SHIFT_EXPRESSION, THIS_EXPRESSION, UNARY_EXPRESSION),
  };

  /* ********************************************************** */
  // multiplicativeExpression ((PLUS | SUB) multiplicativeExpression)*
  public static boolean additiveExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "additiveExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ADDITIVE_EXPRESSION, "<additive expression>");
    r = multiplicativeExpression(b, l + 1);
    r = r && additiveExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ((PLUS | SUB) multiplicativeExpression)*
  private static boolean additiveExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "additiveExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!additiveExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "additiveExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (PLUS | SUB) multiplicativeExpression
  private static boolean additiveExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "additiveExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = additiveExpression_1_0_0(b, l + 1);
    r = r && multiplicativeExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS | SUB
  private static boolean additiveExpression_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "additiveExpression_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, SUB);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // equalityExpression (AMP equalityExpression)*
  public static boolean andExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "andExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, AND_EXPRESSION, "<and expression>");
    r = equalityExpression(b, l + 1);
    r = r && andExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (AMP equalityExpression)*
  private static boolean andExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "andExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!andExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "andExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // AMP equalityExpression
  private static boolean andExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "andExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AMP);
    r = r && equalityExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN symbol RPAREN
  public static boolean annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION, null);
    r = consumeToken(b, LPAREN);
    r = r && symbol(b, l + 1);
    p = r; // pin = 2
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // LPAREN argumentsList? RPAREN
  public static boolean arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENTS, null);
    r = consumeToken(b, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, arguments_1(b, l + 1));
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // argumentsList?
  private static boolean arguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments_1")) return false;
    argumentsList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // expression (COMMA expression)*
  static boolean argumentsList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentsList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = expression(b, l + 1);
    r = r && argumentsList_1(b, l + 1);
    exit_section_(b, l, m, r, false, argumentsList_recover_parser_);
    return r;
  }

  // (COMMA expression)*
  private static boolean argumentsList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentsList_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!argumentsList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "argumentsList_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA expression
  private static boolean argumentsList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentsList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !(RPAREN)
  static boolean argumentsList_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentsList_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NEW LBRACKET expression RBRACKET // new [expression evaluating to integer]
  //                | LBRACKET (expression (COMMA expression)* )? RBRACKET // [expression1, expression2, ...]
  //                | dictionaryCreator
  public static boolean arrayCreator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_CREATOR, "<array creator>");
    r = arrayCreator_0(b, l + 1);
    if (!r) r = arrayCreator_1(b, l + 1);
    if (!r) r = dictionaryCreator(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // NEW LBRACKET expression RBRACKET
  private static boolean arrayCreator_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, NEW, LBRACKET);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // LBRACKET (expression (COMMA expression)* )? RBRACKET
  private static boolean arrayCreator_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && arrayCreator_1_1(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // (expression (COMMA expression)* )?
  private static boolean arrayCreator_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_1_1")) return false;
    arrayCreator_1_1_0(b, l + 1);
    return true;
  }

  // expression (COMMA expression)*
  private static boolean arrayCreator_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1);
    r = r && arrayCreator_1_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA expression)*
  private static boolean arrayCreator_1_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_1_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!arrayCreator_1_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayCreator_1_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA expression
  private static boolean arrayCreator_1_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_1_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE (variableInitializer (COMMA variableInitializer)*)? COMMA? RBRACE
  public static boolean arrayInitializer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && arrayInitializer_1(b, l + 1);
    r = r && arrayInitializer_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, ARRAY_INITIALIZER, r);
    return r;
  }

  // (variableInitializer (COMMA variableInitializer)*)?
  private static boolean arrayInitializer_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer_1")) return false;
    arrayInitializer_1_0(b, l + 1);
    return true;
  }

  // variableInitializer (COMMA variableInitializer)*
  private static boolean arrayInitializer_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variableInitializer(b, l + 1);
    r = r && arrayInitializer_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA variableInitializer)*
  private static boolean arrayInitializer_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!arrayInitializer_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayInitializer_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA variableInitializer
  private static boolean arrayInitializer_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && variableInitializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean arrayInitializer_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayInitializer_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // EQ
  //                      | PLUSEQ
  //                      | SUBEQ
  //                      | STAREQ
  //                      | SLASHEQ
  //                      | AMPEQ
  //                      | BAREQ
  //                      | CARETEQ
  //                      | PERCENTEQ
  //                      | LT LT EQ
  //                      | GT GT GT EQ
  //                      | GT GT EQ
  public static boolean assignmentOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignmentOperator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASSIGNMENT_OPERATOR, "<assignment operator>");
    r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, PLUSEQ);
    if (!r) r = consumeToken(b, SUBEQ);
    if (!r) r = consumeToken(b, STAREQ);
    if (!r) r = consumeToken(b, SLASHEQ);
    if (!r) r = consumeToken(b, AMPEQ);
    if (!r) r = consumeToken(b, BAREQ);
    if (!r) r = consumeToken(b, CARETEQ);
    if (!r) r = consumeToken(b, PERCENTEQ);
    if (!r) r = parseTokens(b, 0, LT, LT, EQ);
    if (!r) r = parseTokens(b, 0, GT, GT, GT, EQ);
    if (!r) r = parseTokens(b, 0, GT, GT, EQ);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // unaryExpression (bitwiseOperator unaryExpression)*
  public static boolean bitwiseExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, BITWISE_EXPRESSION, "<bitwise expression>");
    r = unaryExpression(b, l + 1);
    r = r && bitwiseExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (bitwiseOperator unaryExpression)*
  private static boolean bitwiseExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!bitwiseExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "bitwiseExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // bitwiseOperator unaryExpression
  private static boolean bitwiseExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = bitwiseOperator(b, l + 1);
    r = r && unaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // AMP | CARET| BAR
  public static boolean bitwiseOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseOperator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BITWISE_OPERATOR, "<bitwise operator>");
    r = consumeToken(b, AMP);
    if (!r) r = consumeToken(b, CARET);
    if (!r) r = consumeToken(b, BAR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BLING
  public static boolean blingExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "blingExpression")) return false;
    if (!nextTokenIs(b, BLING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BLING);
    exit_section_(b, m, BLING_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE blockStatement* RBRACE
  public static boolean block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && block_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, BLOCK, r);
    return r;
  }

  // blockStatement*
  private static boolean block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!blockStatement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "block_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // variableDeclarationList SEMI
  //                  | statement
  public static boolean blockStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "blockStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BLOCK_STATEMENT, "<block statement>");
    r = blockStatement_0(b, l + 1);
    if (!r) r = statement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // variableDeclarationList SEMI
  private static boolean blockStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "blockStatement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variableDeclarationList(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CATCH LPAREN catchParameter RPAREN block
  public static boolean catchClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catchClause")) return false;
    if (!nextTokenIs(b, CATCH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CATCH, LPAREN);
    r = r && catchParameter(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && block(b, l + 1);
    exit_section_(b, m, CATCH_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // id (INSTANCEOF qualifiedName)?
  public static boolean catchParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catchParameter")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = id(b, l + 1);
    r = r && catchParameter_1(b, l + 1);
    exit_section_(b, m, CATCH_PARAMETER, r);
    return r;
  }

  // (INSTANCEOF qualifiedName)?
  private static boolean catchParameter_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catchParameter_1")) return false;
    catchParameter_1_0(b, l + 1);
    return true;
  }

  // INSTANCEOF qualifiedName
  private static boolean catchParameter_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catchParameter_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSTANCEOF);
    r = r && qualifiedName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // catchClause (catchClause)*
  public static boolean catches(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catches")) return false;
    if (!nextTokenIs(b, CATCH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = catchClause(b, l + 1);
    r = r && catches_1(b, l + 1);
    exit_section_(b, m, CATCHES, r);
    return r;
  }

  // (catchClause)*
  private static boolean catches_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catches_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!catches_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "catches_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (catchClause)
  private static boolean catches_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catches_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = catchClause(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE classBodyMembers RBRACE
  public static boolean classBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_BODY, null);
    r = consumeToken(b, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, classBodyMembers(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // constDeclaration | fieldDeclarationList | functionDeclaration | classDeclaration | enumDeclaration
  static boolean classBodyMember(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBodyMember")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = constDeclaration(b, l + 1);
    if (!r) r = fieldDeclarationList(b, l + 1);
    if (!r) r = functionDeclaration(b, l + 1);
    if (!r) r = classDeclaration(b, l + 1);
    if (!r) r = enumDeclaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // classBodyMember*
  public static boolean classBodyMembers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBodyMembers")) return false;
    Marker m = enter_section_(b, l, _NONE_, CLASS_BODY_MEMBERS, "<class body members>");
    int c = current_position_(b);
    while (true) {
      if (!classBodyMember(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "classBodyMembers", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // modifiers CLASS componentName (EXTENDS fullyQualifiedReferenceExpression)? classBody
  public static boolean classDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_DECLARATION, "<class declaration>");
    r = modifiers(b, l + 1);
    r = r && consumeToken(b, CLASS);
    p = r; // pin = 2
    r = r && report_error_(b, componentName(b, l + 1));
    r = p && report_error_(b, classDeclaration_3(b, l + 1)) && r;
    r = p && classBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (EXTENDS fullyQualifiedReferenceExpression)?
  private static boolean classDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_3")) return false;
    classDeclaration_3_0(b, l + 1);
    return true;
  }

  // EXTENDS fullyQualifiedReferenceExpression
  private static boolean classDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXTENDS);
    r = r && fullyQualifiedReferenceExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // usingDeclaration
  //                           | moduleDeclaration
  //                           | classDeclaration
  //                           | enumDeclaration
  //                           | constDeclaration
  //                           | fieldDeclarationList
  //                           | functionDeclaration
  //                           | blockStatement
  static boolean compilationUnit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compilationUnit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = usingDeclaration(b, l + 1);
    if (!r) r = moduleDeclaration(b, l + 1);
    if (!r) r = classDeclaration(b, l + 1);
    if (!r) r = enumDeclaration(b, l + 1);
    if (!r) r = constDeclaration(b, l + 1);
    if (!r) r = fieldDeclarationList(b, l + 1);
    if (!r) r = functionDeclaration(b, l + 1);
    if (!r) r = blockStatement(b, l + 1);
    exit_section_(b, l, m, r, false, compilationUnit_auto_recover_);
    return r;
  }

  /* ********************************************************** */
  // id
  public static boolean componentName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "componentName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = id(b, l + 1);
    exit_section_(b, m, COMPONENT_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // inclusiveOrExpression ((AMPAMP | AND) inclusiveOrExpression)*
  public static boolean conditionalAndExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalAndExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, CONDITIONAL_AND_EXPRESSION, "<conditional and expression>");
    r = inclusiveOrExpression(b, l + 1);
    r = r && conditionalAndExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ((AMPAMP | AND) inclusiveOrExpression)*
  private static boolean conditionalAndExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalAndExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!conditionalAndExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "conditionalAndExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (AMPAMP | AND) inclusiveOrExpression
  private static boolean conditionalAndExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalAndExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = conditionalAndExpression_1_0_0(b, l + 1);
    r = r && inclusiveOrExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // AMPAMP | AND
  private static boolean conditionalAndExpression_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalAndExpression_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AMPAMP);
    if (!r) r = consumeToken(b, AND);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // conditionalOrExpression (QUES expression COLON conditionalExpression)?
  public static boolean conditionalExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, CONDITIONAL_EXPRESSION, "<conditional expression>");
    r = conditionalOrExpression(b, l + 1);
    r = r && conditionalExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (QUES expression COLON conditionalExpression)?
  private static boolean conditionalExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalExpression_1")) return false;
    conditionalExpression_1_0(b, l + 1);
    return true;
  }

  // QUES expression COLON conditionalExpression
  private static boolean conditionalExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUES);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && conditionalExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // conditionalAndExpression ((BARBAR | OR) conditionalAndExpression)*
  public static boolean conditionalOrExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalOrExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, CONDITIONAL_OR_EXPRESSION, "<conditional or expression>");
    r = conditionalAndExpression(b, l + 1);
    r = r && conditionalOrExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ((BARBAR | OR) conditionalAndExpression)*
  private static boolean conditionalOrExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalOrExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!conditionalOrExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "conditionalOrExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (BARBAR | OR) conditionalAndExpression
  private static boolean conditionalOrExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalOrExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = conditionalOrExpression_1_0_0(b, l + 1);
    r = r && conditionalAndExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BARBAR | OR
  private static boolean conditionalOrExpression_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalOrExpression_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BARBAR);
    if (!r) r = consumeToken(b, OR);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // modifiers CONST componentName (EQ expression)? SEMI
  public static boolean constDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONST_DECLARATION, "<const declaration>");
    r = modifiers(b, l + 1);
    r = r && consumeToken(b, CONST);
    p = r; // pin = 2
    r = r && report_error_(b, componentName(b, l + 1));
    r = p && report_error_(b, constDeclaration_3(b, l + 1)) && r;
    r = p && consumeToken(b, SEMI) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (EQ expression)?
  private static boolean constDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constDeclaration_3")) return false;
    constDeclaration_3_0(b, l + 1);
    return true;
  }

  // EQ expression
  private static boolean constDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constDeclaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQ);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // objectCreator | arrayCreator
  public static boolean creator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "creator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CREATOR, "<creator>");
    r = objectCreator(b, l + 1);
    if (!r) r = arrayCreator(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NEW LBRACE RBRACE // new {}
  //                     | LBRACE (keyValueInitializer (COMMA keyValueInitializer)* )? RBRACE
  public static boolean dictionaryCreator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionaryCreator")) return false;
    if (!nextTokenIs(b, "<dictionary creator>", LBRACE, NEW)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DICTIONARY_CREATOR, "<dictionary creator>");
    r = parseTokens(b, 0, NEW, LBRACE, RBRACE);
    if (!r) r = dictionaryCreator_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LBRACE (keyValueInitializer (COMMA keyValueInitializer)* )? RBRACE
  private static boolean dictionaryCreator_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionaryCreator_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && dictionaryCreator_1_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (keyValueInitializer (COMMA keyValueInitializer)* )?
  private static boolean dictionaryCreator_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionaryCreator_1_1")) return false;
    dictionaryCreator_1_1_0(b, l + 1);
    return true;
  }

  // keyValueInitializer (COMMA keyValueInitializer)*
  private static boolean dictionaryCreator_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionaryCreator_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = keyValueInitializer(b, l + 1);
    r = r && dictionaryCreator_1_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA keyValueInitializer)*
  private static boolean dictionaryCreator_1_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionaryCreator_1_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!dictionaryCreator_1_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dictionaryCreator_1_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA keyValueInitializer
  private static boolean dictionaryCreator_1_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionaryCreator_1_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && keyValueInitializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // componentName (EQ INTLITERAL)?
  public static boolean enumConstant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstant")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENUM_CONSTANT, null);
    r = componentName(b, l + 1);
    p = r; // pin = 1
    r = r && enumConstant_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (EQ INTLITERAL)?
  private static boolean enumConstant_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstant_1")) return false;
    enumConstant_1_0(b, l + 1);
    return true;
  }

  // EQ INTLITERAL
  private static boolean enumConstant_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstant_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EQ, INTLITERAL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // modifiers ENUM LBRACE enumConstant (COMMA enumConstant)* RBRACE
  public static boolean enumDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_DECLARATION, "<enum declaration>");
    r = modifiers(b, l + 1);
    r = r && consumeTokens(b, 0, ENUM, LBRACE);
    r = r && enumConstant(b, l + 1);
    r = r && enumDeclaration_4(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA enumConstant)*
  private static boolean enumDeclaration_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!enumDeclaration_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumDeclaration_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA enumConstant
  private static boolean enumDeclaration_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && enumConstant(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // hasExpression ((EQEQ | BANGEQ) hasExpression)*
  public static boolean equalityExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, EQUALITY_EXPRESSION, "<equality expression>");
    r = hasExpression(b, l + 1);
    r = r && equalityExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ((EQEQ | BANGEQ) hasExpression)*
  private static boolean equalityExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!equalityExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "equalityExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (EQEQ | BANGEQ) hasExpression
  private static boolean equalityExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = equalityExpression_1_0_0(b, l + 1);
    r = r && hasExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // EQEQ | BANGEQ
  private static boolean equalityExpression_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityExpression_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQEQ);
    if (!r) r = consumeToken(b, BANGEQ);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // andExpression (CARET andExpression)*
  public static boolean exclusiveOrExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exclusiveOrExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, EXCLUSIVE_OR_EXPRESSION, "<exclusive or expression>");
    r = andExpression(b, l + 1);
    r = r && exclusiveOrExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (CARET andExpression)*
  private static boolean exclusiveOrExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exclusiveOrExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!exclusiveOrExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "exclusiveOrExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // CARET andExpression
  private static boolean exclusiveOrExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exclusiveOrExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CARET);
    r = r && andExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // conditionalExpression (assignmentOperator expression)?
  public static boolean expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, EXPRESSION, "<expression>");
    r = conditionalExpression(b, l + 1);
    r = r && expression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (assignmentOperator expression)?
  private static boolean expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_1")) return false;
    expression_1_0(b, l + 1);
    return true;
  }

  // assignmentOperator expression
  private static boolean expression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = assignmentOperator(b, l + 1);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // expression (COMMA expression)*
  public static boolean expressionList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION_LIST, "<expression list>");
    r = expression(b, l + 1);
    r = r && expressionList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA expression)*
  private static boolean expressionList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionList_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!expressionList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expressionList_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA expression
  private static boolean expressionList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // varOrFieldDeclaration
  public static boolean fieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varOrFieldDeclaration(b, l + 1);
    exit_section_(b, m, FIELD_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // modifiers VAR fieldDeclaration (COMMA fieldDeclaration)* SEMI
  public static boolean fieldDeclarationList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclarationList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_DECLARATION_LIST, "<field declaration list>");
    r = modifiers(b, l + 1);
    r = r && consumeToken(b, VAR);
    r = r && fieldDeclaration(b, l + 1);
    r = r && fieldDeclarationList_3(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA fieldDeclaration)*
  private static boolean fieldDeclarationList_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclarationList_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!fieldDeclarationList_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fieldDeclarationList_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA fieldDeclaration
  private static boolean fieldDeclarationList_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclarationList_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && fieldDeclaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // variableDeclarationList
  //           | expressionList
  public static boolean forInit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forInit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_INIT, "<for init>");
    r = variableDeclarationList(b, l + 1);
    if (!r) r = expressionList(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // FOR LPAREN forInit? SEMI expression? SEMI expressionList? RPAREN statement
  public static boolean forStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FOR, LPAREN);
    r = r && forStatement_2(b, l + 1);
    r = r && consumeToken(b, SEMI);
    r = r && forStatement_4(b, l + 1);
    r = r && consumeToken(b, SEMI);
    r = r && forStatement_6(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && statement(b, l + 1);
    exit_section_(b, m, FOR_STATEMENT, r);
    return r;
  }

  // forInit?
  private static boolean forStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_2")) return false;
    forInit(b, l + 1);
    return true;
  }

  // expression?
  private static boolean forStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_4")) return false;
    expression(b, l + 1);
    return true;
  }

  // expressionList?
  private static boolean forStatement_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_6")) return false;
    expressionList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // componentName (COMMA componentName)*
  public static boolean formalParameterDeclarations(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "formalParameterDeclarations")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = componentName(b, l + 1);
    r = r && formalParameterDeclarations_1(b, l + 1);
    exit_section_(b, m, FORMAL_PARAMETER_DECLARATIONS, r);
    return r;
  }

  // (COMMA componentName)*
  private static boolean formalParameterDeclarations_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "formalParameterDeclarations_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!formalParameterDeclarations_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "formalParameterDeclarations_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA componentName
  private static boolean formalParameterDeclarations_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "formalParameterDeclarations_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && componentName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // referenceOrThisExpression qualifiedReferenceExpression*
  static boolean fullyQualifiedReferenceExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fullyQualifiedReferenceExpression")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = referenceOrThisExpression(b, l + 1);
    r = r && fullyQualifiedReferenceExpression_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // qualifiedReferenceExpression*
  private static boolean fullyQualifiedReferenceExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fullyQualifiedReferenceExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!qualifiedReferenceExpression(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fullyQualifiedReferenceExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // modifiers FUNCTION componentName
  //  LPAREN formalParameterDeclarations? RPAREN block
  public static boolean functionDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DECLARATION, "<function declaration>");
    r = modifiers(b, l + 1);
    r = r && consumeToken(b, FUNCTION);
    r = r && componentName(b, l + 1);
    p = r; // pin = 3
    r = r && report_error_(b, consumeToken(b, LPAREN));
    r = p && report_error_(b, functionDeclaration_4(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    r = p && block(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // formalParameterDeclarations?
  private static boolean functionDeclaration_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDeclaration_4")) return false;
    formalParameterDeclarations(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // instanceOfExpression (HAS symbol)?
  public static boolean hasExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hasExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, HAS_EXPRESSION, "<has expression>");
    r = instanceOfExpression(b, l + 1);
    r = r && hasExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (HAS symbol)?
  private static boolean hasExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hasExpression_1")) return false;
    hasExpression_1_0(b, l + 1);
    return true;
  }

  // HAS symbol
  private static boolean hasExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hasExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HAS);
    r = r && symbol(b, l + 1);
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
  // (LBRACKET expression RBRACKET)+
  //                    | arguments
  //                    | DOT CLASS
  //                    | DOT IDENTIFIER arguments
  //                    | DOT SUPER arguments
  public static boolean identifierSuffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IDENTIFIER_SUFFIX, "<identifier suffix>");
    r = identifierSuffix_0(b, l + 1);
    if (!r) r = arguments(b, l + 1);
    if (!r) r = parseTokens(b, 0, DOT, CLASS);
    if (!r) r = identifierSuffix_3(b, l + 1);
    if (!r) r = identifierSuffix_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (LBRACKET expression RBRACKET)+
  private static boolean identifierSuffix_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierSuffix_0_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!identifierSuffix_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "identifierSuffix_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // LBRACKET expression RBRACKET
  private static boolean identifierSuffix_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT IDENTIFIER arguments
  private static boolean identifierSuffix_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    r = r && arguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT SUPER arguments
  private static boolean identifierSuffix_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, SUPER);
    r = r && arguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // exclusiveOrExpression (BAR exclusiveOrExpression)*
  public static boolean inclusiveOrExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inclusiveOrExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, INCLUSIVE_OR_EXPRESSION, "<inclusive or expression>");
    r = exclusiveOrExpression(b, l + 1);
    r = r && inclusiveOrExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (BAR exclusiveOrExpression)*
  private static boolean inclusiveOrExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inclusiveOrExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!inclusiveOrExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "inclusiveOrExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // BAR exclusiveOrExpression
  private static boolean inclusiveOrExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inclusiveOrExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BAR);
    r = r && exclusiveOrExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // relationalExpression (INSTANCEOF qualifiedName)?
  public static boolean instanceOfExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instanceOfExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, INSTANCE_OF_EXPRESSION, "<instance of expression>");
    r = relationalExpression(b, l + 1);
    r = r && instanceOfExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (INSTANCEOF qualifiedName)?
  private static boolean instanceOfExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instanceOfExpression_1")) return false;
    instanceOfExpression_1_0(b, l + 1);
    return true;
  }

  // INSTANCEOF qualifiedName
  private static boolean instanceOfExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instanceOfExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSTANCEOF);
    r = r && qualifiedName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // primary EQGT expression
  public static boolean keyValueInitializer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "keyValueInitializer")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KEY_VALUE_INITIALIZER, "<key value initializer>");
    r = primary(b, l + 1);
    r = r && consumeToken(b, EQGT);
    r = r && expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INTLITERAL
  //           | LONGLITERAL
  //           | FLOATLITERAL
  //           | DOUBLELITERAL
  //           | HEX_LITERAL
  //           | CHARLITERAL
  //           | string
  //           | TRUE
  //           | FALSE
  //           | NULL
  public static boolean literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL, "<literal>");
    r = consumeToken(b, INTLITERAL);
    if (!r) r = consumeToken(b, LONGLITERAL);
    if (!r) r = consumeToken(b, FLOATLITERAL);
    if (!r) r = consumeToken(b, DOUBLELITERAL);
    if (!r) r = consumeToken(b, HEX_LITERAL);
    if (!r) r = consumeToken(b, CHARLITERAL);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    if (!r) r = consumeToken(b, NULL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // annotation? STATIC? HIDDEN?
  public static boolean modifiers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "modifiers")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MODIFIERS, "<modifiers>");
    r = modifiers_0(b, l + 1);
    r = r && modifiers_1(b, l + 1);
    r = r && modifiers_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
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
  // LBRACE moduleBodyMembers RBRACE
  public static boolean moduleBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "moduleBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_BODY, null);
    r = consumeToken(b, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, moduleBodyMembers(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // constDeclaration | fieldDeclarationList | functionDeclaration | classDeclaration | enumDeclaration | moduleDeclaration
  static boolean moduleBodyMember(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "moduleBodyMember")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = constDeclaration(b, l + 1);
    if (!r) r = fieldDeclarationList(b, l + 1);
    if (!r) r = functionDeclaration(b, l + 1);
    if (!r) r = classDeclaration(b, l + 1);
    if (!r) r = enumDeclaration(b, l + 1);
    if (!r) r = moduleDeclaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // moduleBodyMember*
  public static boolean moduleBodyMembers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "moduleBodyMembers")) return false;
    Marker m = enter_section_(b, l, _NONE_, MODULE_BODY_MEMBERS, "<module body members>");
    int c = current_position_(b);
    while (true) {
      if (!moduleBodyMember(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "moduleBodyMembers", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // modifiers MODULE componentName moduleBody
  public static boolean moduleDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "moduleDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_DECLARATION, "<module declaration>");
    r = modifiers(b, l + 1);
    r = r && consumeToken(b, MODULE);
    p = r; // pin = 2
    r = r && report_error_(b, componentName(b, l + 1));
    r = p && moduleBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
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
  // bitwiseExpression ((STAR | SLASH | PERCENT) unaryExpression)*
  public static boolean multiplicativeExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiplicativeExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, MULTIPLICATIVE_EXPRESSION, "<multiplicative expression>");
    r = bitwiseExpression(b, l + 1);
    r = r && multiplicativeExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ((STAR | SLASH | PERCENT) unaryExpression)*
  private static boolean multiplicativeExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiplicativeExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!multiplicativeExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "multiplicativeExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (STAR | SLASH | PERCENT) unaryExpression
  private static boolean multiplicativeExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiplicativeExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = multiplicativeExpression_1_0_0(b, l + 1);
    r = r && unaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // STAR | SLASH | PERCENT
  private static boolean multiplicativeExpression_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiplicativeExpression_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STAR);
    if (!r) r = consumeToken(b, SLASH);
    if (!r) r = consumeToken(b, PERCENT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NEW fullyQualifiedReferenceExpression arguments classBody?
  public static boolean objectCreator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objectCreator")) return false;
    if (!nextTokenIs(b, NEW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_CREATOR, null);
    r = consumeToken(b, NEW);
    r = r && fullyQualifiedReferenceExpression(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, arguments(b, l + 1));
    r = p && objectCreator_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // classBody?
  private static boolean objectCreator_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objectCreator_3")) return false;
    classBody(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LPAREN expression RPAREN
  public static boolean parExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parExpression")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, PAR_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // parExpression
  //                   | fullyQualifiedReferenceExpression identifierSuffix?
  //                   | literal
  //                   | symbol
  //                   | creator
  //                   | VOID DOT CLASS
  static boolean primary(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parExpression(b, l + 1);
    if (!r) r = primary_1(b, l + 1);
    if (!r) r = literal(b, l + 1);
    if (!r) r = symbol(b, l + 1);
    if (!r) r = creator(b, l + 1);
    if (!r) r = parseTokens(b, 0, VOID, DOT, CLASS);
    exit_section_(b, m, null, r);
    return r;
  }

  // fullyQualifiedReferenceExpression identifierSuffix?
  private static boolean primary_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fullyQualifiedReferenceExpression(b, l + 1);
    r = r && primary_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // identifierSuffix?
  private static boolean primary_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_1_1")) return false;
    identifierSuffix(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // componentName (DOT componentName)*
  public static boolean qualifiedName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = componentName(b, l + 1);
    r = r && qualifiedName_1(b, l + 1);
    exit_section_(b, m, QUALIFIED_NAME, r);
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
  // DOT referenceExpression
  public static boolean qualifiedReferenceExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedReferenceExpression")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && referenceExpression(b, l + 1);
    exit_section_(b, m, REFERENCE_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // id
  public static boolean referenceExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceExpression")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = id(b, l + 1);
    exit_section_(b, m, REFERENCE_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // referenceExpression
  //                                     | thisExpression
  //                                     | blingExpression
  static boolean referenceOrThisExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceOrThisExpression")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = referenceExpression(b, l + 1);
    if (!r) r = thisExpression(b, l + 1);
    if (!r) r = blingExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // shiftExpression (relationalOp shiftExpression)*
  public static boolean relationalExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationalExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, RELATIONAL_EXPRESSION, "<relational expression>");
    r = shiftExpression(b, l + 1);
    r = r && relationalExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (relationalOp shiftExpression)*
  private static boolean relationalExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationalExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!relationalExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "relationalExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // relationalOp shiftExpression
  private static boolean relationalExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationalExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = relationalOp(b, l + 1);
    r = r && shiftExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LT EQ | GT EQ | LT | GT
  public static boolean relationalOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationalOp")) return false;
    if (!nextTokenIs(b, "<relational op>", GT, LT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RELATIONAL_OP, "<relational op>");
    r = parseTokens(b, 0, LT, EQ);
    if (!r) r = parseTokens(b, 0, GT, EQ);
    if (!r) r = consumeToken(b, LT);
    if (!r) r = consumeToken(b, GT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DOT IDENTIFIER arguments?
  //            | LBRACKET expression RBRACKET
  public static boolean selector(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector")) return false;
    if (!nextTokenIs(b, "<selector>", DOT, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SELECTOR, "<selector>");
    r = selector_0(b, l + 1);
    if (!r) r = selector_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DOT IDENTIFIER arguments?
  private static boolean selector_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    r = r && selector_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // arguments?
  private static boolean selector_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_0_2")) return false;
    arguments(b, l + 1);
    return true;
  }

  // LBRACKET expression RBRACKET
  private static boolean selector_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // additiveExpression (shiftOp additiveExpression)*
  public static boolean shiftExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shiftExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, SHIFT_EXPRESSION, "<shift expression>");
    r = additiveExpression(b, l + 1);
    r = r && shiftExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (shiftOp additiveExpression)*
  private static boolean shiftExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shiftExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!shiftExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "shiftExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // shiftOp additiveExpression
  private static boolean shiftExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shiftExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = shiftOp(b, l + 1);
    r = r && additiveExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LT LT | GT GT GT | GT GT
  public static boolean shiftOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shiftOp")) return false;
    if (!nextTokenIs(b, "<shift op>", GT, LT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SHIFT_OP, "<shift op>");
    r = parseTokens(b, 0, LT, LT);
    if (!r) r = parseTokens(b, 0, GT, GT, GT);
    if (!r) r = parseTokens(b, 0, GT, GT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // block
  //             | IF parExpression statement (ELSE statement)?
  //             | forStatement
  //             | WHILE parExpression statement
  //             | DO statement WHILE parExpression SEMI
  //             | tryStatement
  //             | SWITCH parExpression LBRACE switchBlockStatementGroups RBRACE
  //             | RETURN expression? SEMI
  //             | THROW expression SEMI
  //             | BREAK referenceExpression? SEMI
  //             | CONTINUE referenceExpression? SEMI
  //             | expression SEMI
  //             | IDENTIFIER COLON statement
  //             | SEMI
  public static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = block(b, l + 1);
    if (!r) r = statement_1(b, l + 1);
    if (!r) r = forStatement(b, l + 1);
    if (!r) r = statement_3(b, l + 1);
    if (!r) r = statement_4(b, l + 1);
    if (!r) r = tryStatement(b, l + 1);
    if (!r) r = statement_6(b, l + 1);
    if (!r) r = statement_7(b, l + 1);
    if (!r) r = statement_8(b, l + 1);
    if (!r) r = statement_9(b, l + 1);
    if (!r) r = statement_10(b, l + 1);
    if (!r) r = statement_11(b, l + 1);
    if (!r) r = statement_12(b, l + 1);
    if (!r) r = consumeToken(b, SEMI);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IF parExpression statement (ELSE statement)?
  private static boolean statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF);
    r = r && parExpression(b, l + 1);
    r = r && statement(b, l + 1);
    r = r && statement_1_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ELSE statement)?
  private static boolean statement_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_1_3")) return false;
    statement_1_3_0(b, l + 1);
    return true;
  }

  // ELSE statement
  private static boolean statement_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHILE parExpression statement
  private static boolean statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WHILE);
    r = r && parExpression(b, l + 1);
    r = r && statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DO statement WHILE parExpression SEMI
  private static boolean statement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DO);
    r = r && statement(b, l + 1);
    r = r && consumeToken(b, WHILE);
    r = r && parExpression(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // SWITCH parExpression LBRACE switchBlockStatementGroups RBRACE
  private static boolean statement_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SWITCH);
    r = r && parExpression(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && switchBlockStatementGroups(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // RETURN expression? SEMI
  private static boolean statement_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RETURN);
    r = r && statement_7_1(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // expression?
  private static boolean statement_7_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_7_1")) return false;
    expression(b, l + 1);
    return true;
  }

  // THROW expression SEMI
  private static boolean statement_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THROW);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // BREAK referenceExpression? SEMI
  private static boolean statement_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_9")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BREAK);
    r = r && statement_9_1(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // referenceExpression?
  private static boolean statement_9_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_9_1")) return false;
    referenceExpression(b, l + 1);
    return true;
  }

  // CONTINUE referenceExpression? SEMI
  private static boolean statement_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_10")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTINUE);
    r = r && statement_10_1(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // referenceExpression?
  private static boolean statement_10_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_10_1")) return false;
    referenceExpression(b, l + 1);
    return true;
  }

  // expression SEMI
  private static boolean statement_11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_11")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER COLON statement
  private static boolean statement_12(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_12")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    r = r && statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // switchLabel (blockStatement)*
  public static boolean switchBlockStatementGroup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchBlockStatementGroup")) return false;
    if (!nextTokenIs(b, "<switch block statement group>", CASE, DEFAULT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_BLOCK_STATEMENT_GROUP, "<switch block statement group>");
    r = switchLabel(b, l + 1);
    r = r && switchBlockStatementGroup_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (blockStatement)*
  private static boolean switchBlockStatementGroup_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchBlockStatementGroup_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!switchBlockStatementGroup_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "switchBlockStatementGroup_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (blockStatement)
  private static boolean switchBlockStatementGroup_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchBlockStatementGroup_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = blockStatement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (switchBlockStatementGroup)*
  public static boolean switchBlockStatementGroups(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchBlockStatementGroups")) return false;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_BLOCK_STATEMENT_GROUPS, "<switch block statement groups>");
    int c = current_position_(b);
    while (true) {
      if (!switchBlockStatementGroups_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "switchBlockStatementGroups", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // (switchBlockStatementGroup)
  private static boolean switchBlockStatementGroups_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchBlockStatementGroups_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = switchBlockStatementGroup(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CASE expression COLON | DEFAULT COLON
  public static boolean switchLabel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchLabel")) return false;
    if (!nextTokenIs(b, "<switch label>", CASE, DEFAULT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_LABEL, "<switch label>");
    r = switchLabel_0(b, l + 1);
    if (!r) r = parseTokens(b, 0, DEFAULT, COLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // CASE expression COLON
  private static boolean switchLabel_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchLabel_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CASE);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COLON referenceExpression
  public static boolean symbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "symbol")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SYMBOL, null);
    r = consumeToken(b, COLON);
    p = r; // pin = 1
    r = r && referenceExpression(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // THIS | SELF
  public static boolean thisExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "thisExpression")) return false;
    if (!nextTokenIs(b, "<this expression>", SELF, THIS)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, THIS_EXPRESSION, "<this expression>");
    r = consumeToken(b, THIS);
    if (!r) r = consumeToken(b, SELF);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TRY block (catches FINALLY block | catches | FINALLY block)
  public static boolean tryStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tryStatement")) return false;
    if (!nextTokenIs(b, TRY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TRY);
    r = r && block(b, l + 1);
    r = r && tryStatement_2(b, l + 1);
    exit_section_(b, m, TRY_STATEMENT, r);
    return r;
  }

  // catches FINALLY block | catches | FINALLY block
  private static boolean tryStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tryStatement_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tryStatement_2_0(b, l + 1);
    if (!r) r = catches(b, l + 1);
    if (!r) r = tryStatement_2_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // catches FINALLY block
  private static boolean tryStatement_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tryStatement_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = catches(b, l + 1);
    r = r && consumeToken(b, FINALLY);
    r = r && block(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FINALLY block
  private static boolean tryStatement_2_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tryStatement_2_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FINALLY);
    r = r && block(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PLUS unaryExpression
  //                   | SUB unaryExpression
  //                   | PLUSPLUS unaryExpression
  //                   | SUBSUB unaryExpression
  //                   | unaryExpressionNotPlusMinus
  public static boolean unaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, UNARY_EXPRESSION, "<unary expression>");
    r = unaryExpression_0(b, l + 1);
    if (!r) r = unaryExpression_1(b, l + 1);
    if (!r) r = unaryExpression_2(b, l + 1);
    if (!r) r = unaryExpression_3(b, l + 1);
    if (!r) r = unaryExpressionNotPlusMinus(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PLUS unaryExpression
  private static boolean unaryExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    r = r && unaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SUB unaryExpression
  private static boolean unaryExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SUB);
    r = r && unaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUSPLUS unaryExpression
  private static boolean unaryExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpression_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUSPLUS);
    r = r && unaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SUBSUB unaryExpression
  private static boolean unaryExpression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpression_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SUBSUB);
    r = r && unaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TILDE unaryExpression
  //                                       | BANG unaryExpression
  //                                       | primary selector* (PLUSPLUS | SUBSUB)?
  static boolean unaryExpressionNotPlusMinus(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpressionNotPlusMinus")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = unaryExpressionNotPlusMinus_0(b, l + 1);
    if (!r) r = unaryExpressionNotPlusMinus_1(b, l + 1);
    if (!r) r = unaryExpressionNotPlusMinus_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // TILDE unaryExpression
  private static boolean unaryExpressionNotPlusMinus_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpressionNotPlusMinus_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TILDE);
    r = r && unaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BANG unaryExpression
  private static boolean unaryExpressionNotPlusMinus_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpressionNotPlusMinus_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BANG);
    r = r && unaryExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // primary selector* (PLUSPLUS | SUBSUB)?
  private static boolean unaryExpressionNotPlusMinus_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpressionNotPlusMinus_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = primary(b, l + 1);
    r = r && unaryExpressionNotPlusMinus_2_1(b, l + 1);
    r = r && unaryExpressionNotPlusMinus_2_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // selector*
  private static boolean unaryExpressionNotPlusMinus_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpressionNotPlusMinus_2_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!selector(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "unaryExpressionNotPlusMinus_2_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (PLUSPLUS | SUBSUB)?
  private static boolean unaryExpressionNotPlusMinus_2_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpressionNotPlusMinus_2_2")) return false;
    unaryExpressionNotPlusMinus_2_2_0(b, l + 1);
    return true;
  }

  // PLUSPLUS | SUBSUB
  private static boolean unaryExpressionNotPlusMinus_2_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpressionNotPlusMinus_2_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUSPLUS);
    if (!r) r = consumeToken(b, SUBSUB);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // USING fullyQualifiedReferenceExpression (AS componentName)? SEMI
  public static boolean usingDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingDeclaration")) return false;
    if (!nextTokenIs(b, USING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, USING_DECLARATION, null);
    r = consumeToken(b, USING);
    r = r && fullyQualifiedReferenceExpression(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, usingDeclaration_2(b, l + 1));
    r = p && consumeToken(b, SEMI) && r;
    exit_section_(b, l, m, r, p, null);
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

  /* ********************************************************** */
  // componentName (LBRACKET RBRACKET)* (EQ variableInitializer)?
  static boolean varOrFieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varOrFieldDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = componentName(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, varOrFieldDeclaration_1(b, l + 1));
    r = p && varOrFieldDeclaration_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (LBRACKET RBRACKET)*
  private static boolean varOrFieldDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varOrFieldDeclaration_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!varOrFieldDeclaration_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "varOrFieldDeclaration_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // LBRACKET RBRACKET
  private static boolean varOrFieldDeclaration_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varOrFieldDeclaration_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EQ variableInitializer)?
  private static boolean varOrFieldDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varOrFieldDeclaration_2")) return false;
    varOrFieldDeclaration_2_0(b, l + 1);
    return true;
  }

  // EQ variableInitializer
  private static boolean varOrFieldDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varOrFieldDeclaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQ);
    r = r && variableInitializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // varOrFieldDeclaration
  public static boolean variableDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varOrFieldDeclaration(b, l + 1);
    exit_section_(b, m, VARIABLE_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // VAR variableDeclaration (COMMA variableDeclaration)*
  public static boolean variableDeclarationList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarationList")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VAR);
    r = r && variableDeclaration(b, l + 1);
    r = r && variableDeclarationList_2(b, l + 1);
    exit_section_(b, m, VARIABLE_DECLARATION_LIST, r);
    return r;
  }

  // (COMMA variableDeclaration)*
  private static boolean variableDeclarationList_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarationList_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!variableDeclarationList_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "variableDeclarationList_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA variableDeclaration
  private static boolean variableDeclarationList_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarationList_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && variableDeclaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // arrayInitializer | expression
  public static boolean variableInitializer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableInitializer")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_INITIALIZER, "<variable initializer>");
    r = arrayInitializer(b, l + 1);
    if (!r) r = expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  final static Parser argumentsList_recover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return argumentsList_recover(b, l + 1);
    }
  };
  final static Parser compilationUnit_auto_recover_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return !nextTokenIsFast(b, BANG, BLING,
        BREAK, CHARLITERAL, CLASS, COLON, CONST, CONTINUE,
        DO, DOUBLELITERAL, ENUM, FALSE, FLOATLITERAL, FOR,
        FUNCTION, HEX_LITERAL, HIDDEN, IDENTIFIER, IF, INTLITERAL,
        LBRACE, LBRACKET, LONGLITERAL, LPAREN, MODULE, NEW,
        NULL, PLUS, PLUSPLUS, RETURN, SELF, SEMI,
        STATIC, SUB, SUBSUB, SWITCH, THIS, THROW,
        TILDE, TRUE, TRY, USING, VAR, VOID, WHILE, STRING);
    }
  };
}

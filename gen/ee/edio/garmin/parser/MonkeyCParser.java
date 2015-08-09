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
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class MonkeyCParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == DOUBLELITERAL) {
      r = DOUBLELITERAL(b, 0);
    }
    else if (t == FLOATLITERAL) {
      r = FLOATLITERAL(b, 0);
    }
    else if (t == INTLITERAL) {
      r = INTLITERAL(b, 0);
    }
    else if (t == LONGLITERAL) {
      r = LONGLITERAL(b, 0);
    }
    else if (t == ADDITIVE_EXPRESSION) {
      r = additiveExpression(b, 0);
    }
    else if (t == AND_EXPRESSION) {
      r = andExpression(b, 0);
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
    else if (t == CLASS_BODY_DECLARATION) {
      r = classBodyDeclaration(b, 0);
    }
    else if (t == CLASS_CREATOR_REST) {
      r = classCreatorRest(b, 0);
    }
    else if (t == CLASS_DECLARATION) {
      r = classDeclaration(b, 0);
    }
    else if (t == CLASS_OR_INTERFACE_TYPE) {
      r = classOrInterfaceType(b, 0);
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
    else if (t == CREATED_NAME) {
      r = createdName(b, 0);
    }
    else if (t == CREATOR) {
      r = creator(b, 0);
    }
    else if (t == ENUM_BODY) {
      r = enumBody(b, 0);
    }
    else if (t == ENUM_BODY_DECLARATIONS) {
      r = enumBodyDeclarations(b, 0);
    }
    else if (t == ENUM_CONSTANT) {
      r = enumConstant(b, 0);
    }
    else if (t == ENUM_CONSTANTS) {
      r = enumConstants(b, 0);
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
    else if (t == EXPLICIT_CONSTRUCTOR_INVOCATION) {
      r = explicitConstructorInvocation(b, 0);
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
    else if (t == FOR_INIT) {
      r = forInit(b, 0);
    }
    else if (t == FOR_STATEMENT) {
      r = forStatement(b, 0);
    }
    else if (t == FORMAL_PARAMETER_DECLS) {
      r = formalParameterDecls(b, 0);
    }
    else if (t == FORMAL_PARAMETERS) {
      r = formalParameters(b, 0);
    }
    else if (t == FUNCTION_DECLARATION) {
      r = functionDeclaration(b, 0);
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
    else if (t == LOCAL_VARIABLE_DECLARATION) {
      r = localVariableDeclaration(b, 0);
    }
    else if (t == LOCAL_VARIABLE_DECLARATION_STATEMENT) {
      r = localVariableDeclarationStatement(b, 0);
    }
    else if (t == MEMBER_DECL) {
      r = memberDecl(b, 0);
    }
    else if (t == MODIFIERS) {
      r = modifiers(b, 0);
    }
    else if (t == MULTIPLICATIVE_EXPRESSION) {
      r = multiplicativeExpression(b, 0);
    }
    else if (t == NEW_ARRAY_INITIALIZER) {
      r = newArrayInitializer(b, 0);
    }
    else if (t == NEW_DICTIONARY_INITIALIZER) {
      r = newDictionaryInitializer(b, 0);
    }
    else if (t == NORMAL_CLASS_DECLARATION) {
      r = normalClassDeclaration(b, 0);
    }
    else if (t == NORMAL_PARAMETER_DECL) {
      r = normalParameterDecl(b, 0);
    }
    else if (t == PAR_EXPRESSION) {
      r = parExpression(b, 0);
    }
    else if (t == PRIMARY) {
      r = primary(b, 0);
    }
    else if (t == PRIMITIVE_TYPE) {
      r = primitiveType(b, 0);
    }
    else if (t == QUALIFIED_NAME) {
      r = qualifiedName(b, 0);
    }
    else if (t == QUALIFIED_NAME_LIST) {
      r = qualifiedNameList(b, 0);
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
    else if (t == TRY_STATEMENT) {
      r = tryStatement(b, 0);
    }
    else if (t == TYPE) {
      r = type(b, 0);
    }
    else if (t == TYPE_DECLARATION) {
      r = typeDeclaration(b, 0);
    }
    else if (t == UNARY_EXPRESSION) {
      r = unaryExpression(b, 0);
    }
    else if (t == UNARY_EXPRESSION_NOT_PLUS_MINUS) {
      r = unaryExpressionNotPlusMinus(b, 0);
    }
    else if (t == USING_DECLARATION) {
      r = usingDeclaration(b, 0);
    }
    else if (t == VARIABLE_DECLARATOR) {
      r = variableDeclarator(b, 0);
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

  /* ********************************************************** */
  // NUMBER ('d' | 'D')
  public static boolean DOUBLELITERAL(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DOUBLELITERAL")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    r = r && DOUBLELITERAL_1(b, l + 1);
    exit_section_(b, m, DOUBLELITERAL, r);
    return r;
  }

  // 'd' | 'D'
  private static boolean DOUBLELITERAL_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DOUBLELITERAL_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "d");
    if (!r) r = consumeToken(b, "D");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER ('f' | 'F')
  public static boolean FLOATLITERAL(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FLOATLITERAL")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    r = r && FLOATLITERAL_1(b, l + 1);
    exit_section_(b, m, FLOATLITERAL, r);
    return r;
  }

  // 'f' | 'F'
  private static boolean FLOATLITERAL_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FLOATLITERAL_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "f");
    if (!r) r = consumeToken(b, "F");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INTEGER
  public static boolean INTLITERAL(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "INTLITERAL")) return false;
    if (!nextTokenIs(b, INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INTEGER);
    exit_section_(b, m, INTLITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // INTEGER ('l' | 'L')
  public static boolean LONGLITERAL(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LONGLITERAL")) return false;
    if (!nextTokenIs(b, INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INTEGER);
    r = r && LONGLITERAL_1(b, l + 1);
    exit_section_(b, m, LONGLITERAL, r);
    return r;
  }

  // 'l' | 'L'
  private static boolean LONGLITERAL_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LONGLITERAL_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "l");
    if (!r) r = consumeToken(b, "L");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // multiplicativeExpression ((PLUS | SUB) multiplicativeExpression)*
  public static boolean additiveExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "additiveExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<additive expression>");
    r = multiplicativeExpression(b, l + 1);
    r = r && additiveExpression_1(b, l + 1);
    exit_section_(b, l, m, ADDITIVE_EXPRESSION, r, false, null);
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
    Marker m = enter_section_(b, l, _NONE_, "<and expression>");
    r = equalityExpression(b, l + 1);
    r = r && andExpression_1(b, l + 1);
    exit_section_(b, l, m, AND_EXPRESSION, r, false, null);
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
  // LPAREN (expressionList)? RPAREN
  public static boolean arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && arguments_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ARGUMENTS, r);
    return r;
  }

  // (expressionList)?
  private static boolean arguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments_1")) return false;
    arguments_1_0(b, l + 1);
    return true;
  }

  // (expressionList)
  private static boolean arguments_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expressionList(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NEW createdName LBRACKET RBRACKET (LBRACKET RBRACKET)* arrayInitializer
  //                | NEW createdName LBRACKET expression RBRACKET (LBRACKET expression RBRACKET)* (LBRACKET RBRACKET)*
  //                | newArrayInitializer | newDictionaryInitializer
  public static boolean arrayCreator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<array creator>");
    r = arrayCreator_0(b, l + 1);
    if (!r) r = arrayCreator_1(b, l + 1);
    if (!r) r = newArrayInitializer(b, l + 1);
    if (!r) r = newDictionaryInitializer(b, l + 1);
    exit_section_(b, l, m, ARRAY_CREATOR, r, false, null);
    return r;
  }

  // NEW createdName LBRACKET RBRACKET (LBRACKET RBRACKET)* arrayInitializer
  private static boolean arrayCreator_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW);
    r = r && createdName(b, l + 1);
    r = r && consumeTokens(b, 0, LBRACKET, RBRACKET);
    r = r && arrayCreator_0_4(b, l + 1);
    r = r && arrayInitializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACKET RBRACKET)*
  private static boolean arrayCreator_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_0_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!arrayCreator_0_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayCreator_0_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // LBRACKET RBRACKET
  private static boolean arrayCreator_0_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_0_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEW createdName LBRACKET expression RBRACKET (LBRACKET expression RBRACKET)* (LBRACKET RBRACKET)*
  private static boolean arrayCreator_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW);
    r = r && createdName(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    r = r && arrayCreator_1_5(b, l + 1);
    r = r && arrayCreator_1_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACKET expression RBRACKET)*
  private static boolean arrayCreator_1_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_1_5")) return false;
    int c = current_position_(b);
    while (true) {
      if (!arrayCreator_1_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayCreator_1_5", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // LBRACKET expression RBRACKET
  private static boolean arrayCreator_1_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_1_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACKET RBRACKET)*
  private static boolean arrayCreator_1_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_1_6")) return false;
    int c = current_position_(b);
    while (true) {
      if (!arrayCreator_1_6_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayCreator_1_6", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // LBRACKET RBRACKET
  private static boolean arrayCreator_1_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayCreator_1_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE (variableInitializer (COMMA variableInitializer)*)? (COMMA)? RBRACE
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

  // (COMMA)?
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
    Marker m = enter_section_(b, l, _NONE_, "<assignment operator>");
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
    exit_section_(b, l, m, ASSIGNMENT_OPERATOR, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LBRACE (blockStatement)* RBRACE
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

  // (blockStatement)*
  private static boolean block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!block_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "block_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (blockStatement)
  private static boolean block_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = blockStatement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // localVariableDeclarationStatement
  //                  | classDeclaration
  //                  | statement
  public static boolean blockStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "blockStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<block statement>");
    r = localVariableDeclarationStatement(b, l + 1);
    if (!r) r = classDeclaration(b, l + 1);
    if (!r) r = statement(b, l + 1);
    exit_section_(b, l, m, BLOCK_STATEMENT, r, false, null);
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
  // IDENTIFIER (LBRACKET RBRACKET)*
  public static boolean catchParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catchParameter")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && catchParameter_1(b, l + 1);
    exit_section_(b, m, CATCH_PARAMETER, r);
    return r;
  }

  // (LBRACKET RBRACKET)*
  private static boolean catchParameter_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catchParameter_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!catchParameter_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "catchParameter_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // LBRACKET RBRACKET
  private static boolean catchParameter_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "catchParameter_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, RBRACKET);
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
  // LBRACE classBodyDeclaration* RBRACE
  public static boolean classBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && classBody_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, CLASS_BODY, r);
    return r;
  }

  // classBodyDeclaration*
  private static boolean classBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!classBodyDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "classBody_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // SEMI | (STATIC)? block | memberDecl
  public static boolean classBodyDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBodyDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<class body declaration>");
    r = consumeToken(b, SEMI);
    if (!r) r = classBodyDeclaration_1(b, l + 1);
    if (!r) r = memberDecl(b, l + 1);
    exit_section_(b, l, m, CLASS_BODY_DECLARATION, r, false, null);
    return r;
  }

  // (STATIC)? block
  private static boolean classBodyDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBodyDeclaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = classBodyDeclaration_1_0(b, l + 1);
    r = r && block(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (STATIC)?
  private static boolean classBodyDeclaration_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBodyDeclaration_1_0")) return false;
    consumeToken(b, STATIC);
    return true;
  }

  /* ********************************************************** */
  // arguments (classBody)?
  public static boolean classCreatorRest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classCreatorRest")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arguments(b, l + 1);
    r = r && classCreatorRest_1(b, l + 1);
    exit_section_(b, m, CLASS_CREATOR_REST, r);
    return r;
  }

  // (classBody)?
  private static boolean classCreatorRest_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classCreatorRest_1")) return false;
    classCreatorRest_1_0(b, l + 1);
    return true;
  }

  // (classBody)
  private static boolean classCreatorRest_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classCreatorRest_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = classBody(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // normalClassDeclaration | enumDeclaration
  public static boolean classDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<class declaration>");
    r = normalClassDeclaration(b, l + 1);
    if (!r) r = enumDeclaration(b, l + 1);
    exit_section_(b, l, m, CLASS_DECLARATION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER (DOT IDENTIFIER)*
  public static boolean classOrInterfaceType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classOrInterfaceType")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && classOrInterfaceType_1(b, l + 1);
    exit_section_(b, m, CLASS_OR_INTERFACE_TYPE, r);
    return r;
  }

  // (DOT IDENTIFIER)*
  private static boolean classOrInterfaceType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classOrInterfaceType_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!classOrInterfaceType_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "classOrInterfaceType_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // DOT IDENTIFIER
  private static boolean classOrInterfaceType_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classOrInterfaceType_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // usingDeclaration* typeDeclaration*
  static boolean compilationUnit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compilationUnit")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = compilationUnit_0(b, l + 1);
    r = r && compilationUnit_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // usingDeclaration*
  private static boolean compilationUnit_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compilationUnit_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!usingDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "compilationUnit_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // typeDeclaration*
  private static boolean compilationUnit_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compilationUnit_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!typeDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "compilationUnit_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // inclusiveOrExpression (AMPAMP inclusiveOrExpression)*
  public static boolean conditionalAndExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalAndExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<conditional and expression>");
    r = inclusiveOrExpression(b, l + 1);
    r = r && conditionalAndExpression_1(b, l + 1);
    exit_section_(b, l, m, CONDITIONAL_AND_EXPRESSION, r, false, null);
    return r;
  }

  // (AMPAMP inclusiveOrExpression)*
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

  // AMPAMP inclusiveOrExpression
  private static boolean conditionalAndExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalAndExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AMPAMP);
    r = r && inclusiveOrExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // conditionalOrExpression (QUES expression COLON conditionalExpression)?
  public static boolean conditionalExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<conditional expression>");
    r = conditionalOrExpression(b, l + 1);
    r = r && conditionalExpression_1(b, l + 1);
    exit_section_(b, l, m, CONDITIONAL_EXPRESSION, r, false, null);
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
  // conditionalAndExpression (BARBAR conditionalAndExpression)*
  public static boolean conditionalOrExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalOrExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<conditional or expression>");
    r = conditionalAndExpression(b, l + 1);
    r = r && conditionalOrExpression_1(b, l + 1);
    exit_section_(b, l, m, CONDITIONAL_OR_EXPRESSION, r, false, null);
    return r;
  }

  // (BARBAR conditionalAndExpression)*
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

  // BARBAR conditionalAndExpression
  private static boolean conditionalOrExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditionalOrExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BARBAR);
    r = r && conditionalAndExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // classOrInterfaceType | primitiveType
  public static boolean createdName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "createdName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<created name>");
    r = classOrInterfaceType(b, l + 1);
    if (!r) r = primitiveType(b, l + 1);
    exit_section_(b, l, m, CREATED_NAME, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NEW classOrInterfaceType classCreatorRest | arrayCreator
  public static boolean creator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "creator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<creator>");
    r = creator_0(b, l + 1);
    if (!r) r = arrayCreator(b, l + 1);
    exit_section_(b, l, m, CREATOR, r, false, null);
    return r;
  }

  // NEW classOrInterfaceType classCreatorRest
  private static boolean creator_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "creator_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW);
    r = r && classOrInterfaceType(b, l + 1);
    r = r && classCreatorRest(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE enumConstants? COMMA? enumBodyDeclarations? RBRACE
  public static boolean enumBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && enumBody_1(b, l + 1);
    r = r && enumBody_2(b, l + 1);
    r = r && enumBody_3(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, ENUM_BODY, r);
    return r;
  }

  // enumConstants?
  private static boolean enumBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBody_1")) return false;
    enumConstants(b, l + 1);
    return true;
  }

  // COMMA?
  private static boolean enumBody_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBody_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  // enumBodyDeclarations?
  private static boolean enumBody_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBody_3")) return false;
    enumBodyDeclarations(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // SEMI classBodyDeclaration*
  public static boolean enumBodyDeclarations(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBodyDeclarations")) return false;
    if (!nextTokenIs(b, SEMI)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMI);
    r = r && enumBodyDeclarations_1(b, l + 1);
    exit_section_(b, m, ENUM_BODY_DECLARATIONS, r);
    return r;
  }

  // classBodyDeclaration*
  private static boolean enumBodyDeclarations_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBodyDeclarations_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!classBodyDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumBodyDeclarations_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER (arguments)? (classBody)?
  public static boolean enumConstant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstant")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && enumConstant_1(b, l + 1);
    r = r && enumConstant_2(b, l + 1);
    exit_section_(b, m, ENUM_CONSTANT, r);
    return r;
  }

  // (arguments)?
  private static boolean enumConstant_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstant_1")) return false;
    enumConstant_1_0(b, l + 1);
    return true;
  }

  // (arguments)
  private static boolean enumConstant_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstant_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (classBody)?
  private static boolean enumConstant_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstant_2")) return false;
    enumConstant_2_0(b, l + 1);
    return true;
  }

  // (classBody)
  private static boolean enumConstant_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstant_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = classBody(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // enumConstant (COMMA enumConstant)*
  public static boolean enumConstants(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstants")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = enumConstant(b, l + 1);
    r = r && enumConstants_1(b, l + 1);
    exit_section_(b, m, ENUM_CONSTANTS, r);
    return r;
  }

  // (COMMA enumConstant)*
  private static boolean enumConstants_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstants_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!enumConstants_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumConstants_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA enumConstant
  private static boolean enumConstants_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumConstants_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && enumConstant(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // modifiers ENUM IDENTIFIER enumBody
  public static boolean enumDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<enum declaration>");
    r = modifiers(b, l + 1);
    r = r && consumeTokens(b, 0, ENUM, IDENTIFIER);
    r = r && enumBody(b, l + 1);
    exit_section_(b, l, m, ENUM_DECLARATION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // instanceOfExpression ((EQEQ | BANGEQ) instanceOfExpression)*
  public static boolean equalityExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<equality expression>");
    r = instanceOfExpression(b, l + 1);
    r = r && equalityExpression_1(b, l + 1);
    exit_section_(b, l, m, EQUALITY_EXPRESSION, r, false, null);
    return r;
  }

  // ((EQEQ | BANGEQ) instanceOfExpression)*
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

  // (EQEQ | BANGEQ) instanceOfExpression
  private static boolean equalityExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = equalityExpression_1_0_0(b, l + 1);
    r = r && instanceOfExpression(b, l + 1);
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
    Marker m = enter_section_(b, l, _NONE_, "<exclusive or expression>");
    r = andExpression(b, l + 1);
    r = r && exclusiveOrExpression_1(b, l + 1);
    exit_section_(b, l, m, EXCLUSIVE_OR_EXPRESSION, r, false, null);
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
  // SUPER arguments SEMI
  //                                 | primary DOT SUPER arguments SEMI
  public static boolean explicitConstructorInvocation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitConstructorInvocation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<explicit constructor invocation>");
    r = explicitConstructorInvocation_0(b, l + 1);
    if (!r) r = explicitConstructorInvocation_1(b, l + 1);
    exit_section_(b, l, m, EXPLICIT_CONSTRUCTOR_INVOCATION, r, false, null);
    return r;
  }

  // SUPER arguments SEMI
  private static boolean explicitConstructorInvocation_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitConstructorInvocation_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SUPER);
    r = r && arguments(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // primary DOT SUPER arguments SEMI
  private static boolean explicitConstructorInvocation_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitConstructorInvocation_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = primary(b, l + 1);
    r = r && consumeTokens(b, 0, DOT, SUPER);
    r = r && arguments(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // conditionalExpression (assignmentOperator expression)?
  public static boolean expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = conditionalExpression(b, l + 1);
    r = r && expression_1(b, l + 1);
    exit_section_(b, l, m, EXPRESSION, r, false, null);
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
    Marker m = enter_section_(b, l, _NONE_, "<expression list>");
    r = expression(b, l + 1);
    r = r && expressionList_1(b, l + 1);
    exit_section_(b, l, m, EXPRESSION_LIST, r, false, null);
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
  // modifiers VAR variableDeclarator
  //                     (COMMA variableDeclarator)* SEMI
  public static boolean fieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<field declaration>");
    r = modifiers(b, l + 1);
    r = r && consumeToken(b, VAR);
    r = r && variableDeclarator(b, l + 1);
    r = r && fieldDeclaration_3(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, l, m, FIELD_DECLARATION, r, false, null);
    return r;
  }

  // (COMMA variableDeclarator)*
  private static boolean fieldDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!fieldDeclaration_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fieldDeclaration_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA variableDeclarator
  private static boolean fieldDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && variableDeclarator(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // localVariableDeclaration| expressionList
  public static boolean forInit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forInit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<for init>");
    r = localVariableDeclaration(b, l + 1);
    if (!r) r = expressionList(b, l + 1);
    exit_section_(b, l, m, FOR_INIT, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // FOR LPAREN VAR IDENTIFIER COLON expression RPAREN statement
  //         // normal for loop
  //         | FOR LPAREN (forInit)? SEMI (expression)? SEMI (expressionList)? RPAREN statement
  public static boolean forStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = forStatement_0(b, l + 1);
    if (!r) r = forStatement_1(b, l + 1);
    exit_section_(b, m, FOR_STATEMENT, r);
    return r;
  }

  // FOR LPAREN VAR IDENTIFIER COLON expression RPAREN statement
  private static boolean forStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FOR, LPAREN, VAR, IDENTIFIER, COLON);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FOR LPAREN (forInit)? SEMI (expression)? SEMI (expressionList)? RPAREN statement
  private static boolean forStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FOR, LPAREN);
    r = r && forStatement_1_2(b, l + 1);
    r = r && consumeToken(b, SEMI);
    r = r && forStatement_1_4(b, l + 1);
    r = r && consumeToken(b, SEMI);
    r = r && forStatement_1_6(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (forInit)?
  private static boolean forStatement_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_1_2")) return false;
    forStatement_1_2_0(b, l + 1);
    return true;
  }

  // (forInit)
  private static boolean forStatement_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = forInit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (expression)?
  private static boolean forStatement_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_1_4")) return false;
    forStatement_1_4_0(b, l + 1);
    return true;
  }

  // (expression)
  private static boolean forStatement_1_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_1_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (expressionList)?
  private static boolean forStatement_1_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_1_6")) return false;
    forStatement_1_6_0(b, l + 1);
    return true;
  }

  // (expressionList)
  private static boolean forStatement_1_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_1_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expressionList(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // normalParameterDecl (COMMA normalParameterDecl)*
  public static boolean formalParameterDecls(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "formalParameterDecls")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = normalParameterDecl(b, l + 1);
    r = r && formalParameterDecls_1(b, l + 1);
    exit_section_(b, m, FORMAL_PARAMETER_DECLS, r);
    return r;
  }

  // (COMMA normalParameterDecl)*
  private static boolean formalParameterDecls_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "formalParameterDecls_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!formalParameterDecls_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "formalParameterDecls_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA normalParameterDecl
  private static boolean formalParameterDecls_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "formalParameterDecls_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && normalParameterDecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN (formalParameterDecls)? RPAREN
  public static boolean formalParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "formalParameters")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && formalParameters_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, FORMAL_PARAMETERS, r);
    return r;
  }

  // (formalParameterDecls)?
  private static boolean formalParameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "formalParameters_1")) return false;
    formalParameters_1_0(b, l + 1);
    return true;
  }

  // (formalParameterDecls)
  private static boolean formalParameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "formalParameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = formalParameterDecls(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FUNCTION IDENTIFIER formalParameters (THROWS qualifiedNameList)?
  //                         LBRACE (explicitConstructorInvocation)? (blockStatement)* RBRACE
  public static boolean functionDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDeclaration")) return false;
    if (!nextTokenIs(b, FUNCTION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FUNCTION, IDENTIFIER);
    r = r && formalParameters(b, l + 1);
    r = r && functionDeclaration_3(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && functionDeclaration_5(b, l + 1);
    r = r && functionDeclaration_6(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, FUNCTION_DECLARATION, r);
    return r;
  }

  // (THROWS qualifiedNameList)?
  private static boolean functionDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDeclaration_3")) return false;
    functionDeclaration_3_0(b, l + 1);
    return true;
  }

  // THROWS qualifiedNameList
  private static boolean functionDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDeclaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THROWS);
    r = r && qualifiedNameList(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (explicitConstructorInvocation)?
  private static boolean functionDeclaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDeclaration_5")) return false;
    functionDeclaration_5_0(b, l + 1);
    return true;
  }

  // (explicitConstructorInvocation)
  private static boolean functionDeclaration_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDeclaration_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = explicitConstructorInvocation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (blockStatement)*
  private static boolean functionDeclaration_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDeclaration_6")) return false;
    int c = current_position_(b);
    while (true) {
      if (!functionDeclaration_6_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "functionDeclaration_6", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (blockStatement)
  private static boolean functionDeclaration_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "functionDeclaration_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = blockStatement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (LBRACKET RBRACKET) + DOT CLASS
  //                    | (LBRACKET expression RBRACKET)+
  //                    | arguments
  //                    | DOT CLASS
  //                    | DOT IDENTIFIER arguments
  //                    | DOT SUPER arguments
  public static boolean identifierSuffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<identifier suffix>");
    r = identifierSuffix_0(b, l + 1);
    if (!r) r = identifierSuffix_1(b, l + 1);
    if (!r) r = arguments(b, l + 1);
    if (!r) r = parseTokens(b, 0, DOT, CLASS);
    if (!r) r = identifierSuffix_4(b, l + 1);
    if (!r) r = identifierSuffix_5(b, l + 1);
    exit_section_(b, l, m, IDENTIFIER_SUFFIX, r, false, null);
    return r;
  }

  // (LBRACKET RBRACKET) + DOT CLASS
  private static boolean identifierSuffix_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierSuffix_0_0(b, l + 1);
    r = r && consumeTokens(b, 0, DOT, CLASS);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACKET RBRACKET) +
  private static boolean identifierSuffix_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierSuffix_0_0_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!identifierSuffix_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "identifierSuffix_0_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // LBRACKET RBRACKET
  private static boolean identifierSuffix_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACKET expression RBRACKET)+
  private static boolean identifierSuffix_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierSuffix_1_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!identifierSuffix_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "identifierSuffix_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // LBRACKET expression RBRACKET
  private static boolean identifierSuffix_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && expression(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT IDENTIFIER arguments
  private static boolean identifierSuffix_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    r = r && arguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT SUPER arguments
  private static boolean identifierSuffix_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierSuffix_5")) return false;
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
    Marker m = enter_section_(b, l, _NONE_, "<inclusive or expression>");
    r = exclusiveOrExpression(b, l + 1);
    r = r && inclusiveOrExpression_1(b, l + 1);
    exit_section_(b, l, m, INCLUSIVE_OR_EXPRESSION, r, false, null);
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
  // relationalExpression (INSTANCEOF type)?
  public static boolean instanceOfExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instanceOfExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<instance of expression>");
    r = relationalExpression(b, l + 1);
    r = r && instanceOfExpression_1(b, l + 1);
    exit_section_(b, l, m, INSTANCE_OF_EXPRESSION, r, false, null);
    return r;
  }

  // (INSTANCEOF type)?
  private static boolean instanceOfExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instanceOfExpression_1")) return false;
    instanceOfExpression_1_0(b, l + 1);
    return true;
  }

  // INSTANCEOF type
  private static boolean instanceOfExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instanceOfExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSTANCEOF);
    r = r && type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // primary EQGT primary
  public static boolean keyValueInitializer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "keyValueInitializer")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<key value initializer>");
    r = primary(b, l + 1);
    r = r && consumeToken(b, EQGT);
    r = r && primary(b, l + 1);
    exit_section_(b, l, m, KEY_VALUE_INITIALIZER, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INTLITERAL
  //           | LONGLITERAL
  //           | FLOATLITERAL
  //           | DOUBLELITERAL
  //           | CHARLITERAL
  //           | STRINGLITERAL
  //           | TRUE
  //           | FALSE
  //           | NULL
  public static boolean literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<literal>");
    r = INTLITERAL(b, l + 1);
    if (!r) r = LONGLITERAL(b, l + 1);
    if (!r) r = FLOATLITERAL(b, l + 1);
    if (!r) r = DOUBLELITERAL(b, l + 1);
    if (!r) r = consumeToken(b, CHARLITERAL);
    if (!r) r = consumeToken(b, STRINGLITERAL);
    if (!r) r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    if (!r) r = consumeToken(b, NULL);
    exit_section_(b, l, m, LITERAL, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VAR variableDeclarator (COMMA variableDeclarator)*
  public static boolean localVariableDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "localVariableDeclaration")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VAR);
    r = r && variableDeclarator(b, l + 1);
    r = r && localVariableDeclaration_2(b, l + 1);
    exit_section_(b, m, LOCAL_VARIABLE_DECLARATION, r);
    return r;
  }

  // (COMMA variableDeclarator)*
  private static boolean localVariableDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "localVariableDeclaration_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!localVariableDeclaration_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "localVariableDeclaration_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA variableDeclarator
  private static boolean localVariableDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "localVariableDeclaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && variableDeclarator(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // localVariableDeclaration SEMI
  public static boolean localVariableDeclarationStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "localVariableDeclarationStatement")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = localVariableDeclaration(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, LOCAL_VARIABLE_DECLARATION_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // fieldDeclaration | functionDeclaration | classDeclaration
  public static boolean memberDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "memberDecl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<member decl>");
    r = fieldDeclaration(b, l + 1);
    if (!r) r = functionDeclaration(b, l + 1);
    if (!r) r = classDeclaration(b, l + 1);
    exit_section_(b, l, m, MEMBER_DECL, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // STATIC* HIDDEN?
  public static boolean modifiers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "modifiers")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<modifiers>");
    r = modifiers_0(b, l + 1);
    r = r && modifiers_1(b, l + 1);
    exit_section_(b, l, m, MODIFIERS, r, false, null);
    return r;
  }

  // STATIC*
  private static boolean modifiers_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "modifiers_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, STATIC)) break;
      if (!empty_element_parsed_guard_(b, "modifiers_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // HIDDEN?
  private static boolean modifiers_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "modifiers_1")) return false;
    consumeToken(b, HIDDEN);
    return true;
  }

  /* ********************************************************** */
  // compilationUnit
  static boolean monkeyCFile(PsiBuilder b, int l) {
    return compilationUnit(b, l + 1);
  }

  /* ********************************************************** */
  // unaryExpression ((STAR | SLASH | PERCENT) unaryExpression)*
  public static boolean multiplicativeExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiplicativeExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<multiplicative expression>");
    r = unaryExpression(b, l + 1);
    r = r && multiplicativeExpression_1(b, l + 1);
    exit_section_(b, l, m, MULTIPLICATIVE_EXPRESSION, r, false, null);
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
  // LBRACKET (variableInitializer (COMMA variableInitializer)*)? (COMMA)? RBRACKET
  public static boolean newArrayInitializer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newArrayInitializer")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && newArrayInitializer_1(b, l + 1);
    r = r && newArrayInitializer_2(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, NEW_ARRAY_INITIALIZER, r);
    return r;
  }

  // (variableInitializer (COMMA variableInitializer)*)?
  private static boolean newArrayInitializer_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newArrayInitializer_1")) return false;
    newArrayInitializer_1_0(b, l + 1);
    return true;
  }

  // variableInitializer (COMMA variableInitializer)*
  private static boolean newArrayInitializer_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newArrayInitializer_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variableInitializer(b, l + 1);
    r = r && newArrayInitializer_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA variableInitializer)*
  private static boolean newArrayInitializer_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newArrayInitializer_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!newArrayInitializer_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "newArrayInitializer_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA variableInitializer
  private static boolean newArrayInitializer_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newArrayInitializer_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && variableInitializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA)?
  private static boolean newArrayInitializer_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newArrayInitializer_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // LBRACE (keyValueInitializer (COMMA keyValueInitializer)*)? (COMMA)? RBRACE
  public static boolean newDictionaryInitializer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newDictionaryInitializer")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && newDictionaryInitializer_1(b, l + 1);
    r = r && newDictionaryInitializer_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, NEW_DICTIONARY_INITIALIZER, r);
    return r;
  }

  // (keyValueInitializer (COMMA keyValueInitializer)*)?
  private static boolean newDictionaryInitializer_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newDictionaryInitializer_1")) return false;
    newDictionaryInitializer_1_0(b, l + 1);
    return true;
  }

  // keyValueInitializer (COMMA keyValueInitializer)*
  private static boolean newDictionaryInitializer_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newDictionaryInitializer_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = keyValueInitializer(b, l + 1);
    r = r && newDictionaryInitializer_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA keyValueInitializer)*
  private static boolean newDictionaryInitializer_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newDictionaryInitializer_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!newDictionaryInitializer_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "newDictionaryInitializer_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA keyValueInitializer
  private static boolean newDictionaryInitializer_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newDictionaryInitializer_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && keyValueInitializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA)?
  private static boolean newDictionaryInitializer_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "newDictionaryInitializer_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // modifiers CLASS IDENTIFIER (EXTENDS type)? classBody
  public static boolean normalClassDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normalClassDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<normal class declaration>");
    r = modifiers(b, l + 1);
    r = r && consumeTokens(b, 0, CLASS, IDENTIFIER);
    r = r && normalClassDeclaration_3(b, l + 1);
    r = r && classBody(b, l + 1);
    exit_section_(b, l, m, NORMAL_CLASS_DECLARATION, r, false, null);
    return r;
  }

  // (EXTENDS type)?
  private static boolean normalClassDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normalClassDeclaration_3")) return false;
    normalClassDeclaration_3_0(b, l + 1);
    return true;
  }

  // EXTENDS type
  private static boolean normalClassDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normalClassDeclaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXTENDS);
    r = r && type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER (LBRACKET RBRACKET)*
  public static boolean normalParameterDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normalParameterDecl")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && normalParameterDecl_1(b, l + 1);
    exit_section_(b, m, NORMAL_PARAMETER_DECL, r);
    return r;
  }

  // (LBRACKET RBRACKET)*
  private static boolean normalParameterDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normalParameterDecl_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!normalParameterDecl_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "normalParameterDecl_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // LBRACKET RBRACKET
  private static boolean normalParameterDecl_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normalParameterDecl_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
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
  //           | IDENTIFIER (DOT IDENTIFIER)* (identifierSuffix)?
  //           | literal
  //           | symbol
  //           | creator
  //           | primitiveType (LBRACKET RBRACKET)* DOT CLASS
  //           | VOID DOT CLASS
  public static boolean primary(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<primary>");
    r = parExpression(b, l + 1);
    if (!r) r = primary_1(b, l + 1);
    if (!r) r = literal(b, l + 1);
    if (!r) r = symbol(b, l + 1);
    if (!r) r = creator(b, l + 1);
    if (!r) r = primary_5(b, l + 1);
    if (!r) r = parseTokens(b, 0, VOID, DOT, CLASS);
    exit_section_(b, l, m, PRIMARY, r, false, null);
    return r;
  }

  // IDENTIFIER (DOT IDENTIFIER)* (identifierSuffix)?
  private static boolean primary_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && primary_1_1(b, l + 1);
    r = r && primary_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (DOT IDENTIFIER)*
  private static boolean primary_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!primary_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "primary_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // DOT IDENTIFIER
  private static boolean primary_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // (identifierSuffix)?
  private static boolean primary_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_1_2")) return false;
    primary_1_2_0(b, l + 1);
    return true;
  }

  // (identifierSuffix)
  private static boolean primary_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierSuffix(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // primitiveType (LBRACKET RBRACKET)* DOT CLASS
  private static boolean primary_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = primitiveType(b, l + 1);
    r = r && primary_5_1(b, l + 1);
    r = r && consumeTokens(b, 0, DOT, CLASS);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACKET RBRACKET)*
  private static boolean primary_5_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_5_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!primary_5_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "primary_5_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // LBRACKET RBRACKET
  private static boolean primary_5_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primary_5_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BOOLEAN
  //                 | CHAR
  //                 | BYTE
  //                 | SHORT
  //                 | INT
  //                 | LONG
  //                 | FLOAT
  //                 | DOUBLE
  public static boolean primitiveType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primitiveType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<primitive type>");
    r = consumeToken(b, BOOLEAN);
    if (!r) r = consumeToken(b, CHAR);
    if (!r) r = consumeToken(b, BYTE);
    if (!r) r = consumeToken(b, SHORT);
    if (!r) r = consumeToken(b, INT);
    if (!r) r = consumeToken(b, LONG);
    if (!r) r = consumeToken(b, FLOAT);
    if (!r) r = consumeToken(b, DOUBLE);
    exit_section_(b, l, m, PRIMITIVE_TYPE, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER (DOT IDENTIFIER)*
  public static boolean qualifiedName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && qualifiedName_1(b, l + 1);
    exit_section_(b, m, QUALIFIED_NAME, r);
    return r;
  }

  // (DOT IDENTIFIER)*
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

  // DOT IDENTIFIER
  private static boolean qualifiedName_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // qualifiedName (COMMA qualifiedName)*
  public static boolean qualifiedNameList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedNameList")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = qualifiedName(b, l + 1);
    r = r && qualifiedNameList_1(b, l + 1);
    exit_section_(b, m, QUALIFIED_NAME_LIST, r);
    return r;
  }

  // (COMMA qualifiedName)*
  private static boolean qualifiedNameList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedNameList_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!qualifiedNameList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "qualifiedNameList_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA qualifiedName
  private static boolean qualifiedNameList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedNameList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && qualifiedName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // shiftExpression (relationalOp shiftExpression)*
  public static boolean relationalExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationalExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<relational expression>");
    r = shiftExpression(b, l + 1);
    r = r && relationalExpression_1(b, l + 1);
    exit_section_(b, l, m, RELATIONAL_EXPRESSION, r, false, null);
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
    Marker m = enter_section_(b, l, _NONE_, "<relational op>");
    r = parseTokens(b, 0, LT, EQ);
    if (!r) r = parseTokens(b, 0, GT, EQ);
    if (!r) r = consumeToken(b, LT);
    if (!r) r = consumeToken(b, GT);
    exit_section_(b, l, m, RELATIONAL_OP, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DOT IDENTIFIER (arguments)?
  //            | LBRACKET expression RBRACKET
  public static boolean selector(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector")) return false;
    if (!nextTokenIs(b, "<selector>", DOT, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<selector>");
    r = selector_0(b, l + 1);
    if (!r) r = selector_1(b, l + 1);
    exit_section_(b, l, m, SELECTOR, r, false, null);
    return r;
  }

  // DOT IDENTIFIER (arguments)?
  private static boolean selector_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    r = r && selector_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (arguments)?
  private static boolean selector_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_0_2")) return false;
    selector_0_2_0(b, l + 1);
    return true;
  }

  // (arguments)
  private static boolean selector_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selector_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
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
    Marker m = enter_section_(b, l, _NONE_, "<shift expression>");
    r = additiveExpression(b, l + 1);
    r = r && shiftExpression_1(b, l + 1);
    exit_section_(b, l, m, SHIFT_EXPRESSION, r, false, null);
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
    Marker m = enter_section_(b, l, _NONE_, "<shift op>");
    r = parseTokens(b, 0, LT, LT);
    if (!r) r = parseTokens(b, 0, GT, GT, GT);
    if (!r) r = parseTokens(b, 0, GT, GT);
    exit_section_(b, l, m, SHIFT_OP, r, false, null);
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
  //             | BREAK IDENTIFIER? SEMI
  //             | CONTINUE IDENTIFIER? SEMI
  //             | expression SEMI
  //             | IDENTIFIER COLON statement
  //             | SEMI
  public static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<statement>");
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
    exit_section_(b, l, m, STATEMENT, r, false, null);
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

  // BREAK IDENTIFIER? SEMI
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

  // IDENTIFIER?
  private static boolean statement_9_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_9_1")) return false;
    consumeToken(b, IDENTIFIER);
    return true;
  }

  // CONTINUE IDENTIFIER? SEMI
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

  // IDENTIFIER?
  private static boolean statement_10_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_10_1")) return false;
    consumeToken(b, IDENTIFIER);
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
    Marker m = enter_section_(b, l, _NONE_, "<switch block statement group>");
    r = switchLabel(b, l + 1);
    r = r && switchBlockStatementGroup_1(b, l + 1);
    exit_section_(b, l, m, SWITCH_BLOCK_STATEMENT_GROUP, r, false, null);
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
    Marker m = enter_section_(b, l, _NONE_, "<switch block statement groups>");
    int c = current_position_(b);
    while (true) {
      if (!switchBlockStatementGroups_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "switchBlockStatementGroups", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, SWITCH_BLOCK_STATEMENT_GROUPS, true, false, null);
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
    Marker m = enter_section_(b, l, _NONE_, "<switch label>");
    r = switchLabel_0(b, l + 1);
    if (!r) r = parseTokens(b, 0, DEFAULT, COLON);
    exit_section_(b, l, m, SWITCH_LABEL, r, false, null);
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
  // COLON IDENTIFIER
  public static boolean symbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "symbol")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COLON, IDENTIFIER);
    exit_section_(b, m, SYMBOL, r);
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
  // classOrInterfaceType (LBRACKET RBRACKET)*
  //        | primitiveType (LBRACKET RBRACKET)*
  public static boolean type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<type>");
    r = type_0(b, l + 1);
    if (!r) r = type_1(b, l + 1);
    exit_section_(b, l, m, TYPE, r, false, null);
    return r;
  }

  // classOrInterfaceType (LBRACKET RBRACKET)*
  private static boolean type_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = classOrInterfaceType(b, l + 1);
    r = r && type_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACKET RBRACKET)*
  private static boolean type_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!type_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "type_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // LBRACKET RBRACKET
  private static boolean type_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // primitiveType (LBRACKET RBRACKET)*
  private static boolean type_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = primitiveType(b, l + 1);
    r = r && type_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACKET RBRACKET)*
  private static boolean type_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!type_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "type_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // LBRACKET RBRACKET
  private static boolean type_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // classDeclaration | SEMI
  public static boolean typeDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<type declaration>");
    r = classDeclaration(b, l + 1);
    if (!r) r = consumeToken(b, SEMI);
    exit_section_(b, l, m, TYPE_DECLARATION, r, false, null);
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
    Marker m = enter_section_(b, l, _NONE_, "<unary expression>");
    r = unaryExpression_0(b, l + 1);
    if (!r) r = unaryExpression_1(b, l + 1);
    if (!r) r = unaryExpression_2(b, l + 1);
    if (!r) r = unaryExpression_3(b, l + 1);
    if (!r) r = unaryExpressionNotPlusMinus(b, l + 1);
    exit_section_(b, l, m, UNARY_EXPRESSION, r, false, null);
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
  //                               | BANG unaryExpression
  //                               | primary (selector)* (PLUSPLUS | SUBSUB)?
  public static boolean unaryExpressionNotPlusMinus(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpressionNotPlusMinus")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<unary expression not plus minus>");
    r = unaryExpressionNotPlusMinus_0(b, l + 1);
    if (!r) r = unaryExpressionNotPlusMinus_1(b, l + 1);
    if (!r) r = unaryExpressionNotPlusMinus_2(b, l + 1);
    exit_section_(b, l, m, UNARY_EXPRESSION_NOT_PLUS_MINUS, r, false, null);
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

  // primary (selector)* (PLUSPLUS | SUBSUB)?
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

  // (selector)*
  private static boolean unaryExpressionNotPlusMinus_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpressionNotPlusMinus_2_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!unaryExpressionNotPlusMinus_2_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "unaryExpressionNotPlusMinus_2_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (selector)
  private static boolean unaryExpressionNotPlusMinus_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpressionNotPlusMinus_2_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = selector(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
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
  // USING IDENTIFIER (DOT IDENTIFIER)+ (AS IDENTIFIER)? SEMI
  public static boolean usingDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingDeclaration")) return false;
    if (!nextTokenIs(b, USING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, USING, IDENTIFIER);
    r = r && usingDeclaration_2(b, l + 1);
    r = r && usingDeclaration_3(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, USING_DECLARATION, r);
    return r;
  }

  // (DOT IDENTIFIER)+
  private static boolean usingDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingDeclaration_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = usingDeclaration_2_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!usingDeclaration_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "usingDeclaration_2", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT IDENTIFIER
  private static boolean usingDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingDeclaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // (AS IDENTIFIER)?
  private static boolean usingDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingDeclaration_3")) return false;
    usingDeclaration_3_0(b, l + 1);
    return true;
  }

  // AS IDENTIFIER
  private static boolean usingDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingDeclaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AS, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER (LBRACKET RBRACKET)* (EQ variableInitializer)?
  public static boolean variableDeclarator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarator")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && variableDeclarator_1(b, l + 1);
    r = r && variableDeclarator_2(b, l + 1);
    exit_section_(b, m, VARIABLE_DECLARATOR, r);
    return r;
  }

  // (LBRACKET RBRACKET)*
  private static boolean variableDeclarator_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarator_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!variableDeclarator_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "variableDeclarator_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // LBRACKET RBRACKET
  private static boolean variableDeclarator_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarator_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EQ variableInitializer)?
  private static boolean variableDeclarator_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarator_2")) return false;
    variableDeclarator_2_0(b, l + 1);
    return true;
  }

  // EQ variableInitializer
  private static boolean variableDeclarator_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclarator_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQ);
    r = r && variableInitializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // arrayInitializer | expression
  public static boolean variableInitializer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableInitializer")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<variable initializer>");
    r = arrayInitializer(b, l + 1);
    if (!r) r = expression(b, l + 1);
    exit_section_(b, l, m, VARIABLE_INITIALIZER, r, false, null);
    return r;
  }

}

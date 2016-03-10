// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class MonkeyVisitor extends PsiElementVisitor {

  public void visitAdditiveExpression(@NotNull MonkeyAdditiveExpression o) {
    visitExpression(o);
  }

  public void visitAndExpression(@NotNull MonkeyAndExpression o) {
    visitExpression(o);
  }

  public void visitAnnotation(@NotNull MonkeyAnnotation o) {
    visitPsiCompositeElement(o);
  }

  public void visitArguments(@NotNull MonkeyArguments o) {
    visitPsiCompositeElement(o);
  }

  public void visitArrayCreator(@NotNull MonkeyArrayCreator o) {
    visitPsiCompositeElement(o);
  }

  public void visitArrayInitializer(@NotNull MonkeyArrayInitializer o) {
    visitPsiCompositeElement(o);
  }

  public void visitAssignmentOperator(@NotNull MonkeyAssignmentOperator o) {
    visitPsiCompositeElement(o);
  }

  public void visitBitwiseExpression(@NotNull MonkeyBitwiseExpression o) {
    visitExpression(o);
  }

  public void visitBitwiseOperator(@NotNull MonkeyBitwiseOperator o) {
    visitPsiCompositeElement(o);
  }

  public void visitBlock(@NotNull MonkeyBlock o) {
    visitPsiCompositeElement(o);
  }

  public void visitBlockStatement(@NotNull MonkeyBlockStatement o) {
    visitPsiCompositeElement(o);
  }

  public void visitCatchClause(@NotNull MonkeyCatchClause o) {
    visitPsiCompositeElement(o);
  }

  public void visitCatchParameter(@NotNull MonkeyCatchParameter o) {
    visitPsiCompositeElement(o);
  }

  public void visitCatches(@NotNull MonkeyCatches o) {
    visitPsiCompositeElement(o);
  }

  public void visitClassBody(@NotNull MonkeyClassBody o) {
    visitPsiCompositeElement(o);
  }

  public void visitClassBodyMembers(@NotNull MonkeyClassBodyMembers o) {
    visitExecutionScope(o);
  }

  public void visitClassCreatorRest(@NotNull MonkeyClassCreatorRest o) {
    visitPsiCompositeElement(o);
  }

  public void visitClassDeclaration(@NotNull MonkeyClassDeclaration o) {
    visitClass(o);
  }

  public void visitComponentName(@NotNull MonkeyComponentName o) {
    visitNamedElement(o);
  }

  public void visitConditionalAndExpression(@NotNull MonkeyConditionalAndExpression o) {
    visitExpression(o);
  }

  public void visitConditionalExpression(@NotNull MonkeyConditionalExpression o) {
    visitExpression(o);
  }

  public void visitConditionalOrExpression(@NotNull MonkeyConditionalOrExpression o) {
    visitExpression(o);
  }

  public void visitConstDeclaration(@NotNull MonkeyConstDeclaration o) {
    visitComponent(o);
  }

  public void visitCreator(@NotNull MonkeyCreator o) {
    visitPsiCompositeElement(o);
  }

  public void visitDictionaryCreator(@NotNull MonkeyDictionaryCreator o) {
    visitPsiCompositeElement(o);
  }

  public void visitEnumConstant(@NotNull MonkeyEnumConstant o) {
    visitComponent(o);
  }

  public void visitEnumDeclaration(@NotNull MonkeyEnumDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitEqualityExpression(@NotNull MonkeyEqualityExpression o) {
    visitExpression(o);
  }

  public void visitExclusiveOrExpression(@NotNull MonkeyExclusiveOrExpression o) {
    visitExpression(o);
  }

  public void visitExpression(@NotNull MonkeyExpression o) {
    visitPsiCompositeElement(o);
  }

  public void visitExpressionList(@NotNull MonkeyExpressionList o) {
    visitPsiCompositeElement(o);
  }

  public void visitFieldDeclaration(@NotNull MonkeyFieldDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitFieldDeclarationList(@NotNull MonkeyFieldDeclarationList o) {
    visitPsiCompositeElement(o);
  }

  public void visitForInit(@NotNull MonkeyForInit o) {
    visitPsiCompositeElement(o);
  }

  public void visitForStatement(@NotNull MonkeyForStatement o) {
    visitPsiCompositeElement(o);
  }

  public void visitFormalParameterDeclarations(@NotNull MonkeyFormalParameterDeclarations o) {
    visitPsiCompositeElement(o);
  }

  public void visitFunctionDeclaration(@NotNull MonkeyFunctionDeclaration o) {
    visitComponent(o);
  }

  public void visitHasExpression(@NotNull MonkeyHasExpression o) {
    visitExpression(o);
  }

  public void visitId(@NotNull MonkeyId o) {
    visitPsiCompositeElement(o);
  }

  public void visitIdentifierSuffix(@NotNull MonkeyIdentifierSuffix o) {
    visitPsiCompositeElement(o);
  }

  public void visitInclusiveOrExpression(@NotNull MonkeyInclusiveOrExpression o) {
    visitExpression(o);
  }

  public void visitInstanceOfExpression(@NotNull MonkeyInstanceOfExpression o) {
    visitExpression(o);
  }

  public void visitKeyValueInitializer(@NotNull MonkeyKeyValueInitializer o) {
    visitPsiCompositeElement(o);
  }

  public void visitLiteral(@NotNull MonkeyLiteral o) {
    visitPsiCompositeElement(o);
  }

  public void visitModifiers(@NotNull MonkeyModifiers o) {
    visitPsiCompositeElement(o);
  }

  public void visitModuleBody(@NotNull MonkeyModuleBody o) {
    visitPsiCompositeElement(o);
  }

  public void visitModuleBodyMembers(@NotNull MonkeyModuleBodyMembers o) {
    visitExecutionScope(o);
  }

  public void visitModuleDeclaration(@NotNull MonkeyModuleDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitMultiplicativeExpression(@NotNull MonkeyMultiplicativeExpression o) {
    visitExpression(o);
  }

  public void visitObjectCreator(@NotNull MonkeyObjectCreator o) {
    visitPsiCompositeElement(o);
  }

  public void visitParExpression(@NotNull MonkeyParExpression o) {
    visitExpression(o);
  }

  public void visitQualifiedName(@NotNull MonkeyQualifiedName o) {
    visitPsiCompositeElement(o);
  }

  public void visitReferenceExpression(@NotNull MonkeyReferenceExpression o) {
    visitExpression(o);
    // visitReference(o);
  }

  public void visitRelationalExpression(@NotNull MonkeyRelationalExpression o) {
    visitExpression(o);
  }

  public void visitRelationalOp(@NotNull MonkeyRelationalOp o) {
    visitPsiCompositeElement(o);
  }

  public void visitSelector(@NotNull MonkeySelector o) {
    visitPsiCompositeElement(o);
  }

  public void visitShiftExpression(@NotNull MonkeyShiftExpression o) {
    visitExpression(o);
  }

  public void visitShiftOp(@NotNull MonkeyShiftOp o) {
    visitPsiCompositeElement(o);
  }

  public void visitStatement(@NotNull MonkeyStatement o) {
    visitPsiCompositeElement(o);
  }

  public void visitSwitchBlockStatementGroup(@NotNull MonkeySwitchBlockStatementGroup o) {
    visitPsiCompositeElement(o);
  }

  public void visitSwitchBlockStatementGroups(@NotNull MonkeySwitchBlockStatementGroups o) {
    visitPsiCompositeElement(o);
  }

  public void visitSwitchLabel(@NotNull MonkeySwitchLabel o) {
    visitPsiCompositeElement(o);
  }

  public void visitSymbol(@NotNull MonkeySymbol o) {
    visitPsiCompositeElement(o);
  }

  public void visitTryStatement(@NotNull MonkeyTryStatement o) {
    visitPsiCompositeElement(o);
  }

  public void visitUnaryExpression(@NotNull MonkeyUnaryExpression o) {
    visitExpression(o);
  }

  public void visitUsingDeclaration(@NotNull MonkeyUsingDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitVariableDeclaration(@NotNull MonkeyVariableDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitVariableDeclarationList(@NotNull MonkeyVariableDeclarationList o) {
    visitPsiCompositeElement(o);
  }

  public void visitVariableInitializer(@NotNull MonkeyVariableInitializer o) {
    visitPsiCompositeElement(o);
  }

  public void visitClass(@NotNull MonkeyClass o) {
    visitPsiCompositeElement(o);
  }

  public void visitComponent(@NotNull MonkeyComponent o) {
    visitPsiCompositeElement(o);
  }

  public void visitExecutionScope(@NotNull MonkeyExecutionScope o) {
    visitPsiCompositeElement(o);
  }

  public void visitNamedElement(@NotNull MonkeyNamedElement o) {
    visitPsiCompositeElement(o);
  }

  public void visitPsiCompositeElement(@NotNull MonkeyPsiCompositeElement o) {
    visitElement(o);
  }

}

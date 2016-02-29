// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class MonkeyVisitor extends PsiElementVisitor {

  public void visitAnnotation(@NotNull MonkeyAnnotation o) {
    visitPsiCompositeElement(o);
  }

  public void visitClassBody(@NotNull MonkeyClassBody o) {
    visitPsiCompositeElement(o);
  }

  public void visitClassBodyMembers(@NotNull MonkeyClassBodyMembers o) {
    visitExecutionScope(o);
  }

  public void visitClassDeclaration(@NotNull MonkeyClassDeclaration o) {
    visitClass(o);
  }

  public void visitComponentName(@NotNull MonkeyComponentName o) {
    visitNamedElement(o);
  }

  public void visitExpression(@NotNull MonkeyExpression o) {
    visitPsiCompositeElement(o);
  }

  public void visitFieldDeclaration(@NotNull MonkeyFieldDeclaration o) {
    visitComponent(o);
  }

  public void visitId(@NotNull MonkeyId o) {
    visitPsiCompositeElement(o);
  }

  public void visitLiteralExpression(@NotNull MonkeyLiteralExpression o) {
    visitExpression(o);
  }

  public void visitModifiers(@NotNull MonkeyModifiers o) {
    visitPsiCompositeElement(o);
  }

  public void visitQualifiedName(@NotNull MonkeyQualifiedName o) {
    visitPsiCompositeElement(o);
  }

  public void visitReferenceExpression(@NotNull MonkeyReferenceExpression o) {
    visitExpression(o);
    // visitReference(o);
  }

  public void visitSymbol(@NotNull MonkeySymbol o) {
    visitPsiCompositeElement(o);
  }

  public void visitUsingDeclaration(@NotNull MonkeyUsingDeclaration o) {
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

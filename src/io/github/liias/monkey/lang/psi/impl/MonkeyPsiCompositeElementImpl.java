package io.github.liias.monkey.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.tree.IElementType;
import gnu.trove.THashSet;
import io.github.liias.monkey.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class MonkeyPsiCompositeElementImpl extends ASTWrapperPsiElement implements MonkeyPsiCompositeElement {

  public MonkeyPsiCompositeElementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public IElementType getTokenType() {
    return getNode().getElementType();
  }

  @Override
  public String toString() {
    return getTokenType().toString();
  }

  @Override
  public boolean processDeclarations(@NotNull PsiScopeProcessor processor,
                                     @NotNull ResolveState state,
                                     PsiElement lastParent,
                                     @NotNull PsiElement place) {
    return processDeclarationsImpl(this, processor, state)
        && super.processDeclarations(processor, state, lastParent, place);
  }

  public static boolean processDeclarationsImpl(@Nullable PsiElement context,
                                                PsiScopeProcessor processor,
                                                ResolveState state) {
    if (context == null) {
      return true;
    }
    for (MonkeyComponentName element : getDeclarationElementToProcess(context)) {
      if (!processor.execute(element, state)) {
        return false;
      }
    }
    return true;
  }

  private static Set<MonkeyComponentName> getDeclarationElementToProcess(@NotNull PsiElement context) {
    final Set<MonkeyComponentName> result = new THashSet<>();
    for (PsiElement child : context.getChildren()) {

      if (child instanceof MonkeyEnumDeclaration) {
        MonkeyEnumDeclaration enumDeclaration = (MonkeyEnumDeclaration) child;
        List<MonkeyEnumConstant> enumConstantList = enumDeclaration.getEnumConstantList();
        for (MonkeyEnumConstant monkeyEnumConstant : enumConstantList) {
          result.add(monkeyEnumConstant.getComponentName());
        }
      }
      if (child instanceof MonkeyFieldDeclarationList) {
        MonkeyFieldDeclarationList monkeyFieldDeclarationList = (MonkeyFieldDeclarationList) child;
        for (MonkeyFieldDeclaration fieldDeclaration : monkeyFieldDeclarationList.getFieldDeclarationList()) {
          result.add(fieldDeclaration.getComponentName());
        }
      }

      if (child instanceof MonkeyFormalParameterDeclarations) {
        MonkeyFormalParameterDeclarations monkeyFormalParameterDeclarations = (MonkeyFormalParameterDeclarations) child;
        for (MonkeyComponentName monkeyComponentName : monkeyFormalParameterDeclarations.getComponentNameList()) {
          result.add(monkeyComponentName);
        }
      }

      // TODO: there must be some other way...
      if (child instanceof MonkeyBlock) {
        MonkeyBlock monkeyBlock = (MonkeyBlock) child;
        List<MonkeyBlockStatement> blockStatementList = monkeyBlock.getBlockStatementList();
        for (MonkeyBlockStatement monkeyBlockStatement : blockStatementList) {
          MonkeyVariableDeclarationList variableDeclarationList = monkeyBlockStatement.getVariableDeclarationList();
          if (variableDeclarationList != null) {
            for (MonkeyVariableDeclaration monkeyVariableDeclaration : variableDeclarationList.getVariableDeclarationList()) {
              result.add((monkeyVariableDeclaration).getComponentName());
            }
          }
        }
      }

      if (child instanceof MonkeyComponent) {
        MonkeyComponent monkeyComponent = (MonkeyComponent) child;
        result.add(monkeyComponent.getComponentName());
      }
    }
    return result;
  }
}
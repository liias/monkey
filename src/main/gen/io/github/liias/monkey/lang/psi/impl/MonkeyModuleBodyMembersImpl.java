// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.liias.monkey.lang.psi.MonkeyTypes.*;
import io.github.liias.monkey.lang.psi.*;

public class MonkeyModuleBodyMembersImpl extends MonkeyPsiCompositeElementImpl implements MonkeyModuleBodyMembers {

  public MonkeyModuleBodyMembersImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MonkeyVisitor visitor) {
    visitor.visitModuleBodyMembers(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyVisitor) accept((MonkeyVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MonkeyClassDeclaration> getClassDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyClassDeclaration.class);
  }

  @Override
  @NotNull
  public List<MonkeyConstDeclaration> getConstDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyConstDeclaration.class);
  }

  @Override
  @NotNull
  public List<MonkeyEnumDeclaration> getEnumDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyEnumDeclaration.class);
  }

  @Override
  @NotNull
  public List<MonkeyFieldDeclarationList> getFieldDeclarationListList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyFieldDeclarationList.class);
  }

  @Override
  @NotNull
  public List<MonkeyFunctionDeclaration> getFunctionDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyFunctionDeclaration.class);
  }

  @Override
  @NotNull
  public List<MonkeyModuleDeclaration> getModuleDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyModuleDeclaration.class);
  }

}

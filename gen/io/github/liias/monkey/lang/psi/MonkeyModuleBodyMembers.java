// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyModuleBodyMembers extends MonkeyExecutionScope {

  @NotNull
  List<MonkeyClassDeclaration> getClassDeclarationList();

  @NotNull
  List<MonkeyConstDeclaration> getConstDeclarationList();

  @NotNull
  List<MonkeyEnumDeclaration> getEnumDeclarationList();

  @NotNull
  List<MonkeyFieldDeclarationList> getFieldDeclarationListList();

  @NotNull
  List<MonkeyFunctionDeclaration> getFunctionDeclarationList();

  @NotNull
  List<MonkeyModuleDeclaration> getModuleDeclarationList();

}

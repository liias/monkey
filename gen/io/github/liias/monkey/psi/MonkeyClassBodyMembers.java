// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyClassBodyMembers extends MonkeyExecutionScope {

  @NotNull
  List<MonkeyClassDeclaration> getClassDeclarationList();

  @NotNull
  List<MonkeyConstDeclaration> getConstDeclarationList();

  @NotNull
  List<MonkeyFieldDeclaration> getFieldDeclarationList();

  @NotNull
  List<MonkeyFunctionDeclaration> getFunctionDeclarationList();

}

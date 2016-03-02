// This is a generated file. Not intended for manual editing.
package io.github.liias.monkey.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyEnumDeclaration extends MonkeyPsiCompositeElement {

  @NotNull
  List<MonkeyEnumConstant> getEnumConstantList();

  @NotNull
  MonkeyModifiers getModifiers();

}

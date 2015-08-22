// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyCFunctionDeclaration extends MonkeyCPsiCompositeElement {

  @NotNull
  List<MonkeyCBlockStatement> getBlockStatementList();

  @Nullable
  MonkeyCExplicitConstructorInvocation getExplicitConstructorInvocation();

  @NotNull
  MonkeyCFormalParameters getFormalParameters();

  @NotNull
  MonkeyCModifiers getModifiers();

  @Nullable
  MonkeyCQualifiedNameList getQualifiedNameList();

  @NotNull
  PsiElement getIdentifier();

}

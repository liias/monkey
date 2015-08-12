// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyCVariableDeclarator extends MonkeyCNamedElement {

  @Nullable
  MonkeyCVariableInitializer getVariableInitializer();

  @NotNull
  PsiElement getIdentifier();

  String getKey();

  String getValue();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}

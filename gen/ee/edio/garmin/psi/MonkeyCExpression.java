// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyCExpression extends MonkeyCPsiCompositeElement {

  @Nullable
  MonkeyCAssignmentOperator getAssignmentOperator();

  @NotNull
  MonkeyCConditionalExpression getConditionalExpression();

  @Nullable
  MonkeyCExpression getExpression();

}

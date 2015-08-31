// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyExpression extends MonkeyPsiCompositeElement {

  @Nullable
  MonkeyAssignmentOperator getAssignmentOperator();

  @NotNull
  MonkeyConditionalExpression getConditionalExpression();

  @Nullable
  MonkeyExpression getExpression();

}

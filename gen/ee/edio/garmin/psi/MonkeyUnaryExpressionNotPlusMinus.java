// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyUnaryExpressionNotPlusMinus extends MonkeyPsiCompositeElement {

  @Nullable
  MonkeyPrimary getPrimary();

  @NotNull
  List<MonkeySelector> getSelectorList();

  @Nullable
  MonkeyUnaryExpression getUnaryExpression();

}

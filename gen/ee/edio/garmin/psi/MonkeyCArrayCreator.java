// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyCArrayCreator extends MonkeyCPsiCompositeElement {

  @Nullable
  MonkeyCArrayInitializer getArrayInitializer();

  @Nullable
  MonkeyCCreatedName getCreatedName();

  @NotNull
  List<MonkeyCExpression> getExpressionList();

  @Nullable
  MonkeyCNewArrayInitializer getNewArrayInitializer();

  @Nullable
  MonkeyCNewDictionaryInitializer getNewDictionaryInitializer();

}

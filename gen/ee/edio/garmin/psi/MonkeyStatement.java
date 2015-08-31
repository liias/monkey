// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyStatement extends MonkeyPsiCompositeElement {

  @Nullable
  MonkeyBlock getBlock();

  @Nullable
  MonkeyExpression getExpression();

  @Nullable
  MonkeyForStatement getForStatement();

  @Nullable
  MonkeyParExpression getParExpression();

  @NotNull
  List<MonkeyStatement> getStatementList();

  @Nullable
  MonkeySwitchBlockStatementGroups getSwitchBlockStatementGroups();

  @Nullable
  MonkeyTryStatement getTryStatement();

  @Nullable
  PsiElement getIdentifier();

}

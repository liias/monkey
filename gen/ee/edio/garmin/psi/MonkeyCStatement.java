// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MonkeyCStatement extends MonkeyCPsiCompositeElement {

  @Nullable
  MonkeyCBlock getBlock();

  @Nullable
  MonkeyCExpression getExpression();

  @Nullable
  MonkeyCForStatement getForStatement();

  @Nullable
  MonkeyCParExpression getParExpression();

  @NotNull
  List<MonkeyCStatement> getStatementList();

  @Nullable
  MonkeyCSwitchBlockStatementGroups getSwitchBlockStatementGroups();

  @Nullable
  MonkeyCTryStatement getTryStatement();

  @Nullable
  PsiElement getIdentifier();

}

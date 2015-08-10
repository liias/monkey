// This is a generated file. Not intended for manual editing.
package ee.edio.garmin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ee.edio.garmin.psi.MonkeyCTypes.*;
import ee.edio.garmin.psi.*;

public class MonkeyCSwitchBlockStatementGroupImpl extends MonkeyCPsiCompositeElementImpl implements MonkeyCSwitchBlockStatementGroup {

  public MonkeyCSwitchBlockStatementGroupImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MonkeyCVisitor) ((MonkeyCVisitor)visitor).visitSwitchBlockStatementGroup(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MonkeyCBlockStatement> getBlockStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MonkeyCBlockStatement.class);
  }

  @Override
  @NotNull
  public MonkeyCSwitchLabel getSwitchLabel() {
    return findNotNullChildByClass(MonkeyCSwitchLabel.class);
  }

}

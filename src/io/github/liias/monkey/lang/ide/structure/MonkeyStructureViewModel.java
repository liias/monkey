package io.github.liias.monkey.lang.ide.structure;

import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import io.github.liias.monkey.lang.psi.MonkeyClass;
import io.github.liias.monkey.lang.psi.MonkeyComponent;
import io.github.liias.monkey.lang.psi.MonkeyFieldDeclaration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyStructureViewModel extends StructureViewModelBase implements StructureViewModelBase.ElementInfoProvider {
  public MonkeyStructureViewModel(@NotNull PsiFile psiFile, @Nullable Editor editor) {
    super(psiFile, editor, new MonkeyStructureViewElement(psiFile));
    // order matters, first elements are compared first when walking up parents in AST:
    withSuitableClasses(MonkeyFieldDeclaration.class, MonkeyClass.class);
  }

  @Override
  public boolean shouldEnterElement(Object element) {
    return element instanceof MonkeyClass;
  }

  @Override
  public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
    return false;
  }

  @Override
  public boolean isAlwaysLeaf(StructureViewTreeElement element) {
    final Object value = element.getValue();
    return value instanceof MonkeyComponent && !(value instanceof MonkeyClass);
  }
}

package io.github.liias.monkey.ide.structure;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyStructureViewFactory implements PsiStructureViewFactory {
  @Override
  public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
    return new TreeBasedStructureViewBuilder() {
      @Override
      @NotNull
      public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
        return new MonkeyStructureViewModel(psiFile, editor);
      }

      @Override
      public boolean isRootNodeShown() {
        return false;
      }
    };
  }
}

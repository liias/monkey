package ee.edio.garmin.structure;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import ee.edio.garmin.psi.MonkeyFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyStructureViewBuilderFactory implements PsiStructureViewFactory {
  @Nullable
  @Override
  public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
    if (!(psiFile instanceof MonkeyFile)) return null;
    return new TreeBasedStructureViewBuilder() {
      @Override
      @NotNull
      public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
        final MonkeyFileTreeElement monkeyFileTreeElement = new MonkeyFileTreeElement(psiFile);

        final StructureViewModelBase structureViewModelBase = new StructureViewModelBase(psiFile, editor, monkeyFileTreeElement);
        return structureViewModelBase;
        //return new JavaFileTreeModel((PsiJavaFile)psiFile, editor);
      }

      @Override
      public boolean isRootNodeShown() {
        return false;
      }
    };
  }
}

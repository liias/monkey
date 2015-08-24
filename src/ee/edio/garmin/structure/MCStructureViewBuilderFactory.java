package ee.edio.garmin.structure;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import ee.edio.garmin.psi.MonkeyCFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MCStructureViewBuilderFactory implements PsiStructureViewFactory {
  @Nullable
  @Override
  public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
    if (!(psiFile instanceof MonkeyCFile)) return null;
    return new TreeBasedStructureViewBuilder() {
      @Override
      @NotNull
      public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
        final MCFileTreeElement mcFileTreeElement = new MCFileTreeElement(psiFile);

        final StructureViewModelBase structureViewModelBase = new StructureViewModelBase(psiFile, editor, mcFileTreeElement);
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

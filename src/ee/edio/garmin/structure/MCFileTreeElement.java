package ee.edio.garmin.structure;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;


public class MCFileTreeElement extends PsiTreeElementBase<PsiFile> implements ItemPresentation {
  public MCFileTreeElement(PsiFile file) {
    super(file);
  }

  @Override
  public String getPresentableText() {
    return getElement().getName();
  }

  @Override
  @NotNull
  public Collection<StructureViewTreeElement> getChildrenBase() {
    //PsiClass[] classes = getElement().getClasses();
    ArrayList<StructureViewTreeElement> result = new ArrayList<>();
/*    for (PsiClass aClass : classes) {
      result.add(new JavaClassTreeElement(aClass, false, new HashSet<PsiClass>()));
    }*/
    return result;

  }
}


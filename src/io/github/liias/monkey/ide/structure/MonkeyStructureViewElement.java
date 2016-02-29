package io.github.liias.monkey.ide.structure;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import gnu.trove.THashSet;
import io.github.liias.monkey.psi.*;
import io.github.liias.monkey.psi.impl.MonkeyPsiCompositeElementImpl;
import io.github.liias.monkey.resolve.ComponentNameScopeProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MonkeyStructureViewElement extends PsiTreeElementBase<NavigatablePsiElement> {
  protected MonkeyStructureViewElement(@NotNull NavigatablePsiElement psiElement) {
    super(psiElement);
  }

  @NotNull
  @Override
  public Collection<StructureViewTreeElement> getChildrenBase() {
    NavigatablePsiElement element = getElement();
    List<StructureViewTreeElement> result = new ArrayList<>();

    if (element instanceof MonkeyFile) {
      THashSet<MonkeyComponentName> componentNames = new THashSet<>();
      MonkeyPsiCompositeElementImpl.processDeclarationsImpl(element, new ComponentNameScopeProcessor(componentNames), ResolveState.initial());
      for (MonkeyComponentName componentName : componentNames) {
        PsiElement parent = componentName.getParent();
        if (parent instanceof MonkeyComponent) {
          result.add(new MonkeyStructureViewElement((MonkeyComponent) parent));
        }
      }
    } else if (element instanceof MonkeyClass) {
      for (MonkeyComponent subNamedComponent : getNamedSubComponents((MonkeyClass) element)) {
        result.add(new MonkeyStructureViewElement(subNamedComponent));
      }
    }

    return result;
  }

  @NotNull
  private static List<MonkeyComponent> getNamedSubComponents(MonkeyClass monkeyClass) {
    PsiElement classBodyMembers = monkeyClass.getBodyMembers();

    List<MonkeyComponent> result = new ArrayList<>();
    if (classBodyMembers == null) {
      return result;
    }
    MonkeyComponent[] namedComponents = PsiTreeUtil.getChildrenOfType(classBodyMembers, MonkeyComponent.class);
    if (namedComponents != null) {
      ContainerUtil.addAll(result, namedComponents);
    }
    return result;
  }

  @Nullable
  private static MonkeyClassBodyMembers getBody(@Nullable final MonkeyClass monkeyClass) {
    final MonkeyClassBody body = monkeyClass instanceof MonkeyClassDeclaration ? ((MonkeyClassDeclaration) monkeyClass).getClassBody() : null;
    return body == null ? null : body.getClassBodyMembers();
  }

  @Nullable
  @Override
  public String getPresentableText() {
    NavigatablePsiElement element = getElement();
    ItemPresentation presentation = element == null ? null : element.getPresentation();
    return presentation == null ? null : presentation.getPresentableText();
  }
}

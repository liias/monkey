package ee.edio.garmin.psi.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.UnfairTextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.ContainerUtil;
import ee.edio.garmin.MonkeyUtil;
import ee.edio.garmin.psi.MonkeyId;
import ee.edio.garmin.psi.MonkeyReference;
import ee.edio.garmin.util.MonkeyElementGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MonkeyReferenceImpl extends MonkeyExpressionImpl implements MonkeyReference, PsiPolyVariantReference {
  public MonkeyReferenceImpl(ASTNode node) {
    super(node);
  }

  @Override
  public PsiElement getElement() {
    return this;
  }

  @Override
  public PsiReference getReference() {
    return this;
  }

  @Override
  public TextRange getRangeInElement() {
    final TextRange textRange = getTextRange();

    //MonkeyReference[] monkeyReferences = PsiTreeUtil.getChildrenOfType(this, MonkeyReference.class);
    final MonkeyReference[] monkeyReferences = findReferences();
    if (monkeyReferences != null && monkeyReferences.length > 0) {
      TextRange lastReferenceRange = monkeyReferences[monkeyReferences.length - 1].getTextRange();
      return new UnfairTextRange(
          lastReferenceRange.getStartOffset() - textRange.getStartOffset(),
          lastReferenceRange.getEndOffset() - textRange.getEndOffset()
      );
    }

    return new UnfairTextRange(0, textRange.getEndOffset() - textRange.getStartOffset());
  }

  @NotNull
  @Override
  public String getCanonicalText() {
    return getText();
  }

  @Override
  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
    PsiElement element = this;
    if (getText().indexOf('.') != -1) {
      // libPrefix.name
      final PsiElement lastChild = getLastChild();
      element = lastChild == null ? this : lastChild;
    }
    final MonkeyId identifier = PsiTreeUtil.getChildOfType(element, MonkeyId.class);
    final MonkeyId identifierNew = MonkeyElementGenerator.createIdentifierFromText(getProject(), newElementName);
    if (identifier != null && identifierNew != null) {
      element.getNode().replaceChild(identifier.getNode(), identifierNew.getNode());
    }
    return this;
  }

  @Override
  public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
    return this;
  }


  private MonkeyReference[] findReferences() {
    return PsiTreeUtil.getChildrenOfType(this.getParent(), MonkeyReference.class);
  }

  @Override
  public boolean isReferenceTo(PsiElement element) {
    final MonkeyReference[] references = findReferences();
    final boolean chain = references != null && references.length == 2;
    if (chain) {
      return false;
    }
    return true;
    //final PsiElement target = resolve();
/*    if (element.getParent() instanceof MonkeyClass &&
        target != null &&
        MonkeyComponentType.typeOf(target.getParent()) == MonkeyComponentType.CONSTRUCTOR) {
      return true;
    }*/
    //return target == element;
  }

  @Override
  public boolean isSoft() {
    return false;
  }

  @Override
  public PsiElement resolve() {
    ResolveResult[] resolveResults = multiResolve(false);
    return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
/*    final ResolveResult[] resolveResults = multiResolve(true);
    return resolveResults.length == 0 ||
        resolveResults.length > 1 ||
        !resolveResults[0].isValidResult() ? null : resolveResults[0].getElement();*/
  }

  @NotNull
  @Override
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    Project project = getProject();
    final List<? extends PsiElement> properties = MonkeyUtil.findReferences(project, this);
    List<ResolveResult> results = new ArrayList<>();
    for (PsiElement property : properties) {
      results.add(new PsiElementResolveResult(property));
    }
    return results.toArray(new ResolveResult[results.size()]);


/*    final List<? extends PsiElement> elements = new ArrayList<>();
    *//*final List<? extends PsiElement> elements =
        ResolveCache.getInstance(getProject())
            .resolveWithCaching(this, MonkeyResolver.INSTANCE, true, incompleteCode);*//*
    return toCandidateInfoArray(elements);*/
  }

  private static ResolveResult[] toCandidateInfoArray(@Nullable List<? extends PsiElement> elements) {
    if (elements == null) {
      return ResolveResult.EMPTY_ARRAY;
    }
    elements = ContainerUtil.filter(elements, new Condition<PsiElement>() {
      @Override
      public boolean value(PsiElement element) {
        return element != null;
      }
    });
    final ResolveResult[] result = new ResolveResult[elements.size()];
    for (int i = 0, size = elements.size(); i < size; i++) {
      result[i] = new PsiElementResolveResult(elements.get(i));
    }
    return result;
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    return LookupElement.EMPTY_ARRAY;
  }
}

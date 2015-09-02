package ee.edio.garmin.psi.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.UnfairTextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.ContainerUtil;
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

    MonkeyReference[] MonkeyReferences = PsiTreeUtil.getChildrenOfType(this, MonkeyReference.class);
    if (MonkeyReferences != null && MonkeyReferences.length > 0) {
      TextRange lastReferenceRange = MonkeyReferences[MonkeyReferences.length - 1].getTextRange();
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

  @Override
  public boolean isReferenceTo(PsiElement element) {
    final MonkeyReference[] references = PsiTreeUtil.getChildrenOfType(this, MonkeyReference.class);
    final boolean chain = references != null && references.length == 2;
    if (chain) {
      return false;
    }
    final PsiElement target = resolve();
/*    if (element.getParent() instanceof MonkeyClass &&
        target != null &&
        MonkeyComponentType.typeOf(target.getParent()) == MonkeyComponentType.CONSTRUCTOR) {
      return true;
    }*/
    return target == element;
  }

  @Override
  public boolean isSoft() {
    return false;
  }

  @Override
  public PsiElement resolve() {
    final ResolveResult[] resolveResults = multiResolve(true);

    return resolveResults.length == 0 ||
        resolveResults.length > 1 ||
        !resolveResults[0].isValidResult() ? null : resolveResults[0].getElement();
  }

  @NotNull
  @Override
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    final List<? extends PsiElement> elements = new ArrayList<>();
    /*final List<? extends PsiElement> elements =
        ResolveCache.getInstance(getProject())
            .resolveWithCaching(this, MonkeyResolver.INSTANCE, true, incompleteCode);*/
    return toCandidateInfoArray(elements);
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

  /*@NotNull
  @Override
  public MonkeyClassResolveResult resolveMonkeyClass() {
    if (this instanceof MonkeySuperExpression) {
      final MonkeyClass MonkeyClass = PsiTreeUtil.getParentOfType(this, MonkeyClass.class);
      return MonkeyClass == null ? MonkeyClassResolveResult.EMPTY : MonkeyClass.getSuperClassResolvedOrObjectClass();
    }

    if (this instanceof MonkeyNewExpression) {
      final MonkeyClassResolveResult result = MonkeyResolveUtil.resolveClassByType(PsiTreeUtil.getChildOfType(this, MonkeyType.class));
      result.specialize(this);
      return result;
    }

    if (this instanceof MonkeyCallExpression) {
      final MonkeyExpression expression = ((MonkeyCallExpression)this).getExpression();
      final MonkeyClassResolveResult leftResult = tryGetLeftResolveResult(expression);
      if (expression instanceof MonkeyReference) {
        final MonkeyClassResolveResult result =
            MonkeyResolveUtil.getMonkeyClassResolveResult(((MonkeyReference) expression).resolve(), leftResult.getSpecialization());
        result.specialize(this);
        return result;
      }
    }

    if (this instanceof MonkeyCascadeReferenceExpression) {
      PsiElement parent = this.getParent();
      if (parent instanceof MonkeyValueExpression) {
        final List<MonkeyExpression> expressionList = ((MonkeyValueExpression)parent).getExpressionList();
        final MonkeyExpression firstExpression = expressionList.isEmpty() ? null : expressionList.get(0);
        if (firstExpression instanceof MonkeyReference) {
          return ((MonkeyReference)firstExpression).resolveMonkeyClass();
        }
      }
    }

    if (this instanceof MonkeyAwaitExpression) {
      final MonkeyExpression expression = ((MonkeyAwaitExpression)this).getExpression();
      if (expression instanceof MonkeyReference) {
        final MonkeyClassResolveResult result = ((MonkeyReference)expression).resolveMonkeyClass();
        final MonkeyClass resolvedClass = result.getMonkeyClass();
        if (resolvedClass != null && "Future".equals(resolvedClass.getName())) {
          final MonkeyClassResolveResult unwrappedFuture = result.getSpecialization().get(resolvedClass, "T");
          return unwrappedFuture == null ? MonkeyClassResolveResult.EMPTY : unwrappedFuture;
        }
        else {
          return result;
        }
      }
    }

    return MonkeyResolveUtil.getMonkeyClassResolveResult(resolve(), tryGetLeftResolveResult(this).getSpecialization());
  }*/

/*  @NotNull
  private static MonkeyClassResolveResult tryGetLeftResolveResult(MonkeyExpression expression) {
    final MonkeyReference[] childReferences = PsiTreeUtil.getChildrenOfType(expression, MonkeyReference.class);
    final MonkeyReference leftReference = childReferences != null ? childReferences[0] : null;
    return leftReference != null
        ? leftReference.resolveMonkeyClass()
        : MonkeyClassResolveResult.create(PsiTreeUtil.getParentOfType(expression, MonkeyClass.class));
  }*/

  @NotNull
  @Override
  public Object[] getVariants() {
    return LookupElement.EMPTY_ARRAY;
  }
}

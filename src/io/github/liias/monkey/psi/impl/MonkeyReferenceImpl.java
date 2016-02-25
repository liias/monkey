package io.github.liias.monkey.psi.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.UnfairTextRange;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import io.github.liias.monkey.psi.MonkeyId;
import io.github.liias.monkey.psi.MonkeyReference;
import io.github.liias.monkey.psi.util.MonkeyElementGenerator;
import io.github.liias.monkey.resolve.MonkeyResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    MonkeyReference[] references = PsiTreeUtil.getChildrenOfType(this, MonkeyReference.class);
    if (references != null && references.length > 0) {
      TextRange lastReferenceRange = references[references.length - 1].getTextRange();
      return new UnfairTextRange(
          lastReferenceRange.getStartOffset() - textRange.getStartOffset(),
          lastReferenceRange.getEndOffset() - textRange.getEndOffset()
      );
    }

    return new UnfairTextRange(0, textRange.getEndOffset() - textRange.getStartOffset());
  }

  @Nullable
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
    final List<? extends PsiElement> elements =
        ResolveCache.getInstance(getProject()).resolveWithCaching(this, MonkeyResolver.INSTANCE, true, incompleteCode);
    //return MonkeyResolveUtil.toCandidateInfoArray(elements);
    return PsiElementResolveResult.createResults(elements);
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
    return target == element;
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    return LookupElement.EMPTY_ARRAY;
  }

  @Override
  public boolean isSoft() {
    return false;
  }
}

package io.github.liias.monkey.lang.ide.parameterInfo;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ArrayUtil;
import io.github.liias.monkey.lang.psi.*;
import io.github.liias.monkey.lang.resolve.MonkeyComponentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MonkeyParameterInfoHandler implements ParameterInfoHandlerWithTabActionSupport<MonkeyArguments, Object, MonkeyExpression> {

  public static final String PARAMS_DELIMITER = ", ";

  @Nullable
  @Override
  public Object[] getParametersForLookup(LookupElement item, ParameterInfoContext context) {
    final Object o = item.getObject();
    if (o instanceof PsiElement) {
      final PsiElement element = (PsiElement) o;
      final MonkeyComponentType type = MonkeyComponentType.typeOf(element.getParent());
      if (type == MonkeyComponentType.FUNCTION) {
        return new Object[]{element.getParent()};
      }
    }

    return ArrayUtil.EMPTY_OBJECT_ARRAY;
  }

  @Nullable
  @Override
  public Object[] getParametersForDocumentation(Object p, ParameterInfoContext context) {
    return ArrayUtil.EMPTY_OBJECT_ARRAY;
  }

  @Override
  public boolean couldShowInLookup() {
    return true;
  }

  @Nullable
  @Override
  public MonkeyArguments findElementForParameterInfo(@NotNull CreateParameterInfoContext context) {
    return findArgumentList(context.getFile(), context.getOffset(), context.getParameterListStart());
  }

  @Nullable
  @Override
  public MonkeyArguments findElementForUpdatingParameterInfo(@NotNull UpdateParameterInfoContext context) {
    return findArgumentList(context.getFile(), context.getOffset(), context.getParameterListStart());
  }

  private MonkeyArguments findArgumentList(PsiFile file, int offset, int parameterStart) {
    return ParameterInfoUtils.findArgumentList(file, offset, parameterStart, this);
  }

  @Override
  public void showParameterInfo(@NotNull MonkeyArguments monkeyArguments, @NotNull CreateParameterInfoContext context) {
    PsiElement parent = monkeyArguments.getParent();
    PsiElement prevSibling = parent.getPrevSibling();
    if (prevSibling instanceof MonkeyReferenceExpression) {
      MonkeyReferenceExpression referenceExpression = (MonkeyReferenceExpression) prevSibling;
      MonkeyFunctionDeclaration monkeyFunctionDeclaration = resolveCall(referenceExpression);
      if (monkeyFunctionDeclaration != null) {
        context.setItemsToShow(new Object[]{monkeyFunctionDeclaration});
        context.showHint(monkeyArguments, monkeyArguments.getTextRange().getStartOffset(), this);
      }
    }
  }

  @Nullable
  private static MonkeyFunctionDeclaration resolveCall(MonkeyReferenceExpression call) {
    PsiReference reference = call.getReference();
    PsiElement element = reference != null ? reference.resolve() : null;
    if (element instanceof MonkeyComponentName) {
      PsiElement parent = element.getParent();
      if (parent instanceof MonkeyFunctionDeclaration) {
        return (MonkeyFunctionDeclaration) parent;
      }
    }

    return null;
  }

  // TODO: resolve reference here?
  @Override
  public void updateParameterInfo(@NotNull MonkeyArguments monkeyArguments, @NotNull UpdateParameterInfoContext context) {
    context.setCurrentParameter(ParameterInfoUtils.getCurrentParameterIndex(monkeyArguments.getNode(), context.getOffset(), getActualParameterDelimiterType()));
  }

  @Nullable
  @Override
  public String getParameterCloseChars() {
    return ParameterInfoUtils.DEFAULT_PARAMETER_CLOSE_CHARS;
  }

  @Override
  public boolean tracksParameterIndex() {
    return true;
  }

  @NotNull
  @Override
  public Class<MonkeyArguments> getArgumentListClass() {
    return MonkeyArguments.class;
  }

  @NotNull
  @Override
  public IElementType getActualParametersRBraceType() {
    return MonkeyTypes.RBRACE;
  }

  @NotNull
  @Override
  public Set<Class> getArgumentListAllowedParentClasses() {
    return ImmutableSet.of(MonkeyIdentifierSuffix.class);
  }

  @NotNull
  @Override
  public Set<? extends Class> getArgListStopSearchClasses() {
    return Collections.singleton(MonkeyFunctionDeclaration.class);
  }

  @NotNull
  @Override
  public IElementType getActualParameterDelimiterType() {
    return MonkeyTypes.COMMA;
  }

  @NotNull
  @Override
  public MonkeyExpression[] getActualParameters(@NotNull MonkeyArguments monkeyArguments) {
    return Iterables.toArray(monkeyArguments.getExpressionList(), MonkeyExpression.class);
  }

  @Override
  public void updateUI(Object p, @NotNull ParameterInfoUIContext context) {
    if (p == null) {
      context.setUIComponentEnabled(false);
      return;
    }
    MonkeyFunctionDeclaration monkeyFunctionDeclaration = p instanceof MonkeyFunctionDeclaration ? (MonkeyFunctionDeclaration) p : null;
    if (monkeyFunctionDeclaration == null) {
      return;
    }

    MonkeyFormalParameterDeclarations formalParameterDeclarations = monkeyFunctionDeclaration.getFormalParameterDeclarations();
    List<MonkeyComponentName> componentNameList = formalParameterDeclarations == null ? new ArrayList<>() : formalParameterDeclarations.getComponentNameList();

    String message;
    if (componentNameList.isEmpty()) {
      message = CodeInsightBundle.message("parameter.info.no.parameters");
    } else {
      message = componentNameList.stream().map(NavigationItem::getName).collect(Collectors.joining(PARAMS_DELIMITER));
    }

    // btw, if stopping on a breakpoint here, updateParameterInfo() is not called out in time and index will be -1
    int currentParameterIndex = context.getCurrentParameterIndex();
    boolean grayedOut = currentParameterIndex != -1 &&
        !componentNameList.isEmpty() && componentNameList.size() <= currentParameterIndex;
    context.setUIComponentEnabled(!grayedOut);

    TextRange parameterRange = getParameterRange(componentNameList, currentParameterIndex);
    context.setupUIComponentPresentation(
        message,
        parameterRange.getStartOffset(),
        parameterRange.getEndOffset(),
        !context.isUIComponentEnabled(),
        false, //TODO: set deprecated from doccomment?
        false,
        context.getDefaultParameterColor()
    );
  }

  private static TextRange getParameterRange(List<MonkeyComponentName> componentNameList, int index) {
    if (index == -1 || componentNameList.size() <= index) {
      return new TextRange(0, 0);
    }
    int paramsDelimiterLength = PARAMS_DELIMITER.length();
    int startOffset = componentNameList.stream()
        .limit(index)
        .mapToInt(p -> p.getTextLength() + paramsDelimiterLength)
        .sum();

    int paramLength = componentNameList.get(index).getTextLength();
    return new TextRange(startOffset, startOffset + paramLength);
  }
}

package io.github.liias.monkey.lang.documentation;

import com.google.common.base.Strings;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.util.containers.ContainerUtil;
import io.github.liias.monkey.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class MonkeyDocumentationProvider extends AbstractDocumentationProvider {

  @Override
  public String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
    String quickNavigateInfo = getQuickNavigateInfo(element, originalElement);

    StringBuilder docTextBuilder = new StringBuilder();
    if (element.getParent() instanceof MonkeyComponent) {
      MonkeyComponent monkeyComponent = (MonkeyComponent) element.getParent();
      List<PsiComment> commentsForElement = getCommentsForElement(monkeyComponent);

      List<String> parameters = MonkeyDocParser.getParameters(commentsForElement);
      List<String> parameterOptions = MonkeyDocParser.getOptions(commentsForElement);
      List<String> funcReturn = MonkeyDocParser.getTagValues(commentsForElement, MonkeyDocParser.RETURN_TAG);
      List<String> appTypes = MonkeyDocParser.getTagValues(commentsForElement, MonkeyDocParser.APPTYPE_TAG);
      List<String> since = MonkeyDocParser.getTagValues(commentsForElement, MonkeyDocParser.SINCE_TAG);
      List<String> devices = MonkeyDocParser.getTagValues(commentsForElement, MonkeyDocParser.DEVICE_TAG);
      List<String> permissions = MonkeyDocParser.getTagValues(commentsForElement, MonkeyDocParser.PERMISSION_TAG);
      List<String> seealso = MonkeyDocParser.getTagValues(commentsForElement, MonkeyDocParser.SEE_TAG);

      String docTextStart = commentsForElement.stream()
        .filter(c -> c.getText().startsWith("//!"))
        .map(c -> StringUtil.trimStart(c.getText(), "//!").trim())
        .filter(line -> !line.startsWith(MonkeyDocParser.PARAM_TAG))
        .filter(line -> !line.startsWith(MonkeyDocParser.OPTION_TAG))
        .filter(line -> !line.startsWith(MonkeyDocParser.RETURN_TAG))
        .filter(line -> !line.startsWith(MonkeyDocParser.APPTYPE_TAG))
        .filter(line -> !line.startsWith(MonkeyDocParser.SINCE_TAG))
        .filter(line -> !line.startsWith(MonkeyDocParser.DEVICE_TAG))
        .filter(line -> !line.startsWith(MonkeyDocParser.PERMISSION_TAG))
        .filter(line -> !line.startsWith(MonkeyDocParser.SEE_TAG))
        .collect(Collectors.joining("<br>"));
      docTextBuilder.append(docTextStart).append("<br>");

      appendItems("Parameters", parameters, docTextBuilder);
      appendItems("Parameter Options", parameterOptions, docTextBuilder);
      appendItems("Returns", funcReturn, docTextBuilder);
      appendItems("Application Types", appTypes, docTextBuilder);
      appendItems("Since", since, docTextBuilder);
      appendItems("Devices", devices, docTextBuilder);
      appendItems("Permissions", permissions, docTextBuilder);
      appendItems("See Also", seealso, docTextBuilder);
      //docText = commentsForElement.stream().map(PsiElement::getText).collect(Collectors.joining("\n"));
      //docText = getDocumentationText(monkeyComponent);
    }

    String docText = docTextBuilder.toString();
    if (quickNavigateInfo != null) {
      return "<pre>" + quickNavigateInfo + "</pre>" + docText;
    }
    return Strings.nullToEmpty(docText);
  }

  private static void appendItems(String title, List<String> items, StringBuilder docTextBuilder) {
    if (!items.isEmpty()) {
      docTextBuilder.append("<dl>");
      docTextBuilder.append("<dt><b>").append(title).append(":</b></dt>");
      for (String item : items) {
        docTextBuilder.append("<dd>").append(item).append("</dd>");
      }
      docTextBuilder.append("</dl>");
    }
  }

  @NotNull
  private static List<PsiComment> getCommentsForElement(@Nullable PsiElement element) {
    if (element == null) return ContainerUtil.emptyList();
    List<PsiComment> result = ContainerUtil.newArrayList();
    PsiElement e;
    for (e = element.getPrevSibling(); e != null; e = e.getPrevSibling()) {
      if (e instanceof PsiWhiteSpace) {
        if (e.getText().contains("\n\n")) return result;
        continue;
      }
      if (e instanceof PsiComment) {
        PsiComment psiComment = (PsiComment) e;
        if (psiComment.getTokenType().equals(MonkeyTypes.SINGLE_LINE_DOC_COMMENT)) {
          result.add(0, psiComment);
        }
      } else {
        return result;
      }
    }
    return result;
  }

  /*
  @Nullable
  private static String getDocumentationText(final MonkeyComponent monkeyComponent) {
    // PSI is not perfect currently, doc comment may be not part of the corresponding MonkeyComponent element, so docs are searched for in several places:
    // - direct child of this MonkeyComponent
    // - previous sibling (or previous sibling of parent element if this element is first child of its parent DartClassMembers)
    // Consequent line doc comments (///) are joined

    // 1. Look for multiline doc comment as direct child
    final MonkeyDocComment multilineComment = PsiTreeUtil.getChildOfType(monkeyComponent, MonkeyDocComment.class);
    if (multilineComment != null) return getMultilineDocCommentText(multilineComment);

    // 2. Look for single line doc comments as direct children
    final PsiComment[] childComments = PsiTreeUtil.getChildrenOfType(monkeyComponent, PsiComment.class);
    if (childComments != null) {
      //
      final String docText = getSingleLineDocCommentsText(childComments);
      if (docText != null) return docText;
    }


    // 3. Look for multiline doc comment or line doc comments as previous siblings
    PsiElement prevSibling = monkeyComponent.getPrevSibling().getPrevSibling();
    if (prevSibling instanceof PsiDocComment) {

    }
    return null;
  }
*/

  /*
  @Nullable
  private static String getSingleLineDocCommentsText(final @NotNull PsiComment[] comments) {
    StringBuilder buf = null;

    for (PsiComment comment : comments) {
      if (comment.getNode().getElementType() == MonkeyTypes.SINGLE_LINE_DOC_COMMENT) {
        if (buf == null) {
          buf = new StringBuilder();
        } else {
          buf.append('\n');
        }
        String SINGLE_LINE_DOC_COMMENT = "//!";

        final String text = comment.getText();
        if (text.startsWith(SINGLE_LINE_DOC_COMMENT + " ")) {
          buf.append(StringUtil.trimStart(text, SINGLE_LINE_DOC_COMMENT + " "));
        } else {
          buf.append(StringUtil.trimStart(text, SINGLE_LINE_DOC_COMMENT));
        }
      }
    }

    return buf == null ? null : buf.toString();
  }

  private static String getMultilineDocCommentText(MonkeyDocComment multilineComment) {
    return multilineComment.getFirstChild().getText();
  }
*/

  @Nullable
  @Override
  public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {


    if (!(element instanceof MonkeyComponent)) {
      element = element.getParent();
    }
    if (element instanceof MonkeyComponent) {
      final StringBuilder sb = new StringBuilder();
      appendSignature((MonkeyComponent) element, sb);
      if (sb.length() > 0) {
        return sb.toString();
      }
    }

    return null;
  }

  private static void appendSignature(final MonkeyComponent namedComponent, final StringBuilder builder) {
    if (namedComponent instanceof MonkeyFunctionDeclaration) {
      appendFunctionSignature(builder, (MonkeyFunctionDeclaration) namedComponent);
    }
  }

  private static void appendFunctionSignature(final StringBuilder builder, final MonkeyFunctionDeclaration function) {
    builder.append("<b>").append(function.getName()).append("</b>");

    builder.append('(');

    MonkeyFormalParameterDeclarations formalParameterDeclarations = function.getFormalParameterDeclarations();
    if (formalParameterDeclarations != null) {
      List<MonkeyComponentName> names = formalParameterDeclarations.getComponentNameList();
      String args = names.stream()
        .map(cn -> cn.getName())
        .collect(Collectors.joining(", "));
      builder.append(args);
    }

    //builder.append(StringUtil.escapeXml(
    //    DartPresentableUtil.getPresentableParameterList(function, new DartGenericSpecialization(), true, true, false)));
    builder.append(')');
    builder.append(' ');
  }

  private static void newLine(StringBuilder buffer) {
    // Don't know why space has to be added after newline for good text alignment...
    buffer.append("\n ");
  }
}

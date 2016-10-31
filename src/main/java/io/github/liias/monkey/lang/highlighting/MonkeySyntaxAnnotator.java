package io.github.liias.monkey.lang.highlighting;

import com.intellij.codeInsight.CodeInsightUtilCore;
import com.intellij.codeInsight.daemon.JavaErrorMessages;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import io.github.liias.monkey.lang.psi.MonkeyAnnotation;
import io.github.liias.monkey.lang.psi.MonkeyConstDeclaration;
import io.github.liias.monkey.lang.psi.MonkeyLiteral;
import io.github.liias.monkey.lang.psi.MonkeySymbol;
import org.jetbrains.annotations.NotNull;

// Btw, if feeling confused if you should use Annotator or HighlightVisitor, then answer is Annotator, quoting:
// "Annotator API was created specifically for use by third-party language plugins, whereas HighlightVisitor is what IDEA itself originally used"
public class MonkeySyntaxAnnotator implements Annotator, DumbAware {
  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    element.accept(new PsiRecursiveElementVisitor() {
      @Override
      public void visitElement(PsiElement element) {
        if (element instanceof MonkeyAnnotation) {
          highlightPsiElement(element, holder, MonkeySyntaxHighlighter.MC_SYMBOL);
        } else if (element instanceof MonkeySymbol) {
          highlightPsiElement(element, holder, MonkeySyntaxHighlighter.MC_SYMBOL);
        } else if (element instanceof MonkeyConstDeclaration) {
          // TODO: would be nice to highlight usage (reference) as well
          MonkeyConstDeclaration monkeyConstDeclaration = (MonkeyConstDeclaration) element;
          highlightPsiElement(monkeyConstDeclaration.getComponentName(), holder, MonkeySyntaxHighlighter.MC_CONSTANT);
        } else if (element instanceof MonkeyLiteral) {
          MonkeyLiteral monkeyLiteral = (MonkeyLiteral) element;
          PsiElement charliteral = monkeyLiteral.getCharliteral();
          if (charliteral != null) {
            annotateCharLiteral(holder, charliteral);
          }
        }
      }
    });
  }

  private static void highlightPsiElement(PsiElement element, @NotNull AnnotationHolder holder, @NotNull TextAttributesKey colorKey) {
    TextAttributes enforcedTextAttributes = EditorColorsManager.getInstance().getGlobalScheme().getAttributes(colorKey);
    holder.createInfoAnnotation(element, null)
      .setEnforcedTextAttributes(enforcedTextAttributes);
  }

  // TODO: this is not just syntax annotator but error annotation..., so rename the class or move this to some other annotator?
  private static void annotateCharLiteral(@NotNull AnnotationHolder holder, PsiElement charliteral) {
    String text = charliteral.getText();
    if (text != null && text.length() >= 2) {
      if (text.startsWith("'") && text.endsWith("'")) {
        String withoutQuotes = text.substring(1, text.length() - 1);
        StringBuilder chars = new StringBuilder();
        boolean success = CodeInsightUtilCore.parseStringCharacters(withoutQuotes, chars, null);
        if (!success) {
          String message = JavaErrorMessages.message("illegal.escape.character.in.character.literal");
          holder.createErrorAnnotation(charliteral, message);
        }
        int length = chars.length();
        if (length > 1) {
          String message = JavaErrorMessages.message("too.many.characters.in.character.literal");
          holder.createErrorAnnotation(charliteral, message);
        } else if (length == 0) {
          String message = JavaErrorMessages.message("empty.character.literal");
          holder.createErrorAnnotation(charliteral, message);
        }
      }
    }
  }
}

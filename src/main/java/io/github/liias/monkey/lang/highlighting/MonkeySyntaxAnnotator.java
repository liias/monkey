package io.github.liias.monkey.lang.highlighting;

import com.intellij.codeInsight.CodeInsightUtilCore;
import com.intellij.codeInsight.daemon.JavaErrorMessages;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import io.github.liias.monkey.lang.psi.MonkeyAnnotation;
import io.github.liias.monkey.lang.psi.MonkeyLiteral;
import io.github.liias.monkey.lang.psi.MonkeySymbol;
import org.jetbrains.annotations.NotNull;

public class MonkeySyntaxAnnotator implements Annotator, DumbAware {
  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    element.accept(new PsiRecursiveElementVisitor() {
      @Override
      public void visitElement(PsiElement element) {
        if (element instanceof MonkeyAnnotation) {
          annotateAnnotation(element, holder);
        } else if (element instanceof MonkeySymbol) {
          annotateSymbol(element, holder);
        } else if (element instanceof MonkeyLiteral) {
          MonkeyLiteral monkeyLiteral = (MonkeyLiteral) element;
          annotateLiteral(monkeyLiteral, holder);
        }
      }
    });
  }

  private static void annotateAnnotation(PsiElement element, @NotNull AnnotationHolder holder) {
    TextAttributes enforcedTextAttributes = EditorColorsManager.getInstance().getGlobalScheme().getAttributes(MonkeySyntaxHighlighter.MC_SYMBOL);
    holder.createInfoAnnotation(element, null)
      .setEnforcedTextAttributes(enforcedTextAttributes);
  }

  private static void annotateSymbol(PsiElement element, @NotNull AnnotationHolder holder) {
    TextAttributes enforcedTextAttributes = EditorColorsManager.getInstance().getGlobalScheme().getAttributes(MonkeySyntaxHighlighter.MC_SYMBOL);
    holder.createInfoAnnotation(element, null)
      .setEnforcedTextAttributes(enforcedTextAttributes);
  }

  // TODO: this is not just syntax annotator but error annotation..., so rename the class or move this to some other annotator
  // Btw, if feeling confused if you should use Annotator or HighlightVisitor, then answer is Annotator, quoting:
  // "Annotator API was created specifically for use by third-party language plugins, whereas HighlightVisitor is what IDEA itself originally used"
  private static void annotateLiteral(MonkeyLiteral monkeyLiteral, @NotNull AnnotationHolder holder) {
    PsiElement charliteral = monkeyLiteral.getCharliteral();
    if (charliteral != null) {
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

  private static void ok() {

  }
}

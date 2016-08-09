package io.github.liias.monkey.lang.highlighting;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import io.github.liias.monkey.lang.psi.MonkeyAnnotation;
import org.jetbrains.annotations.NotNull;

public class MonkeySyntaxAnnotator implements Annotator, DumbAware {
  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    element.accept(new PsiRecursiveElementVisitor() {
      @Override
      public void visitElement(PsiElement element) {
        if (element instanceof MonkeyAnnotation) {
          TextAttributes enforcedTextAttributes = EditorColorsManager.getInstance().getGlobalScheme().getAttributes(MonkeySyntaxHighlighter.MC_ANNOTATION);

          holder.createInfoAnnotation(element, null)
            .setEnforcedTextAttributes(enforcedTextAttributes);
        }
      }
    });
  }
}

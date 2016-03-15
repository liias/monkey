package io.github.liias.monkey.lang.ide;

import com.intellij.lang.CodeDocumentationAwareCommenterEx;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.tree.IElementType;
import io.github.liias.monkey.lang.psi.MonkeyTypes;
import org.jetbrains.annotations.Nullable;

public class MonkeyCommenter implements CodeDocumentationAwareCommenterEx {
  @Nullable
  @Override
  public String getLineCommentPrefix() {
    return "//";
  }

  @Nullable
  @Override
  public String getBlockCommentPrefix() {
    return "/*";
  }

  @Nullable
  @Override
  public String getBlockCommentSuffix() {
    return "*/";
  }

  @Nullable
  @Override
  public String getCommentedBlockCommentPrefix() {
    return null;
  }

  @Nullable
  @Override
  public String getCommentedBlockCommentSuffix() {
    return null;
  }

  @Override
  public boolean isDocumentationCommentText(PsiElement element) {

    return false;
  }

  @Nullable
  @Override
  public IElementType getLineCommentTokenType() {
    return MonkeyTypes.SINGLE_LINE_COMMENT;
  }

  @Nullable
  @Override
  public IElementType getBlockCommentTokenType() {
    return MonkeyTypes.BLOCK_COMMENT;

  }

  @Nullable
  @Override
  public IElementType getDocumentationCommentTokenType() {
    return MonkeyTypes.SINGLE_LINE_DOC_COMMENT;
  }

  @Nullable
  @Override
  public String getDocumentationCommentPrefix() {
    return "//!";
  }

  @Nullable
  @Override
  public String getDocumentationCommentLinePrefix() {
    return "//!";
  }

  @Nullable
  @Override
  public String getDocumentationCommentSuffix() {
    // TODO: maybe "//"?
    return null;
  }

  @Override
  public boolean isDocumentationComment(PsiComment element) {
    return element instanceof PsiDocComment;
  }
}

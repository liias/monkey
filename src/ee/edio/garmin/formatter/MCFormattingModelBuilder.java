package ee.edio.garmin.formatter;

import com.google.common.collect.Lists;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MCFormattingModelBuilder implements FormattingModelBuilder {
  @NotNull
  @Override
  public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
    final MCFormattingBlock mCFormattingBlock = new MCFormattingBlock(element.getNode(), null, null);
    return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), mCFormattingBlock, settings);
  }

  @Nullable
  @Override
  public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
    return null;
  }

  public static class MCFormattingBlock extends AbstractBlock {
    protected MCFormattingBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment) {
      super(node, wrap, alignment);
    }

    @Override
    protected List<Block> buildChildren() {
      return Lists.newArrayList();
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
      return null;
    }

    @Override
    public boolean isLeaf() {
      return getNode().getFirstChildNode() == null;
    }
  }
}
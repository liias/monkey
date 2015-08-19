package ee.edio.garmin.editor;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.intellij.codeInsight.folding.CodeFoldingSettings;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import ee.edio.garmin.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MCFoldingBuilder extends FoldingBuilderEx implements DumbAware {
  @NotNull
  @Override
  public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
    if (!(root instanceof MonkeyCFile)) {
      return FoldingDescriptor.EMPTY;
    }
    MonkeyCFile file = (MonkeyCFile) root;
    if (!file.isContentsLoaded()) {
      return FoldingDescriptor.EMPTY;
    }
    List<FoldingDescriptor> result = Lists.newArrayList();

    /*final PsiElement lastChild = file.getLastChild();
    if (lastChild != null && lastChild instanceof MonkeyCTypeDeclaration) {
      MonkeyCTypeDeclaration typeDecl = (MonkeyCTypeDeclaration) lastChild;
      List<MonkeyCClassBodyDeclaration> classBodyDeclarationList = typeDecl.getClassDeclaration().getNormalClassDeclaration().getClassBody().getClassBodyDeclarationList();
      for (MonkeyCClassBodyDeclaration classBodyDeclaration : classBodyDeclarationList) {
        final MonkeyCFunctionDeclaration functionDeclaration = classBodyDeclaration.getMemberDecl().getFunctionDeclaration();
        if (functionDeclaration != null) {
          final List<MonkeyCBlockStatement> blockStatementList = functionDeclaration.getBlockStatementList();
          if (!blockStatementList.isEmpty()) {
            MonkeyCBlockStatement block = blockStatementList.get(0);
            if (block != null && block.getTextRange().getLength() > 1) {
              result.add(new FoldingDescriptor(block, block.getTextRange()));
            }
          }
        }
      }
    }*/

    return Iterables.toArray(result, FoldingDescriptor.class);
  }

  @Nullable
  @Override
  public String getPlaceholderText(@NotNull ASTNode node) {
    return "...";
  }

  @Override
  public boolean isCollapsedByDefault(@NotNull ASTNode node) {
    CodeFoldingSettings codeFoldingSettings = CodeFoldingSettings.getInstance();
    final IElementType elementType = node.getElementType();
    final ASTNode treeParent = node.getTreeParent();
    if (treeParent != null && treeParent.getPsi() instanceof MonkeyCFunctionDeclaration) {
      return codeFoldingSettings.COLLAPSE_METHODS;
    } else if (elementType == MonkeyCTypes.USING_DECLARATION) {
      return codeFoldingSettings.COLLAPSE_IMPORTS;
    }
    return false;
  }
}

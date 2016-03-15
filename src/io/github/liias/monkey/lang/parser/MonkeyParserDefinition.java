package io.github.liias.monkey.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import io.github.liias.monkey.lang.MonkeyLanguage;
import io.github.liias.monkey.lang.lexer.MonkeyLexer;
import io.github.liias.monkey.lang.psi.MonkeyFile;
import io.github.liias.monkey.lang.psi.MonkeyTypes;
import io.github.liias.monkey.lang.psi.impl.MonkeyDocCommentImpl;
import org.jetbrains.annotations.NotNull;

public class MonkeyParserDefinition implements ParserDefinition {
  public static final IFileElementType FILE = new IFileElementType(Language.findInstance(MonkeyLanguage.class));

  @NotNull
  @Override
  public Lexer createLexer(Project project) {
    return new MonkeyLexer();
  }

  @Override
  public PsiParser createParser(Project project) {
    return new MonkeyParser();
  }

  @Override
  public IFileElementType getFileNodeType() {
    return FILE;
  }

  @NotNull
  @Override
  public TokenSet getWhitespaceTokens() {
    return MonkeyTokenTypesSets.WHITE_SPACES;
  }

  @NotNull
  @Override
  public TokenSet getCommentTokens() {
    return MonkeyTokenTypesSets.COMMENTS;
  }

  @NotNull
  @Override
  public TokenSet getStringLiteralElements() {
    return MonkeyTokenTypesSets.STRINGS;
  }

  @NotNull
  @Override
  public PsiElement createElement(ASTNode node) {
    if (node.getElementType() == MonkeyTypes.SINGLE_LINE_DOC_COMMENT) {
      return new MonkeyDocCommentImpl(node);
    }
    return MonkeyTypes.Factory.createElement(node);
  }

  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new MonkeyFile(viewProvider);
  }

  @Override
  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }
}

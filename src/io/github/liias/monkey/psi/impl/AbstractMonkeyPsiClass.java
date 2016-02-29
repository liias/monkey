package io.github.liias.monkey.psi.impl;


import com.intellij.lang.ASTNode;
import io.github.liias.monkey.psi.MonkeyClass;
import org.jetbrains.annotations.NotNull;

abstract public class AbstractMonkeyPsiClass extends AbstractMonkeyComponentImpl implements MonkeyClass {
  public AbstractMonkeyPsiClass(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "MonkeyClassDeclaration:" + getName();
  }
}

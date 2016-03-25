package io.github.liias.monkey.lang.psi.impl;


import com.intellij.lang.ASTNode;
import io.github.liias.monkey.lang.psi.MonkeyClass;
import io.github.liias.monkey.lang.psi.MonkeyClassBody;
import io.github.liias.monkey.lang.psi.MonkeyClassBodyMembers;
import io.github.liias.monkey.lang.psi.MonkeyClassDeclaration;
import org.jetbrains.annotations.NotNull;

abstract public class AbstractMonkeyPsiClass extends AbstractMonkeyComponentImpl implements MonkeyClass {
  public AbstractMonkeyPsiClass(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public MonkeyClassBodyMembers getBodyMembers() {
    if (this instanceof MonkeyClassDeclaration) {
      MonkeyClassDeclaration monkeyClassDeclaration = (MonkeyClassDeclaration) this;
      MonkeyClassBody classBody = monkeyClassDeclaration.getClassBody();
      if (classBody != null) {
        return classBody.getClassBodyMembers();
      }
    }
    return null;
  }


  @Override
  public String toString() {
    return "MonkeyClassDeclaration:" + getName();
  }
}

package io.github.liias.monkey.runnoevil;

import com.intellij.execution.Location;
import com.intellij.execution.PsiLocation;
import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import com.intellij.lang.Language;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.testIntegration.TestFramework;
import com.intellij.util.IncorrectOperationException;
import icons.MonkeyIcons;
import io.github.liias.monkey.lang.MonkeyLanguage;
import io.github.liias.monkey.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MonkeyTestFramework implements TestFramework {
  @NotNull
  @Override
  public String getName() {
    return "Run No Evil";
  }

  @NotNull
  @Override
  public Icon getIcon() {
    return MonkeyIcons.MODULE_TEST_16;
  }

  @Override
  public boolean isLibraryAttached(@NotNull Module module) {
    // true if SDK version matches
    return true;
  }

  @Nullable
  @Override
  public String getLibraryPath() {
    return null;
  }

  @Nullable
  @Override
  public String getDefaultSuperClass() {
    return null;
  }

  @Override
  public boolean isTestClass(@NotNull PsiElement clazz) {
    return clazz instanceof MonkeyClass && isTestClass((MonkeyClass) clazz, false);
  }

  @Override
  public boolean isPotentialTestClass(@NotNull PsiElement clazz) {
    return clazz instanceof MonkeyClass && isTestClass((MonkeyClass) clazz, true);
  }

  private boolean isTestClass(MonkeyClass monkeyClass, boolean canBePotential) {
    if (monkeyClass == null) {
      return false;
    }
    if (canBePotential) return true; // isUnderTestSources, but monkey mixes with normal sources
    List<MonkeyFunctionDeclaration> classFunctionDeclarations = Optional.of(monkeyClass.getBodyMembers())
      .map(MonkeyClassBodyMembers::getFunctionDeclarationList).orElse(Collections.emptyList());

    for (MonkeyFunctionDeclaration classFunctionDeclaration : classFunctionDeclarations) {
      if (isTestMethod(classFunctionDeclaration)) {
        return true;
      }
    }

    return false;
  }

  @Nullable
  @Override
  public PsiElement findSetUpMethod(@NotNull PsiElement clazz) {
    return null;
  }

  @Nullable
  @Override
  public PsiElement findTearDownMethod(@NotNull PsiElement clazz) {
    return null;
  }

  @Nullable
  @Override
  public PsiElement findOrCreateSetUpMethod(@NotNull PsiElement clazz) throws IncorrectOperationException {
    return null;
  }

  @Override
  public FileTemplateDescriptor getSetUpMethodFileTemplateDescriptor() {
    return null;
  }

  @Override
  public FileTemplateDescriptor getTearDownMethodFileTemplateDescriptor() {
    return null;
  }

  @Override
  public FileTemplateDescriptor getTestMethodFileTemplateDescriptor() {
    return new FileTemplateDescriptor("RunNoEvil Test Method.mc");
  }

  @Override
  public boolean isIgnoredMethod(PsiElement element) {
    return false;
  }

  @Override
  public boolean isTestMethod(PsiElement element) {
    return element instanceof MonkeyFunctionDeclaration && getTestMethod(element, true, true) != null;
  }

  public static MonkeyFunctionDeclaration getTestMethod(PsiElement element, boolean checkAbstract, boolean checkRunWith) {
    PsiManager manager = element.getManager();
    Location<PsiElement> location = PsiLocation.fromPsiElement(manager.getProject(), element);
    Iterator<Location<MonkeyFunctionDeclaration>> iterator = location.getAncestors(MonkeyFunctionDeclaration.class, false);

    Location<MonkeyFunctionDeclaration> methodLocation;
    do {
      if (!iterator.hasNext()) {
        return null;
      }

      methodLocation = iterator.next();
    } while (!isTestMethod(methodLocation, checkAbstract, checkRunWith));

    return methodLocation.getPsiElement();
  }

  // TODO: test method must be static if inside a class
  private static boolean isTestMethod(Location<? extends MonkeyFunctionDeclaration> location, boolean checkAbstract, boolean checkRunWith) {
    MonkeyFunctionDeclaration functionDeclaration = location.getPsiElement();

    MonkeyAnnotation annotation = functionDeclaration.getModifiers().getAnnotation();
    if (annotation == null) {
      return false;
    }

    MonkeyId id = annotation.getSymbol().getId();
    if (id != null && "test".equals(id.getIdentifier().getText())) {
      return true;
    }

    return false;
  }

  @NotNull
  @Override
  public Language getLanguage() {
    return MonkeyLanguage.INSTANCE;
  }
}

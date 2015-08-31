package ee.edio.garmin.psi;

import com.intellij.psi.PsiNameIdentifierOwner;

/*
Every element which can be renamed or referenced
 needs to implement com.intellij.psi.PsiNamedElement interface.
 */
public interface MonkeyNamedElement extends MonkeyPsiCompositeElement, PsiNameIdentifierOwner {

}
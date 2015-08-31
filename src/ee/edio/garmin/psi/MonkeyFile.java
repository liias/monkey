package ee.edio.garmin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import ee.edio.garmin.MonkeyFileType;
import ee.edio.garmin.MonkeyLanguage;
import org.jetbrains.annotations.NotNull;

public class MonkeyFile extends PsiFileBase {
  public MonkeyFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, MonkeyLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return MonkeyFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "Monkey C File";
  }
}

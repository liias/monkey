package ee.edio.garmin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import ee.edio.garmin.MonkeyCFileType;
import ee.edio.garmin.MonkeyCLanguage;
import org.jetbrains.annotations.NotNull;

public class MonkeyCFile extends PsiFileBase {
  public MonkeyCFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, MonkeyCLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return MonkeyCFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "Monkey C File";
  }
}

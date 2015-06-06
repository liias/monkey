package ee.edio.garmin;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighterProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MonkeyCSyntaxHighlighterFactory extends SyntaxHighlighterFactory implements SyntaxHighlighterProvider {
  @NotNull
  @Override
  public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
    return new MonkeyCSyntaxHighlighter();
  }

  @Nullable
  @Override
  public SyntaxHighlighter create(@NotNull FileType fileType, Project project, VirtualFile file) {
    return new MonkeyCSyntaxHighlighter();
  }
}

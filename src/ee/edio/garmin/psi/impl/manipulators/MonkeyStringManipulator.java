package ee.edio.garmin.psi.impl.manipulators;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;
import ee.edio.garmin.psi.MonkeyStringLiteral;
import org.jetbrains.annotations.NotNull;

public class MonkeyStringManipulator extends AbstractElementManipulator<MonkeyStringLiteral> {
  @Override
  public MonkeyStringLiteral handleContentChange(@NotNull MonkeyStringLiteral literal, @NotNull TextRange range, String newContent)
      throws IncorrectOperationException {
    final String newText = range.replace(literal.getText(), newContent);
    return literal.updateText(newText);
  }

  @NotNull
  @Override
  public TextRange getRangeInElement(@NotNull final MonkeyStringLiteral element) {
    return element.getTextLength() > 2 ? TextRange.from(1, element.getTextLength() - 2) : TextRange.EMPTY_RANGE;
  }
}

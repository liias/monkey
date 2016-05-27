package io.github.liias.monkey.lang.documentation;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiComment;

import java.util.List;
import java.util.stream.Collectors;

public class MonkeyDocParser {
  private static final String DEPRECATED_TAG = "@deprecated";
  private static final String SINCE_TAG = "@since";
  private static final String DEVICE_TAG = "@device";
  private static final String PERMISSION_TAG = "@permission";
  private static final String APPTYPE_TAG = "@apptype";
  private static final String SEE_TAG = "@see";
  private static final String PARAM_TAG = "@param";
  private static final String OPTION_TAG = "@option";
  private static final String RETURN_TAG = "@return";

  public static List<String> getParameters(List<PsiComment> comment) {
    List<String> paramLines = comment.stream()
      .filter(c -> c.getText().startsWith("//!"))
      .map(c -> StringUtil.trimStart(c.getText(), "//!").trim())
      .filter(line -> line.startsWith(PARAM_TAG))
      .map(line -> StringUtil.trimStart(line, PARAM_TAG).trim())
      .map(line -> line.startsWith("[") ? line : line)
      .collect(Collectors.toList());

    return paramLines;
  }

}

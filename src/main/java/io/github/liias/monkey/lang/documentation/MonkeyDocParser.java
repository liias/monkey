package io.github.liias.monkey.lang.documentation;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiComment;

import java.util.List;
import java.util.stream.Collectors;

public class MonkeyDocParser {
  public static final String SINCE_TAG = "@since";
  public static final String DEVICE_TAG = "@device";
  public static final String PERMISSION_TAG = "@permission";
  public static final String APPTYPE_TAG = "@apptype";
  public static final String PARAM_TAG = "@param";
  public static final String OPTION_TAG = "@option";
  public static final String RETURN_TAG = "@return";
  public static final String SEE_TAG = "@see";
  private static final String DEPRECATED_TAG = "@deprecated";

  public static List<String> getParameters(List<PsiComment> comment) {
    return getTagValues(comment, PARAM_TAG).stream()
      .map(line -> line.startsWith("[") ? line : line)
      .collect(Collectors.toList());
  }

  public static List<String> getOptions(List<PsiComment> comment) {
    return getTagValues(comment, OPTION_TAG).stream()
      .map(line -> line.startsWith("[") ? line : line)
      .collect(Collectors.toList());
  }

  public static List<String> getTagValues(List<PsiComment> comment, String tag) {
    return comment.stream()
      .filter(c -> c.getText().startsWith("//!"))
      .map(c -> StringUtil.trimStart(c.getText(), "//!").trim())
      .filter(line -> line.startsWith(tag))
      .map(line -> StringUtil.trimStart(line, tag).trim())
      .collect(Collectors.toList());
  }
}

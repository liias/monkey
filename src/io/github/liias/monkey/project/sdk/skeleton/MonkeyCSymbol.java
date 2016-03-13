package io.github.liias.monkey.project.sdk.skeleton;

public class MonkeyCSymbol {
  private String name;
  private String documentation;

  public void setName(String name) {
    this.name = name;
  }

  public void setDocumentation(String documentation) {
    this.documentation = parseHtmlText(documentation);
  }

  private String parseHtmlText(String htmlText) {
    String text = htmlText;
    text = text.replaceAll("<br/>", System.getProperty("line.separator"));
    text = text.replaceAll("<.*>", "");
    return text.trim();
  }

  public boolean startsWith(String start) {
    return name.startsWith(start);
  }

  public void replace(String target, String replacement) {
    name = name.replace(target, replacement);
  }

  public String getName() {
    return name;
  }

  public String getDocumentation() {
    return documentation;
  }
}

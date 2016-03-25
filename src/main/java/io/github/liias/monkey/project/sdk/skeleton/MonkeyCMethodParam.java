package io.github.liias.monkey.project.sdk.skeleton;

public class MonkeyCMethodParam {
  private final String mType;
  private final String mDocumentation;
  private final String mName;

  public MonkeyCMethodParam(String name, String documentation) {
    mName = name;

    String tempType = null;
    String tempDocumentation = null;

    int paramIndex = documentation.indexOf("<div class=\"param\">");
    while (paramIndex != -1)
    {
      int paramIndexEnd = documentation.indexOf("</div>", paramIndex);
      String description = documentation.substring(paramIndex + 19, paramIndexEnd).trim();
      String paramName = description;
      int firstSpaceIndex = paramName.indexOf(" ");
      if (firstSpaceIndex != -1) {
        paramName = paramName.substring(0, firstSpaceIndex);
      }
      if (paramName.equals(name))
      {
        int startI = description.indexOf("[");
        int endI = description.indexOf("]");
        if ((startI != -1) && (endI != -1)) {
          tempType = description.substring(startI + 1, endI);
        }
        if (endI == -1) {
          endI = name.length();
        }
        if (description.length() <= endI) {
          break;
        }
        tempDocumentation = description.substring(endI + 1);

        break;
      }
      paramIndex = documentation.indexOf("<div class=\"param\">", paramIndexEnd);
    }


    mType = tempType;
    mDocumentation = tempDocumentation;
  }

  public String getDocumentation()
  {
    String type = mType != null ? " <i>(" + mType + ")</i>" : "";

    return type + (mDocumentation != null ? " - " + mDocumentation : "");
  }
}

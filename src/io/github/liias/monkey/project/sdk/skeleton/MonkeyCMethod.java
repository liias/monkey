package io.github.liias.monkey.project.sdk.skeleton;

import java.util.ArrayList;
import java.util.List;

public class MonkeyCMethod extends MonkeyCSymbol {
  private final String parentName;
  List<MonkeyCMethodParam> params = new ArrayList<>();

  public MonkeyCMethod(String parentName) {
    this.parentName = parentName;
  }

  public void addParameter(MonkeyCMethodParam param) {
    params.add(param);
  }

  /*public String getAdditionalProposalInfo()
  {
    if ((mDocumentation == null) && (mParameters.isEmpty()) && (mReturnVal == null)) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    sb.append(mDocumentation != null ? mDocumentation : "");
    sb.append("\n");
    if (!mParameters.isEmpty())
    {
      sb.append("\n<b>Parameters:</b>\n");
      for (MonkeyCMethodParam param : mParameters) {
        sb.append("<bullet><b>" + param.getName() + "</b>" + param.getDocumentation() + "\n");
      }
      sb.append("\n");
    }
    sb.append(mReturnVal.getDocumentation());

    return sb.toString();
  }*/
}

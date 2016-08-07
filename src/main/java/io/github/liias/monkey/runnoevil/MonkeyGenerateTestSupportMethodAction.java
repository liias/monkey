package io.github.liias.monkey.runnoevil;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.codeInsight.template.impl.ConstantNode;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiFile;
import com.intellij.testIntegration.BaseGenerateTestSupportMethodAction;
import com.intellij.testIntegration.TestFramework;
import com.intellij.testIntegration.TestIntegrationUtils;
import io.github.liias.monkey.lang.file.MonkeyFileType;
import org.jetbrains.annotations.NotNull;

public class MonkeyGenerateTestSupportMethodAction extends BaseGenerateAction {
  protected static final Logger LOG = Logger.getInstance("#" + BaseGenerateTestSupportMethodAction.class.getName());

  public MonkeyGenerateTestSupportMethodAction() {
    super(new MyHandler(TestIntegrationUtils.MethodKind.TEST));
  }

  @Override
  protected boolean isValidForFile(@NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
    return file.getFileType() == MonkeyFileType.INSTANCE;
  }

  private static class MyHandler implements CodeInsightActionHandler {
    private final TestIntegrationUtils.MethodKind methodKind;

    private MyHandler(TestIntegrationUtils.MethodKind methodKind) {
      this.methodKind = methodKind;
    }

    private TestIntegrationUtils.MethodKind getMethodKind() {
      return methodKind;
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
      MonkeyTestFramework framework = new MonkeyTestFramework();

      Template template = createTestMethodTemplate(getMethodKind(), framework, project, null, true);
      TemplateManager.getInstance(project).startTemplate(editor, template);
    }

    private static Template createTestMethodTemplate(TestIntegrationUtils.MethodKind methodKind, TestFramework testFramework, Project project, String name, boolean automatic) {
      FileTemplateDescriptor templateDesc = methodKind.getFileTemplateDescriptor(testFramework);
      String templateName = templateDesc.getFileName();
      FileTemplate fileTemplate = FileTemplateManager.getInstance(project).getCodeTemplate(templateName);
      Template template = TemplateManager.getInstance(project).createTemplate("", "");

      String templateText = fileTemplate.getText();
      if (name == null) name = methodKind.getDefaultName();
      templateText = StringUtil.replace(templateText, "${BODY}", "");

      int from = 0;
      while (true) {
        int index = templateText.indexOf("${NAME}", from);
        if (index == -1) break;

        template.addTextSegment(templateText.substring(from, index));

        if (index > 0 && !Character.isWhitespace(templateText.charAt(index - 1))) {
          name = StringUtil.capitalize(name);
        } else {
          name = StringUtil.decapitalize(name);
        }
        if (from == 0) {
          Expression nameExpr = new ConstantNode(name);
          template.addVariable("name", nameExpr, nameExpr, !automatic);
        } else {
          template.addVariableSegment("name");
        }

        from = index + "${NAME}".length();
      }
      template.addTextSegment(templateText.substring(from, templateText.length()));
      template.setToIndent(true);
      template.setToReformat(true);
      template.setToShortenLongNames(true);

      return template;
    }

    @Override
    public boolean startInWriteAction() {
      return false;
    }
  }
}

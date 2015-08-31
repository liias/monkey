package ee.edio.garmin;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class MonkeyColorSettingsPage implements ColorSettingsPage {

  private final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
      new AttributesDescriptor("Function declaration", MonkeySyntaxHighlighter.MC_FUNCTION_DECLARATION),
      new AttributesDescriptor("Class name", MonkeySyntaxHighlighter.MC_CLASS_NAME)
  };

  @Nullable
  @Override
  public Icon getIcon() {
    return MonkeyIcons.FILE;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new MonkeySyntaxHighlighter();
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "using Toybox.Application as App;\n" +
        "using Toybox.WatchUi as Ui;\n" +
        "using Toybox.System as Sys;\n" +
        "\n" +
        "class EsimeneApp extends App.AppBase {\n" +
        "\n" +
        "    //! onStart() is called on application start up\n" +
        "    function onStart() {\n" +
        "    \tSys.println(\"EsimeneApp.onStart\");\n" +
        "    }\n" +
        "\n" +
        "    //! onStop() is called when your application is exiting\n" +
        "    function onStop() {\n" +
        "    \tSys.println(\"EsimeneApp.onStop\");\n" +
        "    }\n" +
        "\n" +
        "    //! Return the initial view of your application here\n" +
        "    function getInitialView() {\n" +
        "    \tvar view = new EsimeneView();\n" +
        "    \tSys.println(\"EsimeneApp.getInitialView\");\n" +
        "        return [view, new EsimeneDelegate(view)];\n" +
        "    }\n" +
        "\n" +
        "}";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return DESCRIPTORS;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "Monkey C";
  }
}

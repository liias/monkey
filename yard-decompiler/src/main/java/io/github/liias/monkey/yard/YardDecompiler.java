package io.github.liias.monkey.yard;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.trimToNull;

public class YardDecompiler {

  private final String docDirPath; // includes last slash
  public static final String MODULE_TEMPLATE_START = "module %s {\n";
  public static final String CLASS_TEMPLATE_START = "class %s";
  public static final String METHOD_TEMPLATE_START = "function %s(";

  public static final String STEP_MARKER = "  ";

  public YardDecompiler(String docDirPath) {
    this.docDirPath = docDirPath;
  }

  public String parse() {
    try {
      SdkModuleOrClass topLevelModule = parseModule("top-level-namespace.html", "top-level-namespace");
      return generateSkeletons(topLevelModule);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void addToStr(int step, StringBuilder builder, SdkModuleOrClass sdkModuleOrClass) {
    String stepPrefix = getStepPrefix(step);
    StringBuilder skeletonBuilder = new StringBuilder();

    addComments(step, skeletonBuilder, sdkModuleOrClass);

    if (sdkModuleOrClass instanceof SdkModule) {
      skeletonBuilder.append(stepPrefix).append(String.format(MODULE_TEMPLATE_START, sdkModuleOrClass.getName()));
    } else {
      skeletonBuilder.append(stepPrefix)
        .append(String.format(CLASS_TEMPLATE_START, sdkModuleOrClass.getName()));
      SdkClass sdkClass = (SdkClass) sdkModuleOrClass;
      if (sdkClass.getParentClassName() != null) {
        skeletonBuilder.append(" extends ").append(sdkClass.getParentClassName());
      }
      skeletonBuilder.append(" {\n");
    }

    addConstantsToStringBuilder(step + 1, sdkModuleOrClass, skeletonBuilder);

    for (SdkModuleOrClass childModuleOrClass : sdkModuleOrClass.getChildModulesOrClasses()) {
      addToStr(step + 1, skeletonBuilder, childModuleOrClass);
    }

    addFieldsToStringBuilder(step + 1, sdkModuleOrClass, skeletonBuilder);
    addMethodsToStringBuilder(step + 1, sdkModuleOrClass, skeletonBuilder);

    skeletonBuilder.append(stepPrefix).append("}");
    builder.append(skeletonBuilder.toString()).append("\n\n");
  }

  private String generateSkeletons(SdkModuleOrClass toplevelModule) {
    StringBuilder builder = new StringBuilder();
    builder.append("// This is generated Connect IQ API stub, do not edit this!\n\n");

    for (SdkModuleOrClass sdkModuleOrClass : toplevelModule.getChildModulesOrClasses()) {
      addToStr(0, builder, sdkModuleOrClass);
    }

    String modulesAndClassesSkeleton = builder.toString();

    return modulesAndClassesSkeleton;
  }

  private static String getStepPrefix(int step) {
    return StringUtils.repeat(STEP_MARKER, step);
  }

  private static String commentMultilineString(String commentPrefix, String multilineString) {
    String wrapped = WordUtils.wrap(multilineString, 80);

    return new BufferedReader(new StringReader(wrapped))
      .lines().map(l -> WordUtils.wrap(l, 80))
      .collect(Collectors.joining("\n" + commentPrefix, commentPrefix, ""));
  }

  private static void addComments(int step, StringBuilder skeletonBuilder, SdkEntity sdkEntity) {
    String stepPrefix = getStepPrefix(step);

    if (sdkEntity.isDeprecated()) {
      skeletonBuilder.append(stepPrefix).append("//! @deprecated ");
      if (sdkEntity.getDeprecatedText() != null) {
        skeletonBuilder.append(sdkEntity.getDeprecatedText());
      }
      skeletonBuilder.append("\n");
    }

    String commentPrefix = stepPrefix + "//! ";
    if (sdkEntity.getDocumentation() != null) {
      String comment = commentMultilineString(commentPrefix, sdkEntity.getDocumentation());
      skeletonBuilder.append(comment).append("\n");
    }
    if (sdkEntity.getSince() != null) {
      skeletonBuilder.append(commentPrefix).append("@since ").append(sdkEntity.getSince()).append("\n");
    }

    for (String supportedDevice : sdkEntity.getSupportedDevices()) {
      skeletonBuilder.append(commentPrefix).append("@device ").append(supportedDevice).append("\n");
    }

    for (String permission : sdkEntity.getPermissions()) {
      skeletonBuilder.append(commentPrefix).append("@permission ").append(permission).append("\n");
    }

    for (String appType : sdkEntity.getAppTypes()) {
      skeletonBuilder.append(commentPrefix).append("@apptype ").append(appType).append("\n");
    }

    for (String see : sdkEntity.getSee()) {
      skeletonBuilder.append(commentPrefix).append("@see ").append(see).append("\n");
    }

    if (sdkEntity instanceof SdkMethod) {
      SdkMethod sdkMethod = (SdkMethod) sdkEntity;

      for (SdkMethod.SdkMethodParameter param : sdkMethod.getParameters()) {
        skeletonBuilder.append(commentPrefix).append("@param ");
        skeletonBuilder.append(paramAsDocString(param));
        skeletonBuilder.append("\n");
      }

      for (SdkMethod.SdkMethodParameterOption option : sdkMethod.getOptions()) {
        skeletonBuilder.append(commentPrefix).append("@option ");
        skeletonBuilder.append(optionAsDocString(option));
        skeletonBuilder.append("\n");
      }

      if (sdkMethod.getReturnType() != null || sdkMethod.getReturnComment() != null) {
        skeletonBuilder.append(commentPrefix).append("@return ");

        if (sdkMethod.getReturnType() != null) {
          String returnType = "[" + sdkMethod.getReturnType() + "]";
          skeletonBuilder.append(returnType);
        }
        if (sdkMethod.getReturnComment() != null) {
          skeletonBuilder.append(" ").append(sdkMethod.getReturnComment());
        }

        skeletonBuilder.append("\n");
      }
    }

    if (sdkEntity instanceof SdkField) {
      SdkField sdkField = (SdkField) sdkEntity;
      if (sdkField.getReturnType() != null) {
        String returnType = "[" + sdkField.getReturnType() + "]";
        skeletonBuilder.append(stepPrefix).append("//! @return ").append(returnType).append("\n");
      }
    }
  }

  private static String optionAsDocString(SdkMethod.SdkMethodParameterOption option) {
    // @option options [Drawable] :title the title for the Picker. Required.
    return option.getParameterName() + " " + paramAsDocString(option);
  }

  private static String paramAsDocString(SdkMethod.SdkMethodParameter param) {
    StringBuilder docStringBuilder = new StringBuilder();
    if (param.getType() != null) {
      docStringBuilder.append("[").append(param.getType()).append("] ");
    }
    docStringBuilder.append(param.getSafeName());

    if (param.getDocumentation() != null) {
      docStringBuilder.append(" ").append(param.getDocumentation());
    }
    return docStringBuilder.toString();
  }

  private static void addConstantsToStringBuilder(int step, SdkModuleOrClass sdkModuleOrClass, StringBuilder moduleSkeletonBuilder) {
    String stepPrefix = getStepPrefix(step);
    for (SdkConstant sdkConstant : sdkModuleOrClass.getChildConstants()) {
      addComments(step, moduleSkeletonBuilder, sdkConstant);
      String constantSkeleton = "const " + sdkConstant.getName() + " = " + sdkConstant.getValue() + ";";
      moduleSkeletonBuilder.append(stepPrefix).append(constantSkeleton).append("\n\n");
    }
  }

  private static void addFieldsToStringBuilder(int step, SdkModuleOrClass sdkModuleOrClass, StringBuilder moduleSkeletonBuilder) {
    String stepPrefix = getStepPrefix(step);
    for (SdkField sdkField : sdkModuleOrClass.getChildFields()) {
      addComments(step, moduleSkeletonBuilder, sdkField);
      String constantSkeleton = "var " + sdkField.getName() + ";";
      moduleSkeletonBuilder.append(stepPrefix).append(constantSkeleton).append("\n\n");
    }
  }

  private static void addMethodsToStringBuilder(int step, SdkModuleOrClass sdkModuleOrClass, StringBuilder moduleSkeletonBuilder) {
    String stepPrefix = getStepPrefix(step);
    for (SdkMethod sdkMethod : sdkModuleOrClass.getChildMethods()) {
      addComments(step, moduleSkeletonBuilder, sdkMethod);
      String methodSkeleton = String.format(METHOD_TEMPLATE_START, sdkMethod.getName());
      String params = sdkMethod.getParameters().stream()
        .map(SdkMethod.SdkMethodParameter::getSafeName)
        .collect(Collectors.joining(", "));
      moduleSkeletonBuilder.append(stepPrefix).append(methodSkeleton).append(params).append(") {}").append("\n\n");
    }
  }

  private SdkModuleOrClass parseModule(String moduleHtmlRelativePath, String moduleOrClassName) throws IOException {
    String moduleHtmlPath = this.docDirPath + moduleHtmlRelativePath;
    File moduleHtmlFile = new File(moduleHtmlPath);
    Document moduleDocument = Jsoup.parse(moduleHtmlFile, null);

    String h1 = moduleDocument.select("h1").first().text();
    boolean isModule = h1.startsWith("Module:");
    boolean isClass = h1.startsWith("Class:") || h1.startsWith("Exception:");

    SdkModuleOrClass moduleOrClass;
    if (isModule) {
      SdkModule sdkModule = new SdkModule(moduleOrClassName);
      moduleOrClass = sdkModule;
    } else if (isClass) {
      SdkClass sdkClass = new SdkClass(moduleOrClassName);

      Element parentClassEl = moduleDocument.select(".box .r1 .inheritName .object_link a").first();
      if (parentClassEl != null) {
        // <a href="View.html" title="Toybox::WatchUi::View (class)">View</a>
        String parentClassFqn = trimToNull(parentClassEl.attr("title"));
        if (parentClassFqn != null) {
          sdkClass.setParentClassFqn(parentClassFqn.substring(0, parentClassFqn.length() - 8));
        }
      }

      moduleOrClass = sdkClass;
    } else {
      moduleOrClass = new SdkModule("toplevel");
    }

    findAndSetDocCommentParams(moduleDocument, moduleOrClass);

    for (Element constantEl : moduleDocument.select("dl.constants dt")) {
      addConstant(moduleOrClass, constantEl);
    }
    for (Element fieldEl : moduleDocument.select("#instance_attr_details .method_details ")) {
      addField(moduleOrClass, fieldEl);
    }
    for (Element methodEl : moduleDocument.select("#constructor_details .method_details")) {
      addMethod(moduleOrClass, methodEl);
    }
    for (Element methodEl : moduleDocument.select("#instance_method_details .method_details")) {
      addMethod(moduleOrClass, methodEl);
    }

    //Elements modules1 = moduleDocument.select(".children .modules ~ .object_link");
    //Elements classes1 = moduleDocument.select(".children .classes ~ .object_link");
    // could also get hierarchy from class_list.html
    Elements modulesAndClasses = moduleDocument.select("h2:contains(Defined Under Namespace) ~ .children .object_link");
    for (Element moduleOrClassEl : modulesAndClasses) {
      Element moduleLink = moduleOrClassEl.child(0);
      String childModuleHtmlRelativePath = moduleLink.attr("href");
      String childModuleName = moduleLink.text();

      childModuleHtmlRelativePath = getBeforeIfExists(moduleHtmlRelativePath, "/") + childModuleHtmlRelativePath;

      SdkModuleOrClass childModuleOrClass = parseModule(childModuleHtmlRelativePath, childModuleName);
      moduleOrClass.addChildModuleOrClass(childModuleOrClass);
    }

    return moduleOrClass;
  }

  private void addField(SdkModuleOrClass moduleOrClass, Element fieldEl) {
    Elements signatureEl = fieldEl.select(".signature");
    String fieldName = signatureEl.select("strong").text();
    SdkField sdkField = new SdkField(fieldName);
    findAndSetDocCommentParams(fieldEl, sdkField);
    moduleOrClass.addChildField(sdkField);
  }

  private void addConstant(SdkModuleOrClass moduleOrClass, Element constantEl) {
    String[] nameAndValue = constantEl.ownText().split("=");
    SdkConstant sdkConstant = new SdkConstant(nameAndValue[0].trim(), nameAndValue[1].trim());
    findAndSetDocCommentParams(constantEl, sdkConstant);
    moduleOrClass.addChildConstant(sdkConstant);
  }

  private void addMethod(SdkModuleOrClass moduleOrClass, Element methodEl) {
    Element signatureEl = methodEl.select(".signature").first();
    String methodName = signatureEl.select("strong").text();
    SdkMethod sdkMethod = new SdkMethod(methodName);

    // pre 2.1.5:
    // - (<tt>Object</tt>) <strong>switchToView</strong>(view, delegate, transition)
    // "- () (view, delegate, transition)"
    // "() (view, delegate, transition)"

    // since 2.1.5:
    // #<strong>switchToView</strong>(view, delegate, transition)  ⇒ <tt>Object</tt>
    // "#(view, delegate, transition)  ⇒ "
    // "(view, delegate, transition)"

    String paramNames = signatureEl.ownText()
      .replace("-", "")
      .replace("#", "")
      .replace("⇒", "")
      .trim();

    int iLeftP = paramNames.lastIndexOf("(");
    int iRightP = paramNames.lastIndexOf(")");
    Set<String> validParamNames = new HashSet<>();

    if (iLeftP != -1 && iRightP != -1) {
      String validParamsStr = paramNames.substring(iLeftP + 1, iRightP);
      if (!validParamsStr.isEmpty()) {
        String[] validParams = validParamsStr.split(",");
        for (String validParam : validParams) {
          validParamNames.add(validParam.trim());
        }
      }
    }

    Elements tagsEl = methodEl.select(".tags");

    for (Element methodParamEl : tagsEl.select("ul.param li")) {
      SdkMethod.SdkMethodParameter param = parseParam(methodParamEl);
      String paramName = param.getName();
      if (!validParamNames.contains(paramName) &&
        !paramName.equals("...")// "..." is special case and will be printed as "args" instead
        ) {
        SdkMethod.SdkMethodParameterOption option = paramToOption(param, param.getName());
        sdkMethod.addOption(option); // Some options are marked as mistakenly parameters in SDK API doc
        continue;
      }
      sdkMethod.addParameter(param);
    }

    for (Element optionsList : tagsEl.select("ul.option")) {
      Element optionsTagTitle = optionsList.previousElementSibling();
      String optionsParameterName = "";
      if (optionsTagTitle != null) {
        Elements optionsParameterNameEl = optionsTagTitle.select("tt");
        if (optionsParameterNameEl != null) {
          optionsParameterName = optionsParameterNameEl.text().trim();
        }
      }

      for (Element paramOptionEl : optionsList.select("li")) {
        SdkMethod.SdkMethodParameterOption option = parseParamOption(optionsParameterName, paramOptionEl);
        sdkMethod.addOption(option);
      }
    }

    findAndSetDocCommentParams(methodEl, sdkMethod);

    moduleOrClass.addChildMethod(sdkMethod);
  }

  private static SdkMethod.SdkMethodParameterOption paramToOption(SdkMethod.SdkMethodParameter parameter, String parameterName) {
    SdkMethod.SdkMethodParameterOption option = new SdkMethod.SdkMethodParameterOption(parameter.getName(), parameter.getType(), parameterName);
    option.setDocumentation(parameter.getDocumentation());
    return option;
  }

  private static SdkMethod.SdkMethodParameterOption parseParamOption(String parameterName, Element methodParamOptionEl) {
    SdkMethod.SdkMethodParameter template = parseParam(methodParamOptionEl);
    return paramToOption(template, parameterName);
  }

  private static SdkMethod.SdkMethodParameter parseParam(Element methodParamEl) {
    String paramName = methodParamEl.select(".name").first().text();

    Element paramTypeEl = methodParamEl.select(".type tt").first(); // typeless has .type, but not tt
    String paramType = paramTypeEl == null ? null : trimToNull(paramTypeEl.text().trim());
    String paramComment = trimToNull(methodParamEl.select(".inline p").text());

    SdkMethod.SdkMethodParameter param = new SdkMethod.SdkMethodParameter(paramName, paramType);
    param.setDocumentation(paramComment);
    return param;
  }

  private static void findAndSetDocCommentParams(Element el, SdkEntity sdkEntity) {
    findAndSetTagParams(el, sdkEntity);
    findAndSetCommentParams(el, sdkEntity);
  }

  private static void findAndSetTagParams(Element el, SdkEntity sdkEntity) {
    Element tagsEl = getTagsEl(el, sdkEntity);
    if (tagsEl == null) {
      return;
    }

    findAndSetSince(tagsEl, sdkEntity);
    findAndSetSeeAlsos(tagsEl, sdkEntity);
    findAndSetSupportedDevices(tagsEl, sdkEntity);
    findAndSetPermissions(tagsEl, sdkEntity);
    findAndSetAppTypes(tagsEl, sdkEntity);

    if (sdkEntity instanceof HasReturnType) {
      findAndSetReturnType(tagsEl, (HasReturnType) sdkEntity);
    }
    if (sdkEntity instanceof SdkMethod) {
      findAndSetReturnComment(el, (SdkMethod) sdkEntity);
    }
  }

  private static void findAndSetCommentParams(Element el, SdkEntity sdkEntity) {
    Element docstringEl = getDocstringEl(el, sdkEntity);
    if (docstringEl == null) {
      return;
    }

    findAndSetComment(docstringEl, sdkEntity);
    findAndSetDeprecated(docstringEl, sdkEntity);
  }

  private static Element getDocstringEl(Element parent, SdkEntity sdkEntity) {
    if (sdkEntity instanceof SdkModuleOrClass) {
      return parent.select("h2:contains(Overview) ~ .docstring").first();
    }
    return parent.select(".docstring").first();
  }

  private static Element getTagsEl(Element parent, SdkEntity sdkEntity) {
    if (sdkEntity instanceof SdkModuleOrClass) {
      return parent.select("h2:contains(Overview) ~ .docstring ~ .tags").first();
    }
    return parent.select(".tags").first();
  }

  private static void findAndSetComment(Element docstringEl, SdkEntity sdkEntity) {
    Elements paragraphs = docstringEl.select("p");
    String comment = paragraphs.stream()
      .map(Element::text)
      .collect(Collectors.joining("\n\n"));
    sdkEntity.setDocumentation(trimToNull(comment));
  }

  private static void findAndSetDeprecated(Element docstringEl, SdkEntity sdkEntity) {
    Element deprecatedEl = docstringEl.select(".deprecated").first();
    if (deprecatedEl != null) {
      String deprecatedText = deprecatedEl.select("div.inline").text();
      sdkEntity.setDeprecated(trimToNull(deprecatedText));
    }
  }

  private static void findAndSetSince(Element tagsEl, SdkEntity sdkEntity) {
    for (Element sinceEl : tagsEl.select("ul.since li")) {
      sdkEntity.setSince(trimToNull(sinceEl.text()));
    }
  }

  private static void findAndSetSeeAlsos(Element tagsEl, SdkEntity sdkEntity) {
    for (Element seeEl : tagsEl.select("ul.see li")) {
      String see = seeEl.text();
      //Element seeLink = seeEl.select(".object_link a").first(); // can be null
      //String seeFqn = seeLink.attr("title"); //Toybox::WatchUi::Drawable#initialize (method)
      sdkEntity.addSee(see);
    }
  }

  private static void findAndSetSupportedDevices(Element tagsEl, SdkEntity sdkEntity) {
    for (Element el : tagsEl.select("ul.supported li")) {
      sdkEntity.addSupportedDevice(el.text());
    }
  }

  private static void findAndSetPermissions(Element tagsEl, SdkEntity sdkEntity) {
    for (Element el : tagsEl.select("ul.permission li")) {
      sdkEntity.addPermission(el.text());
    }
  }

  private static void findAndSetAppTypes(Element tagsEl, SdkEntity sdkEntity) {
    for (Element el : tagsEl.select("ul.apptype li")) {
      sdkEntity.addAppType(el.text());
    }
  }

  private static void findAndSetReturnComment(Element tagsEl, SdkMethod sdkMethod) {
    Element returnEl = tagsEl.select("ul.return li").first();
    if (returnEl == null) {
      return;
    }
    Element returnCommentEl = returnEl.select(".inline").first();
    if (returnCommentEl == null) {
      return;
    }
    String returnComment = returnCommentEl.text();
    sdkMethod.setReturnComment(trimToNull(returnComment));
  }

  private static void findAndSetReturnType(Element tagsEl, HasReturnType sdkEntityWithReturnType) {
    Element returnEl = tagsEl.select("ul.return li").first();
    if (returnEl == null) {
      return;
    }
    String returnType = returnEl.select(".type tt").text();
    sdkEntityWithReturnType.setReturnType(trimToNull(returnType));
  }

  private static String getBeforeIfExists(String a, String separator) {
    int endIndex = a.lastIndexOf(separator);
    if (endIndex != -1) {
      return a.substring(0, endIndex + 1);
    }
    return "";
  }


  private static class SdkEntity {
    @NotNull
    public String name;
    private String documentation;
    private String since;

    private List<String> supportedDevices = new ArrayList<>();
    private List<String> see = new ArrayList<>();

    // If empty, then all app types are supported
    // from: "Watch Face", "Data Field", "Widget", "App"
    private List<String> appTypes = new ArrayList<>();

    private List<String> permissions = new ArrayList<>();

    private boolean deprecated;
    // deprecatedText can be null even if deprecated is true
    private String deprecatedText;

    public SdkEntity(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public String getDocumentation() {
      return documentation;
    }

    public void setDocumentation(String documentation) {
      this.documentation = documentation;
    }

    public String getSince() {
      return since;
    }

    public void setSince(String since) {
      this.since = since;
    }

    public List<String> getSupportedDevices() {
      return supportedDevices;
    }

    public void addSupportedDevice(String supportedDevice) {
      this.supportedDevices.add(supportedDevice);
    }

    @Override
    public String toString() {
      return name;
    }

    public void setDeprecated(@Nullable String deprecatedText) {
      this.deprecated = true;
      this.deprecatedText = deprecatedText;
    }

    public boolean isDeprecated() {
      return deprecated;
    }

    public String getDeprecatedText() {
      return deprecatedText;
    }

    public List<String> getSee() {
      return see;
    }

    public void addSee(String see) {
      this.see.add(see);
    }

    public void addPermission(String permission) {
      this.permissions.add(permission);
    }

    public List<String> getPermissions() {
      return permissions;
    }

    public List<String> getAppTypes() {
      return appTypes;
    }


    public void addAppType(String appType) {
      this.appTypes.add(appType);
    }
  }

  private static class SdkModuleOrClass extends SdkEntity {
    // TODO: use the html docs to get all information :(
    //public List<SdkModule> childModules = new ArrayList<>();
    //public List<SdkClass> childClasses = new ArrayList<>();

    public List<SdkModuleOrClass> childModulesOrClasses = new ArrayList<>();

    public List<SdkConstant> childConstants = new ArrayList<>();
    public List<SdkField> childFields = new ArrayList<>();
    public List<SdkMethod> childMethods = new ArrayList<>();


    public SdkModuleOrClass(String name) {
      super(name);
    }

    public List<SdkMethod> getChildMethods() {
      return childMethods;
    }

    public void addChildModuleOrClass(SdkModuleOrClass moduleOrClass) {
      childModulesOrClasses.add(moduleOrClass);
    }

    public void addChildConstant(SdkConstant sdkConstant) {
      childConstants.add(sdkConstant);
    }

    public void addChildField(SdkField sdkField) {
      childFields.add(sdkField);
    }

    public void addChildMethod(SdkMethod sdkMethod) {
      childMethods.add(sdkMethod);
    }

    public List<SdkModuleOrClass> getChildModulesOrClasses() {
      return childModulesOrClasses;
    }

    public List<SdkConstant> getChildConstants() {
      return childConstants;
    }

    public List<SdkField> getChildFields() {
      return childFields;
    }


  }

  private static class SdkModule extends SdkModuleOrClass {
    public SdkModule(String name) {
      super(name);
    }
  }

  private static class SdkClass extends SdkModuleOrClass {
    private String parentClassFqn;
    private String parentClassName;

    public SdkClass(String name) {
      super(name);
    }

    // TODO: better to set link for parent SdkClass
    // fqn = fully qualified name
    // e.g Toybox::WatchUi::View
    public void setParentClassFqn(String parentClassFqn) {
      this.parentClassFqn = parentClassFqn;
      if (parentClassFqn != null) {
        int endIndex = parentClassFqn.lastIndexOf("::");
        if (endIndex != -1) {
          this.parentClassName = parentClassFqn.substring(endIndex + 2);
        }
      }
    }

    public String getParentClassFqn() {
      return parentClassFqn;
    }

    public String getParentClassName() {
      return parentClassName;
    }
  }


  private static class SdkConstant extends SdkEntity {
    private final String value;

    public SdkConstant(String name, String value) {
      super(name);
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  private interface HasReturnType {
    void setReturnType(String returnType);

    String getReturnType();
  }

  private static class SdkField extends SdkEntity implements HasReturnType {
    private String returnType;

    public SdkField(String name) {
      super(name);
    }

    @Override
    public void setReturnType(String returnType) {
      this.returnType = returnType;
    }

    @Override
    public String getReturnType() {
      return returnType;
    }
  }

  private static class SdkMethod extends SdkEntity implements HasReturnType {
    private String returnType;
    private String returnComment;

    public List<SdkMethodParameter> parameters = new ArrayList<>();
    public List<SdkMethodParameterOption> options = new ArrayList<>();

    public SdkMethod(String name) {
      super(name);
    }

    @Override
    public void setReturnType(String returnType) {
      this.returnType = returnType;
    }

    @Override
    public String getReturnType() {
      return returnType;
    }

    public String getReturnComment() {
      return returnComment;
    }

    public void setReturnComment(String returnComment) {
      this.returnComment = returnComment;
    }

    public List<SdkMethodParameter> getParameters() {
      return parameters;
    }

    // would be more correct to set options under parameter
    public List<SdkMethodParameterOption> getOptions() {
      return options;
    }

    public void addParameter(SdkMethodParameter parameter) {
      parameters.add(parameter);
    }

    public void addOption(SdkMethodParameterOption option) {
      options.add(option);
    }

    private static class SdkMethodParameterOption extends SdkMethodParameter {
      private String parameterName;

      public SdkMethodParameterOption(String name, String type, String parameterName) {
        super(name, type);
        this.parameterName = parameterName;
      }

      public String getParameterName() {
        return parameterName;
      }
    }

    private static class SdkMethodParameter extends SdkEntity {
      private String type;

      public SdkMethodParameter(String name, String type) {
        super(name);
        this.type = type;
      }

      public String getType() {
        return type;
      }

      public String getSafeName() {
        if (name.equals("...")) {
          return "args";
        }
        return getName();
      }
    }
  }
}


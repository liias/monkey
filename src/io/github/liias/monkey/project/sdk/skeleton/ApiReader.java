package io.github.liias.monkey.project.sdk.skeleton;

import com.intellij.openapi.util.Pair;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ApiReader {
  public static final String API_DEBUG_XML = "api.debug.xml";


  private static String TAG_SYMBOL_TABLE = "symbolTable";
  private static String ATTR_SYMBOL = "symbol";

  private static String ATTR_METHOD = "method";
  private static String ATTR_MODULE = "module";
  private static String ATTR_FIELD = "field";
  private static String ATTR_OBJECT = "object";

  private static String TAG_FUNCTION_TABLE = "functions";
  private static String ATTR_NAME = "name";
  private static String ATTR_PARENT = "parent";
  private static String CHILD_FUNCTION_ENTRY = "functionEntry";
  private static String TAG_DOCUMENTATION = "documentation";
  private static String TAG_PARAM = "param";
  private static String ATTR_ID = "id";

  // includes / in the end
  private final String sdkBinPath;

  //List<MonkeyCMethod> mMethods = new ArrayList<>();
  //List<MonkeyCSymbol> mFields = new ArrayList<>();
  //List<MonkeyCSymbol> mModules = new ArrayList<>();
  //List<MonkeyCSymbol> mClasses = new ArrayList<>();

  private List<SdkModule> sdkModules;
  private List<SdkClass> sdkClasses;


  public ApiReader(String sdkBinPath) {
    this.sdkBinPath = sdkBinPath;
  }

  public String parseApiDebugXml() {
    String apiDebugXmlPath = sdkBinPath + API_DEBUG_XML;

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new File(apiDebugXmlPath));
      importDebugTable2(doc);
    } catch (SAXException | IOException | ParserConfigurationException e) {
      e.printStackTrace();
    }
    return generateSkeletons();
  }

  private String generateSkeletons() {
    String moduleTemplateStart = "module %s {\n";
    String classTemplateStart = "class %s {\n";
    StringBuilder builder = new StringBuilder();
    builder.append("// This is generated Connect IQ API stub, do not edit this!\n\n");

    for (SdkModule sdkModule : sdkModules) {
      StringBuilder moduleSkeletonBuilder = new StringBuilder()
          .append(String.format(moduleTemplateStart, sdkModule.getName()));
      addChildMethodsToStringBuilder(sdkModule, moduleSkeletonBuilder);
      moduleSkeletonBuilder.append("}");
      builder.append(moduleSkeletonBuilder.toString()).append("\n\n");
    }

    for (SdkClass sdkClass : sdkClasses) {
      StringBuilder classSkeletonBuilder = new StringBuilder()
          .append(String.format(classTemplateStart, sdkClass.getName()));
      addChildMethodsToStringBuilder(sdkClass, classSkeletonBuilder);
      classSkeletonBuilder.append("}");
      builder.append(classSkeletonBuilder.toString()).append("\n\n");
    }

    String modulesAndClassesSkeleton = builder.toString();

    return modulesAndClassesSkeleton;
  }

  private void addChildMethodsToStringBuilder(SdkModuleOrClass sdkModuleOrClass, StringBuilder moduleSkeletonBuilder) {
    String methodTemplateStart = "  function %s(";

    for (SdkMethod sdkMethod : sdkModuleOrClass.getChildMethods()) {
      String methodSkeleton = String.format(methodTemplateStart, sdkMethod.getName());
      String params = sdkMethod.getParameters().stream()
          .map(SdkMethod.SdkMethodParameter::getName)
          .collect(Collectors.joining(", "));
      moduleSkeletonBuilder.append(methodSkeleton).append(params).append(") {}").append("\n\n");
    }
  }

  /*
  <entry id="8389701" object="true" symbol="HeartRateIterator"/>
<entry id="8388648" module="true" symbol="WatchUi"/> tüüp ongi attribute

module, object, method, field
   */

  private static class SdkEntity {
    public String name;

    public SdkEntity(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  private static class SdkModuleOrClass extends SdkEntity {
    // TODO: use the html docs to get all information :(
    //public List<SdkModule> childModules = new ArrayList<>();
    //public List<SdkClass> childClasses = new ArrayList<>();

    public List<SdkMethod> childMethods = new ArrayList<>();

    public SdkModuleOrClass(String name) {
      super(name);
    }

    public List<SdkMethod> getChildMethods() {
      return childMethods;
    }

    public void addChild(SdkMethod sdkMethod) {
      childMethods.add(sdkMethod);
    }
  }

  private static class SdkModule extends SdkModuleOrClass {
    public SdkModule(String name) {
      super(name);
    }
  }

  private static class SdkClass extends SdkModuleOrClass {
    public SdkClass(String name) {
      super(name);
    }
  }


  private static class SdkField extends SdkEntity {
    public SdkField(String name) {
      super(name);
    }
  }

  private static class SdkMethod extends SdkEntity {
    private String documentation;
    public List<SdkMethodParameter> parameters = new ArrayList<>();
    public SdkModuleOrClass parent;

    public SdkMethod(String name, String documentation) {
      super(name);
      this.documentation = documentation;
    }

    public List<SdkMethodParameter> getParameters() {
      return parameters;
    }

    public void addParameter(SdkMethodParameter parameter) {
      parameters.add(parameter);
    }

    public void setParent(SdkModuleOrClass parentModuleOrClass) {
      parent = parentModuleOrClass;
      parent.addChild(this);
    }

    private static class SdkMethodParameter extends SdkEntity {
      public String type;
      private String documentation;

      public SdkMethodParameter(String name, String methodDocumentation) {
        super(name);
        Pair<String, String> typeAndDoc = parseDocumentation(name, methodDocumentation);
        this.type = typeAndDoc.getFirst();
        this.documentation = typeAndDoc.getSecond();
      }

      private static Pair<String, String> parseDocumentation(String name, String methodDocumentation) {
        String tempType = null;
        String tempDocumentation = null;

        int paramIndex = methodDocumentation.indexOf("<div class=\"param\">");
        while (paramIndex != -1) {
          int paramIndexEnd = methodDocumentation.indexOf("</div>", paramIndex);
          String description = methodDocumentation.substring(paramIndex + 19, paramIndexEnd).trim();
          String paramName = description;
          int firstSpaceIndex = paramName.indexOf(" ");
          if (firstSpaceIndex != -1) {
            paramName = paramName.substring(0, firstSpaceIndex);
          }
          if (paramName.equals(name)) {
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
          paramIndex = methodDocumentation.indexOf("<div class=\"param\">", paramIndexEnd);
        }
        return new Pair<>(tempType, tempDocumentation);
      }
    }
  }


  private void importDebugTable2(Document doc) {
    Element rootElement = doc.getDocumentElement();
    Node symbolTable = rootElement.getElementsByTagName(TAG_SYMBOL_TABLE).item(0);
    NodeList entries = symbolTable.getChildNodes();


    List<NamedNodeMap> entriesList = new ArrayList<>();
    for (int i = 0; i < entries.getLength(); i++) {
      Node entry = entries.item(i);
      NamedNodeMap attributes = entry.getAttributes();
      if (attributes == null || attributes.getNamedItem(ATTR_SYMBOL) == null) {
        continue;
      }
      entriesList.add(attributes);
    }

    List<SdkModule> sdkModules = entriesList.stream()
        .filter(e -> e.getNamedItem(ATTR_MODULE) != null)
        .map(e -> {
          String name = e.getNamedItem(ATTR_SYMBOL).getNodeValue();
          return new SdkModule(name);
        }).collect(Collectors.toList());
    this.sdkModules = sdkModules;

    List<SdkClass> sdkClasses = entriesList.stream()
        .filter(e -> e.getNamedItem(ATTR_OBJECT) != null)
        .map(e -> {
          String name = e.getNamedItem(ATTR_SYMBOL).getNodeValue();
          return new SdkClass(name);
        }).collect(Collectors.toList());
    this.sdkClasses = sdkClasses;


    Node functionTable = rootElement.getElementsByTagName(TAG_FUNCTION_TABLE).item(0);
    NodeList functionEntries = functionTable.getChildNodes();
    List<Element> functionEntriesList = new ArrayList<>();
    for (int i = 0; i < functionEntries.getLength(); i++) {
      Node functionEntry = functionEntries.item(i);
      if (functionEntry.getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }
      NamedNodeMap attributes = functionEntry.getAttributes();
      if (attributes == null || attributes.getNamedItem(ATTR_NAME) == null) {
        continue;
      }

      functionEntriesList.add((Element) functionEntry);
    }

    List<SdkMethod> sdkMethods = functionEntriesList.stream()
        .map(n -> {
          NamedNodeMap attributes = n.getAttributes();
          String name = attributes.getNamedItem(ATTR_NAME).getNodeValue();

          String methodDocumentation = n.getElementsByTagName(TAG_DOCUMENTATION).item(0).getTextContent();
          SdkMethod sdkMethod = new SdkMethod(name, methodDocumentation);

          NodeList paramNodes = n.getElementsByTagName(TAG_PARAM);
          for (int j = 0; j < paramNodes.getLength(); j++) {
            NamedNodeMap paramAttributes = paramNodes.item(j).getAttributes();
            if ((paramAttributes != null) && (paramAttributes.getNamedItem(ATTR_ID) != null)) {
              String paramName = paramAttributes.getNamedItem(ATTR_ID).getNodeValue();
              SdkMethod.SdkMethodParameter sdkMethodParameter = new SdkMethod.SdkMethodParameter(paramName, methodDocumentation);
              sdkMethod.addParameter(sdkMethodParameter);
            }
          }

          // Either parent class or parent module
          String parentName = attributes.getNamedItem(ATTR_PARENT).getNodeValue();
          if (parentName != null) {
            Optional<SdkModule> parentModuleMaybe = sdkModules.stream().filter(m -> m.getName().equals(parentName)).findFirst();
            if (parentModuleMaybe.isPresent()) {
              sdkMethod.setParent(parentModuleMaybe.get());
            } else {
              Optional<SdkClass> parentClassMaybe = sdkClasses.stream().filter(m -> m.getName().equals(parentName)).findFirst();
              if (parentClassMaybe.isPresent()) {
                sdkMethod.setParent(parentClassMaybe.get());
              }
            }
          }
          return sdkMethod;
        })
        .collect(Collectors.toList());

    List<SdkField> sdkFields = entriesList.stream()
        .filter(e -> e.getNamedItem(ATTR_FIELD) != null)
        .map(e -> {
          String name = e.getNamedItem(ATTR_SYMBOL).getNodeValue();
          return new SdkField(name);
        }).collect(Collectors.toList());


    // first all modules
    // then all objects (classes)
    // then methods
    // then fields (when adding parent find from modules and objects for linkage)
    // then rest
  }

/*
  private void importDebugTable(Document doc) {
    Element rootElement = doc.getDocumentElement();
    Node symbolTable = rootElement.getElementsByTagName(TAG_SYMBOL_TABLE).item(0);
    NodeList entries = symbolTable.getChildNodes();

    Node functionTable = rootElement.getElementsByTagName(TAG_FUNCTION_TABLE).item(0);

    for (int i = 0; i < entries.getLength(); i++) {
      Node entry = entries.item(i);
      NamedNodeMap attributes = entry.getAttributes();
      if (attributes != null && attributes.getNamedItem(ATTR_SYMBOL) != null) {

        if (attributes.getNamedItem(ATTR_METHOD) != null) {
          String name = attributes.getNamedItem(ATTR_SYMBOL).getNodeValue();

          Node functionEntry = functionTable.getFirstChild();
          while (functionEntry != null) {
            if (functionEntry.getNodeName().equals(CHILD_FUNCTION_ENTRY)) {
              NamedNodeMap childAttributes = functionEntry.getAttributes();
              if ((childAttributes != null) && (childAttributes.getNamedItem(ATTR_NAME) != null) &&
                  (name.equals(childAttributes.getNamedItem(ATTR_NAME).getNodeValue())) &&
                  (functionEntry.getNodeType() == Node.ELEMENT_NODE)) {
                Element eChild = (Element) functionEntry;

                // Either parent class or parent module
                String methodParent = null;
                if (childAttributes.getNamedItem(ATTR_PARENT) != null) {
                  methodParent = childAttributes.getNamedItem(ATTR_PARENT).getNodeValue();
                }
                MonkeyCMethod method = new MonkeyCMethod(methodParent);
                method.setName(name);

                String documentation = eChild.getElementsByTagName(TAG_DOCUMENTATION).item(0).getTextContent();
                method.setDocumentation(documentation);

                NodeList paramNodes = eChild.getElementsByTagName(TAG_PARAM);
                for (int j = 0; j < paramNodes.getLength(); j++) {
                  NamedNodeMap paramAttributes = paramNodes.item(j).getAttributes();
                  if ((paramAttributes != null) && (paramAttributes.getNamedItem(ATTR_ID) != null)) {
                    method.addParameter(new MonkeyCMethodParam(paramAttributes.getNamedItem(ATTR_ID).getNodeValue(), documentation));
                  }
                }
                if (!mMethods.contains(method)) {
                  mMethods.add(method);
                }
              }
            }
            functionEntry = functionEntry.getNextSibling();
          }
        }

        if (attributes.getNamedItem(ATTR_MODULE) != null) {
          MonkeyCSymbol module = new MonkeyCSymbol();
          module.setName(attributes.getNamedItem(ATTR_SYMBOL).getNodeValue());
          if ((module.startsWith("Toybox")) || (module.startsWith("globals"))) {
            module.replace("globals_", "");
            module.replace("_", "");
          }
          mModules.add(module);
        }

        if (attributes.getNamedItem(ATTR_FIELD) != null) {
          MonkeyCSymbol field = new MonkeyCSymbol();
          field.setName(attributes.getNamedItem(ATTR_SYMBOL).getNodeValue());
          mFields.add(field);
        }

        if (attributes.getNamedItem(ATTR_OBJECT) != null) {
          MonkeyCSymbol monkeyClass = new MonkeyCSymbol();
          monkeyClass.setName(attributes.getNamedItem(ATTR_SYMBOL).getNodeValue());
          mClasses.add(monkeyClass);
        }

      }
    }

    System.out.println("valmis");
  }
  */
}

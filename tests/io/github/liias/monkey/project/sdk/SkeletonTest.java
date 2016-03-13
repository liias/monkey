package io.github.liias.monkey.project.sdk;

import org.junit.Test;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SkeletonTest {

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

  List<MonkeyCSymbol> mMethods = new ArrayList<>();
  List<MonkeyCSymbol> mFields = new ArrayList<>();
  List<MonkeyCSymbol> mModules = new ArrayList<>();
  List<MonkeyCSymbol> mClasses = new ArrayList<>();

  @Test
  public void testSomething() {

    String mbPath = "C:\\Users\\madis\\Downloads\\iqsdk" + File.separator + "bin" + File.separator + API_DEBUG_XML;

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new File(mbPath));
      importDebugTable(doc);
    } catch (SAXException | IOException | ParserConfigurationException e) {
      e.printStackTrace();
    }

    assertThat("a", equalTo("a"));
  }

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
                  (functionEntry.getNodeType() == 1)) {
                Element eChild = (Element) functionEntry;

                String parentMethodName = null;
                if (childAttributes.getNamedItem(ATTR_PARENT) != null) {
                  parentMethodName = childAttributes.getNamedItem(ATTR_PARENT).getNodeValue();
                }
                MonkeyCSymbol method = new MonkeyCMethod(parentMethodName);
                method.setName(name);

                String documentation = eChild.getElementsByTagName(TAG_DOCUMENTATION).item(0).getTextContent();
                method.setDocumentation(documentation);

                NodeList paramNodes = eChild.getElementsByTagName(TAG_PARAM);
                for (int j = 0; j < paramNodes.getLength(); j++) {
                  NamedNodeMap paramAttributes = paramNodes.item(j).getAttributes();
                  if ((paramAttributes != null) && (paramAttributes.getNamedItem(ATTR_ID) != null)) {
                    ((MonkeyCMethod) method).addParameter(new MonkeyCMethodParam(paramAttributes.getNamedItem(ATTR_ID).getNodeValue(), documentation));
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
}

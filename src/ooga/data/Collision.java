package ooga.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import ooga.engine.GameException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Collision {
  private Map<Pair<String, String>, Set<String>> collisionMap = new HashMap<>();

  public Collision (PathManager pathManager) {
    Document xmlFile = convertXMLFileToXMLDocument(pathManager.getFilePath(PathManager.COLLISIONS));
    getCollisionRulesFromXML(xmlFile);
  }

  public Collision (String filepath) {
    Document xmlFile = convertXMLFileToXMLDocument(filepath);
    getCollisionRulesFromXML(xmlFile);
  }

  public Set<String> getActions(Integer status, String sprite1, String sprite2) {
    Pair<String, String> set = new Pair<>(sprite1, sprite2);
    return collisionMap.get(set);
  }

  public Map<Pair<String, String>, Set<String>> getCollisionRules() {
    return collisionMap;
  }

  private void getCollisionRulesFromXML(Document collisions) {
    System.out.println("Start");
    for (int i = 0; i < collisions.getChildNodes().item(0).getChildNodes().getLength(); i++) {
      if (collisions.getChildNodes().item(0).getChildNodes().item(i).getNodeName()
          .compareTo("#text") != 0) {
        Node node = collisions.getChildNodes().item(0).getChildNodes().item(i);
        String sprite1 =
            node.getNodeName() + node.getAttributes().getNamedItem("status").getNodeValue();
        for (int j = 0; j < node.getChildNodes().getLength(); j++) {
          Pair<String, String> keys;
          HashSet<String> values = new HashSet<>();
          Node subnode2 = node.getChildNodes().item(j);
          if (subnode2.getNodeName().compareTo("#text") != 0) {
            String sprite2 =
                subnode2.getNodeName() + subnode2.getAttributes().getNamedItem("status")
                    .getNodeValue();
            if (subnode2.getNodeName().compareTo("#text") != 0) {
              keys = new Pair<>(sprite1, sprite2);
              for (int l = 0; l < subnode2.getChildNodes().getLength(); l++) {
                Node subnode3 = subnode2.getChildNodes().item(l);
                if (subnode3.getNodeName().compareTo("#text") != 0) {
                  values.add(subnode3.getTextContent());
                }
              }
              collisionMap.put(keys, values);
            }
          }
        }
      }
    }
  }

  /**
   * Method from https://howtodoinjava.com/xml/xml-to-string-write-xml-file/
   */
  private Document convertXMLFileToXMLDocument(String filePath)
  {
    //Parser that produces DOM object trees from XML content
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    //API to obtain DOM Document instance
    DocumentBuilder builder = null;
    try
    {
      //Create DocumentBuilder with default configuration
      builder = factory.newDocumentBuilder();

      //Parse the content to Document object
      return builder.parse(new File(filePath));
    }
    catch (Exception e)
    {
      throw new GameException(e.getMessage());
    }
  }
}

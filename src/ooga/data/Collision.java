package ooga.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javafx.util.Pair;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Collision {
  private ArrayList<HashMap<Pair<String, String>, HashSet<String>>> collisionList = new ArrayList<>();

  public Collision () {
    PathManager pathManager = new PathManager();
    Document xmlFile = convertXMLFileToXMLDocument(pathManager.getFilePath(PathManager.COLLISIONS));
    getCollisionRules(xmlFile);
  }

  public Collision (String filepath) {
    Document xmlFile = convertXMLFileToXMLDocument(filepath);
    getCollisionRules(xmlFile);
  }

  public Set<String> getActions(Integer status, String sprite1, String sprite2) {
    Pair<String, String> set = new Pair<>(sprite1, sprite2);
    return collisionList.get(status-1).get(set);
  }

  private void getCollisionRules(Document collisions) {
    for (int i = 0; i < collisions.getChildNodes().item(0).getChildNodes().getLength(); i++){
      HashMap<Pair<String, String>, HashSet<String>> map = new HashMap<>();
      if (collisions.getChildNodes().item(0).getChildNodes().item(i).getNodeName().compareTo("#text") != 0){
        Node node = collisions.getChildNodes().item(0).getChildNodes().item(i);
          for (int j = 0; j < node.getChildNodes().getLength(); j++) {
            Node subnode = node.getChildNodes().item(j);
              for (int k = 0; k < subnode.getChildNodes().getLength(); k++) {
                Pair<String, String> keys;
                HashSet<String> values = new HashSet<>();
                Node subnode2 = subnode.getChildNodes().item(k);
                if (subnode2.getNodeName().compareTo("#text") != 0) {
                  keys = new Pair<>(subnode2.getParentNode().getNodeName(), subnode2.getNodeName());
                  for (int l = 0; l < subnode2.getChildNodes().getLength(); l++) {
                    Node subnode3 = subnode2.getChildNodes().item(l);
                    if (subnode3.getNodeName().compareTo("#text") != 0) {
                      values.add(subnode3.getTextContent());
                    }
                  }
                  map.put(keys, values);
                }
              }
            }
        collisionList.add(map);
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
      e.printStackTrace();
    }
    return null;
  }

  public String toString(){
    StringBuilder s = new StringBuilder();
    for (HashMap<Pair<String, String>, HashSet<String>> h: collisionList){
      s.append(h.toString());
    }
    return s.toString();
  }

}

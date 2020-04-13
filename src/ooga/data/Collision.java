package ooga.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Collision {
  private ArrayList<HashMap<HashSet<String>, HashSet<String>>> collisionList = new ArrayList<>();

  public Collision (String filePath) {
    Document xmlFile = convertXMLFileToXMLDocument(filePath);
    getCollisionRules(xmlFile);
  }

  public List<String> getActions(String sprite1, String sprite2) {


    return null;
  }

  public List getCollisionRules(Document collisions) {

    for (int i = 0; i < collisions.getChildNodes().item(0).getChildNodes().getLength(); i++){
      HashSet<String> keys = new HashSet<>();
      HashSet<String> values = new HashSet<>();
      HashMap<HashSet<String>, HashSet<String>> map = new HashMap<>();
      if (collisions.getChildNodes().item(0).getChildNodes().item(i).getNodeName().compareTo("#text") != 0){
        Node node = collisions.getChildNodes().item(0).getChildNodes().item(i);
//        System.out.println(node.getTextContent());
        for (int j = 0; j < node.getChildNodes().getLength(); j++){
          Node subnode = node.getChildNodes().item(j);
          for (int k = 0; k < subnode.getChildNodes().getLength(); k++){
            Node subnode2 = subnode.getChildNodes().item(k);
            if(subnode2.getNodeName().compareTo("#text") != 0) {
              System.out.println("#########################");
              System.out.println(subnode2.getParentNode().getNodeName());
              keys.add(subnode2.getParentNode().getNodeName());
              System.out.println(subnode2.getNodeName());
              keys.add(subnode2.getNodeValue());
              for (int l = 0; l < subnode2.getChildNodes().getLength(); l++) {
                Node subnode3 = subnode2.getChildNodes().item(l);
                if (subnode3.getNodeName().compareTo("#text") != 0) {
                  System.out.println(subnode3.getTextContent());
                  values.add(subnode3.getTextContent());
                }
              }
              map.put(keys, values);
              System.out.println("#########################");
            }
          }
        }
      }
//      System.out.println(collisions.getChildNodes().item(0).getChildNodes().item(i).getTextContent());
      collisionList.add(map);
    }
    return null;
  }

  /**
   * Method from https://howtodoinjava.com/xml/xml-to-string-write-xml-file/
   */
  private static Document convertXMLFileToXMLDocument(String filePath)
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

}

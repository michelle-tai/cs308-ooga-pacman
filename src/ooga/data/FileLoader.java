package ooga.data;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class FileLoader {

  public FileLoader() {

  }

  public static Map getCollisionRules(Document collisions) {
    HashSet<String> keys = new HashSet<>();
    HashSet<String> values = new HashSet<>();

    for (int i = 0; i < collisions.getChildNodes().item(0).getChildNodes().getLength(); i++){
      if (collisions.getChildNodes().item(0).getChildNodes().item(i).getNodeName().compareTo("#text") != 0){
        Node node = collisions.getChildNodes().item(0).getChildNodes().item(i);
//        System.out.println(node.getTextContent());
        for (int j = 0; j < node.getChildNodes().getLength(); j++){
          Node subnode = node.getChildNodes().item(j);
          for (int k = 0; k < subnode.getChildNodes().getLength(); k++){
            Node subnode2 = subnode.getChildNodes().item(k);
            if(subnode2.getNodeName().compareTo("#text") != 0){
              System.out.println("#########################");
              System.out.println(subnode2.getParentNode().getNodeName());
              System.out.println(subnode2.getNodeName());
              System.out.println(subnode2.getTextContent());
              System.out.println("#########################");
            }
          }
        }
      }
//      System.out.println(collisions.getChildNodes().item(0).getChildNodes().item(i).getTextContent());
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

  public static void main (String[] args) {
//    System.out.println(Objects
//        .requireNonNull(convertXMLFileToXMLDocument("src/resources/collisions/collisions.xml"))
//        .getElementsByTagName("PacMan").item(0).getTextContent());
    getCollisionRules(Objects
        .requireNonNull(convertXMLFileToXMLDocument("src/resources/collisions/collisions.xml")));
  }
}

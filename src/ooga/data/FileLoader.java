package ooga.data;

import java.io.File;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class FileLoader {

  public FileLoader() {

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
    System.out.println(Objects
        .requireNonNull(convertXMLFileToXMLDocument("src/resources/collisions/collisions.xml")).getDocumentElement().getChildNodes());
  }
}

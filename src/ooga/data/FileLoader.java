package ooga.data;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FileLoader {
  public FileLoader() {

  }

  private Document parseXmlFile(File xmlDoc) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    Document doc = null;
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      doc = builder.parse(xmlDoc);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      System.out.println("ex");
    }
    return (doc);
  }

  public static void main (String[] args) {
    System.out.println(parseXmlFile(new File("resources.")));
  }
}

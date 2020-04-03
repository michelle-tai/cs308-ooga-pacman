/**
 * Loads file information
 */
public interface FileLoader {

  // Returns the content of a file as a ResourceBundle
  public ResourceBundle getFileContent(String filepath);

}
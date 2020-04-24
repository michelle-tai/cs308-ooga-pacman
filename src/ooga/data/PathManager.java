package ooga.data;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PathManager {

  public static final String COLLISIONS = "Collisions";
  public static final String ERRORS = "Errors";
  public static final String PROPERTIES = "GameProperties";
  public static final String RULES = "Rules";
  public static final String LEVELS = "Levels";
  public static final String BLOCKIMAGE = "BlockImage";
  public static final String CHERRYIMAGE = "CherryImage";
  public static final String FOODIMAGES = "FoodImage";
  public static final String PLAYPAUSEIMAGE = "PlayPauseImage";
  public static final String DARKFORMAT =  "DarkFormat";
  public static final String LIGHTFORMAT = "LightFormat";
  public static final String STARTFORMAT = "StartFormat";
  public static final String GHOSTIMAGES = "GhostImages";
  public static final String SCAREDGHOST = "ScaredGhost";
  public static final String PACKMANIMAGE = "PackManImages";
  public static final String ENGLISHBUTTONS = "EnglishButtons";

  private ResourceBundle filePaths;
  private String gamePath;

  public PathManager(String game) {
    gamePath = game;
    filePaths = ResourceBundle.getBundle(game + "/FilePaths");
  }

  public String getFilePath(String keyWord){
    return filePaths.getString(keyWord);
  }

  public String getFilePath(String keyWord, Integer index) {
    List<String> list = Arrays.asList(filePaths.getString(keyWord).split(","));
    if (index >= list.size()) {
      System.out.println("WARNING: Key requested does not have an associated image.");
      index = list.size()-1;
    }
    return list.get(index);
  }

  public ResourceBundle getResourceBundle (String name) {
    return ResourceBundle.getBundle(getFilePath(name));
    // TODO add error checking
  }

  public String getString (String bundlePath, String key) {
    ResourceBundle bundle = ResourceBundle.getBundle(gamePath+ "/" +bundlePath);
    return bundle.getString(key);
  }
}

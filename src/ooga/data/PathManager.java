package ooga.data;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import ooga.engine.GameException;

public class PathManager {
  public static final ResourceBundle GUI_RESOURCES = ResourceBundle.getBundle("GUIPaths");

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
  public static final String ERROR_MESSAGES = "ErrorMessages";

  private ResourceBundle filePaths;
  private String gamePath;

  public PathManager(String game) {
    gamePath = game;
    filePaths = ResourceBundle.getBundle(game + "/FilePaths");
  }

  public String getFilePath(String keyWord){
    try {
      return filePaths.getString(keyWord);
    } catch (RuntimeException e){
      throw new GameException(e.getMessage());
    }
  }

  public String getFilePath(String keyWord, Integer index) {
    List<String> list = Arrays.asList(filePaths.getString(keyWord).split(","));
    if (index >= list.size()) {
      throw new GameException("Key requested does not have an associated image");
    }
    return list.get(index);
  }

  public ResourceBundle getResourceBundle (String name) {
    try{
      return ResourceBundle.getBundle(getFilePath(name));
    } catch(RuntimeException e){
      throw new GameException(e.getMessage());
    }
  }

  public String getString (String bundlePath, String key) {
    try{
      ResourceBundle bundle = ResourceBundle.getBundle(gamePath+ "/" +bundlePath);
      return bundle.getString(key);
    } catch(RuntimeException e){
      throw new GameException(e.getMessage());
    }
  }
}

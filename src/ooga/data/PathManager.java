package ooga.data;

import java.util.ResourceBundle;

public class PathManager {
  public static final String COLLISIONS = "Collisions";
  public static final String ERRORS = "Errors";
  public static final String PROPERTIES = "Properties";
  public static final String RULES = "Rules";
  public static final String LEVELS = "Levels";
  public static final String BLOCKIMAGE = "BlockImage";
  public static final String CHERRYIMAGE = "CherryImage";
  public static final String FOODIMAGE = "FoodImage";
  public static final String DARKFORMAT =  "DarkFormat";
  public static final String LIGHTFORMAT = "LightFormat";
  public static final String STARTFORMAT = "StartFormat";
  public static final String GHOSTIMAGE = "GhostImages";
  public static final String PACKMANIMAGE = "PackManImage";

  private ResourceBundle filePaths;

  public PathManager(String filename) {
    filePaths = ResourceBundle.getBundle(filename);
  }

  public String getPath(String key){
    return filePaths.getString(key);
  }
}

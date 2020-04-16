package ooga.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PathManager {
  public static final String FILEPATHS = "FilePaths";

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
  public static final String GHOSTIMAGES = "GhostImages";
  public static final String PACKMANIMAGE = "PackManImages";

  private ResourceBundle filePaths;

  public PathManager() {
    filePaths = ResourceBundle.getBundle(FILEPATHS);
  }

  public String getFilePath(String key){
    return filePaths.getString(key);
  }

  public String getGhostPath(Integer index) {
    List<String> ghostList = Arrays.asList(filePaths.getString(GHOSTIMAGES).split(","));
    if (index >= ghostList.size()) {
      System.out.println("WARNING: Ghost requested does not have an associated image.");
      index = ghostList.size()-1;
    }
    return ghostList.get(index);
  }

  public String getPacManPath(Integer index) {
    List<String> pacmanList = Arrays.asList(filePaths.getString(PACKMANIMAGE).split(","));
    if (index >= pacmanList.size()) {
      System.out.println("WARNING: PacMan requested does not have an associated image.");
      index = pacmanList.size()-1;
    }
    return pacmanList.get(index);
  }
}

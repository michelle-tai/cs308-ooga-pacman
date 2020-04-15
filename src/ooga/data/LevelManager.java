package ooga.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {
  private File[] levelFiles;
  private List<Level> myLevels = new ArrayList<>();

  public LevelManager() {
    getAllLevelFiles();
    createLevels();
  }

  public Level getLevel(Integer index){
    return myLevels.get(index);
  }

  private void getAllLevelFiles() {
    File levelFolder = new File(PathManager.LEVELS);
    levelFiles = levelFolder.listFiles();
  }

  private void createLevels() {
    for (File f: levelFiles){
      myLevels.add(new Level(f));
    }
  }
}

package ooga.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {
  private File[] levelFiles;
  private List<Level> myLevels = new ArrayList<>();

  private Level currentLevel;

  public LevelManager() {
    getAllLevelFiles();
    createLevels();
    currentLevel = getLevel(1);
  }

  public Level getCurrentLevel() {
    return currentLevel;
  }

  public void setCurrentLevel(Integer level) {
    currentLevel = getLevel(level);
  }

  public Level getLevel(Integer index){
    return myLevels.get(index);
  }

  public Integer getNumberOfLevels() {
    return myLevels.size();
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

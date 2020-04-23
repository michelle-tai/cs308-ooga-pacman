package ooga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {
  private File[] levelFiles;
  private List<Level> myLevels = new ArrayList<>();
  private PathManager myPathManager;

  private Level currentLevel;

  public LevelManager(String gamePath) {
    myPathManager = new PathManager(gamePath);
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
    File levelFolder = new File(myPathManager.getFilePath(PathManager.LEVELS));
    levelFiles = levelFolder.listFiles();
  }

  private void createLevels() {
    for (File f: levelFiles){
      myLevels.add(createLevelFromFile(f));
    }
  }

  public PathManager getPathManager() {
    return myPathManager;
  }

  private Level createLevelFromFile(File file){
    Method method = null;
    Level level = new Level(myPathManager);
    try{
      BufferedReader br = new BufferedReader(new FileReader(file));
      String string;
      int row = 0;
      while ((string = br.readLine()) != null){
        for( int i = 0; i < string.length(); i++){
          try {
            method = level.getClass().getMethod("method"+string.charAt(i), Integer.class, Integer.class);
          } catch (NoSuchMethodException e) {
            e.printStackTrace();
            //TODO
          }
          try {
            assert method != null;
            method.invoke(level, i, row);
          } catch (IllegalArgumentException e) {
            // TODO
          } catch (IllegalAccessException e) {
            // TODO
          } catch (InvocationTargetException e) {
            // TODO
          }
        }
        row++;
      }
    } catch(FileNotFoundException e){
      //TODO: add error here
      System.out.println("Test File not found");
    } catch (IOException e) {
      //TODO: add error here
      System.out.println(e);
    }
    return level;
  }
}


public class GetLevel {

  GameLevels levels = new GameLevels();
  Level currentLevel;

  public void getNewLevel () {
    currentLevel = levels.getNextLevel();
  }
}

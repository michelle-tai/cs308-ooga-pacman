package ooga.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import ooga.data.Level;
import ooga.data.LevelManager;
import ooga.data.PathManager;
import ooga.engine.GameContainer;
import ooga.engine.GameStep;
import ooga.engine.sprites.Sprite;

public class Controller {

    private GameStep gameStep;
    private int currentScore;
    private LevelManager myLevelManager;

    public Controller(String game){
        myLevelManager = new LevelManager(game);
        gameStep = new GameStep(myLevelManager);
        currentScore = 0;
    }

    public GameContainer getContainer(){
        return gameStep.getGameContainer();
    }

    public void setGameStep() {gameStep.step();}

    public Sprite getCurrentGhost(int ID) {return gameStep.getGameContainer().getGhost(ID);}

    public Sprite getCurrentPacMan(int ID) {return gameStep.getGameContainer().getPacMan(ID);}

    public Sprite getCurrentCoin(int ID) {return gameStep.getGameContainer().getCoin(ID);}

    public Sprite getBlock() {
        return gameStep.getGameContainer().getBlock();
    }

    public PathManager getCurrentPathManager() {
        return myLevelManager.getPathManager();
    }

    public void addScore(int score) { currentScore += score;}

    public LevelManager getLevelManager() {
        return myLevelManager;
    }

    public SimpleIntegerProperty getCurrentScore(){
        return new SimpleIntegerProperty(currentScore);
    }

    public void setLevel(Level level){
        gameStep.getGameContainer().setCurrLevel(level);
//        container = new GameContainer(level);
        System.out.println("new level set in container");
        System.out.println();
    }
}

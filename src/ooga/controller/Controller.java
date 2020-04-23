package ooga.controller;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Map;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import ooga.data.Level;
import ooga.data.PathManager;
import ooga.engine.GameContainer;
import ooga.engine.GameStep;
import ooga.engine.sprites.Sprite;

import java.util.HashMap;
import java.util.HashSet;

public class Controller {

    private GameContainer container;
    private GameStep gameStep;
    private int currentScore;
    private Level currLevel;

    public Controller(){
        File file = Paths.get(PathManager.getFilePath("DefaultLevel")).toFile();
        currLevel = new Level(file);
        container = new GameContainer(currLevel);
        gameStep = new GameStep(container);
        currentScore = 0;
    }

    public Map<Pair<Integer,Integer>, HashSet<Sprite>> getGameContainerMap(){ return container.getModelMap();}

    public GameContainer getContainer(){
        return container;
    }

    public void setGameStep() {gameStep.step();}

    public Sprite getCurrentGhost(int ID) {return container.getGhost(ID);}

    public Sprite getCurrentPacMan(int ID) {return container.getPacMan(ID);}

    public Sprite getCurrentCoin(int ID) {return container.getCoin(ID);}

    public void addScore(int score) { currentScore += score;}

    public SimpleIntegerProperty getCurrentScore(){
        return new SimpleIntegerProperty(currentScore);
    }

    public void setLevel(Level level){
        container.setCurrLevel(level);
//        container = new GameContainer(level);
        System.out.println("new level set in container");
        System.out.println();
    }

}

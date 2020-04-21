package ooga.controller;

import javafx.util.Pair;
import ooga.engine.GameContainer;
import ooga.engine.GameStep;
import ooga.engine.sprites.Sprite;

import java.util.HashMap;
import java.util.HashSet;

public class Controller extends GameContainer {

    private GameContainer container;
    private GameStep gameStep;

    public Controller(){
        container = new GameContainer();
        gameStep = new GameStep(container);
    }

    public HashMap<Pair<Integer,Integer>, HashSet<Sprite>> getGameContainerMap(){ return container.getModelMap();}

    public GameContainer getContainer(){
        return container;
    }

    public void setGameStep() {gameStep.step();}

    public Sprite getCurrentGhost(int ID) {return container.getGhost(ID);}

    public Sprite getCurrentPacMan(int ID) {return container.getPacMan(ID);}

    public Sprite getCurrentCoin(int ID) {return container.getCoin(ID);}

}

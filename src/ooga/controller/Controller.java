package ooga.controller;

import javafx.util.Pair;
import ooga.engine.GameContainer;
import ooga.engine.Sprite;
import ooga.engine.sprites.Ghost;
import ooga.engine.sprites.PacMan;

import java.util.HashMap;
import java.util.HashSet;

public class Controller extends GameContainer {

    private GameContainer container;

    public Controller(){
        container = new GameContainer();
    }

    public HashMap<Pair<Integer,Integer>, HashSet<Sprite>> getGameContainerMap(){ return container.getModelMap();}

    public GameContainer getContainer(){
        return container;
    }

    public Ghost getCurrentGhost(int ID) {return container.getGhost(ID);}

    public PacMan getCurrentPacMan(int ID) {return container.getPacMan(ID);}

}

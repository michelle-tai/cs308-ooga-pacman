package ooga.controller;

import javafx.util.Pair;
import ooga.engine.GameContainer;
import ooga.engine.Sprite;
import ooga.engine.sprites.PacMan;

import java.util.HashMap;
import java.util.HashSet;

public class Controller {

    private GameContainer container;

    public Controller(){
        container = new GameContainer();

    }

    public HashMap<Pair<Integer,Integer>, HashSet<Sprite>> getGameContainerMap(){ return container.getModelMap();}

    public GameContainer getContainer(){
        return container;
    }


}

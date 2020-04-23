package ooga.engine;

import java.util.Map;
import javafx.util.Pair;
import ooga.Main;
import ooga.data.Level;
import ooga.engine.sprites.PacMan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import ooga.engine.sprites.*;

public class GameContainer {

    private static int BlockWidth = Integer.parseInt(Main.MY_RESOURCES.getString("BlockDim"));

    private Map<Pair<Integer,Integer>, HashSet<Sprite>> myMap;
    private HashSet<Sprite> allGameObjects = new HashSet<>();
    private MapGraphNode[][] emptySpots;
    private List<Sprite> myGhostSet = new ArrayList<>();
    private List<Sprite> myPacManSet = new ArrayList<>();
    private List<Sprite> myCoinSet = new ArrayList<>();
    private Level currLevel;


   // private String myMovementType = Main.MY_RESOURCES.getString("GameMovement");

    public GameContainer(Level level){
        currLevel = level;
        emptySpots = currLevel.getInitialEmptySpots();
        myGhostSet = currLevel.getGhosts();
        myPacManSet = currLevel.getPacMen();
        myCoinSet = currLevel.getCoinSet();
        myMap = currLevel.getModelMap();

    }

    public Map<Pair<Integer,Integer>, HashSet<Sprite>> getModelMap(){
        return myMap;
    }

    public MapGraphNode getSpriteMapNode(Sprite sprite){
        int i = sprite.getX()/BlockWidth;
        int row = sprite.getY()/BlockWidth;
        if(emptySpots[i][row] != null){
            return emptySpots[i][row];
        }else{
            return getNonNullMapNode(i, row);
        }
    }

    private MapGraphNode getNonNullMapNode(int i, int row){
        for(int col = i; col < emptySpots.length; col++){
            for(int j = row; j <emptySpots[0].length; j++){
                if(emptySpots[col][j] != null){
                    return emptySpots[col][j];
                }
            }
        }
        return emptySpots[0][0];
    }

    public HashSet<Sprite> getAllGameObjects(){ return currLevel.getAllGameObjects();}


    public List<Sprite> getGhosts(){
        return myGhostSet;
    }

    public Sprite getGhost(int ID){
        return myGhostSet.get(ID);}

    public List<Sprite> getPacMen() {
        return myPacManSet;
    }

    public Sprite getPacMan(int ID){return myPacManSet.get(ID);}

    public Sprite getCoin(int ID){return myCoinSet.get(ID);}

    public HashSet<Sprite> getNeighborhood(int X, int Y){  //todo bound neighborhood size to max single frame bounding speed
        HashSet<Sprite> neighborhood = new HashSet<Sprite>();
        int i = X/BlockWidth;
        int row = Y/BlockWidth;
        addToNeighborhood(neighborhood, i, row);
        addToNeighborhood(neighborhood, i -1, row -1);
        addToNeighborhood(neighborhood, i , row -1);
        addToNeighborhood(neighborhood, i + 1, row -1);
        addToNeighborhood(neighborhood, i -1, row + 1);
        addToNeighborhood(neighborhood, i , row + 1);
        addToNeighborhood(neighborhood, i + 1, row + 1);
        addToNeighborhood(neighborhood, i - 1, row );
        addToNeighborhood(neighborhood, i + 1, row );

        return neighborhood;
    }

    private void addToNeighborhood(HashSet<Sprite> neighborhood, int i, int row){
        Pair<Integer, Integer> spriteLoc = new Pair<Integer, Integer>(i, row);
        if(myMap.containsKey(spriteLoc)){
            neighborhood.addAll(myMap.get(spriteLoc));
        }
    }

    public void remove(Sprite gameObject){
        if(gameObject instanceof PacMan){
            myPacManSet.remove(gameObject);
        }else if(gameObject instanceof Ghost){
            myGhostSet.remove(gameObject);
        }
        int i= gameObject.getX() / BlockWidth;
        int row = gameObject.getY() / BlockWidth;

        Pair<Integer, Integer> loc = new Pair(i, row);

        if(myMap.containsKey(loc)){
            HashSet<Sprite> locSet = myMap.get(loc);
            if(locSet.contains(gameObject)){
                locSet.remove(gameObject);
                myMap.put(loc, locSet);
            }
        }
    }

    public void clearContainer(){
        myMap.clear();
        myCoinSet.clear();
        myPacManSet.clear();
        myGhostSet.clear();
        allGameObjects.clear();
    }

    public void setCurrLevel(Level level){
        currLevel = level;
    }

}

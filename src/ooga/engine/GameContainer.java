package ooga.engine;

import java.util.*;

import javafx.util.Pair;
import ooga.Main;
import ooga.data.Level;
import ooga.data.PathManager;
import ooga.engine.sprites.PacMan;

import ooga.engine.sprites.*;

public class GameContainer {

    private static int BlockWidth;

    private Map<Pair<Integer, Integer>, Set<Sprite>> myMap = new HashMap<Pair<Integer, Integer>, Set<Sprite>>();
    private HashSet<Sprite> allGameObjects = new HashSet<>();
    private MapGraphNode[][] emptySpots = new MapGraphNode[0][0];
    private List<Sprite> myGhostList = new ArrayList<>();
    private List<Sprite> myPacManList = new ArrayList<>();
    private List<Sprite> myCoinList = new ArrayList<>();
    private List<Sprite> myBlockList = new ArrayList<>();
    private Level currLevel;
    private PathManager myPathManager;
    private int upTime;
    private boolean completeStatus;

   // private String myMovementType = Main.MY_RESOURCES.getString("GameMovement");

    public GameContainer(Level level, PathManager pathManager){
        BlockWidth = Integer.parseInt(pathManager.getString("GameProperties", "BlockDim"));
        currLevel = level;
        emptySpots = currLevel.getInitialEmptySpots();
        myGhostList.addAll(currLevel.getGhosts());
        myPacManList.addAll(currLevel.getPacMen());
        myCoinList.addAll(currLevel.getCoins());
        myBlockList.addAll(currLevel.getBlockList());
        myMap.putAll(currLevel.getModelMap());
        myPathManager = pathManager;
        upTime = 0;
        completeStatus = false;
        setGhostSpawn();
    }

    public Map<Pair<Integer, Integer>, Set<Sprite>> getModelMap(){
        return myMap;
    }

    public MapGraphNode getSpriteMapNode(Sprite sprite){
        int i = (sprite.getX())/BlockWidth;
        int row = (sprite.getY())/BlockWidth;
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




    public List<Sprite> getGhosts(){
        return myGhostList;
    }

    public Sprite getGhost(int ID){
        return myGhostList.get(ID);}

    public List<Sprite> getPacMen() {
        return myPacManList;
    }

    public Sprite getPacMan(int ID){
        return myPacManList.get(ID);
    }

    public Sprite getBlock() {
        return myBlockList.get(0);
    }

    public Sprite getCoin(int ID){return myCoinList.get(ID);}

    public PathManager getPathManager() {
        return myPathManager;
    }

    public Set<Sprite> getNeighborhood(int X, int Y){  //todo bound neighborhood size to max single frame bounding speed
        HashSet<Sprite> neighborhood = new HashSet<Sprite>();
        int i = X/BlockWidth;
        int row = Y/BlockWidth;

        addToNeighborhood(neighborhood, i, row);
//        addToNeighborhood(neighborhood, i -1, row -1);
//        addToNeighborhood(neighborhood, i , row -1);
//        addToNeighborhood(neighborhood, i + 1, row -1);
//        addToNeighborhood(neighborhood, i -1, row + 1);
//        addToNeighborhood(neighborhood, i , row + 1);
//        addToNeighborhood(neighborhood, i + 1, row + 1);
//        addToNeighborhood(neighborhood, i - 1, row );
//        addToNeighborhood(neighborhood, i + 1, row );

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
            myPacManList.remove(gameObject);
        }else if(gameObject instanceof Ghost){
            myGhostList.remove(gameObject);
        } else if (gameObject instanceof Coin){
            myCoinList.remove(gameObject);
        }
        int i= gameObject.getX() / BlockWidth;
        int row = gameObject.getY() / BlockWidth;

        Pair<Integer, Integer> loc = new Pair(i, row);

        if(myMap.containsKey(loc)){
            Set<Sprite> locSet = myMap.get(loc);
            if(locSet.contains(gameObject)){
                locSet.remove(gameObject);
                myMap.put(loc, locSet);
            }
        }
    }



    public void clearContainer(){
        myMap.clear();
        myCoinList.clear();
        myPacManList.clear();
        myGhostList.clear();
        myBlockList.clear();
        allGameObjects.clear();
        emptySpots = currLevel.getInitialEmptySpots();
        myGhostList.addAll(currLevel.getGhosts());
        myPacManList.addAll(currLevel.getPacMen());
        myCoinList.addAll(currLevel.getCoins());
        myBlockList.addAll(currLevel.getBlockList());
        myMap.putAll(currLevel.getModelMap());
    }

    private void setGhostSpawn(){
        int itter = 0;
        if(getGhosts().size() > 0){

            int ghostSpawnX = getGhost(0).getX();
            int ghostSpawnY = getGhost(0).getY();
            for(Sprite ghost : getGhosts()){
                ((Ghost) ghost).setGhostSpawn(ghostSpawnX, ghostSpawnY, itter);
                itter++;
            }
        }
    }

    public void setCurrLevel(Level level){
        currLevel = level;
        emptySpots = currLevel.getInitialEmptySpots();
        myGhostList = currLevel.getGhosts();
        myPacManList = currLevel.getPacMen();
        myCoinList = currLevel.getCoins();
        myBlockList = currLevel.getBlockList();
        myMap = currLevel.getModelMap();
    }

    public void mapStep(){
        upTime++;
        Set<Sprite> dynamicSprite = new HashSet<>();
        dynamicSprite.addAll(myGhostList);
        dynamicSprite.addAll(myPacManList);

        for(Sprite sprite : dynamicSprite){
            DynamicSprite dSprite = (DynamicSprite) sprite;
            int i= dSprite.getPrevX() / BlockWidth;
            int row = dSprite.getPrevY() / BlockWidth;

            Pair<Integer, Integer> loc = new Pair(i, row);

            if(myMap.containsKey(loc)){
                Set<Sprite> locSet = myMap.get(loc);
                if(locSet.contains(dSprite)){
                    locSet.remove(dSprite);
                    myMap.put(loc, locSet);
                }
            }

            loc = new Pair<>(sprite.getX()/BlockWidth,sprite.getY()/BlockWidth);
            if(!myMap.containsKey(loc)){
                HashSet<Sprite> locSet = new HashSet<>();
                locSet.add(sprite);
                myMap.put(loc, locSet);
            }else{
                Set<Sprite> locSet = myMap.get(loc);
                locSet.add(sprite);
                myMap.put(loc, locSet);
            }
        }
        if(myCoinList.size() == 0){
            completeStatus = true;
        }

    }

    public void resetUptime(){
        upTime = 0;
    }

    public boolean getCompleteStatus(){
        return completeStatus;
    }
}

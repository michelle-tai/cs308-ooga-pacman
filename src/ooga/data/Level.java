package ooga.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.util.Pair;
import ooga.Main;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.Sprite;
import ooga.engine.sprites.Block;
import ooga.engine.sprites.Coin;
import ooga.engine.sprites.Ghost;
import ooga.engine.sprites.PacMan;

public class Level {

    private Map<Pair<Integer, Integer>, Set<Sprite>> myMap = new HashMap<>();

    private Set<Sprite> allGameObjects = new HashSet<>();
    private Map<Pair<Integer, Integer>, MapGraphNode> emptySpots = new HashMap<>();

    private Integer maxWidth = 0;
    private Integer maxHeight = 0;

    private List<Sprite> myGhostList = new ArrayList<>();
    private List<Sprite> myPacManList = new ArrayList<>();
    private List<Sprite> myCoinList = new ArrayList<>();
    private List<Sprite> myBlockList = new ArrayList<>();

    private static Integer BlockWidth;

    private Integer ghostCount = 0;
    private Integer pacManCount = 0;
    private Integer coinCount = 0;

    private PathManager myPathManager;

    public Level(PathManager pathManager){
        myPathManager = pathManager;
        ResourceBundle resourceBundle = myPathManager.getResourceBundle(PathManager.PROPERTIES);
        BlockWidth = Integer.parseInt(resourceBundle.getString("BlockDim"));
    }

    public Map<Pair<Integer,Integer>, Set<Sprite>> getModelMap(){
        return myMap;
    }

    public void methodx(Integer i, Integer row) {
        Block modelBlock = new Block(BlockWidth * i, BlockWidth * row,
            myPathManager.getFilePath(PathManager.BLOCKIMAGE), myPathManager);
        addSpriteToMap(modelBlock, i, row);
        allGameObjects.add(modelBlock);
        myBlockList.add(modelBlock);
    }

    public void methodg(Integer i, Integer row) {
        int ghostDim = Integer.parseInt(myPathManager.getString(PathManager.PROPERTIES,"GhostWidth"));
        Ghost modelGhost = new Ghost(BlockWidth * i, BlockWidth * row, ghostDim,
            ghostDim, ghostCount, myPathManager.getFilePath(PathManager.GHOSTIMAGES, ghostCount),
            myPathManager.getFilePath(PathManager.SCAREDGHOST), myPathManager);
        myGhostList.add(modelGhost);
        allGameObjects.add(modelGhost);
        addSpriteToMap(modelGhost, i, row);
        addEmptySpot(i, row);
        modelGhost.setMovementType("", myPacManList); //TODO: load targets from data


        ghostCount++;
    }

    public void methodp(Integer i, Integer row) {
        int pacManDim = Integer.parseInt(myPathManager.getString(PathManager.PROPERTIES,"MainCharacterWidth"));
        PacMan modelPacMan = new PacMan(BlockWidth * i,
            BlockWidth * row, pacManDim, pacManDim,
            pacManCount, myPathManager.getFilePath(PathManager.PACKMANIMAGE, pacManCount), myPathManager);
        myPacManList.add(modelPacMan);
        allGameObjects.add(modelPacMan);
        addSpriteToMap(modelPacMan, i, row);
        addEmptySpot(i, row);
        pacManCount++;
    }

    public void method0(Integer i, Integer row) {
        generateFood(i, row, 0);
    }

    public void method1(Integer i, Integer row) {
        generateFood(i, row, 1);
    }

    public void method2(Integer i, Integer row) {
        generateFood(i, row, 2);
    }

    public List<Sprite> getGhosts() {
        return myGhostList;
    }

    public List<Sprite> getPacMen() {
        return myPacManList;
    }

    public List<Sprite> getCoins() {
        return myCoinList;
    }

    public Set<Sprite> getAllGameObjects() {
        return allGameObjects;
    }

    public List<Sprite> getBlockList() {
        return myBlockList;
    }

    public MapGraphNode[][] getInitialEmptySpots() {
        MapGraphNode[][] myNodes = new MapGraphNode[maxHeight][maxWidth];
        for (Pair<Integer, Integer> p : emptySpots.keySet()) {
            myNodes[p.getKey()][p.getValue()] = emptySpots.get(p);
        }
        for (MapGraphNode[] myNode : myNodes) {
            for (int row = 0; row < myNodes[0].length; row++) {
                if (myNode[row] != null) {
                    myNode[row].addNeighbor(myNodes);
                }
            }
        }
        return myNodes;
    }


    private void generateFood(Integer i, Integer row, Integer status) {
        Coin modelFood = new Coin(BlockWidth * i, BlockWidth * row, status, coinCount,
            myPathManager.getFilePath(PathManager.FOODIMAGES, status), myPathManager);
        myCoinList.add(modelFood);
        addSpriteToMap(modelFood, i, row);
        allGameObjects.add(modelFood);
        addEmptySpot(i, row);

        coinCount++;
    }

    private void addSpriteToMap(Sprite sprite, int i, int row){
        calculateHeightWidth(i, row);
        Pair<Integer, Integer> loc = new Pair<>(i,row);
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

    private void calculateHeightWidth(int i, int row) {
        if (i+1 > maxWidth) maxWidth = i+8;
        if (row+1 > maxHeight) maxHeight = row+8;
    }

    private void addEmptySpot(int i, int row) {
        Pair<Integer, Integer> pair = new Pair<>(i, row);
        MapGraphNode node = new MapGraphNode(i, row, myPathManager);
        emptySpots.put(pair, node);
    }
}

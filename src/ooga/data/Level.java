package ooga.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
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

    private Map<Pair<Integer, Integer>, Image> myImages = new HashMap<>();
    private Map<Pair<Integer, Integer>, Set<Sprite>> myMap = new HashMap<>();

    private Set<Sprite> allGameObjects = new HashSet<Sprite>();
    private Map<Pair<Integer, Integer>, MapGraphNode> emptySpots = new HashMap<>();

    private Integer maxWidth = 0;
    private Integer maxHeight = 0;

    private List<Sprite> myGhostList = new ArrayList<>();
    private List<Sprite> myPacManList = new ArrayList<>();
    private List<Sprite> myCoinList = new ArrayList<>();

    private static Integer BlockWidth;

    private Integer ghostCount = 0;
    private Integer pacManCount = 0;
    private Integer coinCount = 0;

    public Level(){
        ResourceBundle resourceBundle = PathManager.getResourceBundle(PathManager.PROPERTIES);
        BlockWidth = Integer.parseInt(resourceBundle.getString("BlockDim"));
    }

    public Image getMapElementImage(int row, int col) {
        Pair<Integer, Integer> pair = new Pair<>(row, col);
        if (myImages.containsKey(pair)) {
            return myImages.get(pair);
        } else {
            // TODO: handle error
            System.out.println("Pair not in map");
        }
        return null;
    }

    public Map<Pair<Integer,Integer>, Set<Sprite>> getModelMap(){
        return myMap;
    }

    public void methodx(Integer i, Integer row) {
        Block modelBlock = new Block(BlockWidth * i, BlockWidth * row);
        addSpriteToMap(modelBlock, i, row);
        allGameObjects.add(modelBlock);

        // @author Caleb Sanford
        addImageToMap((PathManager.getFilePath(PathManager.BLOCKIMAGE)), i, row);
    }

    public void methodg(Integer i, Integer row) {
        int ghostDim = Integer.parseInt(Main.MY_RESOURCES.getString("GhostWidth"));
        Ghost modelGhost = new Ghost(BlockWidth * i, BlockWidth * row, ghostDim, ghostDim, ghostCount);
        myGhostList.add(modelGhost);
        allGameObjects.add(modelGhost);
        addSpriteToMap(modelGhost, i, row);
        addImageToMap(PathManager.getFilePath(PathManager.GHOSTIMAGES, ghostCount), i, row);
        addEmptySpot(i, row);
        modelGhost.setMovementType("", myPacManList); //TODO: load targets from data


        ghostCount++;
    }

    public void methodp(Integer i, Integer row) {
        int pacManDim = Integer.parseInt(Main.MY_RESOURCES.getString("MainCharacterWidth"));
        PacMan modelPacMan = new PacMan(BlockWidth * i, BlockWidth * row, pacManDim, pacManDim, pacManCount);
        myPacManList.add(modelPacMan);
        allGameObjects.add(modelPacMan);
        addSpriteToMap(modelPacMan, i, row);
        addImageToMap(PathManager.getFilePath(PathManager.PACKMANIMAGE, pacManCount), i, row);
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

    public MapGraphNode[][] getInitialEmptySpots(){
        MapGraphNode[][] myNodes = new MapGraphNode[maxHeight][maxWidth];
        for (Pair<Integer, Integer> p: emptySpots.keySet()){
            myNodes[p.getKey()][p.getValue()] = emptySpots.get(p);
        }
        return myNodes;
    }


    private void generateFood(Integer i, Integer row, Integer status) {
        Coin modelFood = new Coin(BlockWidth * i, BlockWidth * row, status, coinCount);
        myCoinList.add(modelFood);
        addSpriteToMap(modelFood, i, row);
        allGameObjects.add(modelFood);
        addEmptySpot(i, row);

        addImageToMap(PathManager.getFilePath(PathManager.FOODIMAGES, status), i, row);
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
        if (i > maxWidth) maxWidth = i;
        if (row > maxHeight) maxHeight = row;
    }

    private void addImageToMap(String imagePath, int i, int row) {
        Pair<Integer, Integer> loc = new Pair<>(i,row);
        Image image = new Image(imagePath);
        myImages.put(loc, image);
    }

    private void addEmptySpot(int i, int row) {
        Pair<Integer, Integer> pair = new Pair<>(i, row);
        MapGraphNode node = new MapGraphNode(i, row);
        emptySpots.put(pair, node);
    }
}

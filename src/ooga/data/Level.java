package ooga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import ooga.engine.sprites.Sprite;
import ooga.engine.sprites.Block;
import ooga.engine.sprites.Coin;
import ooga.engine.sprites.Ghost;
import ooga.engine.sprites.PacMan;

public class Level {

    private Map<Pair<Integer, Integer>, Image> myImages = new HashMap<>();
    private Map<Pair<Integer, Integer>, Sprite> myMap = new HashMap<>();

    private Set<Sprite> allGameObjects = new HashSet<Sprite>();

    private List<Sprite> myGhostList = new ArrayList<>();
    private List<Sprite> myPacManList = new ArrayList<>();
    private List<Sprite> myCoinList = new ArrayList<>();

    private static Integer BlockWidth;
    private PathManager myPathManager = new PathManager();

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

    public Map<Pair<Integer,Integer>, Sprite> getModelMap(){
        return myMap;
    }

    public void methodx(Integer i, Integer row) {
        Block modelBlock = new Block(BlockWidth * i, BlockWidth * row);
        addSpriteToMap(modelBlock, i, row);
        allGameObjects.add(modelBlock);

        // @author Caleb Sanford
        addImageToMap((myPathManager.getFilePath(PathManager.BLOCKIMAGE)), i, row);
    }

    public void methodg(Integer i, Integer row) {
        int ghostDim = Integer.parseInt(Main.MY_RESOURCES.getString("GhostWidth"));
        Ghost modelGhost = new Ghost(BlockWidth * i, BlockWidth * row, ghostDim, ghostDim, ghostCount);
        myGhostList.add(modelGhost);
        allGameObjects.add(modelGhost);
        addSpriteToMap(modelGhost, i, row);
        addImageToMap(myPathManager.getFilePath(PathManager.GHOSTIMAGES, ghostCount), i, row);

        ghostCount++;
    }

    public void methodp(Integer i, Integer row) {
        int pacManDim = Integer.parseInt(Main.MY_RESOURCES.getString("MainCharacterWidth"));
        PacMan modelPacMan = new PacMan(BlockWidth * i, BlockWidth * row, pacManDim, pacManDim, pacManCount);
        myPacManList.add(modelPacMan);
        allGameObjects.add(modelPacMan);
        addSpriteToMap(modelPacMan, i, row);
        addImageToMap(myPathManager.getFilePath(PathManager.PACKMANIMAGE, pacManCount), i, row);

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

    private void generateFood(Integer i, Integer row, Integer status) {
        Coin modelFood = new Coin(BlockWidth * i, BlockWidth * row, status, coinCount);
        myCoinList.add(modelFood);
        addSpriteToMap(modelFood, i, row);
        allGameObjects.add(modelFood);

        addImageToMap(myPathManager.getFilePath(PathManager.FOODIMAGES, status), i, row);
        coinCount++;
    }

    private void addSpriteToMap(Sprite sprite, int i, int row){
        Pair<Integer, Integer> loc = new Pair(i,row);
        myMap.put(loc, sprite);
    }

    private void addImageToMap(String imagePath, int i, int row) {
        Pair<Integer, Integer> loc = new Pair(i,row);
        Image image = new Image(imagePath);
        myImages.put(loc, image);
    }
}

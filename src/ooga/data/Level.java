package ooga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.util.Pair;
import ooga.Main;
import ooga.engine.GameException;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.Sprite;
import ooga.engine.sprites.Block;
import ooga.engine.sprites.Coin;
import ooga.engine.sprites.Ghost;
import ooga.engine.sprites.PacMan;

// pretty much holds the starting info a level, any updates are made in the game container
public class Level {

    private Map<Pair<Integer, Integer>, Image> myImages = new HashMap<>();
    private Map<Pair<Integer, Integer>, HashSet<Sprite>> myMap = new HashMap<>();

    private Integer BlockWidth;
    private PathManager myPathManager = new PathManager();
    private int levelHeight;
    private int levelWidth;
    private HashSet<Sprite> allGameObjects = new HashSet<>();
    private MapGraphNode[][] emptySpots;
    private List<Sprite> myGhostSet = new ArrayList<>();
    private List<Sprite> myPacManSet = new ArrayList<>();
    private List<Sprite> myCoinSet = new ArrayList<>();

    public Level(File level){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PathManager.PROPERTIES);
        BlockWidth = Integer.parseInt(resourceBundle.getString("BlockDim"));
        createMapFromFile(level);
    }

    public Level(File level, Integer blockWidth){
        BlockWidth = blockWidth;
        createMapFromFile(level);
    }

    public Image getMapElementImage(int row, int col) {
        Pair<Integer, Integer> pair = new Pair<>(row, col);
        if (myImages.containsKey(pair)) {
            return myImages.get(pair);
        } else {
            // TODO: handle error
            throw new GameException(Main.ERROR_RESOURCES.getString("PairNotInMap"));
//            System.out.println("Pair not in map");
        }
//        return null;
    }

    public MapGraphNode[][] getInitialEmptySpots(){
        return emptySpots;
    }
    public Map<Pair<Integer,Integer>, HashSet<Sprite>> getModelMap(){
        return myMap;
    }


    public List<Sprite> getGhosts(){
        return myGhostSet;
    }
    public List<Sprite> getPacMen() {
        return myPacManSet;
    }
    public List<Sprite> getCoinSet() {
        return myCoinSet;
    }

    public HashSet<Sprite> getAllGameObjects(){ return allGameObjects;}
    public int getLevelHeight(){
        return levelHeight;
    }

    public int getLevelWidth(){
        return levelWidth;
    }

    /**
     * @author Olga Suchankova
     */
    private void createMapFromFile(File file){
        if(file.length() == 0){
            throw new GameException(Main.ERROR_RESOURCES.getString("EmptyFile"));
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String string;
            int row = 0;
            int ghostNum = 0;
            int pacNum = 0;
            int coinNum = 0;
            levelHeight = Integer.parseInt(br.readLine());
            levelWidth = Integer.parseInt(br.readLine());
            int currRow = 0;

            emptySpots = new MapGraphNode[levelHeight][levelWidth];
            while (((string = br.readLine()) != null) && (currRow < levelHeight)){
                for( int i = 0; i < levelWidth; i++){
                    if(string.charAt(i) != 'x'){
                        emptySpots[i][row] = new MapGraphNode(i, row);
                    }
                    if (string.charAt(i) == 'x'){
                        generateBlock(i, row);
                    } else if (string.charAt(i) == 'o') {
                        generateFood(i , row, coinNum);
                        coinNum++;
                    } else if (string.charAt(i) == 'p'){
                        generatePacMan(i, row, pacNum);
                        pacNum++;
                    } else if (string.charAt(i) == 'g'){
                        generateGhost(i, row, ghostNum);
                        ghostNum++;
                    }
                }
                row++;
                currRow++;
            }
            initializeEmptySpots();
            for(Sprite ghost : myGhostSet){
                ghost.setMovementType("", myPacManSet); //todo: load targets from data
            }
        } catch(FileNotFoundException e){
            //TODO: add error here
            throw new GameException(Main.ERROR_RESOURCES.getString("FileNotFound"));
//            System.out.println("Test File not found");
        } catch (NumberFormatException e){
            throw new GameException(Main.ERROR_RESOURCES.getString("WrongNumberFormat"));
        } catch (IOException| RuntimeException e) {
            //TODO: add error here
            e.printStackTrace();
            throw new GameException(Main.ERROR_RESOURCES.getString("GeneralError"));
//            System.out.println(e);
        }
        System.out.println(levelHeight);
        System.out.println(levelWidth);
    }

    private void initializeEmptySpots(){
        for(int i = 0; i < emptySpots.length; i++){
            for(int row = 0; row <emptySpots[0].length; row++){
//                System.out.println(row);
//                System.out.println(i);
                if(emptySpots[i][row] != null){
                    emptySpots[i][row].addNeighbor(emptySpots);
                }

            }
        }
    }

    private void generateGhost(int i, int row, int ID) {
        int ghostDim = Integer.parseInt(Main.MY_RESOURCES.getString("GhostWidth"));
        Ghost modelGhost = new Ghost(BlockWidth * i, BlockWidth * row, ghostDim, ghostDim, ID);
        myGhostSet.add(modelGhost);
        allGameObjects.add(modelGhost);
        addSpriteToMap(modelGhost, i, row);

    }

    private void generatePacMan(int i, int row, int ID) {
        int pacManDim = Integer.parseInt(Main.MY_RESOURCES.getString("MainCharacterWidth"));
        PacMan modelPacMan = new PacMan(BlockWidth * i, BlockWidth * row, pacManDim, pacManDim, ID);
        myPacManSet.add(modelPacMan);
        allGameObjects.add(modelPacMan);
        addSpriteToMap(modelPacMan, i, row);
    }

    private void generateFood(int i, int row, int ID) {
        Coin modelFood = new Coin(BlockWidth * i, BlockWidth * row, 0, ID);
        myCoinSet.add(modelFood);
        addSpriteToMap(modelFood, i, row);
        allGameObjects.add(modelFood);
    }

    private void generateBlock(int i, int row) {
        Block modelBlock = new Block(BlockWidth * i, BlockWidth * row);
        addSpriteToMap(modelBlock, i, row);
        allGameObjects.add(modelBlock);
    }

    private void addSpriteToMap(Sprite sprite, int i, int row){
        Pair<Integer, Integer> loc = new Pair(i,row);
        if(!myMap.containsKey(loc)){
            HashSet<Sprite> locSet = new HashSet<>();
            locSet.add(sprite);
            myMap.put(loc, locSet);
        }else{
            HashSet locSet = myMap.get(loc);
            locSet.add(sprite);
            myMap.put(loc, locSet);
        }
    }

}

package ooga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.util.Pair;
import ooga.Main;
import ooga.engine.GameException;
import ooga.engine.sprites.Sprite;
import ooga.engine.sprites.Block;
import ooga.engine.sprites.Coin;
import ooga.engine.sprites.Ghost;
import ooga.engine.sprites.PacMan;

public class Level {

    private Map<Pair<Integer, Integer>, Image> myImages = new HashMap<>();
    private Map<Pair<Integer, Integer>, Sprite> myMap = new HashMap<>();

    private static Integer BlockWidth;
    private PathManager myPathManager = new PathManager();

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

    public Map<Pair<Integer,Integer>, Sprite> getModelMap(){
        return myMap;
    }

    /**
     * @author Olga Suchankova
     */
    private void createMapFromFile(File file){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String string;
            int row = 0;
            int ghostNum = 0;
            int pacNum = 0;
            int coinNum = 0;
            while ((string = br.readLine()) != null){
                for( int i = 0; i < string.length(); i++){
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
            }
        } catch(FileNotFoundException e){
            //TODO: add error here
            throw new GameException(Main.ERROR_RESOURCES.getString("FileNotFound"));
//            System.out.println("Test File not found");
        } catch (IOException e) {
            //TODO: add error here
            throw new GameException(Main.ERROR_RESOURCES.getString("GeneralError"));
//            System.out.println(e);
        }
    }

    /**
     * @author Olga Suchankova
     */
    private void generateGhost(int i, int row, int ID) {
        int ghostDim = Integer.parseInt(Main.MY_RESOURCES.getString("GhostWidth"));
        Ghost modelGhost = new Ghost(BlockWidth * i, BlockWidth * row, ghostDim, ghostDim, ID);
        addSpriteToMap(modelGhost, i, row);

        // @author Caleb Sanford
        addImageToMap(myPathManager.getGhostPath(ID), i, row);
    }

    /**
     * @author Olga Suchankova
     */
    private void generatePacMan(int i, int row, int ID) {
        int pacManDim = Integer.parseInt(Main.MY_RESOURCES.getString("MainCharacterWidth"));
        PacMan modelPacMan = new PacMan(BlockWidth * i, BlockWidth * row, pacManDim, pacManDim, ID);
        addSpriteToMap(modelPacMan, i, row);

        // @author Caleb Sanford
        addImageToMap(myPathManager.getPacManPath(ID), i, row);
    }

    /**
     * @author Olga Suchankova
     */
    private void generateFood(int i, int row, int ID) {
        Coin modelFood = new Coin(BlockWidth * i, BlockWidth * row, 0, ID);
        addSpriteToMap(modelFood, i, row);

        // @author Caleb Sanford
        addImageToMap(myPathManager.getFilePath(PathManager.FOODIMAGE), i, row);
    }

    /**
     * @author Olga Suchankova
     */
    private void generateBlock(int i, int row) {
        Block modelBlock = new Block(BlockWidth * i, BlockWidth * row);
        addSpriteToMap(modelBlock, i, row);

        // @author Caleb Sanford
        addImageToMap((myPathManager.getFilePath(PathManager.BLOCKIMAGE)), i, row);
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

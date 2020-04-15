package ooga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import ooga.Main;
import ooga.engine.Sprite;
import ooga.engine.sprites.Block;
import ooga.engine.sprites.Coin;
import ooga.engine.sprites.Ghost;
import ooga.engine.sprites.PacMan;

public class Level {

    private Map<Pair<Integer, Integer>, ImageView> myImages = new HashMap<>();
    private Map<Pair<Integer, Integer>, Sprite> myMap = new HashMap<>();

    private static Integer BlockWidth;

    public Level(File level){
        createMapFromFile(level);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PathManager.PROPERTIES);
        BlockWidth = Integer.parseInt(resourceBundle.getString("BlockDim"));
    }

    public Map<Pair<Integer,Integer>, Sprite> getModelMap(){
        return myMap;
    }

    /**
     * @author Olga Suchankova
     */
    public void createMapFromFile(File file){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String string;
            int row = 0;
            int ghostNum = 0;
            int pacNum = 0;
            while ((string = br.readLine()) != null){
                for( int i = 0; i < string.length(); i++){
                    if (string.charAt(i) == 'x'){
                        generateBlock(i, row);
                    } else if (string.charAt(i) == 'o') {
                        generateFood(i , row);
                    } else if (string.charAt(i) == 'p'){
                        pacNum++;
                        generatePacMan(i, row, pacNum);
                    } else if (string.charAt(i) == 'g'){
                        ghostNum++;
                        generateGhost(i, row, ghostNum);
                    }
                }
                row++;
            }
        } catch(FileNotFoundException e){
            //TODO: add error here
            System.out.println("File not found");
        } catch (IOException e) {
            //TODO: add error here
            System.out.println(e);
        }
    }

    /**
     * @author Olga Suchankova
     */
    private void generateGhost(int i, int row, int ID) {
        int ghostDim = Integer.parseInt(Main.MY_RESOURCES.getString("GhostWidth"));
        Ghost modelGhost = new Ghost(BlockWidth * i, BlockWidth * row, ghostDim, ghostDim, ID);
        addSpriteToMap(modelGhost, i, row);

    }

    /**
     * @author Olga Suchankova
     */
    private void generatePacMan(int i, int row, int ID) {
        int pacManDim = Integer.parseInt(Main.MY_RESOURCES.getString("MainCharacterWidth"));
        PacMan modelPacMan = new PacMan(BlockWidth * i, BlockWidth * row, pacManDim, pacManDim, ID);
        addSpriteToMap(modelPacMan, i, row);
    }

    /**
     * @author Olga Suchankova
     */
    private void generateFood(int i, int row) {
        Coin modelFood = new Coin(BlockWidth * i, BlockWidth * row, 0);
        addSpriteToMap(modelFood, i, row);
    }

    /**
     * @author Olga Suchankova
     */
    private void generateBlock(int i, int row) {
        Block modelBlock = new Block(BlockWidth * i, BlockWidth * row);
        addSpriteToMap(modelBlock, i, row);
    }

    private void addSpriteToMap(Sprite sprite, int i, int row){
        Pair<Integer, Integer> loc = new Pair(i,row);
        myMap.put(loc, sprite);
    }
}

package ooga.engine;


import javafx.util.Pair;
import ooga.Main;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class GameContainer {

    private static int BlockWidth = Integer.parseInt(Main.MY_RESOURCES.getString("BlockDim"));

    private HashMap<Pair<Integer,Integer>, HashSet<Sprite>> myMap;

    private HashSet<Sprite> myGhostSet = new HashSet<Sprite>();
    private HashSet<Sprite> myPacManSet = new HashSet<Sprite>();

    private String myMovementType = Main.MY_RESOURCES.getString("GameMovement");

    public GameContainer(){
        myMap = new HashMap<>();
    }

    public HashMap<Pair<Integer,Integer>, HashSet<Sprite>> getModelMap(){
        return myMap;
    }

    public void createMapFromFile(String level){
        File file = new File(level);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String string;
            int row = 0;
            int ghostNum = 0;
            while ((string = br.readLine()) != null){
                for( int i = 0; i < string.length(); i++){
                    if (string.charAt(i) == 'x'){
                        generateBlock(i, row);
                    } else if (string.charAt(i) == 'o') {
                        generateFood(i , row);
                    } else if (string.charAt(i) == 'p'){
                        generatePacMan(i, row);
                    } else if (string.charAt(i) == 'g'){
                        ghostNum++;
                        generateGhost(i, row);
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

    private void generateGhost(int i, int row) {
        int ghostDim = Integer.parseInt(Main.MY_RESOURCES.getString("GhostWidth"));
       // Ghost modelGhost = new Ghost(BlockWidth * i, BlockWidth * row, ghostDim, ghostDim);
//        myGhostSet.add(modelGhost);
//        addSpriteToMap(modelGhost, i, row);

    }

    private void generatePacMan(int i, int row) {
        int pacManDim = Integer.parseInt(Main.MY_RESOURCES.getString("MainCharacterWidth"));
        PacMan modelPacMan = new PacMan(BlockWidth * i, BlockWidth * row, pacManDim, pacManDim);
        myPacManSet.add(modelPacMan);
        addSpriteToMap(modelPacMan, i, row);
    }

    private void generateFood(int i, int row) {
        Coin modelFood = new Coin(BlockWidth * i, BlockWidth * row);
        addSpriteToMap(modelFood, i, row);
    }

    private void generateBlock(int i, int row) {
        Block modelBlock = new Block(BlockWidth * i, BlockWidth * row);
        addSpriteToMap(modelBlock, i, row);
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

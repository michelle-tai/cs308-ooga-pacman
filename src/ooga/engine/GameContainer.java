package ooga.engine;


import javafx.util.Pair;
import ooga.Main;
import ooga.engine.sprites.Block;
import ooga.engine.sprites.Coin;
import ooga.engine.sprites.PacMan;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import ooga.engine.sprites.*;

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
        Ghost modelGhost = new Ghost(BlockWidth * i, BlockWidth * row, ghostDim, ghostDim);
        myGhostSet.add(modelGhost);
        addSpriteToMap(modelGhost, i, row);

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

    public HashSet<Sprite> getGhosts(){
        return myGhostSet;
    }


    public HashSet<Sprite> getPacMen() {
        return myPacManSet;
    }

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
}

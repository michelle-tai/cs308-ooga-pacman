package ooga.engine;


import javafx.util.Pair;
import ooga.Main;
import ooga.engine.sprites.Block;
import ooga.engine.sprites.Coin;
import ooga.engine.sprites.PacMan;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import ooga.engine.sprites.*;

public class GameContainer {

    private static int BlockWidth = Integer.parseInt(Main.MY_RESOURCES.getString("BlockDim"));

    private HashMap<Pair<Integer,Integer>, HashSet<Sprite>> myMap;
    private HashSet<Sprite> allGameObjects = new HashSet<>();
    private MapGraphNode[][] emptySpots;
    private List<Sprite> myGhostSet = new ArrayList<>();
    private List<Sprite> myPacManSet = new ArrayList<>();
    private List<Sprite> myCoinSet = new ArrayList<>();


   // private String myMovementType = Main.MY_RESOURCES.getString("GameMovement");

    public GameContainer(){
        myMap = new HashMap<>();
    }

    public HashMap<Pair<Integer,Integer>, HashSet<Sprite>> getModelMap(){
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
            for(int j = row; j <emptySpots[0].length; col++){
                if(emptySpots[col][j] != null){
                    return emptySpots[col][j];
                }
            }
        }
        return emptySpots[0][0];
    }

    public HashSet<Sprite> getAllGameObjects(){ return allGameObjects;}

    public void createMapFromFile(String level){
        File file = new File(level);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String string;
            int row = 0;
            int ghostNum = 0;
            int pacNum = 0;
            int coinNum = 0;

            emptySpots = new MapGraphNode[50][50]; //todo: read from data

            while ((string = br.readLine()) != null){
                for( int i = 0; i < string.length(); i++){
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
            }
            initializeEmptySpots();
            for(Sprite ghost : myGhostSet){
                ghost.setMovementType("", myPacManSet); //todo: load targets from data
            }
        } catch(FileNotFoundException e){
            //TODO: add error here
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            //TODO: add error here
            System.out.println(e);
        }
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

}

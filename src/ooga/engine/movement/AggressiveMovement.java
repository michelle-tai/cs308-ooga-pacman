package ooga.engine.movement;

import javafx.util.Pair;
import ooga.Main;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.DynamicSprite;
import ooga.engine.sprites.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AggressiveMovement extends ControllableMovement{
  // would need to either know the map or where pacman currently is
  // i guess can have a target method and based on the target, ...
    //todo: make it so ghosts leave their spawn location;

    private Sprite mySprite;
    private int movedist = 3;
    private List<String> directions = new ArrayList<>();
    private HashMap<String, String> directionOpposites = new HashMap<>();
    private Pair<Integer, Integer> zone;
    private List<Sprite> myTarget;
    private String currDirection;
    private String prevDirection;
    private boolean directionChanged = true;
    private int timeOutSide;
    private int outsideX = 277;
    private int outsideY = 237;



    public AggressiveMovement(Sprite sprite, List<Sprite> targetSprites, int ID){
        super(sprite);
        mySprite = sprite;
        directions.addAll(List.of("Right", "Left", "Up", "Down"));
        directionOpposites.put("Right", "Left");
        directionOpposites.put("Left", "Right");
        directionOpposites.put("Up", "Down");
        directionOpposites.put("Down", "Up");
        myTarget = targetSprites;
        currDirection = "";
        prevDirection = "";
        timeOutSide = ID * 50;
        assignZone(ID);

    }

    public String setNewDirection(String direction){


        if(!currDirection.equals(direction)){
            if((direction.equals("Right") && currDirection.equals("Left")) ||(direction.equals("Left") && currDirection.equals("Right"))){
                directionChanged = false;
            } else if ((direction.equals("Down") && currDirection.equals("Up")) ||(direction.equals("Up") && currDirection.equals("Down"))) {
                directionChanged = false;
            }else{
                directionChanged = true;
            }

            prevDirection = currDirection;
            currDirection = direction;

        }

        return currDirection;
    }

    private void assignZone(int ID){
        int modID = (int) (Math.random()*4);
//        System.out.println(modID);
        switch(modID){
            case 0:
                zone = new Pair<>(500,0);
            case 1:
                zone = new Pair<>(0, 0);
            case 2:
                zone = new Pair<>(0, 500);
            case 3:
                zone = new Pair<>(0, 0);
        }
    }


    public void move(MapGraphNode currentLocation, int upTime){

        if(upTime == timeOutSide){
            mySprite.setX(outsideX);
            mySprite.setY(outsideY);
        }else {

            String moveDir = pickDirection(currentLocation, upTime);
            setNewDirection(moveDir);
            String directionMethod = "move" + moveDir;

            try {
                Method method = this.getClass().getDeclaredMethod(directionMethod, MapGraphNode.class);
                method.setAccessible(true);
                method.invoke(AggressiveMovement.this, currentLocation);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                // Do nothing
            }

            directionChanged = false;
        }

    }

    private String pickDirection(MapGraphNode currentLocation, int upTime){

        List<String> exclude = new ArrayList<String>();

        List<String> potentialDirections = RandomMovement.getDirections(currentLocation, exclude);
        exclude.add(directionOpposites.get(currDirection));
        if(potentialDirections.size() >= 3){
            return findMinLoop(RandomMovement.getDirections(currentLocation, exclude), currentLocation, upTime);
        }

        exclude.add(directionOpposites.get(prevDirection));
        //potentialDirections = RandomMovement.getDirections(currentLocation, exclude);
        return findMinLoop(RandomMovement.getDirections(currentLocation, exclude), currentLocation, upTime);

    }

    private String findMinLoop(List<String> potentialDirections, MapGraphNode currentLocation, int upTime){
        if(potentialDirections.size() > 1){
            Pair<Double, String> minDist = new Pair<>((double) Integer.MAX_VALUE, "");
            for(String dir : potentialDirections) {
                Set<Pair<Integer,Integer>> target = new HashSet<>();
                if(upTime < 10*timeOutSide || true){
                    target.add(zone);
                }else{
                    for(Sprite pM : myTarget){
                        target.add(new Pair<>(pM.getX(), pM.getY()));
                    }
                }
                for (Pair<Integer, Integer> pM : target) {
                    MapGraphNode  moveLoc = getNode(currentLocation, dir);
                    double dist = distanceFromTarget(moveLoc.getXPos(), moveLoc.getYPos(), pM);
                    if (dist < minDist.getKey()) {
                        minDist = new Pair<>(dist, dir);
                    }
                }
            }
            return minDist.getValue();
        }
        return potentialDirections.get(0);
    }

    protected void moveRight(MapGraphNode currentLocation){
        if (!directionChanged) {
            if (currentLocation.getRightNeighbor() != null) {
                int newX = mySprite.getX() + (movedist * 1 * 1);
                mySprite.setX(newX);
            } else {
                int newX = currentLocation.getXPos();
                mySprite.setX(newX);
            }
        }else {
            if (currentLocation.getRightNeighbor() != null) {
                int newX = currentLocation.getXPos();
                int newY = currentLocation.getYPos();
                mySprite.setY(newY);
                mySprite.setX(newX);
            }
        }
    }

    protected void moveLeft(MapGraphNode currentLocation){
        if (!directionChanged) {
            if (currentLocation.getLeftNeighbor() != null) {
                int newX = mySprite.getX() + (movedist * 1* -1);
                mySprite.setX(newX);
            } else {
                int newX = currentLocation.getXPos();
                mySprite.setX(newX);
            }
        }else {
            if (currentLocation.getLeftNeighbor() != null) {
                int newX = currentLocation.getXPos();
                int newY = currentLocation.getYPos();
                mySprite.setY(newY);
                mySprite.setX(newX);
            }
        }
    }

    protected void moveUp(MapGraphNode currentLocation){
        if (!directionChanged){
            if (currentLocation.getTopNeighbor() != null) {
                int newY = mySprite.getY() + (movedist * 1 * -1);
                mySprite.setY(newY);
            } else {
                int newY = currentLocation.getYPos();
                mySprite.setY(newY);
            }
        }else {
            if (currentLocation.getTopNeighbor() != null) {
                int newY = currentLocation.getYPos();
                int newX = currentLocation.getXPos();
                mySprite.setY(newY);
                mySprite.setX(newX);
            }
        }
    }

    protected void moveDown(MapGraphNode currentLocation){
        if (!directionChanged) {
            if (currentLocation.getBottomNeighbor() != null) {
                int newY = mySprite.getY() + (movedist * 1 * 1);
                mySprite.setY(newY);
            } else {
                int newY = currentLocation.getYPos();
                mySprite.setY(newY);

            }
        }else {
            if (currentLocation.getBottomNeighbor() != null) {
                int newY = currentLocation.getYPos() ;
                int newX = currentLocation.getXPos();
                mySprite.setY(newY);
                mySprite.setX(newX);
            }
        }
    }


    private double distanceFromTarget(int X, int Y, Pair<Integer, Integer> target){
        int pMX = target.getKey();
        int pMY = target.getValue();
        double distance = Math.sqrt(Math.pow(X-pMX, 2) + Math.pow(Y-pMY, 2));
        return distance;
    }


    //todo: potentially implement map for neighbors so you dont need lots of if statements
    private static MapGraphNode getNode(MapGraphNode currentLocation, String direction){

            if (direction.equals("Right") && currentLocation.getRightNeighbor() != null) {
                return currentLocation.getRightNeighbor();
            }
            if (direction.equals("Left") && currentLocation.getLeftNeighbor() != null) {
                return currentLocation.getLeftNeighbor();
            }
            if (direction.equals("Up") && currentLocation.getTopNeighbor() != null) {
                return currentLocation.getTopNeighbor();
            }
            if (direction.equals("Down") && currentLocation.getBottomNeighbor() != null) {
                return currentLocation.getBottomNeighbor();
            }
            return null;
    }


}

package ooga.engine.movement;

import javafx.util.Pair;
import ooga.Main;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AggressiveMovement extends ControllableMovement{
  // would need to either know the map or where pacman currently is
  // i guess can have a target method and based on the target, ...
    //todo: make it so ghosts leave their spawn location;

    private Sprite mySprite;
    private int movedist = 10;
    private List<String> directions = new ArrayList<>();
    private List<Sprite> myTarget;
    private String currDirection;
    private String prevDirection;
    private boolean directionChanged = true;


    public AggressiveMovement(Sprite sprite, List<Sprite> targetSprites){
        super(sprite);
        mySprite = sprite;
        directions.addAll(List.of("Right", "Left", "Up", "Down"));
        myTarget = targetSprites;
        currDirection = "";
        prevDirection = "";

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


    public void move(MapGraphNode currentLocation){

        String moveDir = pickDirection(currentLocation);
        setNewDirection(moveDir);
        String directionMethod = "move" + moveDir;
        System.out.println(moveDir);

        try {
            Method method = this.getClass().getDeclaredMethod(directionMethod, MapGraphNode.class);
            method.setAccessible(true);
            method.invoke(AggressiveMovement.this, currentLocation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            // Do nothing
        }

        directionChanged = false;

    }

    private String pickDirection(MapGraphNode currentLocation){
        List<String> exclude = new ArrayList<String>();
        exclude.add(RandomMovement.directionOpposites.get(currDirection));
        exclude.add(RandomMovement.directionOpposites.get(prevDirection));

        List<String> potentialDirections = RandomMovement.getDirections(currentLocation, exclude);
        System.out.println(potentialDirections.size());

        if(potentialDirections.size() > 1){
            Pair<Double, String> minDist = new Pair<>((double) Integer.MAX_VALUE, "");
            for(String dir : potentialDirections) {
                for (Sprite pM : myTarget) {
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

    }

    protected void moveLeft(MapGraphNode currentLocation){

    }

    protected void moveUp(MapGraphNode currentLocation){

    }

    protected void moveDown(MapGraphNode currentLocation){

    }


    private double distanceFromTarget(int X, int Y, Sprite target){
        double min = Integer.MAX_VALUE;
        int pMX = target.getX();
        int pMY = target.getY();
        double distance = Math.sqrt(Math.pow(X-pMX, 2) + Math.pow(Y-pMY, 2));
        return distance;
    }


    private boolean isDirection(String direction){
        for(String dir : directions){
            if(direction.equals(dir)){
                return true;
            }
        }
        return false;
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

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
            //System.out.println("hi");
        }

        directionChanged = false;

    }

    private String pickDirection(MapGraphNode currentLocation){
        List<String> exclude = new ArrayList<String>();
        exclude.add(RandomMovement.directionOpposites.get(currDirection));
        exclude.add(RandomMovement.directionOpposites.get(prevDirection));


        return "";
    }

    protected void moveRight(MapGraphNode currentLocation){

    }

    protected void moveLeft(MapGraphNode currentLocation){

    }

    protected void moveUp(MapGraphNode currentLocation){

    }

    protected void moveDown(MapGraphNode currentLocation){

    }


    private double distanceFromPacMan(int X, int Y){
        double min = Integer.MAX_VALUE;
        for(Sprite pM : myTarget){
            int pMX = pM.getX();
            int pMY = pM.getY();
            double distance = Math.sqrt(Math.pow(X-pMX, 2) + Math.pow(Y-pMY, 2));
            if(distance < min){
                min = distance;
            }
        }
        return min;
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
    private static List<MapGraphNode> getNode(MapGraphNode currentLocation, List<String> dir){
        List<MapGraphNode> ret = new ArrayList<>();
        for(String direction : dir ) {
            if (direction.equals("Right") && currentLocation.getRightNeighbor() != null) {
                ret.add(currentLocation.getRightNeighbor());
            }
            if (direction.equals("Left") && currentLocation.getLeftNeighbor() != null) {
                ret.add(currentLocation.getLeftNeighbor());
            }
            if (direction.equals("Up") && currentLocation.getTopNeighbor() != null) {
                ret.add(currentLocation.getTopNeighbor());
            }
            if (direction.equals("Down") && currentLocation.getBottomNeighbor() != null) {
                ret.add(currentLocation.getBottomNeighbor());
            }
        }
        return ret;
    }


}

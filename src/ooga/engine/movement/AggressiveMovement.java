package ooga.engine.movement;

import ooga.Main;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AggressiveMovement extends ControllableMovement{
  // would need to either know the map or where pacman currently is
  // i guess can have a target method and based on the target, ...
    //todo: make it so ghosts leave their spawn location;

    private Sprite mySprite;
    private int movedist = 10;
    private List<String> directions = new ArrayList<>();
    private List<Sprite> myTarget;
    private String currDirection;
    private boolean directionChanged = true;


    public AggressiveMovement(Sprite sprite, List<Sprite> targetSprites){
        super(sprite);
        mySprite = sprite;
        directions.addAll(List.of("Right", "Left", "Up", "Down"));
        myTarget = targetSprites;
        currDirection = Main.MY_RESOURCES.getString("Right");

    }




    public void move(MapGraphNode currentLocation){

        setNewDirection("Right");
        String directionMethod = "move" + "Right";

        try {
            Method method = this.getClass().getDeclaredMethod(directionMethod, MapGraphNode.class);
            method.setAccessible(true);
            method.invoke(AggressiveMovement.this, currentLocation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            // Do nothing
            System.out.println("hi");
        }
        directionChanged = false;
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


}

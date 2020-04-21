package ooga.engine.movement;

import ooga.engine.MapGraphNode;
import ooga.engine.sprites.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AggressiveMovement extends RandomMovement{
  // would need to either know the map or where pacman currently is
  // i guess can have a target method and based on the target, ...
    //todo: make it so ghosts leave their spawn location;

    private Sprite mySprite;
    private int movedist = 10;
    private List<String> directions = new ArrayList<>();
    private List<Sprite> myTarget;


    public AggressiveMovement(Sprite sprite, List<Sprite> targetSprites){
        super(sprite);
        mySprite = sprite;
        directions.addAll(List.of("Right", "Left", "Up", "Down"));
        myTarget = targetSprites;
    }


    public void move(MapGraphNode currentLocation){
        List<String> possibleDirections = getDirections(currentLocation);
//        System.out.println(possibleDirections.size());
        String moveDir = "";
        for(String direction : possibleDirections){
            int X = Integer.MAX_VALUE;
            int Y = Integer.MAX_VALUE;
            double minDistance = Integer.MAX_VALUE;
            if(isDirection(direction)){
                switch(direction) {
                    case("Right"):
                        X = mySprite.getX() + (movedist * 1 * 1);
                        Y = mySprite.getY();
                    case("Left"):
                        X = mySprite.getX() + (movedist * 1 * -1);
                        Y = mySprite.getY();
                    case("Up"):
                        X = mySprite.getX();
                        Y = mySprite.getY() + (movedist * 1 * -1);
                    case("Down"):
                        X = mySprite.getX();
                        Y = mySprite.getY() + (movedist * 1 * 1);
                }
                double curDistance = distanceFromPacMan(X, Y);
                if(curDistance < minDistance){
                    minDistance = curDistance;
                    moveDir = direction;
                }
            }
        }
        setNewDirection(moveDir);
//        System.out.println(moveDir);
        String directionMethod = "move" + moveDir;

        try {
            Method method = this.getClass().getDeclaredMethod(directionMethod);
            method.setAccessible(true);
            method.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            // Do nothing
        }
//        System.out.println("");
    }

    private void moveRight(){
        int newX = mySprite.getX() + (movedist * 1 * 1);
        mySprite.setX(newX);
    }

    private void moveLeft(){
        int newX = mySprite.getX() + (movedist * 1 * -1);
        mySprite.setX(newX);
    }

    private void moveUp(){
        int newY = mySprite.getY() + (movedist * 1 * -1);
        mySprite.setY(newY);
    }

    private void moveDown(){
        int newY = mySprite.getY() + (movedist * 1 * 1);
        mySprite.setY(newY);
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

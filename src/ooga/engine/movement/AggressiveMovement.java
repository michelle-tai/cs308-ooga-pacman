package ooga.engine.movement;

import javafx.util.Pair;
import ooga.Main;
import ooga.data.PathManager;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.DynamicSprite;
import ooga.engine.sprites.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/*
This class enables aggressive (following pacman) behavior of Ghost objects. To use, call the move(MapGraphNode) currentLocation)
method, and the rest is done internally. The constructor requires target inputs, and a sprite who's location is actually
edited.

@author : Olga Suchankova, Michelle Tai
 */


public class AggressiveMovement extends ControllableMovement{

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
    private int outsideX = 0;
    private int outsideY = 0;
    private int timeDisperse;
    private int upTime;



    public AggressiveMovement(Sprite sprite, List<Sprite> targetSprites, int ID, int lagTime, int disperseTime){
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
        timeOutSide = ID * lagTime;
        timeDisperse =  disperseTime;
        upTime = 0;
    }

    /**
     * Method changes the status of internal variables that are used in correctly setting the new location of the
     * sprite when the object is moved
     * @param direction is the new direction the sprite is moving
     * @return essentially the same string as the input.
     */
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

    private void assignZone(int ID) {
        int modID = (int) (Math.random() * 4);
//        System.out.println(modID);
        switch (modID) {
            case 0:
                zone = new Pair<>(500, 0);
            case 1:
                zone = new Pair<>(0, 0);
            case 2:
                zone = new Pair<>(0, 500);
            case 3:
                zone = new Pair<>(0, 0);
                System.out.print(modID);
                if (modID == 0) {
                    zone = new Pair<>(0, 0);
                } else if (modID == 1) {
                    zone = new Pair<>(0, outsideY * outsideY);
                } else if (modID == 2) {
                    zone = new Pair<>(outsideX * outsideX, 0);
                } else if (modID == 3) {
                    zone = new Pair<>(outsideX * outsideX, outsideY * outsideY);
                }
        }
    }

    /**
     * method which uses private methods to decide which direction to move in.
     * @param currentLocation is the current location of the sprite
     */
    public void move (MapGraphNode currentLocation){
        upTime++;

        if (upTime == timeOutSide) {
            mySprite.setX(outsideX);
            mySprite.setY(outsideY);
        } else {

            String moveDir = pickDirection(currentLocation);
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

    private String pickDirection (MapGraphNode currentLocation){

        List<String> exclude = new ArrayList<String>();

        List<String> potentialDirections = RandomMovement.getDirections(currentLocation, exclude);
        exclude.add(directionOpposites.get(currDirection));
        if (potentialDirections.size() >= 3) {
            return findMinLoop(RandomMovement.getDirections(currentLocation, exclude), currentLocation, upTime);
        }

        exclude.add(directionOpposites.get(prevDirection));
        //potentialDirections = RandomMovement.getDirections(currentLocation, exclude);
        return findMinLoop(RandomMovement.getDirections(currentLocation, exclude), currentLocation, upTime);

    }

    private String findMinLoop (List < String > potentialDirections, MapGraphNode currentLocation,int upTime){
        if (potentialDirections.size() > 1) {
            Pair<Double, String> minDist = new Pair<>((double) Integer.MAX_VALUE, "");
            for (String dir : potentialDirections) {
                Set<Pair<Integer, Integer>> target = new HashSet<>();
                if (upTime < timeOutSide + timeDisperse) {
                    target.add(zone);
                } else {
                    for (Sprite pM : myTarget) {
                        target.add(new Pair<>(pM.getX(), pM.getY()));
                    }
                }
                for (Pair<Integer, Integer> pM : target) {
                    MapGraphNode moveLoc = getNode(currentLocation, dir);
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

    protected void moveRight (MapGraphNode currentLocation){
        if (!directionChanged) {
            if (currentLocation.getRightNeighbor() != null) {
                int newX = mySprite.getX() + (movedist * 1 * 1);
                mySprite.setX(newX);
            } else {
                int newX = currentLocation.getXPos();
                mySprite.setX(newX);
            }
        } else {
            if (currentLocation.getRightNeighbor() != null) {
                int newX = currentLocation.getXPos();
                int newY = currentLocation.getYPos();
                mySprite.setY(newY);
                mySprite.setX(newX);
            }
        }
    }

    protected void moveLeft (MapGraphNode currentLocation){
        if (!directionChanged) {
            if (currentLocation.getLeftNeighbor() != null) {
                int newX = mySprite.getX() + (movedist * 1 * -1);
                mySprite.setX(newX);
            } else {
                int newX = currentLocation.getXPos();
                mySprite.setX(newX);
            }
        } else {
            if (currentLocation.getLeftNeighbor() != null) {
                int newX = currentLocation.getXPos();
                int newY = currentLocation.getYPos();
                mySprite.setY(newY);
                mySprite.setX(newX);
            }
        }
    }

    protected void moveUp (MapGraphNode currentLocation){
        if (!directionChanged) {
            if (currentLocation.getTopNeighbor() != null) {
                int newY = mySprite.getY() + (movedist * 1 * -1);
                mySprite.setY(newY);
            } else {
                int newY = currentLocation.getYPos();
                mySprite.setY(newY);
            }
        } else {
            if (currentLocation.getTopNeighbor() != null) {
                int newY = currentLocation.getYPos();
                int newX = currentLocation.getXPos();
                mySprite.setY(newY);
                mySprite.setX(newX);
            }
        }
    }

    protected void moveDown (MapGraphNode currentLocation){
        if (!directionChanged) {
            if (currentLocation.getBottomNeighbor() != null) {
                int newY = mySprite.getY() + (movedist * 1 * 1);
                mySprite.setY(newY);
            } else {
                int newY = currentLocation.getYPos();
                mySprite.setY(newY);

            }
        } else {
            if (currentLocation.getBottomNeighbor() != null) {
                int newY = currentLocation.getYPos();
                int newX = currentLocation.getXPos();
                mySprite.setY(newY);
                mySprite.setX(newX);
            }
        }
    }


    private double distanceFromTarget ( int X, int Y, Pair<Integer, Integer > target){
        int pMX = target.getKey();
        int pMY = target.getValue();
        double distance = Math.sqrt(Math.pow(X - pMX, 2) + Math.pow(Y - pMY, 2));
        return distance;
    }


    //todo: potentially implement map for neighbors so you dont need lots of if statements
    private static MapGraphNode getNode (MapGraphNode currentLocation, String direction){

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

    /**
     * Initialization method for Aggressive Movement Class. Sets outside of the cage location for ghosts and calls a function
     * which customizes the ghost movement for each instance of the ghost.
     * @param X xloc of outside cage location
     * @param Y yloc of outside cage location
     * @param itter used in switch case statement
     */
    public void ghostSpawn ( int X, int Y, int itter){
        outsideX = X;
        outsideY = Y;
        assignZone(itter);
    }

    /**
     * resets the internal time of the movement class to 0. Used for when ghost is sent back to home.
     */
    public void resetUpTime() {
        upTime = 0;
    }
}


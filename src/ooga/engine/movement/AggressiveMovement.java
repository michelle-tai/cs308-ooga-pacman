package ooga.engine.movement;

import ooga.Main;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
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
    private String prevDirection;
    private boolean directionChanged = true;


    public AggressiveMovement(Sprite sprite, List<Sprite> targetSprites){
        super(sprite);
        mySprite = sprite;
        directions.addAll(List.of("Right", "Left", "Up", "Down"));
        myTarget = targetSprites;
        currDirection = Main.MY_RESOURCES.getString("");
        prevDirection = "";

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

    private String pickDirection(MapGraphNode currentLocation){
        List<String> exclude = new ArrayList<String>();
        exclude.add(RandomMovement.directionOpposites.get(currDirection));
        exclude.add(RandomMovement.directionOpposites.get(prevDirection));

        List<String> potentialDirections = RandomMovement.getDirections(currentLocation, exclude);

        HashSet<MapGraphNode> visited = new HashSet<>();

        visited.add(currentLocation);

        for(String dir : potentialDirections){
            ArrayList<String> dirList = new ArrayList<>();
            dirList.add(dir);
            visited.addAll(getNode(currentLocation, dirList));
            List<MapGraphNode> quene = 
            while
        }

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
            if (direction.equals("Right")) {
                ret.add(currentLocation.getRightNeighbor());
            }
            if (direction.equals("Left")) {
                ret.add(currentLocation.getLeftNeighbor());
            }
            if (direction.equals("Up")) {
                ret.add(currentLocation.getTopNeighbor());
            }
            if (direction.equals("Down")) {
                ret.add(currentLocation.getBottomNeighbor());
            }
        }
        return ret;
    }


}

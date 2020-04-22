package ooga.engine.movement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ooga.Main;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.Sprite;

public class RandomMovement extends ControllableMovement {
  private static List<String> directions = new ArrayList<>();
  protected static HashMap<String, String> directionOpposites = new HashMap<>();
  private Sprite mySprite;
  protected String currDirection;
  private String prevDirection;
  private int movedist = 10;


  public RandomMovement(Sprite sprite){
    super(sprite);
    mySprite = sprite;
    directions.addAll(List.of("Right", "Left", "Up", "Down"));
    directionOpposites.put("Right", "Left");
    directionOpposites.put("Left", "Right");
    directionOpposites.put("Up", "Down");
    directionOpposites.put("Down", "Up");
    currDirection = "";
    prevDirection = "";
  }



  //not the best design but will change later
  @Override
  public void move(MapGraphNode currentLocation){
    //todo: change the design struture rn, but it have this do nothing rn
    List<String> avoid = new ArrayList<>();
    avoid.add(directionOpposites.get(currDirection));
    avoid.add(directionOpposites.get(prevDirection));
    List<String> possibleDirections = getDirections(currentLocation, avoid);
    int min = 0;
    int max = possibleDirections.size();
    int range = max - min; //max 3 min0 range = max - min + 1
    int rand = (int)(Math.random() * range) + min;
    currDirection = setNewDirection(possibleDirections.get(rand));
    String directionMethod = "move" + currDirection;
//    System.out.println(directionMethod);

    try {
      Method method = this.getClass().getDeclaredMethod(directionMethod, MapGraphNode.class);
      method.setAccessible(true);
//      System.out.println(method);
//      System.out.println(getSuperClass());
      method.invoke(this, currentLocation);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
      // Do nothing
    }

    directionChanged = false;
  }

  protected void moveRight(MapGraphNode currentLocation){
//    int newX = mySprite.getX() + (movedist * 1 * 1);
//    mySprite.setX(newX);
//    super.moveRight(currentLocation);
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
//    int newX = mySprite.getX() + (movedist * 1 * -1);
//    mySprite.setX(newX);
//    super.moveLeft(currentLocation);
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
//    int newY = mySprite.getY() + (movedist * 1 * -1);
//    mySprite.setY(newY);
//    super.moveUp(currentLocation);
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
//    int newY = mySprite.getY() + (movedist * 1 * 1);
//    mySprite.setY(newY);
//    super.moveDown(currentLocation);
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


  protected static List<String> getDirections(MapGraphNode currentLocation, List<String> exclude){
    ArrayList<String> possibleDirections = new ArrayList<>();
    if(currentLocation.getRightNeighbor()!= null){
      possibleDirections.add("Right");
    }
    if(currentLocation.getLeftNeighbor() != null){
      possibleDirections.add("Left");
    }
    if(currentLocation.getBottomNeighbor() != null){
      possibleDirections.add("Down");
    }
    if(currentLocation.getTopNeighbor() != null){
      possibleDirections.add("Up");
    }
    if(possibleDirections.size() < 1){
      possibleDirections.add("Do Nothing");
    }

    for(int i = 0; i < exclude.size(); i++){
      if(possibleDirections.size() > 1){
        if(possibleDirections.contains(exclude.get(i))){
          possibleDirections.remove(exclude.get(i));
        }
      }
    }


    return possibleDirections;
  }


}

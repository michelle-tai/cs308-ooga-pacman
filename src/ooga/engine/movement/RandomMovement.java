package ooga.engine.movement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ooga.Main;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.Sprite;

public class RandomMovement extends ControllableMovement {
  private List<String> directions = new ArrayList<>();
  private Sprite mySprite;
  protected String currDirection;
  private int movedist = 10;
  private boolean directionChanged = false;


  public RandomMovement(Sprite sprite){
    super(sprite);
    mySprite = sprite;
    directions.addAll(List.of("Right", "Left", "Up", "Down"));
    currDirection = Main.MY_RESOURCES.getString("Right");
  }

  //not the best design but will change later
  @Override
  public void move(MapGraphNode currentLocation){
    //todo: change the design struture rn, but it have this do nothing rn

    List<String> possibleDirections = getDirections(currentLocation);
    int min = 0;
    int max = possibleDirections.size();
    int range = max - min; //max 3 min0 range = max - min + 1
    int rand = (int)(Math.random() * range) + min;
    setNewDirection(possibleDirections.get(rand));
    String directionMethod = "move" + currDirection;
//    System.out.println(directionMethod);

    try {
      Method method = this.getClass().getDeclaredMethod(directionMethod);
      method.setAccessible(true);
//      System.out.println(method);
//      System.out.println(getSuperClass());
      method.invoke(this);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
      // Do nothing
    }

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


  protected List<String> getDirections(MapGraphNode currentLocation){
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

    return possibleDirections;
  }


}

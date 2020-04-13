package ooga.engine.movement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import ooga.engine.Sprite;

public class RandomMovement extends ControllableMovement {
  private List<String> directions = new ArrayList<>();
  private Sprite mySprite;
  private int movedist = 10;


  public RandomMovement(Sprite sprite){
    super(sprite);
    mySprite = sprite;
    directions.addAll(List.of("Right", "Left", "Up", "Down"));
  }

  //not the best design but will change later
  @Override
  public void move(){
    //todo: change the design struture rn, but it have this do nothing rn
    int min = 0;
    int max = directions.size();
    int range = max - min; //max 3 min0 range = max - min + 1
    int rand = (int)(Math.random() * range) + min;

    String currDirection  = directions.get(rand);
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


}

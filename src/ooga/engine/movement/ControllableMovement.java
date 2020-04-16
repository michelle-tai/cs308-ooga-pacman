package ooga.engine.movement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import ooga.Main;
import ooga.engine.Sprite;

public class ControllableMovement {
  private Sprite mySprite;
  private String currDirection;
  private int movedist = 1;
  private int mySpeed;

  public ControllableMovement(Sprite sprite){
    mySprite = sprite;
    currDirection = Main.MY_RESOURCES.getString("Right");
    mySpeed = sprite.getSpeed();
  }

  public void setNewDirection(String direction){
    currDirection = direction;
  }

  public void move(){
    String directionMethod = "move" + currDirection;

    try {
      Method method = this.getClass().getDeclaredMethod(directionMethod);
      method.invoke(ControllableMovement.this);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
      // Do nothing
    }
  }

  private void moveRight(){
    int newX = mySprite.getX() + (movedist * mySpeed * 1);
    mySprite.setX(newX);
  }

  private void moveLeft(){
    int newX = mySprite.getX() + (movedist * mySpeed * -1);
    mySprite.setX(newX);
//    System.out.println("left");
  }

  private void moveUp(){
    int newY = mySprite.getY() + (movedist * mySpeed * -1);
    mySprite.setY(newY);
  }

  private void moveDown(){
    int newY = mySprite.getY() + (movedist * mySpeed * 1);
    mySprite.setY(newY);
  }

}

package ooga.engine.movement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import ooga.Main;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.Sprite;

public class ControllableMovement {
  private Sprite mySprite;
  private String currDirection;
  private int movedist = 1;
  private int mySpeed;
  private boolean directionChanged = false;

  public ControllableMovement(Sprite sprite){
    mySprite = sprite;
    currDirection = Main.MY_RESOURCES.getString("Right");
    mySpeed = sprite.getSpeed();
  }

  public void setNewDirection(String direction){
    if(!currDirection.equals(direction)){
      if(direction.equals(currDirection)){
        directionChanged = false;
      } else if((direction.equals("Right") && currDirection.equals("Left")) ||(direction.equals("Left") && currDirection.equals("Right"))){
        directionChanged = false;
      } else if ((direction.equals("Down") && currDirection.equals("Up")) ||(direction.equals("Up") && currDirection.equals("Down"))) {
        directionChanged = false;
      }else{
        directionChanged = true;
      }
      currDirection = direction;
    }
  }




  public void move(MapGraphNode currentLocation){
    String directionMethod = "move" + currDirection;

    try {
      Method method = this.getClass().getDeclaredMethod(directionMethod, MapGraphNode.class);
      method.invoke(ControllableMovement.this, currentLocation);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
      // Do nothing
    }
    directionChanged = false;
  }


  //todo: get rid of magic values
  private void moveRight(MapGraphNode currentLocation) {
//    int newX = mySprite.getX() + (movedist * mySpeed * 1);
//    mySprite.setX(newX);

    if (!directionChanged) {
      if (currentLocation.getRightNeighbor() != null) {
        int newX = mySprite.getX() + (movedist * mySpeed * 1);
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

  private void moveLeft(MapGraphNode currentLocation) {
//    int newX = mySprite.getX() + (movedist * mySpeed * -1);
//    mySprite.setX(newX);
    if (!directionChanged) {
      if (currentLocation.getLeftNeighbor() != null) {
        int newX = mySprite.getX() + (movedist * mySpeed * -1);
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

  private void moveUp(MapGraphNode currentLocation) {
//    int newY = mySprite.getY() + (movedist * mySpeed * -1);
//    mySprite.setY(newY);
    if (!directionChanged){
      if (currentLocation.getTopNeighbor() != null) {
        int newY = mySprite.getY() + (movedist * mySpeed * -1);
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

  private void moveDown(MapGraphNode currentLocation) {
//    int newY = mySprite.getY() + (movedist * mySpeed * 1);
//    mySprite.setY(newY);
    if (!directionChanged) {
      if (currentLocation.getBottomNeighbor() != null) {
        int newY = mySprite.getY() + (movedist * mySpeed * 1);
        mySprite.setY(newY);
      } else {
        int newY = currentLocation.getYPos();
        mySprite.setY(newY);

      }
    }else {
        if (currentLocation.getBottomNeighbor() != null) {
          int newY = currentLocation.getYPos();
          int newX = currentLocation.getXPos();
          mySprite.setY(newY);
          mySprite.setX(newX);
        }
      }
    }

}

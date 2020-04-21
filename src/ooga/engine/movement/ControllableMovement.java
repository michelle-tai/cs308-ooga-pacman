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
      currDirection = direction;
      directionChanged = true;
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

  private void moveRight(MapGraphNode currentLocation) {
//    int newX = mySprite.getX() + (movedist * mySpeed * 1);
//    mySprite.setX(newX);

    if (!directionChanged) {
      if (currentLocation.getRightNeighbor() != null) {
        int newX = mySprite.getX() + (movedist * mySpeed * 1);
        mySprite.setX(newX);
      } else {
        if (currentLocation.getRightNeighbor() != null) {
          int newX = currentLocation.getRightNeighbor().getXPos() * 40;
          int newY = currentLocation.getRightNeighbor().getXPos()*40;
          mySprite.setY(newY);
          mySprite.setX(newX);
        }
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
        if (currentLocation.getLeftNeighbor() != null) {
          int newX = currentLocation.getLeftNeighbor().getXPos() * 40;
          int newY = currentLocation.getLeftNeighbor().getYPos()*40;
          mySprite.setY(newY);
          mySprite.setX(newX);
        }
      }
    }
  }

  private void moveUp(MapGraphNode currentLocation) {
//    int newY = mySprite.getY() + (movedist * mySpeed * -1);
//    mySprite.setY(newY);
    if (!directionChanged) {
      if (currentLocation.getTopNeighbor() != null) {
        int newY = mySprite.getY() + (movedist * mySpeed * -1);
        mySprite.setY(newY);
      } else {
        if (currentLocation.getTopNeighbor() != null) {
          int newY = currentLocation.getTopNeighbor().getYPos() * 40;
          int newX = currentLocation.getTopNeighbor().getXPos()*40 ;
          mySprite.setY(newY);
          mySprite.setX(newX);
        }
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
        if (currentLocation.getBottomNeighbor() != null) {
          int newY = currentLocation.getBottomNeighbor().getYPos() * 40;
          int newX = currentLocation.getBottomNeighbor().getXPos()*40;
          mySprite.setY(newY);
          mySprite.setX(newX);
        }
      }
    }
  }

}

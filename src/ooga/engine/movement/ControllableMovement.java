package ooga.engine.movement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import ooga.Main;
import ooga.engine.MapGraphNode;
import ooga.engine.sprites.Sprite;

/**
 * This defines a type of movement that any moveable sprite can have. The movement is controlled through
 * the keys, and these keys can easily be changed in the properties file.
 *
 * Dependencies: Sprite, MapGraphNode
 *
 * @author Michelle Tai
 * @author Olga Suchankova
 */

public class ControllableMovement {
  private Sprite mySprite;
  private String currDirection;
  private int movedist = 1;
  private int mySpeed;
  protected boolean directionChanged = false;

  public ControllableMovement(Sprite sprite){
    mySprite = sprite;
    currDirection = Main.RIGHT;
    mySpeed = sprite.getSpeed();
  }

  /**
   * Sets the new direction in which the sprite is moving. This is so that the frontend can read the key pressed, translate it to
   * either a "right," "left," "up," "down" string using the property file, and pass it to this backend method.
   * @param direction is the new direction the sprite is moving
   * @return the current direction
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
      currDirection = direction;
    }
    return currDirection;
  }


  /**
   * Moves the sprite to a new location by calculating the new x and y positions.
   * This method accepts a MapGraphNode, which helps determine is that sprite can actually move in
   * the direction requested.
   * @param currentLocation is the current location of the sprite
   */
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

  
  protected void moveRight(MapGraphNode currentLocation) {
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

  protected void moveLeft(MapGraphNode currentLocation) {
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

  protected void moveUp(MapGraphNode currentLocation) {
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

  protected void moveDown(MapGraphNode currentLocation) {
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

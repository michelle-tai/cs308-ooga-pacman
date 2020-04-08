package ooga.engine;

import ooga.Main;

public class Movement {
  private Sprite mySprite;
  private String currDirection;
  private int movedist = 4;
  private int mySpeed;

  public Movement(Sprite sprite){
    mySprite = sprite;
    currDirection = Main.MY_RESOURCES.getString("Right");
    mySpeed = sprite.getSpeed;
  }

  public void setNewDirection(String direction){
    currDirection = direction;
  }

  public void move(){
    int dx = -1;
    int dy = -1;
    int movedDistX = movedist*;
    int movedDistY = movedist * mySpeed;
    if(direction.equals(Main.MY_RESOURCES.getString("Right")) || direction.equals(Main.MY_RESOURCES.getString("Left"))){
      movedDistX = movedDistX * dx;
    }
    else if(direction.equals(Main.MY_RESOURCES.getString("Down")) || direction.equals(Main.MY_RESOURCES.getString("Up"))){
      movedDistY = movedDistY * dy;
    }
    xPos += movedDistX;
    yPos += movedDistY;
  }


}

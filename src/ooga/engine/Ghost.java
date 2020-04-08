package ooga.engine;

import javafx.scene.shape.Rectangle;
import ooga.Main;

public class Ghost implements Sprite {
  private int xPos;
  private int yPos;
  private Rectangle hitbox;
  private int mySpeed;
  private int movedist = 35;

  public Ghost(int startingX, int startingY, int hitBoxWidth, int hitBoxLength){
    xPos = startingX;
    yPos = startingY;
    hitbox = new Rectangle(startingX, startingY, hitBoxWidth, hitBoxLength);
  }

  @Override
  public int getX() {
    return xPos;
  }

  @Override
  public int getY() {
    return yPos;
  }

  @Override
  public void setX(int newX) {
    xPos = newX;
  }

  @Override
  public void setY(int newY) {
    yPos = newY;
  }

  @Override
  public Rectangle getHitBox() {
    return hitbox;
  }

  /*
    setter for object movement speed
     */
  public void setSpeed(int speed){
    mySpeed = speed;
  }

  /*
  getter for object movement speed
   */
  public int getSpeed(){
    return mySpeed;
  }

  public void move(){

  }
}

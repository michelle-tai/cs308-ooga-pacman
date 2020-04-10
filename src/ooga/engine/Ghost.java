package ooga.engine;

import javafx.scene.shape.Rectangle;

public abstract class Ghost implements Sprite{
  private int xPos;
  private int yPos;
  private Rectangle hitbox;
  private int mySpeed;
  private int movedist = 35;
  private PacManMovement ghostMove;

  public Ghost(int startingX, int startingY, int hitBoxWidth, int hitBoxLength){
    xPos = startingX;
    yPos = startingY;
    hitbox = new Rectangle(startingX, startingY, hitBoxWidth, hitBoxLength);
    ghostMove = new GhostMovement(this);
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
    System.out.println("newX " + newX);
  }

  @Override
  public void setY(int newY) {
    yPos = newY;
  }

  @Override
  public Rectangle getHitBox() {
    return hitbox;
  }

  @Override
  public String getMovementType() {
    return null;
  }

  @Override
  public void setMovementType(String movementType) {
    //donothing
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
    ghostMove.move();
//    System.out.println("ghost move");
  }
}

package ooga.engine.sprites;

import javafx.scene.shape.Rectangle;
import ooga.engine.movement.RandomMovement;
import ooga.engine.movement.ControllableMovement;
import ooga.engine.Sprite;

public class Ghost implements Sprite{
  private int prevX;
  private int prevY;
  private int homeXPos;
  private int homeYPos;
  private int xPos;
  private int yPos;
  private Rectangle hitbox;
  private int mySpeed;
  private int movedist = 35;
  private ControllableMovement ghostMove;

  public Ghost(int startingX, int startingY, int hitBoxWidth, int hitBoxLength){
    prevX = startingX;
    prevY = startingY;
    homeXPos = startingX;
    homeYPos = startingY;
    xPos = startingX;
    yPos = startingY;
    hitbox = new Rectangle(startingX, startingY, hitBoxWidth, hitBoxLength);
    ghostMove = new RandomMovement(this);
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
    prevX = xPos;
    xPos = newX;

  }

  @Override
  public void setY(int newY) {
    prevY = yPos;
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

  @Override
  public int getStatus() {
    return 0;
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

  public void setHome(){
    setX(homeXPos);
    setY(homeYPos);
  }

  public void setPreviousLocation(){
    xPos = prevX;
    yPos = prevY;
  }

}

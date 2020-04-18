package ooga.engine.sprites;

import javafx.scene.shape.Rectangle;
import ooga.Main;
import ooga.engine.DynamicSprite;
import ooga.engine.movement.RandomMovement;
import ooga.engine.movement.ControllableMovement;
import ooga.engine.Sprite;

public class Ghost extends DynamicSprite{
  private int myStatus;
//  private int myID;
//  private int prevX;
//  private int prevY;
//  private int homeXPos;
//  private int homeYPos;
//  private int xPos;
//  private int yPos;
//  private Rectangle hitbox;
  private int mySpeed;
  private int movedist = 35;
  private ControllableMovement ghostMove;

  public Ghost(int startingX, int startingY, int hitBoxWidth, int hitBoxLength, int ID){
    super(startingX, startingY, hitBoxWidth, hitBoxLength, ID);
    mySpeed = Integer.parseInt(Main.MY_RESOURCES.getString("GhostDefaultSpeed"));
    myStatus = 0;
    ghostMove = new RandomMovement(this);
  }


  @Override
  public String getMovementType() {
    return null;
  }

  @Override
  public void setMovementType(String movementType) {
    //donothing
  }


  @Override
  public int getStatus() {
    return myStatus;
  }

  @Override
  public void setStatus(int newStatus) {
    myStatus = newStatus;
  }



  public void move(){
    ghostMove.move();
//    System.out.println("ghost move");
  }

  @Override
  public int getSpeed() {
    return mySpeed;
  }

  @Override
  public void setSpeed(int newSpeed) {
    mySpeed = newSpeed;
  }


}

package ooga.engine.sprites;

import ooga.Main;
import ooga.engine.MapGraphNode;
import ooga.engine.movement.AggressiveMovement;
import ooga.engine.movement.ControllableMovement;
import ooga.engine.movement.RandomMovement;

import java.util.List;

public class Ghost extends DynamicSprite{
  private int myStatus;

  private int mySpeed;
  private int movedist = 35;
  private ControllableMovement ghostMove;

  public Ghost(int startingX, int startingY, int hitBoxWidth, int hitBoxLength, int ID){
    super(startingX , startingY , hitBoxWidth, hitBoxLength, ID);
    mySpeed = Integer.parseInt(Main.MY_RESOURCES.getString("GhostDefaultSpeed"));
    myStatus = 0;
    //ghostMove = new AggressiveMovement(this, targetSprites);
  }



  @Override
  public String getMovementType() {
    return null;
  }

  @Override
  public void setMovementType(String movementType, List<Sprite> targetSprites) {
    //todo: fix with reflection
    ghostMove = new RandomMovement(this);
  }


  @Override
  public int getStatus() {
    return myStatus;
  }

  @Override
  public void setStatus(int newStatus) {
    myStatus = newStatus;
  }



  public void move(MapGraphNode currentLocation){
    ghostMove.move(currentLocation);
  }

//  public void move(){
//    ghostMove.move();
//  }

  @Override
  public int getSpeed() {
    return mySpeed;
  }

  @Override
  public void setSpeed(int newSpeed) {
    mySpeed = newSpeed;
  }


}

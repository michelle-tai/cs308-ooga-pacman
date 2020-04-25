package ooga.engine.sprites;

import ooga.Main;
import ooga.data.PathManager;
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
  private String myScaredImagePath;
  private int ID;


  public Ghost(int startingX, int startingY, int hitBoxWidth, int hitBoxLength, int ID,
      String imagePath, String scaredImagePath, PathManager pathManager){

    super(startingX  , startingY, hitBoxWidth, hitBoxLength, ID, imagePath);
    mySpeed = Integer.parseInt(pathManager.getString(PathManager.PROPERTIES,"GhostDefaultSpeed"));
    myStatus = 0;
    myScaredImagePath = scaredImagePath;
    //ghostMove = new AggressiveMovement(this, targetSprites);
    this.ID = ID;
  }

  @Override
  public String getMovementType() {
    return null;
  }

  @Override
  public void setMovementType(String movementType, List<Sprite> targetSprites) {
    //todo: fix with reflection
    ghostMove = new AggressiveMovement(this, targetSprites, this.getID());
  }

  @Override
  public int getStatus() {
    return myStatus;
  }

  @Override
  public void setStatus(int newStatus) {
    myStatus = newStatus;
  }

  public String getScaredImagePath() {
    return myScaredImagePath;
  }

  public void setScaredImagePath(String scaredImagePath) {
    this.myScaredImagePath = scaredImagePath;
  }

  public void move(MapGraphNode currentLocation, int runTime){
    ghostMove.move(currentLocation, runTime);
//    System.out.println("");
//    System.out.println(getID());
    if(getID() == 0){
//      System.out.println("zero 0");
    }
//    System.out.println("");
  }



  @Override
  public int getSpeed() {
    return mySpeed;
  }

  @Override
  public void setSpeed(int newSpeed) {
    mySpeed = newSpeed;
  }

  @Override
  public int getID(){
    return ID;
  }


}

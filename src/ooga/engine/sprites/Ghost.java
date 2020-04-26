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
  private int timeSinceStatusSet;
  private int powerUpTime = 20;
  private int timeOutSide = 0;
  private int timeDisperse = 0;


  public Ghost(int startingX, int startingY, int hitBoxWidth, int hitBoxLength, int ID,
      String imagePath, String scaredImagePath, PathManager pathManager){


    super(startingX  , startingY, hitBoxWidth, hitBoxLength, ID, imagePath);
    mySpeed = Integer.parseInt(pathManager.getString(PathManager.PROPERTIES,"GhostDefaultSpeed"));
    myStatus = 0;
    myScaredImagePath = scaredImagePath;
    //ghostMove = new AggressiveMovement(this, targetSprites);
    timeSinceStatusSet = 0;

    powerUpTime = Integer.parseInt(pathManager.getString(PathManager.PROPERTIES,"powerUpTime"));

    timeOutSide = Integer.parseInt(pathManager.getString(PathManager.PROPERTIES,"GhostLagTime"));
    timeDisperse = Integer.parseInt(pathManager.getString(PathManager.PROPERTIES,"GhostDisperseTime"));

  }

  @Override
  public String getMovementType() {
    return null;
  }

  @Override
  public void setMovementType(String movementType, List<Sprite> targetSprites) {

    ghostMove = new AggressiveMovement(this, targetSprites, this.getID(), timeOutSide, timeDisperse);
  }

  @Override
  public int getStatus() {
    return myStatus;
  }

  @Override
  public void setStatus(int newStatus) {
    myStatus = newStatus;
    timeSinceStatusSet = 0;
  }

  public String getScaredImagePath() {
    return myScaredImagePath;
  }

  public void setScaredImagePath(String scaredImagePath) {
    this.myScaredImagePath = scaredImagePath;
  }


  public void move(MapGraphNode currentLocation){
    ghostMove.move(currentLocation);
    timeSinceStatusSet++;
    if(timeSinceStatusSet > powerUpTime){
      myStatus = 0;
    }
  }

  @Override
  public int getSpeed() {
    return mySpeed;
  }

  @Override
  public void setSpeed(int newSpeed) {
    mySpeed = newSpeed;
  }

  public void setGhostSpawn(int X, int Y, int itter){
    ((AggressiveMovement) ghostMove).ghostSpawn(X, Y, itter);
  }

  @Override
  public void setHome(){
    super.setHome();
    myStatus = 0;
    ((AggressiveMovement)ghostMove).resetUpTime();
  }

}

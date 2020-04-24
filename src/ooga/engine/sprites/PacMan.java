package ooga.engine.sprites;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;
import ooga.Main;
import ooga.data.PathManager;
import ooga.engine.MapGraphNode;
import ooga.engine.movement.ControllableMovement;

import java.util.List;

/**
 * The MainCharacter class is what represents Pac-Man. The reason it's called MainCharacter and not Pac-Man
 * is because it can be used with different images as besides Pac-Man: Pac-Man isn't the only option for how
 * this game can be made! We use javafx in this class purely to make a rectangle hitbox for collision checking.
 * @author Michelle Tai
 * @author Olga
 */
public class PacMan extends DynamicSprite implements Sprite {
//  private int myID;
//  private int homeXPos;
//  private int homeYPos;
//  private int prevX;
//  private int prevY;
//  private int xPos;
//  private int yPos;
  private Rectangle hitbox;
  private SimpleIntegerProperty lifeCount;
  private int mySpeed = 5;
  private int movedist = 1;
  private String direction;
  private ControllableMovement myMovement;
  private int myStatus;
  private String pacManMovement;
  private SimpleIntegerProperty myPoints;



  public PacMan(int startingX, int startingY, int hitBoxWidth, int hitBoxLength, int ID, String imagePath, PathManager pathManager){
    super(startingX, startingY, hitBoxWidth, hitBoxLength, ID, imagePath);

    myPoints = new SimpleIntegerProperty();
    lifeCount = new SimpleIntegerProperty();
    lifeCount.setValue(Integer.parseInt(pathManager.getString(PathManager.PROPERTIES, "MaxLives")));
    mySpeed = Integer.parseInt(pathManager.getString(PathManager.PROPERTIES, "PacManDefaultSpeed"));
    myStatus = Integer.parseInt(pathManager.getString(PathManager.PROPERTIES, "PacManInitStatus"));
    pacManMovement = pathManager.getString(PathManager.PROPERTIES, "PacManMovement");
    direction = Main.RIGHT;
    myMovement = new ControllableMovement(this);

  }

  @Override
  public int getStatus() {
    return myStatus;
  }

  @Override
  public void setStatus(int newStatus) {
    myStatus = newStatus;
  }


  @Override
  public String getMovementType() {
    return pacManMovement;
  }

  @Override
  public void setMovementType(String movementType, List<Sprite> targetSprite) {
    pacManMovement = movementType;
  }


  //maybe change so that its not so many ifs

  /**
   * moves the pacman by updating its x and y positions. movement conditions is based on a properties file, so
   * this keys can easily be changed. ideally, will have a move object so that there can be different types of movements, but
   * that can come later
   *  direction is the String value (typically the KeyCode name) that holds which key is pressed.
   *        this value is compared to the corresponding values of the "Right", "Left", ... keys in the properties file
   */
//  public void move(String direction){
//    if(direction.equals(Main.MY_RESOURCES.getString("Right"))){
//      xPos += (movedist*mySpeed);
//      System.out.println("moved right -> new x : " + xPos);
//    }
//    else if(direction.equals(Main.MY_RESOURCES.getString("Left"))){
//      xPos -= (movedist*mySpeed);
//      System.out.println("moved left -> new x : " + xPos);
//    }
//    else if(direction.equals(Main.MY_RESOURCES.getString("Down"))){
//      yPos += (movedist*mySpeed);
//      System.out.println("moved down -> new y : " + yPos);
//    }
//    else if(direction.equals(Main.MY_RESOURCES.getString("Up"))){
//      yPos -= (movedist*mySpeed);
//      System.out.println("moved up -> new y : " + yPos);
//    }
//  }
//  public void move(String direction){
//    int dx = -1;
//    int dy = -1;
//    int movedDistX = movedist*mySpeed;
//    int movedDistY = movedist * mySpeed;
//    if(direction.equals(Main.MY_RESOURCES.getString("Right")) || direction.equals(Main.MY_RESOURCES.getString("Left"))){
//      movedDistX = movedDistX * dx;
//    }
//    else if(direction.equals(Main.MY_RESOURCES.getString("Down")) || direction.equals(Main.MY_RESOURCES.getString("Up"))){
//      movedDistY = movedDistY * dy;
//    }
//    xPos += movedDistX;
//    yPos += movedDistY;
//  }

  public void move(MapGraphNode currentLocation){
    myMovement.move(currentLocation);
  }
//  public void move(){
//    myMovement.move();
//  }

  public void changeDirection(String dir){
    myMovement.setNewDirection(dir);
  }

  /*
  getter for pacman id for multiplayer purposes
   */
//  public int getID();

  /*
  updates the x and y of the object and checks for collisions. LocationObject is a subset of all game objects which
  allows for collision checking
   */
//  public void move(LocationObject neighborhood);


  /*
  returns orientation of pacman
   */
 // public String getOrientation();

    /*
  sets orientation of pacman
   */
  // public void setOrientation(String newOrientation);

  /*
  increment pacman's lives by 1
   */
  public void incrementLives(){
    lifeCount.setValue(lifeCount.getValue() + 1);
  }

  /*
  decrements pacman's lives by 1
   */
  public void decrementLives(){
    lifeCount.setValue(lifeCount.getValue() -1);
  }

  /*
  getter for number of lives
   */
  public SimpleIntegerProperty getLivesLeft(){
    return lifeCount;
  }

  /*
  resets pacman based on xml file
   */


  public void addPoints(int newPoints){
    myPoints.setValue(myPoints.getValue() + newPoints);

}


  public SimpleIntegerProperty getPointsProperty(){
    return myPoints;
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

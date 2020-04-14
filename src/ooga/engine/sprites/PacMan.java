package ooga.engine.sprites;
import javafx.scene.shape.Rectangle;
import ooga.Main;
import ooga.engine.Sprite;
import ooga.engine.movement.PacManMovement;

/**
 * The MainCharacter class is what represents Pac-Man. The reason it's called MainCharacter and not Pac-Man
 * is because it can be used with different images as besides Pac-Man: Pac-Man isn't the only option for how
 * this game can be made! We use javafx in this class purely to make a rectangle hitbox for collision checking.
 * @author Michelle Tai
 * @author Olga
 */
public class PacMan implements Sprite {
  private int homeXPos;
  private int homeYPos;
  private int prevX;
  private int prevY;
  private int xPos;
  private int yPos;
  private Rectangle hitbox;
  private int lifeCount;
  private int mySpeed = 5;
  private int movedist = 1;
  private String direction;
  private PacManMovement myMovement;
  private int myStatus;
  private String pacManMovement;
  private int myPoints;


  public PacMan(int startingX, int startingY, int hitBoxWidth, int hitBoxLength){
    myPoints = 0;
    homeXPos = startingX;
    homeYPos = startingY;
    prevX = startingX;
    prevY = startingY;
    xPos = startingX;
    yPos = startingY;
    hitbox = new Rectangle(startingX, startingY, hitBoxWidth, hitBoxLength);
    lifeCount = Integer.parseInt(Main.MY_RESOURCES.getString("MaxLives"));
    mySpeed = Integer.parseInt(Main.MY_RESOURCES.getString("DefaultSpeed"));
    myStatus = Integer.parseInt(Main.MY_RESOURCES.getString("PacManInitStatus"));
    pacManMovement = Main.MY_RESOURCES.getString("PacManMovement");
    direction = Main.MY_RESOURCES.getString("Right");
    myMovement = new PacManMovement(this);

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
    return pacManMovement;
  }

  @Override
  public void setMovementType(String movementType) {
    pacManMovement = movementType;
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

  //maybe change so that its not so many ifs

  /**
   * moves the pacman by updating its x and y positions. movement conditions is based on a properties file, so
   * this keys can easily be changed. ideally, will have a move object so that there can be different types of movements, but
   * that can come later
   * @param direction is the String value (typically the KeyCode name) that holds which key is pressed.
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

  public void move(){
    myMovement.move();
  }

  public void changeDirection(String dir){
    myMovement.setNewDirection(dir);
  }

  /*
  getter for object status, indicaticating the curent powerup, or lack of powerup
   */
  public int getStatus(){
    return myStatus;
  }

  /*
setter for object status, indicaticating the curent powerup, or lack of powerup
*/
//  public String setStatus();

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
    lifeCount++;
  }

  /*
  decrements pacman's lives by 1
   */
  public void decrementLives(){
    lifeCount--;
  }

  /*
  getter for number of lives
   */
  public int getLivesLeft(){
    return lifeCount;
  }

  /*
  resets pacman based on xml file
   */
public void setHome(){
  setX(homeXPos);
  setY(homeYPos);
}

public void addPoints(int newPoints){
  myPoints += newPoints;
}


public void setPreviousLocation(){
  xPos = prevX;
  yPos = prevY;
}

}

package ooga.engine;
import javafx.scene.shape.Rectangle;

/**
 * This is the interface all the objects in the game will implement. Everything in the game will have an x and y position, as well as a hit box
 * @author Michelle Tai
 */
public interface Sprite {

  /**
   * Get the sprite's current x position
   * @return the current double value of the x position
   */
  public int getX();

  /**
   * Get the sprite's current y position
   * @return the current double value for the y position
   */
  public int getY();

  /**
   * Set the x position of the sprite to a new value
   * @param newX is the new x position (double)
   */
  public void setX(int newX);

  /**
   * Set the y position of the sprite to a new value
   * @param newY is a new y position (double)
   */
  public void setY(int newY);

  /**
   * Get the hitbox of the sprite, which is used to determine whether or not object have collided
   * @return a Rectangle object the represents the hitbox
   */
  public Rectangle getHitBox();

  public String getMovementType();

  public void setMovementType(String movementType);
  /**
   * Get the current speed value of the sprite
   * @return current speed of the sprite
   */
  public int getSpeed();

  /**
   * Set the speed value of the sprite to a new speed value
   * @param newSpeed is the new speed of the sprite
   */
  public void setSpeed(int newSpeed);

  public int getStatus();

  public void setStatus(int newStatus);

}

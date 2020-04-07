package ooga.engine;
import javafx.scene.shape.Rectangle;

/**
 * This is the interface all the objects in the game will implement. Everything in the game will have an x and y position, as well as a hit box
 */
public interface Sprite {

  /**
   * Get the sprite's current x position
   * @return the current double value of the x position
   */
  public double getX();

  /**
   * Get the sprite's current y position
   * @return the current double value for the y position
   */
  public double getY();

  /**
   * Set the x position of the sprite to a new value
   * @param newX is the new x position (double)
   */
  public void setX(double newX);

  /**
   * Set the y position of the sprite to a new value
   * @param newY is a new y position (double)
   */
  public void setY(double newY);

  /**
   * Get the hitbox of the sprite, which is used to determine whether or not object have collided
   * @return a Rectangle object the represents the hitbox
   */
  public Rectangle getHitBox();
  
}

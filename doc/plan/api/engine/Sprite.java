/**
 * A possible interface or abstract class that any sprite in the game extends, like the pacman, the coins, the enemies, etc
 */

public interface Sprite {

  /**
   * @return current x location
   */
  public double getX();
  /**
   * @return current y location
   */
  public double getY();

  /**
   * set new x location
   * @param newX is the new x location
   */
  public void setX(double newX);

  /**
   * set the new y location
   * @param newY is the new y location
   */
  public void setY(double newY);

  /**
   * updates anything else in the sprite that is needed
   */
  public void update();
}

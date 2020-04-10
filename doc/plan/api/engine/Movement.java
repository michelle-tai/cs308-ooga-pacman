/**
 * This will contribute to composition of the sprites by allowing sprites to have a movement method that defines how
 * the sprite will be moved. For example, with the move up with be by 100 px or 50 px. this can make it easy to change how a sprite moves
 */
public abstract class Movement {

  /**
   * moves the sprite to the left
   */
  public abstract void moveLeft();
  /**
   * moves the sprite to the right
   */
  public abstract void moveRight();
  /**
   * moves the sprite up
   */
  public abstract void moveUp();
  /**
   * moves the sprite down
   */
  public abstract void moveDown();

  /**
   * can set the speed of the sprite, since the speed of the sprite affects the movement
   * (higher speed = moves further in a frame)
   * @param speed is the new speed
   */
  public abstract void setSpeed(double speed);
}

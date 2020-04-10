/**
 * this holds all possible collisions for each sprite type and the behavior each sprite should have when colliding
 */

public interface Visitor {

  /**
   * this can be implemented multiple times, based on the sprite type
   * each sprite will implement collision behaviors for every other sprite
   * ex of this call will be other.visit(this); this gets rid of the need of doing instanceof or if
   * statements
   * @param other is the sprite it collides with
   */
  public void collidedWith(Sprite2 other);
}

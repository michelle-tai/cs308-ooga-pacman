/**
 * an interface that sprites can implement if it will have collision behavior. all sprites will probably implement this, but in case there
 * are sprites that don't actually do anything with collisions
 */

public interface Collidable {

  /**
   * this method is called on both this object and the object it collides with. this class will call on both object's visit methods
   * (visitor strategy)
   * @param other
   */
  public void collidesWith(Sprite other);
}

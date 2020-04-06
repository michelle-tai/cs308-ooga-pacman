import java.util.List;

/**
 * Gamemap holds the locations of all objects or maybe holds all objects?
 */

public interface GameMap {


  /**
   * Adds a game piece into the game map, since all game pieces will be a sprite (aka have a location and size)
   * @param sprite
   */
  void addGamePiece(Sprite sprite);

  /**
   * moves a sprite to a new location?
   */
  void moveSprite(Sprite sprite, int newX, int newY);

  /**
   * gets a list of sprites so that the front end can render the images, but the list is copy
   * of the sprites bc dont want other people to be able to change thing?
   */
  List<Sprite> iterableSpriteList();



}

/**
 * Creates an instance of a Map in the view portion of the program. This contains all the visual elements and responsibilities
 * of the Map.
 */
public interface PacManView {
  /*
Creates an instance of the map, only one instance is needed
 */
  void PacManView(Map map);
  /*
Reads in the map layout from a text file
 */
  void readFile();
  /*
  Creates instances of a coin every time the read file reads a coin
   */
  void createCoin();
  /*
Creates instances of a powerup every time the read file reads a powerup
 */
  void createPowerUp();
  /*
Creates instances of a wall every time the read file reads a wall
*/
  void createWall();
}

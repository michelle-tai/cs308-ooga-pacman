/**
 * Creates the main visualization for the player and adds all the elements together.
 */
public interface Visualizer {
  /*
  Sets the scene using a stage
   */
  Scene setScene;
  /*
  Creates an instance of the pacman and adds it to the scene
   */
  PacMan createPacMan();
  /*
  Creates an an instnace of the ghost and adds it to the scene
   */
  Ghost2 createGhost();
  /*
  Creates an instance of the map and adds it to the scene
   */
  Map createMap();
}

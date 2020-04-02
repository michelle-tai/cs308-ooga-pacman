/**
 * Creates an instance of a ghost in the view portion of the program. This contains all the visual elements and responsibilities
 * of the ghost.
 */
public interface GhostView {
  /*
  Creates an instance of the ghost, can have multiple instances
   */
  void GhostView(Ghost ghost, String name);
  /*
  Uses an imageView to create a visual of each ghost and visual store each object
   */
  ImageView createGhost();
  /*
  updates the position of the ghost visual determined by the back end or engine
   */
  void update(double newX, double newY, double orientation)
    /*
    Sets the speed of the ghost that the animation should be played
     */
  void setSpeed(int speed);
  /*
  animates the ghost to move according to the locations of the backend, should be constant movement, will most likely need
  a step function
   */
  void playAnimation();
}

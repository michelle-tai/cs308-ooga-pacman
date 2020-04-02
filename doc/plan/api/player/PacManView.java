/**
 * Creates an instance of a pacman in the view portion of the program. This contains all the visual elements and responsibilities
 * of the pacman.
 */
public interface PacManView {
  /*
Creates an instance of the pacman, can have multiple instances
 */
  void PacManView(PacMan pacMan, String name);
  /*
Uses an imageView to create a visual of each pacman and visual store each object
 */
  ImageView createPacMan();
  /*
updates the position of the pacman visual determined by the back end or engine
 */
  void update(double newX, double newY, double orientation)
  /*
Sets the speed of the pacman that the animation should be played
*/
  void setSpeed(int speed);
  /*
animates the pacman to move according to the locations of the backend, should be constant movement, will most likely need
a step function
 */
  void playAnimation();
  /*
  gets the PacMan image
   */
  ImageView getImage();

  /*
 sets the PacMan image
  */
  void setImage(ImageView image);

}

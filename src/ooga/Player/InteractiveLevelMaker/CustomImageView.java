package ooga.Player.InteractiveLevelMaker;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class extends the ImageView so that the ImageView functionalities are retained, but only a getPath and
 * setPath methods are added. The path signifies a string that holds the image name of the ImageView. I
 * couldn't find a way to actually get the string form of the image name from ImageView, so I had to create
 * this. I would improve the design so that, instead, I would have this class contain an ImageView, and
 * have each instance of this class be in a data structure so that I can access the imageview to be shown
 * in the gui and the name for the backend. Better yet, I would create a frontend and back class for this
 * so that the frontend is the image, the backend is the name in a data structure.
 *
 * Dependencies: ImageView
 *
 * @author Michelle Tai
 */

public class CustomImageView extends ImageView {
  private String str;

  public CustomImageView(){
    super();
  }

  public CustomImageView(Image image){
    super(image);
  }

  /**
   * Sets the image path string to Path
   * @param path
   */
  public void setPath(String path){
    str = path;
  }

  /**
   * @return str, which holds the String of the image path
   */
  public String getPath(){
    return str;
  }
}

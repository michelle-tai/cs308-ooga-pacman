package ooga.Player.InteractiveLevelMaker;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class CustomImageView extends ImageView {
  private String str;

  public CustomImageView(){
    super();
  }

  public CustomImageView(Image image){
    super(image);
  }


  public void setPath(String path){
    str = path;
  }

  public String getPath(){
    return str;
  }
}

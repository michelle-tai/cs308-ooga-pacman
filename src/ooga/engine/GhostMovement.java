package ooga.engine;

import java.util.ArrayList;
import java.util.List;

public class GhostMovement extends Movement {
  private List<String> directionList = new ArrayList<>(List.of("Right, Left, Up, Down"));

  public GhostMovement(Sprite sprite){
    super(sprite);
  }

  //not the best design but will change later
  public void setNewDirection(){
    //todo: change the design struture rn, but it have this do nothing rn
    int min = 0;
    int max = 3;
    int range = max - min + 1; //max 3 min0 range = max - min + 1
    int rand = (int)(Math.random() * range) + min;
    super.setNewDirection(directionList.get(rand));
  }


}

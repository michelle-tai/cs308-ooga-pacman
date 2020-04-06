package ooga.Player.Map;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class MapView {

    public MapView(){

    }

    //TODO: create map from a data file and create other classes for power ups
    public Node createMap(){
        HBox hbox = new HBox();
        Rectangle placeholder = new Rectangle(400, 500);
        hbox.getChildren().add(placeholder);
        return hbox;
    }
}

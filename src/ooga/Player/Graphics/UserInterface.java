package ooga.Player.Graphics;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class UserInterface {

    public UserInterface(){

    }

    public Node createComponents(){
        VBox vbox = new VBox();
        Rectangle placeholder = new Rectangle(200, 500, Color.BLUE);
        vbox.getChildren().add(placeholder);
        return vbox;
    }
}

package ooga.Player.Graphics;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class NonUserInterface {

    public NonUserInterface(){

    }

    public Node createComponents(){
        VBox vbox = new VBox();
        Rectangle placeholder = new Rectangle(200, 500);
        vbox.getChildren().add(placeholder);
        return vbox;
    }
}

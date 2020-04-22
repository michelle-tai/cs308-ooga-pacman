package ooga.Player.Graphics;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.util.ResourceBundle;

public class Styler {

    public static final int IMAGE_WIDTH = 20;
    public static final int IMAGE_HEIGHT = 20;

    private ResourceBundle myResources;

    public Styler(ResourceBundle resources){
        myResources = resources;
    }

    public String getResourceText(String input){return myResources.getString(input);}

    public Label createLabel(String string){
        Label label = new Label(getResourceText(string));
        label.setId(string);
        return label;
    }

    public Button createButton(String string, EventHandler e){
        Button button = new Button(getResourceText(string));
        button.setOnAction(e);
        return button;
    }

    public Button createButtonImage(EventHandler e, String path){
        ImageView image = new ImageView(path);
        image.setFitWidth(IMAGE_WIDTH);
        image.setFitHeight(IMAGE_HEIGHT);
        Button button = new Button();
        button.setOnAction(e);
        button.setGraphic(image);
        return button;
    }

    public Hyperlink createLink(String string, EventHandler e){
        Hyperlink link = new Hyperlink(getResourceText(string));
        link.setOnAction(e);
        link.setId(string);
        return link;
    }
}

package ooga.Player.Graphics;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

public class Styler {

    private ResourceBundle myResources;

    public Styler(ResourceBundle resources){
        myResources = resources;
    }

    public String getResourceText(String input){return myResources.getString(input);}

    public Label createLabel(String string){
        return new Label(myResources.getString(string));
    }

    public Button createButton(String string, EventHandler e){
        Button button = new Button(myResources.getString(string));
        button.setOnAction(e);
        return button;
    }
}

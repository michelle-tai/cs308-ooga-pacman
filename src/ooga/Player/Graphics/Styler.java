package ooga.Player.Graphics;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

public class Styler {

    private ResourceBundle myResources;

    public Styler(ResourceBundle resources){
        myResources = resources;
    }

    public String getResourceText(String input){return myResources.getString(input);}

    public Label createLabel(String string){
        Label label = new Label(getResourceText(string));
        return label;
    }

    public Button createButton(String string, EventHandler e){
        Button button = new Button(getResourceText(string));
        button.setOnAction(e);
        return button;
    }

    public Hyperlink createLink(String string, EventHandler e){
        Hyperlink link = new Hyperlink(getResourceText(string));
        link.setOnAction(e);
        return link;
    }
}

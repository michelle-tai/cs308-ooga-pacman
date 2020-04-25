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

    /**
     * This class creates basic UI components with a bare structure so that minimal duplicate code is used.
     * @param resources - takes in a resource bundle so it does not have to be found every time
     */
    public Styler(ResourceBundle resources){
        myResources = resources;
    }

    private String getResourceText(String input){return myResources.getString(input);}

    /**
     * Creates a standard label and sets the id.
     * @param string - text to be displayed in the label
     * @return the created label
     */
    public Label createLabel(String string){
        Label label = new Label(getResourceText(string));
        label.setId(string);
        return label;
    }

    /**
     * Creates a standard button, sets the id.
     * @param string - text to be displayed on the button
     * @param e - event that occurs when button is clicked
     * @return the created button
     */
    public Button createButton(String string, EventHandler e){
        Button button = new Button(getResourceText(string));
        button.setId(string);
        button.setOnAction(e);
        return button;
    }

    /**
     * Creates a button using only the image and no text.
     * @param e - the event that occurs when the button is clicked
     * @param path - the path to the image used to create the button
     * @return the created image button
     */
    public Button createButtonImage(EventHandler e, String path){
        ImageView image = new ImageView(path);
        image.setFitWidth(IMAGE_WIDTH);
        image.setFitHeight(IMAGE_HEIGHT);
        Button button = new Button();
        button.setOnAction(e);
        button.setGraphic(image);
        return button;
    }

    /**
     * Creates a standard hyperlink and sets the ID.
     * @param string - the text displayed as the hyperlink
     * @param e - the event that occurs when the link is clicked
     * @return
     */
    public Hyperlink createLink(String string, EventHandler e){
        Hyperlink link = new Hyperlink(getResourceText(string));
        link.setOnAction(e);
        link.setId(string);
        return link;
    }
}

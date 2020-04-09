package ooga.Player.Graphics;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ooga.Player.Visualizer;

import java.util.ResourceBundle;

public class UserInterface {

    public static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_FOLDER = RESOURCES + "/formats/";
    public static final String DARK_STYLESHEET = "DarkStyling.css";
    public static final String LIGHT_STYLESHEET = "LightStyling.css";
    public static final String ENGLISH_BUTTONS = "EnglishButtons";
    public static final int VBOX_SPACING = 10;

    private Styler styler;
    private ResourceBundle myResources;
    private Visualizer myVisualizer;


    public UserInterface(Visualizer visualizer){
        myVisualizer = visualizer;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_FOLDER + ENGLISH_BUTTONS);
        styler = new Styler(myResources);
    }

    public Node createComponents(){
        VBox vbox = new VBox(VBOX_SPACING);
        HBox hbox = new HBox(styler.createButton("DarkMode", e->changeStyleSheet(DARK_STYLESHEET)),
                styler.createButton("LightMode", e-> changeStyleSheet(LIGHT_STYLESHEET)));
        VBox.setVgrow(vbox, Priority.ALWAYS);
        HBox.setHgrow(hbox, Priority.ALWAYS);
        vbox.setPadding(new Insets(VBOX_SPACING, VBOX_SPACING, VBOX_SPACING, VBOX_SPACING));
        vbox.getChildren().addAll( styler.createLabel("Settings"), hbox);
        return vbox;
    }

    private void changeStyleSheet(String stylesheet){
        myVisualizer.getMyScene().getStylesheets().clear();
        myVisualizer.getMyScene().getStylesheets()
                .add(getClass().getClassLoader()
                        .getResource(DEFAULT_RESOURCE_FOLDER + stylesheet).toExternalForm());
    }

}

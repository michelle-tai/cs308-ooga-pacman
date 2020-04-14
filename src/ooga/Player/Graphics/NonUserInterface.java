package ooga.Player.Graphics;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.*;
import java.util.ResourceBundle;

public class NonUserInterface {

    public static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_FOLDER = RESOURCES + "/formats/";
    public static final String ENGLISH_BUTTONS = "EnglishButtons";
    public static final String RULES_FILE = "src/resources/GameRules.txt";
    public static final int VBOX_SPACING = 10;
    private static final int PACMAN_WIDTH = 25;
    private static final int PACMAN_HEIGHT = 25;

    private Styler styler;
    private ResourceBundle myResources;
    private SimpleIntegerProperty livesLeft;
    private SimpleIntegerProperty status;

    public NonUserInterface(){
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_FOLDER + ENGLISH_BUTTONS);
        styler = new Styler(myResources);
        livesLeft = new SimpleIntegerProperty();
        status = new SimpleIntegerProperty();
    }

    public Node createComponents(){
        VBox vbox = new VBox(VBOX_SPACING);
        vbox.getChildren().addAll( styler.createLabel("LiveCount"), addLives(), styler.createLabel("DefaultRules"), readRules());
        vbox.setPadding(new Insets(VBOX_SPACING, VBOX_SPACING, VBOX_SPACING, VBOX_SPACING));
        return vbox;
    }

    private HBox addLives(){
        HBox hbox = new HBox(VBOX_SPACING);
        for(int i=0; i < livesLeft.getValue(); i++){
            String string = "resources/pacman/pacman1.png";
            ImageView pacmanImage = new ImageView(string);
            pacmanImage.setFitWidth(PACMAN_WIDTH);
            pacmanImage.setFitHeight(PACMAN_HEIGHT);
            hbox.getChildren().add(pacmanImage);
        }
        return hbox;
    }

    private TextArea readRules(){
        TextArea rules = new TextArea();
        File file = new File(RULES_FILE);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                rules.appendText(line + "\n");
            }
        } catch(IOException e){
            //TODO catch the error
        }
        rules.setEditable(false);
        rules.setWrapText(true);
        return rules;
    }

    public SimpleIntegerProperty getLivesLeft() {return livesLeft;}

    public SimpleIntegerProperty getStatus() {return status;}
}

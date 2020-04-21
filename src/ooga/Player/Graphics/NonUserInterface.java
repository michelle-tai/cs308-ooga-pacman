package ooga.Player.Graphics;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.*;
import java.util.ResourceBundle;
import ooga.data.PathManager;

public class NonUserInterface {

    public static final int VBOX_SPACING = 10;
    private static final int PACMAN_WIDTH = 25;
    private static final int PACMAN_HEIGHT = 25;

    private Styler styler;
    private ResourceBundle myResources;
    private SimpleIntegerProperty livesLeft;
    private SimpleIntegerProperty status;
    private SimpleIntegerProperty score;

    public NonUserInterface(){
        myResources = PathManager.getResourceBundle(PathManager.ENGLISHBUTTONS);
        styler = new Styler(myResources);
        livesLeft = new SimpleIntegerProperty();
        status = new SimpleIntegerProperty();
        score = new SimpleIntegerProperty();
    }

    public Node createComponents(){
        VBox vbox = new VBox(VBOX_SPACING);
        Label currentScore = new Label(Integer.toString(score.getValue()));
        vbox.getChildren().addAll( styler.createLabel("LiveCount"), addLives(),
                styler.createLabel("CurrentScore"), currentScore,
                styler.createLabel("DefaultRules"), readRules());
        vbox.setPadding(new Insets(VBOX_SPACING, VBOX_SPACING, VBOX_SPACING, VBOX_SPACING));
        return vbox;
    }

    private HBox addLives(){
        HBox hbox = new HBox(VBOX_SPACING);
        for(int i=0; i < livesLeft.getValue(); i++){
            ImageView pacmanImage = new ImageView(PathManager.getPacManPath(0));
            pacmanImage.setFitWidth(PACMAN_WIDTH);
            pacmanImage.setFitHeight(PACMAN_HEIGHT);
            hbox.getChildren().add(pacmanImage);
        }
        return hbox;
    }

    private TextArea readRules(){
        TextArea rules = new TextArea();
        File file = new File(PathManager.RULES);
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

    public SimpleIntegerProperty getScore() {
        return score;}


//    public SimpleIntegerProperty getStatus() {return status;}
}

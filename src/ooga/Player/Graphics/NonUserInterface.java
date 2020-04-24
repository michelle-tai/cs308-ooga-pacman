package ooga.Player.Graphics;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import ooga.data.PathManager;

public class NonUserInterface {

    public static final int VBOX_SPACING = 10;
    private static final int PACMAN_WIDTH = 25;
    private static final int PACMAN_HEIGHT = 25;
    public static final String ERROR_DIALOG = "No rules found";

    private Styler styler;
    private ResourceBundle myResources;
    private SimpleIntegerProperty livesLeft;
    private Label currentScore;
    private PathManager myPathManager;

    public NonUserInterface(PathManager pathManager){
        myPathManager = pathManager;
        myResources = ResourceBundle.getBundle(PathManager.GUI_RESOURCES.getString(PathManager.ENGLISHBUTTONS));
        styler = new Styler(myResources);
        livesLeft = new SimpleIntegerProperty();
        currentScore = new Label();
    }

    public Node createComponents(){
        VBox vbox = new VBox(VBOX_SPACING);
        vbox.getChildren().addAll( styler.createLabel("LiveCount"), addLives(),
                styler.createLabel("CurrentScore"), currentScore,
                styler.createLabel("DefaultRules"), readRules());
        vbox.setPadding(new Insets(VBOX_SPACING, VBOX_SPACING, VBOX_SPACING, VBOX_SPACING));
        return vbox;
    }

    private HBox addLives() {
        HBox hbox = new HBox(VBOX_SPACING);
        List<ImageView> list = new ArrayList<>();
        for (int i = 0; i < livesLeft.getValue(); i++) {
            ImageView pacmanImage = new ImageView(myPathManager.getFilePath(PathManager.PACKMANIMAGE, 0));
            pacmanImage.setFitWidth(PACMAN_WIDTH);
            pacmanImage.setFitHeight(PACMAN_HEIGHT);
            list.add(pacmanImage);
        }
        livesLeft.addListener(
                    e -> {
                        hbox.getChildren().clear();
                        System.out.println(hbox.getChildren());
                        list.remove(list.size()-1);
                       System.out.println(list);
                       hbox.getChildren().addAll(list);
                    }
            );
       hbox.getChildren().addAll(list);
            return hbox;
        }

    private TextArea readRules(){
        TextArea rules = new TextArea();
        File file = new File(myPathManager.getFilePath(PathManager.RULES));
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                rules.appendText(line + "\n");
            }
        } catch(IOException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(e.getMessage());
            errorAlert.setContentText(ERROR_DIALOG);
            errorAlert.showAndWait();
        }
        rules.setEditable(false);
        rules.setWrapText(true);
        return rules;
    }

    public SimpleIntegerProperty getLivesLeft() {return livesLeft;}

    public Label getScore() {
        return currentScore;}

}

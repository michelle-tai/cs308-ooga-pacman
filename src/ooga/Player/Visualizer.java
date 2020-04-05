package ooga.Player;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Visualizer {

    private Stage myStage;
    private Group pacmen;

    public Visualizer (Stage stage){
        myStage = stage;
        pacmen = new Group();

    }

    public Scene setupScene(){
        Group root = new Group();
        //TODO: need to create a borderpane or viewpane and add everything to there and make that what creates the scene with CSS files
        Scene myScene = new Scene(root, 500, 500, Color.AZURE);
        return myScene;
    }
}

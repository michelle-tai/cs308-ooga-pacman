package ooga;


import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Player.Visualizer;
import ooga.engine.GameException;
import ooga.engine.GameStep;

import static javafx.application.Application.launch;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    public static final String RIGHT = "Right";
    public static final String LEFT = "Left";
    public static final String UP = "up";
    public static final String DOWN = "Down";

    public static final String TITLE = "Pac-Man";

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Visualizer myVisualizer = new Visualizer(stage);
        stage.setScene(myVisualizer.startScene());
        stage.setTitle(TITLE);
        stage.show();
    }
}

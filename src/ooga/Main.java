package ooga;


import javafx.application.Application;
import javafx.stage.Stage;
import ooga.Player.Visualizer;

import static javafx.application.Application.launch;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {

    public static final String TITLE = "Pac-Man";

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Visualizer myVisualizer = new Visualizer(stage);
        stage.setScene(myVisualizer.setupScene());
        stage.setTitle(TITLE);
        stage.show();
    }
}

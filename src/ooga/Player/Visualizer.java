package ooga.Player;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Player.Ghost.GhostView;
import ooga.Player.Graphics.NonUserInterface;
import ooga.Player.Graphics.UserInterface;
import ooga.Player.Map.MapView;
import ooga.Player.PacMan.PacManView;
import ooga.engine.GameException;

public class Visualizer {

    public static final int VIEWPANE_PADDING = 10;
    public static final int VIEWPANE_MARGIN = 0;
    private static final String RESOURCES = "src/resources";
    private static final String LEVEL_ONE = RESOURCES + "/levels/level1";

    private Stage myStage;
    private Group pacmen;
    private MapView myMapView;
    private NonUserInterface nonUserInterface;
    private UserInterface userInterface;

    //
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Timeline animation;

    public Visualizer (Stage stage){
        myStage = stage;
        pacmen = new Group();
        myMapView = new MapView(this);
        nonUserInterface = new NonUserInterface();
        userInterface = new UserInterface();
    }

    public Scene setupScene(){
        Scene myScene = new Scene(createView());
        //TODO: add CSS files that can be changes to light and dark mode
        return myScene;
    }

    private BorderPane createView(){
        BorderPane viewPane = new BorderPane();
        viewPane.setPadding(new Insets(VIEWPANE_MARGIN, VIEWPANE_PADDING, VIEWPANE_PADDING, VIEWPANE_PADDING));
        //TODO: for multiplayer, pass in different level 1 depending on how many players
        Node map = myMapView.createMap(LEVEL_ONE);
        Node nonUInferface = nonUserInterface.createComponents();
        Node uInterface = userInterface.createComponents();

        viewPane.setLeft(nonUInferface);
        viewPane.setCenter(map);
        viewPane.setRight(uInterface);

        return viewPane;
    }

    public void addPacmen(int index, int row){
    //TODO: need to add an instance of the pacmen to the backend
        PacManView createPacMan = new PacManView(myMapView.getPacmen(), this, index, row);
    }

    public void addGhosts(int index, int row, int ghostNum){
        //TODO: need to add an instance of the ghosts to the backend
        GhostView createGhosts = new GhostView(myMapView.getGhosts(), this, index, row, ghostNum);
    }

    private void beginAnimation() {
        try {
            KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
            animation = new Timeline();
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.getKeyFrames().add(frame);
        }
        catch (java.lang.Exception e){
            throw new GameException(e); //can change later
        }
    }

    //todo: add in step method implementation
    private void step(double elapsedTime){


    }
}

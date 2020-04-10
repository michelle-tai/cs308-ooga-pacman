package ooga.Player;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Player.Ghost.GhostView;
import ooga.Player.Graphics.NonUserInterface;
import ooga.Player.Graphics.UserInterface;
import ooga.Player.Map.MapView;
import ooga.Player.PacMan.PacManView;
import ooga.engine.GameContainer;
import ooga.engine.GameException;
import ooga.engine.GameStep;

public class Visualizer {

    public static final int VIEWPANE_PADDING = 10;
    public static final int VIEWPANE_MARGIN = 0;
    public static final String RESOURCES = "src/resources";
    public static final String LEVEL_ONE = RESOURCES + "/levels/level1";
    public static final String RESOURCES1 = "resources";
    public static final String DEFAULT_RESOURCE_FOLDER = RESOURCES1 + "/formats/";
    public static final String LIGHT_STYLESHEET = "LightStyling.css";
    public static final int FRAMES_PER_SECOND = 10;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Stage myStage;
    private Group pacmen;
    private MapView myMapView;
    private GameStep myStepper;
    private GameContainer myContainer;
    private NonUserInterface nonUserInterface;
    private UserInterface userInterface;
    private PacManView createPacMan;
    private Scene myScene;
//    private GhostView createGhosts;
    private List<GhostView> ghostCollection;
    private List<PacManView> pacmanCollection;
    private Timeline animation;


    public Visualizer (Stage stage){
        myStage = stage;
        pacmen = new Group();
        myMapView = new MapView(this);
        nonUserInterface = new NonUserInterface();
        userInterface = new UserInterface(this);
        ghostCollection = new ArrayList<>();
        pacmanCollection = new ArrayList<>();
        myContainer = new GameContainer();
        myStepper = new GameStep(myContainer);


    }

    public Scene setupScene(){
        myScene = new Scene(createView());
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myScene.getStylesheets()
                .add(getClass().getClassLoader().getResource(DEFAULT_RESOURCE_FOLDER + LIGHT_STYLESHEET)
                        .toExternalForm());
        beginAnimation();
        return myScene;
    }

    private BorderPane createView(){
        BorderPane viewPane = new BorderPane();
        viewPane.setPadding(new Insets(VIEWPANE_MARGIN, VIEWPANE_PADDING, VIEWPANE_PADDING, VIEWPANE_PADDING));
        //TODO: for multiplayer, pass in different level 1 depending on how many players
        Node map = myMapView.createMap(LEVEL_ONE, myContainer);
        Node nonUInferface = nonUserInterface.createComponents();
        Node uInterface = userInterface.createComponents();

        viewPane.setLeft(nonUInferface);
        viewPane.setCenter(map);
        viewPane.setRight(uInterface);

        return viewPane;
    }

    public void addPacmen(int index, int row){
    //TODO: need to add an instance of the pacmen to the backend

//        PacManView createPacMan = new PacManView(myMapView.getPacmen(), this, index, row);
        createPacMan = new PacManView(myMapView.getPacmen(), this, index, row);
        pacmanCollection.add(createPacMan);
    }

    public void addGhosts(int index, int row, int ghostNum){
        //TODO: need to add an instance of the ghosts to the backend
        GhostView createGhosts = new GhostView(myMapView.getGhosts(), this, index, row, ghostNum);
        ghostCollection.add(createGhosts);
    }

    private void beginAnimation() {
        try {
            KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
            animation = new Timeline();
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.getKeyFrames().add(frame);
            animation.play();
        }
        catch (java.lang.Exception e){
            throw new GameException(e); //can change later
        }
    }

    //todo: add in step method implementation
    private void step(double elapsedTime){
        createPacMan.update();
        for(PacManView pc : pacmanCollection){
            pc.update();
        }
        for(GhostView gv : ghostCollection){
            gv.update();
        }
//        createGhosts.update();
    }

    private void handleKeyInput(KeyCode code){
        for(PacManView pc : pacmanCollection){
            pc.handleKeyInput(code);
        }
//        createPacMan.handleKeyInput(code);
    }

    public Scene getMyScene(){return myScene;}
}

package ooga.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Player.Ghost.GhostView;
import ooga.Player.Graphics.NonUserInterface;
import ooga.Player.Graphics.Styler;
import ooga.Player.Graphics.UserInterface;
import ooga.Player.Map.MapView;
import ooga.Player.PacMan.PacManView;
import ooga.controller.Controller;
import ooga.engine.GameContainer;
import ooga.engine.GameException;
import ooga.engine.GameStep;
import ooga.engine.sprites.*;

public class Visualizer {

    public static final int VIEWPANE_PADDING = 10;
    public static final int VIEWPANE_MARGIN = 0;
    public static final String RESOURCES = "src/resources";
    public static final String LEVEL_ONE = RESOURCES + "/levels/level1";
    public static final String RESOURCES1 = "resources";
    public static final String DEFAULT_RESOURCE_FOLDER = RESOURCES1 + "/formats/";
    public static final String LIGHT_STYLESHEET = "LightStyling.css";
    public static final String START_STYLESHEET = "StartStyling.css";
    public static final String ENGLISH_BUTTONS = "EnglishButtons";
    public static final int FRAMES_PER_SECOND = 10;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int STARTSCREEN_WIDTH = 600;
    public static final int STARTSCREEN_HEIGHT = 400;
    public static final int VBOX_INSETS = 100;
    public static final int VBOX_SPACING = 10;


    private Stage myStage;
    private Group pacmen;
    private MapView myMapView;
    private NonUserInterface nonUserInterface;
    private UserInterface userInterface;
    private PacManView currentPacMan;
    private Scene myScene;
    private GhostView currentGhost;
    private List<GhostView> ghostCollection;
    private List<PacManView> pacmanCollection;
    private Timeline animation;
    private BorderPane viewPane;
    private Controller myController;
    private Styler styler;
    private ResourceBundle myResources;
    private GameStep myGameStep;

    public Visualizer (Stage stage){
        myStage = stage;
        pacmen = new Group();
        myMapView = new MapView(this);
        nonUserInterface = new NonUserInterface();
        userInterface = new UserInterface(this);
        ghostCollection = new ArrayList<>();
        pacmanCollection = new ArrayList<>();
        myController = new Controller();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_FOLDER + ENGLISH_BUTTONS);
        styler = new Styler(myResources);
        myGameStep = new GameStep(myController.getContainer());
    }

    public Scene startScene(){
        Scene start = new Scene(createStartScene());
        start.getStylesheets()
                .add(getClass().getClassLoader().getResource(DEFAULT_RESOURCE_FOLDER + START_STYLESHEET)
                        .toExternalForm());
        return start;
    }

    private VBox createStartScene(){
        VBox vbox = new VBox(VBOX_SPACING);
        vbox.setPrefSize(STARTSCREEN_WIDTH, STARTSCREEN_HEIGHT);
        vbox.setPadding(new Insets(VBOX_INSETS, VBOX_INSETS, VBOX_INSETS, VBOX_INSETS));
        HBox hbox = new HBox(styler.createLink("UploadGrid", e->launchFileChooser(new Stage(), "Grid")),
                styler.createLink("UploadData", e->launchFileChooser(new Stage(), "Data")),
                styler.createLink("UploadPlayers", e->launchFileChooser(new Stage(), "Players")));
        vbox.getChildren().addAll(styler.createLabel("Pac-Man"), hbox, styler.createButton("Start", e->myStage.setScene(setupScene())));
        return vbox;
    }

    private Scene setupScene(){
        myScene = new Scene(createView());
        myScene.getStylesheets()
                .add(getClass().getClassLoader().getResource(DEFAULT_RESOURCE_FOLDER + LIGHT_STYLESHEET)
                        .toExternalForm());
        beginAnimation();
        return myScene;
    }

    private File launchFileChooser(Stage stage, String feature){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose" + feature);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Data Files", "*.XML", "*.txt"));
        return fileChooser.showOpenDialog(stage);
    }

    private BorderPane createView(){
        viewPane = new BorderPane();
        viewPane.setPadding(new Insets(VIEWPANE_MARGIN, VIEWPANE_PADDING, VIEWPANE_PADDING, VIEWPANE_PADDING));
        //TODO: for multiplayer, pass in different level 1 depending on how many players
        Node map = myMapView.createMap(LEVEL_ONE, myController.getContainer());
        Node nonUInferface = nonUserInterface.createComponents();
        Node uInterface = userInterface.createComponents();
        viewPane.setLeft(nonUInferface);
        viewPane.setCenter(map);
        viewPane.setRight(uInterface);
        viewPane.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return viewPane;
    }

    public void addPacmen(int index, int row, int ID){
    //TODO: need to add an instance of the pacmen to the backend
        PacManView createPacMan = new PacManView(myMapView.getPacmen(), this, index, row, ID, myController);
        pacmanCollection.add(createPacMan);
        setPacMan(pacmanCollection.size());
    }

    public void setPacMan(int index){
        currentPacMan = pacmanCollection.get(index -1 );
        nonUserInterface.getLivesLeft().bind(currentPacMan.pacmanLives());
        nonUserInterface.getStatus().bind(currentPacMan.pacmanStatus());
    }

    public void addGhosts(int index, int row, int ID){
        //TODO: need to add an instance of the ghosts to the backend
        GhostView createGhosts = new GhostView(myMapView.getGhosts(), this, index, row, ID, myController);
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
//        createPacMan.update();
        myGameStep.step();
        viewPane.requestFocus();
        for(PacManView pc : pacmanCollection){
            pc.update();
        }
        for(GhostView gv : ghostCollection){
            gv.update();
        }
        //createGhosts.update();
    }

    private void handleKeyInput(KeyCode code){
//        System.out.println(code.getName());
        for(PacManView pc : pacmanCollection){
            pc.handleKeyInput(code);
        }
        if(code == KeyCode.SPACE){
            myMapView.changeGameStatus();
            System.out.println(myMapView.gameStatus());
        }
      //  createPacMan.handleKeyInput(code);
    }

    public Scene getMyScene(){return myScene;}

    public PacManView getCurrentPacMan(){return currentPacMan;}
}

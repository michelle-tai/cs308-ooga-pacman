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
import ooga.Main;
import ooga.Player.Ghost.GhostView;
import ooga.Player.Graphics.NonUserInterface;
import ooga.Player.Graphics.Styler;
import ooga.Player.Graphics.UserInterface;
import ooga.Player.Map.CoinView;
import ooga.Player.Map.MapView;
import ooga.Player.PacMan.PacManView;
import ooga.controller.Controller;
import ooga.data.Level;
import ooga.data.PathManager;
import ooga.engine.GameException;
import ooga.engine.GameStep;

public class Visualizer {

    public static final int VIEWPANE_PADDING = 10;
    public static final int VIEWPANE_MARGIN = 0;
    public static final int FRAMES_PER_SECOND = 10;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final int STARTSCREEN_WIDTH = 600;
    public static final int STARTSCREEN_HEIGHT = 400;
    public static final int VBOX_INSETS = 100;
    public static final int VBOX_SPACING = 10;


    private Stage myStage;
    private MapView myMapView;
    private NonUserInterface nonUserInterface;
    private UserInterface userInterface;
    private PacManView currentPacMan;
    private Scene myScene;
    private GhostView currentGhost;
    private List<GhostView> ghostCollection;
    private List<PacManView> pacmanCollection;
    private List<CoinView> coinCollection;
    private Timeline pacmanAnimation;
    private Timeline ghostAnimation;
    private Timeline otherAnimation;
    private BorderPane viewPane;
    private Controller myController;
    private Styler styler;
    private ResourceBundle myResources;
    private GameStep myGameStep;
    private Boolean gameStatus;
    private Group map;
    private Level currLevel;

    public Visualizer (Stage stage){
        myStage = stage;
        myController = new Controller();
        myMapView = new MapView(this);
        nonUserInterface = new NonUserInterface();
        userInterface = new UserInterface(this);
        ghostCollection = new ArrayList<>();
        pacmanCollection = new ArrayList<>();
        coinCollection = new ArrayList<>();
        myResources = PathManager.getResourceBundle(PathManager.ENGLISHBUTTONS);
        styler = new Styler(myResources);
        myGameStep = new GameStep(myController.getContainer());
        gameStatus = true;
    }

    public Scene startScene(){
        Scene start = new Scene(createStartScene());
        start.getStylesheets()
                .add(getClass().getClassLoader().getResource(PathManager.getFilePath(PathManager.STARTFORMAT))
                        .toExternalForm());
        return start;
    }

    private VBox createStartScene(){
        VBox vbox = new VBox(VBOX_SPACING);
        vbox.setPrefSize(STARTSCREEN_WIDTH, STARTSCREEN_HEIGHT);
        vbox.setPadding(new Insets(VBOX_INSETS, VBOX_INSETS, VBOX_INSETS, VBOX_INSETS));
        HBox hbox = new HBox(styler.createLink("UploadGrid", e-> {
            try {
//                myController.setLevel(new Level(launchFileChooser(new Stage(), "Grid"))); //TODO
                System.out.println("new level set");
//                currLevel = new Level(launchFileChooser(new Stage(), "Grid"));
//                map = myController.setModelMap(currLevel.getModelMap());
            } catch(RuntimeException eee){
                //todo: change
//                setDefaults();
//                new Alert(AlertType.WARNING, Main.MY_RESOURCES.getString("DefaultUsed")).showAndWait();
                throw new GameException(Main.ERROR_RESOURCES.getString("DefaultUsed"));
            }
        }
        ),
                styler.createLink("UploadData", e->launchFileChooser(new Stage(), "Data")),
                styler.createLink("UploadPlayers", e->launchFileChooser(new Stage(), "Players")));
        vbox.getChildren().addAll(styler.createLabel("Pac-Man"), hbox, styler.createButton("Start", e->myStage.setScene(setupScene())));
        return vbox;
    }

    private Scene setupScene(){
        myScene = new Scene(createView());
        myScene.getStylesheets()
                .add(getClass().getClassLoader().getResource(PathManager.getFilePath(PathManager.LIGHTFORMAT))
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
        map = myMapView.createMap(myController.getContainer());
        Node nonUInferface = nonUserInterface.createComponents();
        Node uInterface = userInterface.createComponents();
        viewPane.setLeft(nonUInferface);
        viewPane.setCenter(map);
        viewPane.setRight(uInterface);
        viewPane.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return viewPane;
    }

    public void addPacmen(int index, int row, int ID){
        PacManView createPacMan = new PacManView(myMapView.getPacmen(), index, row, ID, myController, this);
        pacmanCollection.add(createPacMan);
        setPacMan(ID);
    }

    public void setPacMan(int index){
        currentPacMan = pacmanCollection.get(index);
        nonUserInterface.getLivesLeft().bind(currentPacMan.pacmanLives());
        nonUserInterface.getScore().textProperty().bind(currentPacMan.pacmanScore().asString());
    }

    public void addGhosts(int index, int row, int ID){
        GhostView createGhosts = new GhostView(myMapView.getGhosts(), index, row, ID, myController, this);
        ghostCollection.add(createGhosts);
        currentGhost = ghostCollection.get(ghostCollection.size() -1 );
    }

    public void addCoins(int index, int row, int ID){
        CoinView createCoins = new CoinView(myMapView.getCoins(), index, row, ID, myController);
        coinCollection.add(createCoins);
    }

    private void beginAnimation() {
        try {
            KeyFrame step = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
            otherAnimation = new Timeline();
            otherAnimation.setCycleCount(Timeline.INDEFINITE);
            otherAnimation.getKeyFrames().addAll(step);
            otherAnimation.play();
            KeyFrame pacManStep = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> pacmanStep());
            pacmanAnimation = new Timeline();
            pacmanAnimation.setCycleCount(Timeline.INDEFINITE);
            pacmanAnimation.getKeyFrames().addAll(pacManStep);
            pacmanAnimation.play();
            KeyFrame ghostStep = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> ghostStep());
            ghostAnimation = new Timeline();
            ghostAnimation.setCycleCount(Timeline.INDEFINITE);
            ghostAnimation.getKeyFrames().addAll(ghostStep);
            ghostAnimation.play();
        }
        catch (java.lang.Exception e){
            throw new GameException(e); //can change later
        }
    }

    private void pacmanStep(){
        for(PacManView pc : pacmanCollection){
            pc.update();
        }
    }

    private void ghostStep(){
        for(GhostView gv : ghostCollection){
            gv.update();
        }
    }

    private void step(){
        myController.setGameStep();
        myGameStep.step();
        viewPane.requestFocus();
        for(CoinView cw: coinCollection){
            cw.update();
        }
    }

    private void handleKeyInput(KeyCode code){
        for(PacManView pc : pacmanCollection){
            pc.handleKeyInput(code);
        }
        if(code == KeyCode.SPACE){
            pauseOrPlay();
        }
    }

    public void pauseOrPlay(){
        myMapView.changeGameStatus();
        changeGameStatus();
        if(!gameStatus){
            otherAnimation.stop();
            pacmanAnimation.stop();
            ghostAnimation.stop();
        } else{
            viewPane.requestFocus();
            pacmanAnimation.play();
            ghostAnimation.play();
            otherAnimation.play();
        }
    }

    private void changeGameStatus() {gameStatus = !gameStatus;}

    public boolean getGameStatus() {return gameStatus;}

    public void setPacManSpeed(double speed){
        pacmanAnimation.setRate(speed);
    }

    public void setGhostSpeed(double speed){
        ghostAnimation.setRate(speed);
    }

    public Scene getMyScene(){return myScene;}

    public PacManView getCurrentPacMan(){return currentPacMan;}

   public void restartLevel(){
       nonUserInterface.getScore().textProperty().unbind();
       currentPacMan.resetScore();
       myController.resetGame();
       map = new Group();
       map = myMapView.createMap(myController.getContainer());
       viewPane.setCenter(map);
       nonUserInterface.getScore().textProperty().bind(currentPacMan.pacmanScore().asString());

   }

    private void setDefaults(){
        map = myMapView.createMap(myController.getContainer());
    }
}

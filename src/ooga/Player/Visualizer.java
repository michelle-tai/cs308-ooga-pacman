package ooga.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Player.Ghost.GhostView;
import ooga.Player.Graphics.NonUserInterface;
import ooga.Player.Graphics.UserInterface;
import ooga.Player.Map.CoinView;
import ooga.Player.Map.MapView;
import ooga.Player.PacMan.PacManView;
import ooga.controller.Controller;
import ooga.data.PathManager;
import ooga.engine.GameStep;

public class Visualizer {

    public static final int VIEWPANE_PADDING = 10;
    public static final int VIEWPANE_MARGIN = 0;
    public static final int FRAMES_PER_SECOND = 10;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final String ERROR_DIALOG = "Animation could not begin";

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
    private ResourceBundle myResources;
    private GameStep myGameStep;
    private Boolean gameStatus;

    public Visualizer (Stage stage, String currGame){
        myStage = stage;
        myController = new Controller(currGame);
        myMapView = new MapView(this, myController);
        nonUserInterface = new NonUserInterface(myController.getCurrentPathManager());
        userInterface = new UserInterface(this, myController);
        ghostCollection = new ArrayList<>();
        pacmanCollection = new ArrayList<>();
        coinCollection = new ArrayList<>();
        myResources = ResourceBundle.getBundle(PathManager.GUI_RESOURCES.getString(PathManager.ENGLISHBUTTONS));
        myGameStep = new GameStep(myController.getContainer());
        gameStatus = true;
    }

    public Scene setupScene(){
        myScene = new Scene(createView());
        myScene.getStylesheets()
                .add(getClass().getClassLoader().getResource(PathManager.GUI_RESOURCES.getString(PathManager.LIGHTFORMAT))
                        .toExternalForm());
        beginAnimation();
        return myScene;
    }

    private BorderPane createView(){
        viewPane = new BorderPane();
        viewPane.setPadding(new Insets(VIEWPANE_MARGIN, VIEWPANE_PADDING, VIEWPANE_PADDING, VIEWPANE_PADDING));
        Node map = myMapView.createMap(myController.getContainer());
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

    private void setPacMan(int index){
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
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(e.getMessage());
            errorAlert.setContentText(ERROR_DIALOG);
            errorAlert.showAndWait();
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
        StartScreen myStartScreen = new StartScreen(myStage);
        myStage.setScene(myStartScreen.startScene());
   }

}

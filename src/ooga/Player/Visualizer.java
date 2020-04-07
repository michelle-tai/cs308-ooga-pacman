package ooga.Player;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.Player.Graphics.NonUserInterface;
import ooga.Player.Graphics.UserInterface;
import ooga.Player.Map.MapView;

public class Visualizer {

    public static final int VIEWPANE_PADDING = 10;
    public static final int VIEWPANE_MARGIN = 0;
    private static final String RESOURCES = "resources";
    private static final String LEVEL_ONE = RESOURCES + ".levels.level1";

    private Stage myStage;
    private Group pacmen;
    private MapView myMapView;
    private NonUserInterface nonUserInterface;
    private UserInterface userInterface;

    public Visualizer (Stage stage){
        myStage = stage;
        pacmen = new Group();
        myMapView = new MapView();
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
        Node map = myMapView.createMap(LEVEL_ONE);
        Node nonUInferface = nonUserInterface.createComponents();
        Node uInterface = userInterface.createComponents();

        viewPane.setLeft(nonUInferface);
        viewPane.setCenter(map);
        viewPane.setRight(uInterface);

        return viewPane;
    }
}

package ooga.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.Player.Graphics.Styler;
import java.io.File;
import java.util.ResourceBundle;
import ooga.data.PathManager;

public class StartScreen {

    public static final int VBOX_INSETS = 100;
    public static final int VBOX_SPACING = 10;
    public static final int STARTSCREEN_WIDTH = 600;
    public static final int STARTSCREEN_HEIGHT = 400;

    private Styler styler;
    private Stage myStage;
    private Visualizer myVisualizer;
    private String currGame;
    private ResourceBundle myResources;
    private Label testLabel;
    private MediaPlayer songPlayer;

    /**
     * Creates an instance of the start screen both when the main class is run and when the exit button is clicked
     * @param stage - stage passed in from the main class
     */
    public StartScreen(Stage stage){
        myResources = ResourceBundle.getBundle(PathManager.GUI_RESOURCES.getString(PathManager.ENGLISHBUTTONS));
        styler = new Styler(myResources);
        myStage = stage;
        testLabel = new Label();
        testLabel.setId("Game");
        testLabel.setText(currGame);
    }

    /**
     * Creates the scene in which is set to the stage for the start screen and adds the CSS
     * @return
     */
    public Scene startScene(){
        Scene start = new Scene(createStartScene());
        start.getStylesheets()
                .add(getClass().getClassLoader().getResource(PathManager.GUI_RESOURCES.getString(PathManager.STARTFORMAT))
                        .toExternalForm());
        Media song = new Media(new File(PathManager.GUI_RESOURCES.getString(PathManager.SONG)).toURI().toString());
        songPlayer = new MediaPlayer(song);
        songPlayer.play();
        return start;
    }

    private VBox createStartScene(){
        VBox vbox = new VBox(VBOX_SPACING);
        vbox.setPrefSize(STARTSCREEN_WIDTH, STARTSCREEN_HEIGHT);
        vbox.setPadding(new Insets(VBOX_INSETS, VBOX_INSETS, VBOX_INSETS, VBOX_INSETS));
        HBox hbox = new HBox(
                styler.createLink("UploadData", e->launchFileChooser(new Stage(), "Data")),
                styler.createLink("UploadPlayers", e->launchFileChooser(new Stage(), "Players")));
        vbox.getChildren().addAll(styler.createLabel("Pac-Man"), hbox, createGameCombo(), styler.createButton("Start", e->{
                    myVisualizer = new Visualizer(myStage, currGame);
                    myStage.setScene(myVisualizer.setupScene());
                    songPlayer.stop();
                }));
        return vbox;
    }

    private ComboBox<String> createGameCombo(){
        ObservableList<String> path = FXCollections.observableArrayList();
        File resourcesFolder = new File("./resources");
        File [] gameFiles = resourcesFolder.listFiles();
        for(File f: gameFiles){
            path.add(f.getName());
        }
        ComboBox<String> paths = new ComboBox(path);
        paths.setOnAction( e-> {currGame = paths.getValue();});
        paths.setPromptText("ChooseGame");
        paths.setId("ChooseGame");
        return paths;
    }

    private File launchFileChooser(Stage stage, String feature){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose" + feature);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Data Files", "*.XML", "*.txt"));
        return fileChooser.showOpenDialog(stage);
    }
}

package ooga.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.Player.Graphics.Styler;
import ooga.data.PathManager;
import ooga.engine.GameException;

import java.io.File;
import java.util.ResourceBundle;

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

    public StartScreen(Stage stage){
        myResources = ResourceBundle.getBundle("defaultPacMan/languages/EnglishButtons");
        styler = new Styler(myResources);
        myStage = stage;
    }

    public Scene startScene(){
        Scene start = new Scene(createStartScene());
//        start.getStylesheets()
//                .add(getClass().getClassLoader().getResource(myController.getCurrentPathManager().getFilePath(PathManager.STARTFORMAT))
//                        .toExternalForm());
        return start;
    }

    public VBox createStartScene(){
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
//                        throw new GameException(myController.getCurrentPathManager().getString(PathManager.PROPERTIES,"DefaultUsed"));
                    }
                }
        ),
                styler.createLink("UploadData", e->launchFileChooser(new Stage(), "Data")),
                styler.createLink("UploadPlayers", e->launchFileChooser(new Stage(), "Players")));
        vbox.getChildren().addAll(styler.createLabel("Pac-Man"), hbox, createGameCombo(), styler.createButton("Start", e->{
                    myVisualizer = new Visualizer(myStage, currGame);
                    myStage.setScene(myVisualizer.setupScene());
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

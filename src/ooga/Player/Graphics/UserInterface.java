package ooga.Player.Graphics;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.Player.Visualizer;
import java.util.ResourceBundle;
import ooga.controller.Controller;
import ooga.data.PathManager;

public class UserInterface {

    public static final int VBOX_SPACING = 10;

    private Styler styler;
    private ResourceBundle myResources;
    private Visualizer myVisualizer;
    private Controller myController;

    /**
     * Creates the components on the right side of the screen in which the user interacts with.
     * @param visualizer - an instance of the visualizer
     * @param controller - an instance of the controller
     */
    public UserInterface(Visualizer visualizer, Controller controller){
        myController = controller;
        myVisualizer = visualizer;
        myResources = ResourceBundle.getBundle(PathManager.GUI_RESOURCES.getString(PathManager.ENGLISHBUTTONS));
        styler = new Styler(myResources);
    }

    /**
     * Creates all the components in this class and adds them to a node to be returned
     * @return a node containing all necessary elements
     */
    public Node createComponents(){
        BorderPane pane = new BorderPane();
        pane.setTop(createVbox());
        pane.setBottom(styler.createLink("Exit", e->{myVisualizer.restartLevel();}));
        return pane;
    }

    private VBox createVbox(){
        VBox vbox = new VBox(VBOX_SPACING);
        HBox hbox = new HBox(styler.createButton("DarkMode", e->changeStyleSheet(myController.getCurrentPathManager().GUI_RESOURCES.getString(PathManager.DARKFORMAT))),
                styler.createButton("LightMode", e-> changeStyleSheet(myController.getCurrentPathManager().GUI_RESOURCES.getString(PathManager.LIGHTFORMAT))));
        VBox.setVgrow(vbox, Priority.ALWAYS);
        HBox.setHgrow(hbox, Priority.ALWAYS);
        vbox.setPadding(new Insets(VBOX_SPACING, VBOX_SPACING, VBOX_SPACING, VBOX_SPACING));
        vbox.getChildren().addAll( styler.createLabel("Settings"), hbox,  styler.createButtonImage(e->myVisualizer.pauseOrPlay(),myController.getCurrentPathManager().getFilePath(PathManager.PLAYPAUSEIMAGE)),
                styler.createLabel("Change"),
                styler.createButton("ChoosePacMan", e->myVisualizer.getCurrentPacMan().choosePacMan(myVisualizer.getCurrentPacMan().getPacManImage(new Stage()))));
                return vbox;
    }

    private void changeStyleSheet(String stylesheetPath){
        myVisualizer.getMyScene().getStylesheets().clear();
        myVisualizer.getMyScene().getStylesheets()
                .add(getClass().getClassLoader()
                        .getResource(stylesheetPath).toExternalForm());
    }

}

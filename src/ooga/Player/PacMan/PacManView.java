package ooga.Player.PacMan;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import ooga.Main;
import ooga.Player.Visualizer;
import ooga.engine.sprites.*;

public class PacManView {

    private static final int PACMAN_WIDTH = 35;
    private static final int PACMAN_HEIGHT = 35;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;

    private Visualizer myVisualizer;
    private Group myPacMen;
    private ImageView myImage;
    private PacMan pacmanModel;
    private int numLives;


    public PacManView(Group pacmen, PacMan modelPacMan, Visualizer visualizer, int indexNum, int rowNum){
        myVisualizer = visualizer;
        myPacMen = pacmen;
        pacmanModel = modelPacMan;
        myImage = createPacManImage(indexNum, rowNum);
    }

    public void update(){
        pacmanModel.move();
        myImage.setX(pacmanModel.getX());
        myImage.setY(pacmanModel.getY());
    }

    public int getLives(){
        numLives = pacmanModel.getLivesLeft();
        return numLives;
    }

    public SimpleIntegerProperty pacmanLives(){
            return new SimpleIntegerProperty(getLives());
    }

    /**
     * Passes the keycode string name to the backend so that the new location based on the key pressed
     * can be calculated accordingly.
     * @param code is the KeyCode value of the key pressed
     */
    public void handleKeyInput(KeyCode code){
        pacmanModel.changeDirection(code.getName());
        System.out.println("Key pressed is: " + code.getName());
    }

    private ImageView createPacManImage(int index, int rows){
        String string = "resources/pacman/pacman1.png";
        ImageView pacmanImage = new ImageView(string);
        pacmanImage.setFitWidth(PACMAN_WIDTH);
        pacmanImage.setFitHeight(PACMAN_HEIGHT);
        pacmanImage.setX((BLOCK_WIDTH * (index)) + (BLOCK_WIDTH / 2 - pacmanImage.getBoundsInLocal().getWidth() / 2));
        pacmanImage.setY((BLOCK_HEIGHT * rows) + (BLOCK_HEIGHT / 2 - pacmanImage.getBoundsInLocal().getHeight() / 2));
        myPacMen.getChildren().add(pacmanImage);
        return pacmanImage;
    }

}

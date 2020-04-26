package ooga.Player.PacMan;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.Player.Visualizer;
import ooga.controller.Controller;
import ooga.data.PathManager;
import ooga.engine.sprites.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PacManView {

    public static final int PACMAN_WIDTH = 35;
    public static final int PACMAN_HEIGHT = 35;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;
    public static final String ERROR_DIALOG = "Please Choose Another File";
    public static final String XML_FILEPATH = "user.dir";
    public static final double RIGHT_ROTATE = 0;
    public static final double LEFT_ROTATE = 180;
    public static final double UP_ROTATE = 270;
    public static final double DOWN_ROTATE = 90;
    public static final int IMAGE_SHIFT = 20;

    private Group myPacMen;
    private ImageView myImage;
    private PacMan pacmanModel;
    private Controller myController;
    private Visualizer myVisualizer;
    private int ID;
    private MediaPlayer songPlayer;
    private int livesLeft;
    private int previousLives;

    /**
     * Creates an instance of the pacman in the view for each instance of the pacman in the backend
     * @param pacmen - group of pacman created in the visualizer
     * @param indexNum - the column number of the pacman used for placement
     * @param rowNum - the row number of the pacman used for placement
     * @param IDvalue - the ID value of the pacman used for identification
     * @param controller - an instance of the controller
     * @param visualizer - and instance of the visualizer
     */
    public PacManView(Group pacmen, int indexNum, int rowNum, int IDvalue, Controller controller, Visualizer visualizer){
        myController = controller;
        myVisualizer = visualizer;
        myPacMen = pacmen;
        ID = IDvalue;
        pacmanModel = (PacMan) myController.getCurrentPacMan(ID);
        myImage = createPacManImage(indexNum, rowNum);
        Media song = new Media(new File(PathManager.GUI_RESOURCES.getString(PathManager.DIES)).toURI().toString());
        songPlayer = new MediaPlayer(song);
        previousLives = 0;
    }

    /**
     * Updates the position of the pacman at every step and checks the status in order to adjust other game elements.
     */
    public void update(){
        myImage.setX(pacmanModel.getX() - IMAGE_SHIFT);
        myImage.setY(pacmanModel.getY() - IMAGE_SHIFT);
        checkStatus();
        myVisualizer.setPacManSpeed(pacmanModel.getSpeed());
//        System.out.println(pacmanModel.getStatus());
        System.out.println(pacmanModel.getLivesLeft().getValue()  );
    }

    /**
     * Used for binding the live count in the backend with the front end
     * @return a simple integer property to bind the values
     */
    public SimpleIntegerProperty pacmanLives() {
        pacmanModel.getLivesLeft().addListener( e->{
           checkLives();
        });
        return pacmanModel.getLivesLeft();
    }

    /**
     * used for binding the score in the backend with the front end
     * @return a simple integer property to bind the values
     */
    public SimpleIntegerProperty pacmanScore(){
        return pacmanModel.getPointsProperty();
    }

    private void checkLives(){
        songPlayer.stop();
        myVisualizer.pauseOrPlay();
        songPlayer.play();
//        if(previousLives != livesLeft){
//            previousLives = livesLeft;
//            myVisualizer.pauseOrPlay();
//            songPlayer.play();
//        }
    }
    private void checkStatus(){
        int status = pacmanModel.getStatus();
        if (status == 0){
            pacmanModel.setSpeed(Integer.parseInt(myController.getCurrentPathManager().getString(PathManager.PROPERTIES, "PacManDefaultSpeed")));
        } else if (status == 1){
            pacmanModel.setSpeed(Integer.parseInt((myController.getCurrentPathManager().getString(PathManager.PROPERTIES,"PacManDefaultSpeed"))));
        } else if (status == 2){
            pacmanModel.setSpeed(Integer.parseInt(myController.getCurrentPathManager().getString(PathManager.PROPERTIES,"PacManDefaultSpeed")) * 2);
        }
    }

    /**
     * Passes the keycode string name to the backend so that the new location based on the key pressed
     * can be calculated accordingly.
     * @param code is the KeyCode value of the key pressed
     */
    public void handleKeyInput(KeyCode code){
        if(myVisualizer.getGameStatus()){
            if(code == KeyCode.RIGHT && myController.getContainer().getSpriteMapNode(pacmanModel).getRightNeighbor() != null){
                updateOrientation("right");
                pacmanModel.changeDirection(code.getName());
            } else if (code == KeyCode.LEFT && myController.getContainer().getSpriteMapNode(pacmanModel).getLeftNeighbor() != null){
                updateOrientation("left");
                pacmanModel.changeDirection(code.getName());
            } else if (code == KeyCode.UP && myController.getContainer().getSpriteMapNode(pacmanModel).getTopNeighbor() != null){
                updateOrientation("up");
                pacmanModel.changeDirection(code.getName());
            } else if (code == KeyCode.DOWN && myController.getContainer().getSpriteMapNode(pacmanModel).getBottomNeighbor() != null){
                updateOrientation("down");
                pacmanModel.changeDirection(code.getName());
            }
        }
    }

    private ImageView createPacManImage(int index, int rows){
        ImageView pacmanImage = new ImageView(pacmanModel.getImagePath());
        pacmanImage.setFitWidth(PACMAN_WIDTH);
        pacmanImage.setFitHeight(PACMAN_HEIGHT);
        pacmanImage.setX((BLOCK_WIDTH * (index)) + (BLOCK_WIDTH / 2 - pacmanImage.getBoundsInLocal().getWidth() / 2));
        pacmanImage.setY((BLOCK_HEIGHT * rows) + (BLOCK_HEIGHT / 2 - pacmanImage.getBoundsInLocal().getHeight() / 2));
        myPacMen.getChildren().add(pacmanImage);
        return pacmanImage;
    }

    /**
     * Allows user to choose a new image for the pacman in the user interface.
     * @param imageFile - the new image for the pacman
     */
    public void choosePacMan(File imageFile) {
        ImageView pacmanImage = new ImageView();
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            pacmanImage.setImage(image);
            setShape(pacmanImage);
        } catch (IllegalArgumentException e){
            return;
        } catch (IOException e) {
            retryLoadFile(e.getMessage());
        }
    }

    private void retryLoadFile(String message) {
        boolean badFile;
        displayError(message);
        do {
            badFile = false;
            try {
                choosePacMan(getPacManImage(new Stage()));
            } catch (NullPointerException e){
                return;
            } catch (Exception e){
                displayError(e.getMessage());
                badFile = true;
            }
        } while (badFile);
    }

    private void displayError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(message);
        errorAlert.setContentText(ERROR_DIALOG);
        errorAlert.showAndWait();
    }

    /**
     * creates a file chooser for the new pacman
     * @param stage - stage to create a file chooser
     * @return a new file of an image
     */
    public File getPacManImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose PacMan Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        fileChooser.setInitialDirectory(new File(System.getProperty(XML_FILEPATH)));
        return fileChooser.showOpenDialog(stage);
    }

    private void setShape(ImageView pacMan){
        pacMan.setFitWidth(PACMAN_WIDTH);
        pacMan.setFitHeight(PACMAN_HEIGHT);
        myPacMen.getChildren().remove(myImage);
        myImage = pacMan;
        myPacMen.getChildren().add(myImage);
        set(pacmanModel.getX(), pacmanModel.getY());
    }

    private void set(int newX, int newY){
        myImage.setX(newX);
        myImage.setY(newY);
    }

    private void updateOrientation(String direction){
        switch(direction){
            case "right": myImage.setRotate(RIGHT_ROTATE);
            break;
            case "left": myImage.setRotate(LEFT_ROTATE);
            break;
            case "up": myImage.setRotate(UP_ROTATE);
            break;
            case "down": myImage.setRotate(DOWN_ROTATE);
            break;
        }
    }
}

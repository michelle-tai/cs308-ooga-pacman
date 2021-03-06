package ooga.Player.Ghost;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ooga.Player.Visualizer;
import ooga.controller.Controller;
import ooga.data.PathManager;
import ooga.engine.sprites.*;

public class GhostView {

    private static final int GHOST_WIDTH = 35;
    private static final int GHOST_HEIGHT = 35;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;
    public static final int GHOST_SHIFT = 20;

    private Group myGhosts;
    private ImageView myImage;
    private Ghost ghostModel;
    private Controller myController;
    private Visualizer myVisualizer;
    private int ID;

    /**
     * This class creates an instance of the ghostview and has its own individual properties
     * @param ghosts - takes in the group of all ghosts
     * @param indexNum - takes in the column in order to set location
     * @param rowNum - takes in the row in order to set location
     * @param idValue - takes in an ID value
     * @param controller - takes in an instance of the controller
     * @param visualizer - takes in an instance of the visualizer
     */
    public GhostView(Group ghosts, int indexNum, int rowNum, int idValue, Controller controller, Visualizer visualizer){
        myController = controller;
        myVisualizer = visualizer;
        myGhosts = ghosts;
        ID = idValue;
        ghostModel = (Ghost) myController.getCurrentGhost(ID);
        myImage = createGhostImage(indexNum, rowNum);
    }

    /**
     * Updates the position of the ghost at every step and checks its status to change it imaging.
     */
    public void update() {
        myImage.setX(ghostModel.getX() - GHOST_SHIFT);
        myImage.setY(ghostModel.getY() - GHOST_SHIFT);
        checkStatus();
        myVisualizer.setGhostSpeed(ghostModel.getSpeed());
    }

    private void checkStatus(){
        int status = ghostModel.getStatus();
        if (status == 0){
            changeImage(0);
            ghostModel.setSpeed(Integer.parseInt(myController.getCurrentPathManager().getString(PathManager.PROPERTIES, "GhostDefaultSpeed")));
        } else if (status == 1){
            changeImage(1);
            ghostModel.setSpeed(Integer.parseInt(myController.getCurrentPathManager().getString(PathManager.PROPERTIES, "GhostDefaultSpeed")));
        } else if (status == 2){
            changeImage(0);
            ghostModel.setSpeed((Integer.parseInt(myController.getCurrentPathManager().getString(PathManager.PROPERTIES, "GhostDefaultSpeed"))) * 2);
        }
    }

    private ImageView createGhostImage(int index, int rows){
        ImageView ghostImage = new ImageView(ghostModel.getImagePath());
        ghostImage.setFitWidth(GHOST_WIDTH);
        ghostImage.setFitHeight(GHOST_HEIGHT);
        ghostImage.setX((BLOCK_WIDTH * (index)) + (BLOCK_WIDTH / 2 - ghostImage.getBoundsInLocal().getWidth() / 2));
        ghostImage.setY((BLOCK_HEIGHT * rows) + (BLOCK_HEIGHT / 2 - ghostImage.getBoundsInLocal().getHeight() / 2));
        myGhosts.getChildren().add(ghostImage);
        return ghostImage;
    }

    private void changeImage(int imageIndex){
        ImageView newImage = new ImageView();
        if( imageIndex == 0){
            newImage = new ImageView(ghostModel.getImagePath());
        } else if (imageIndex == 1){
            newImage = new ImageView(ghostModel.getScaredImagePath());
        }
        newImage.setFitWidth(GHOST_WIDTH);
        newImage.setFitHeight(GHOST_WIDTH);
        myGhosts.getChildren().remove(myImage);
        myImage = newImage;
        myGhosts.getChildren().add(myImage);
        set(ghostModel.getX(), ghostModel.getY());
    }

    private void set(int newX, int newY){
        myImage.setX(newX);
        myImage.setY(newY);
    }
}

package ooga.Player.Ghost;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ooga.Main;
import ooga.Player.Visualizer;
import ooga.controller.Controller;
import ooga.data.PathManager;
import ooga.engine.sprites.*;

public class GhostView {

    private static final int GHOST_WIDTH = 35;
    private static final int GHOST_HEIGHT = 35;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;

    private Group myGhosts;
    private ImageView myImage;
    private Ghost ghostModel;
    private Controller myController;
    private Visualizer myVisualizer;
    private int ID;

    public GhostView(Group ghosts, int indexNum, int rowNum, int idValue, Controller controller, Visualizer visualizer){
        myController = controller;
        myVisualizer = visualizer;
        myGhosts = ghosts;
        ID = idValue;
        ghostModel = (Ghost) myController.getCurrentGhost(ID);
        myImage = createGhostImage(indexNum, rowNum);
    }

    public void update() {
        myImage.setX(ghostModel.getX() - 20);
        myImage.setY(ghostModel.getY() - 20);
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

package ooga.Player.Ghost;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ooga.Main;
import ooga.Player.Visualizer;
import ooga.controller.Controller;
import ooga.data.PathManager;
import ooga.engine.sprites.Sprite;
import ooga.engine.sprites.*;

public class GhostView {

    private static final int GHOST_WIDTH = 35;
    private static final int GHOST_HEIGHT = 35;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;
    public static final int SCARED_GHOST = 5;

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
//        ghostModel.move();
        myImage.setX(ghostModel.getX());
        myImage.setY(ghostModel.getY());
        checkStatus();
        myVisualizer.setGhostSpeed(ghostModel.getSpeed());
    }

    private void checkStatus(){
        int status = ghostModel.getStatus();
        if (status == 0){
            changeImage(ID);
            ghostModel.setSpeed(Integer.parseInt(Main.MY_RESOURCES.getString("GhostDefaultSpeed")));
        } else if (status == 1){
            changeImage(SCARED_GHOST);
            ghostModel.setSpeed(Integer.parseInt(Main.MY_RESOURCES.getString("GhostDefaultSpeed")));
        } else if (status == 2){
            ghostModel.setSpeed((Integer.parseInt(Main.MY_RESOURCES.getString("GhostDefaultSpeed"))) * 2);
        }
    }

    private ImageView createGhostImage(int index, int rows){
        String string = "resources/ghost/ghost" + (ID+1) + ".png";
        ImageView ghostImage = new ImageView(PathManager.getGhostPath(ID));
        ghostImage.setFitWidth(GHOST_WIDTH);
        ghostImage.setFitHeight(GHOST_HEIGHT);
        ghostImage.setX((BLOCK_WIDTH * (index)) + (BLOCK_WIDTH / 2 - ghostImage.getBoundsInLocal().getWidth() / 2));
        ghostImage.setY((BLOCK_HEIGHT * rows) + (BLOCK_HEIGHT / 2 - ghostImage.getBoundsInLocal().getHeight() / 2));
        myGhosts.getChildren().add(ghostImage);
        return ghostImage;
    }

    private void changeImage(int imageIndex){
        ImageView newImage = new ImageView(PathManager.getGhostPath(imageIndex));
        newImage.setFitWidth(GHOST_WIDTH);
        newImage.setFitHeight(GHOST_WIDTH);
        myGhosts.getChildren().remove(myImage);
        myImage = newImage;
        myGhosts.getChildren().add(myImage);
        set(ghostModel.getX(), ghostModel.getY());
    }

    public void set(int newX, int newY){
        myImage.setX(newX);
        myImage.setY(newY);
    }

}

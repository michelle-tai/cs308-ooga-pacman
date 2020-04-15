package ooga.Player.Ghost;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ooga.Main;
import ooga.Player.Visualizer;
import ooga.engine.sprites.*;

public class GhostView {

    private static final int GHOST_WIDTH = 35;
    private static final int GHOST_HEIGHT = 35;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;

    private Visualizer myVisualizer;
    private Group myGhosts;
    private ImageView myImage;
    private Ghost ghostModel;
    private int ID;

    public GhostView(Group ghosts, Ghost modelGhost, Visualizer visualizer, int indexNum, int rowNum){
        myVisualizer = visualizer;
        myGhosts = ghosts;
        ghostModel = modelGhost;
        ID = ghostModel.getID();
        System.out.println(myGhosts.getChildren());
        myImage = createGhostImage(indexNum, rowNum);



    }

    public void update() {
        ghostModel.move();
        myImage.setX(ghostModel.getX());
        myImage.setY(ghostModel.getY());
    }

    private ImageView createGhostImage(int index, int rows){
        String string = "resources/ghost/ghost" + ID + ".png";
        ImageView ghostImage = new ImageView(string);
        ghostImage.setFitWidth(GHOST_WIDTH);
        ghostImage.setFitHeight(GHOST_HEIGHT);
        ghostImage.setX((BLOCK_WIDTH * (index)) + (BLOCK_WIDTH / 2 - ghostImage.getBoundsInLocal().getWidth() / 2));
        ghostImage.setY((BLOCK_HEIGHT * rows) + (BLOCK_HEIGHT / 2 - ghostImage.getBoundsInLocal().getHeight() / 2));
        myGhosts.getChildren().add(ghostImage);
        return ghostImage;
    }

}

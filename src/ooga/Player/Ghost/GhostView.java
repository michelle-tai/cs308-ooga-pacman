package ooga.Player.Ghost;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ooga.Player.Visualizer;

public class GhostView {

    private static final int GHOST_WIDTH = 50;
    private static final int GHOST_HEIGHT = 40;

    private Visualizer myVisualizer;
    private Group myGhosts;
    private ImageView myImage;

    public GhostView(Group ghosts, Visualizer visualizer){
        myVisualizer = visualizer;
        myGhosts = ghosts;
        myImage = createGhostImage();
    }

    private ImageView createGhostImage(){
        String string = "resources/pacman/ghost1.png";
        ImageView ghostImage = new ImageView(string);
        ghostImage.setFitWidth(GHOST_WIDTH);
        ghostImage.setFitHeight(GHOST_HEIGHT);
        //TODO: add a starting point
        myGhosts.getChildren().add(ghostImage);
        return ghostImage;
    }

}

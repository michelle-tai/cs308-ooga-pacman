package ooga.Player.Ghost;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ooga.Main;
import ooga.Player.Visualizer;
import ooga.engine.Ghost;

public class GhostView {

    private static final int GHOST_WIDTH = 35;
    private static final int GHOST_HEIGHT = 35;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;

    private Visualizer myVisualizer;
    private Group myGhosts;
    private ImageView myImage;
    private Ghost modelGhost;

    public GhostView(Group ghosts, Visualizer visualizer, int indexNum, int rowNum, int ghostNum){
        myVisualizer = visualizer;
        myGhosts = ghosts;
        System.out.println(myGhosts.getChildren());
        myImage = createGhostImage(indexNum, rowNum, ghostNum);
        int ghostWidth = Integer.parseInt(Main.MY_RESOURCES.getString("GhostWidth"));
        int ghostHeight = Integer.parseInt(Main.MY_RESOURCES.getString("GhostHeight"));

        modelGhost = new Ghost(BLOCK_WIDTH * indexNum, BLOCK_HEIGHT * rowNum, ghostWidth, ghostHeight);
    }

    public void update() {
        modelGhost.move();
        myImage.setX(modelGhost.getX());
        myImage.setY(modelGhost.getY());
    }

    private ImageView createGhostImage(int index, int rows, int ghostNum){
        String string = "resources/ghost/ghost" + ghostNum + ".png";
        ImageView ghostImage = new ImageView(string);
        ghostImage.setFitWidth(GHOST_WIDTH);
        ghostImage.setFitHeight(GHOST_HEIGHT);
        ghostImage.setX((BLOCK_WIDTH * (index)) + (BLOCK_WIDTH / 2 - ghostImage.getBoundsInLocal().getWidth() / 2));
        ghostImage.setY((BLOCK_HEIGHT * rows) + (BLOCK_HEIGHT / 2 - ghostImage.getBoundsInLocal().getHeight() / 2));
        myGhosts.getChildren().add(ghostImage);
        return ghostImage;
    }

}

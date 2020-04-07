package ooga.Player.PacMan;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ooga.Player.Visualizer;

public class PacManView {

    private static final int PACMAN_WIDTH = 35;
    private static final int PACMAN_HEIGHT = 35;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;

    private Visualizer myVisualizer;
    private Group myPacMen;
    private ImageView myImage;

    public PacManView(Group pacmen, Visualizer visualizer, int indexNum, int rowNum){
        myVisualizer = visualizer;
        myPacMen = pacmen;
        myImage = createPacManImage(indexNum, rowNum);
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

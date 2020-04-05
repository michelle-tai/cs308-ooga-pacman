package ooga.Player.PacMan;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ooga.Player.Visualizer;

public class PacManView {

    private static final int PACMAN_WIDTH = 50;
    private static final int PACMAN_HEIGHT = 40;

    private Visualizer myVisualizer;
    private Group myPacMen;
    private ImageView myImage;

    public PacManView(Group pacmen, Visualizer visualizer){
        myVisualizer = visualizer;
        myPacMen = pacmen;
        myImage = createPacManImage();
    }

    private ImageView createPacManImage(){
        String string = "resources/pacman/pacman1.png";
        ImageView pacmanImage = new ImageView(string);
        pacmanImage.setFitWidth(PACMAN_WIDTH);
        pacmanImage.setFitHeight(PACMAN_HEIGHT);
       //TODO: add a starting point
        myPacMen.getChildren().add(pacmanImage);
        return pacmanImage;
    }

}

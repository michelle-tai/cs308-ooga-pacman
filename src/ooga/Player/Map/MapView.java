package ooga.Player.Map;

import java.util.Set;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import ooga.Player.Graphics.Styler;
import ooga.Player.Visualizer;
import ooga.data.PathManager;
import ooga.engine.*;
import ooga.engine.sprites.*;

import java.util.ResourceBundle;

public class MapView {

    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;

    private Group pacmen;
    private Group ghosts;
    private Visualizer myVisualizer;
    private boolean gameStatus;
    private Styler styler;
    private ResourceBundle myResources;
    private Group totalMap;
    private Label pauseLabel;
    private Group coins;

    public MapView(Visualizer visualizer){
        myVisualizer = visualizer;
        gameStatus = true;
        myResources = myVisualizer.getController().getCurrentPathManager().getResourceBundle(PathManager.ENGLISHBUTTONS);
        styler = new Styler(myResources);
        pauseLabel = styler.createLabel("Pause");
        pauseLabel.setId("pause");
    }

    public Group createMap(GameContainer container) {
        totalMap = new Group();
        pacmen = new Group();
        ghosts = new Group();
        coins = new Group();
        totalMap.getChildren().addAll(createMapFromContainer(container), coins, pacmen, ghosts);
        return totalMap;
    }

    private Node createMapFromContainer(GameContainer container) {
        Group map = new Group();
        for(Pair<Integer, Integer> loc : container.getModelMap().keySet()) {
            Set<Sprite> objects = container.getModelMap().get(loc);
            for (Sprite sprite : objects) {
                if (sprite instanceof Block) {
                    map.getChildren().add(generateBlock(loc.getKey(), loc.getValue()));
                } else if (sprite instanceof Coin) {
                    myVisualizer.addCoins(loc.getKey(), loc.getValue(), ((Coin) sprite).getMyID());
                } else if (sprite instanceof Ghost) {
                    myVisualizer.addGhosts(loc.getKey(), loc.getValue(), ((Ghost) sprite).getID());
                } else if (sprite instanceof PacMan){
                    myVisualizer.addPacmen(loc.getKey(), loc.getValue(), ((PacMan) sprite).getID());
                }
            }
        }
            return map;
    }

    private ImageView generateBlock(int index, int rowNum){
        ImageView blockImage = new ImageView(myVisualizer.getController().getBlock().getImagePath());
        blockImage.setFitWidth(BLOCK_WIDTH);
        blockImage.setFitHeight(BLOCK_HEIGHT);
        blockImage.setX(BLOCK_WIDTH * index);
        blockImage.setY(BLOCK_HEIGHT * rowNum);
        return blockImage;
    }

    public Group getPacmen(){return pacmen;}

    public Group getGhosts(){return ghosts;}

    public Group getCoins(){return coins;}

    public void changeGameStatus(){
       gameStatus = !gameStatus;
        if(!gameStatus){
            totalMap.getChildren().add(pauseLabel);
        } else {
            totalMap.getChildren().remove(pauseLabel);
        }
    }

}

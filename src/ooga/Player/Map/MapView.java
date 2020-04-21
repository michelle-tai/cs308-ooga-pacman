package ooga.Player.Map;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import ooga.Player.Graphics.Styler;
import ooga.Player.Visualizer;
import ooga.controller.Controller;
import ooga.data.PathManager;
import ooga.engine.*;
import ooga.engine.sprites.*;

import java.util.HashSet;
import java.util.ResourceBundle;

public class MapView {

    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;
    public static final int FOOD_WIDTH = 10;
    public static final int FOOD_HEIGHT = 10;

    private Group pacmen;
    private Group ghosts;
    private Visualizer myVisualizer;
    private Controller myController;
    private boolean gameStatus;
    private Styler styler;
    private ResourceBundle myResources;
    private Group totalMap;
    private Label pauseLabel;
    private Group coins;

    public MapView(Visualizer visualizer){
        pacmen = new Group();
        ghosts = new Group();
        coins = new Group();
        myVisualizer = visualizer;
        myController = new Controller();
        gameStatus = true;
        myResources = PathManager.getResourceBundle(PathManager.ENGLISHBUTTONS);
        styler = new Styler(myResources);
        pauseLabel = styler.createLabel("Pause");
        pauseLabel.setId("pause");
    }

    public Node createMap(String level, GameContainer container) {
        totalMap = new Group();
        totalMap.getChildren().addAll(createMapFromContainer(level, container), coins, pacmen, ghosts);
        return totalMap;
    }

    private Node createMapFromContainer(String level, GameContainer container) {
        container.createMapFromFile(level);
        Group map = new Group();
        for(Pair<Integer, Integer> loc : container.getModelMap().keySet()) {
            HashSet<Sprite> objects = container.getModelMap().get(loc);
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
        ImageView blockImage = new ImageView(PathManager.getFilePath(PathManager.BLOCKIMAGE));
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
            System.out.println("added pause");
        } else {
            totalMap.getChildren().remove(pauseLabel);
            System.out.println("removed pause");
        }
    }

    public boolean gameStatus() {return gameStatus;}

}

package ooga.Player.Map;

import java.util.Set;
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
import java.util.ResourceBundle;

public class MapView {

    private int BLOCK_WIDTH;
    private int BLOCK_HEIGHT;

    private Group pacmen;
    private Group ghosts;
    private Visualizer myVisualizer;
    private boolean gameStatus;
    private Styler styler;
    private ResourceBundle myResources;
    private Group totalMap;
    private Label pauseLabel;
    private Group coins;
    private Controller myController;

    /**
     * Creates a front end version of the map from the backend game container
     * @param visualizer - an instance of the visualizer
     * @param controller - an instance of the controller
     */
    public MapView(Visualizer visualizer, Controller controller){
        myVisualizer = visualizer;
        myController = controller;
        gameStatus = true;
        myResources = ResourceBundle.getBundle(PathManager.GUI_RESOURCES.getString(PathManager.ENGLISHBUTTONS));
        styler = new Styler(myResources);
        pauseLabel = styler.createLabel("Pause");
        pauseLabel.setId("pause");

        BLOCK_WIDTH = Integer.parseInt(myController.getCurrentPathManager().getResourceBundle(PathManager.PROPERTIES).getString("BlockDim"));
        BLOCK_HEIGHT = Integer.parseInt(myController.getCurrentPathManager().getResourceBundle(PathManager.PROPERTIES).getString("BlockDim"));
    }

    /**
     * Creates a map from the game container every time this method is called
     * @param container - the game container used to create the map in the back end
     * @return a group of nodes making up the map in the view
     */
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
        ImageView blockImage = new ImageView(myController.getBlock().getImagePath());
        blockImage.setFitWidth(BLOCK_WIDTH);
        blockImage.setFitHeight(BLOCK_HEIGHT);
        blockImage.setX(BLOCK_WIDTH * index);
        blockImage.setY(BLOCK_HEIGHT * rowNum);
        return blockImage;
    }

    /**
     * @return the group of pacmen created from the mapview
     */
    public Group getPacmen(){return pacmen;}

    /**
     * @return the group of ghosts created from the mapview
     */
    public Group getGhosts(){return ghosts;}

    /**
     * @return the group of coins created from the mapview
     */
    public Group getCoins(){return coins;}

    /**
     * changes the game status so that the game can be paused
     */
    public void changeGameStatus(){
       gameStatus = !gameStatus;
        if(!gameStatus){
            totalMap.getChildren().add(pauseLabel);
        } else {
            totalMap.getChildren().remove(pauseLabel);
        }
    }

}

package ooga.Player.Map;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import ooga.Player.Graphics.Styler;
import ooga.Player.Visualizer;
import ooga.controller.Controller;
import ooga.engine.*;
import ooga.engine.sprites.*;
import java.io.*;
import java.util.HashSet;
import java.util.ResourceBundle;

public class MapView {

    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;
    public static final int FOOD_WIDTH = 10;
    public static final int FOOD_HEIGHT = 10;
    public static final String RESOURCES1 = "resources";
    public static final String DEFAULT_RESOURCE_FOLDER = RESOURCES1 + "/formats/";
    public static final String ENGLISH_BUTTONS = "EnglishButtons";

    private Group pacmen;
    private Group ghosts;
    private Visualizer myVisualizer;
    private Controller myController;
    private boolean gameStatus;
    private Styler styler;
    private ResourceBundle myResources;
    private Group totalMap;
    private Label pauseLabel;

    public MapView(Visualizer visualizer){
        pacmen = new Group();
        ghosts = new Group();
        myVisualizer = visualizer;
        myController = new Controller();
        gameStatus = true;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_FOLDER + ENGLISH_BUTTONS);
        styler = new Styler(myResources);
        pauseLabel = styler.createLabel("Pause");
        pauseLabel.setId("pause");
    }

    public Node createMap(String level, GameContainer container) {
        totalMap = new Group();
        totalMap.getChildren().addAll(createMapFromContainer(level, container), pacmen, ghosts);
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
                    map.getChildren().add(generateFood(loc.getKey(), loc.getValue()));
                } else if (sprite instanceof Ghost) {
                    myVisualizer.addGhosts(loc.getKey(), loc.getValue(), (Ghost) sprite);
                } else if (sprite instanceof PacMan){
                    myVisualizer.addPacmen(loc.getKey(), loc.getValue(), (PacMan) sprite);
                }
            }
        }
            return map;
    }

    private ImageView generateBlock(int index, int rowNum){
        String string = "resources/map/block1.png";
        ImageView blockImage = new ImageView(string);
        blockImage.setFitWidth(BLOCK_WIDTH);
        blockImage.setFitHeight(BLOCK_HEIGHT);
        blockImage.setX(BLOCK_WIDTH * index);
        blockImage.setY(BLOCK_HEIGHT * rowNum);
        return blockImage;
    }

    private ImageView generateFood(int index, int rowNum){
        String string = "resources/map/food1.png";
        ImageView foodImage = new ImageView(string);
        foodImage.setFitWidth(FOOD_WIDTH);
        foodImage.setFitHeight(FOOD_HEIGHT);
        foodImage.setX((BLOCK_WIDTH * (index)) + (BLOCK_WIDTH / 2 - foodImage.getBoundsInLocal().getWidth() / 2));
        foodImage.setY((BLOCK_HEIGHT * rowNum) + (BLOCK_HEIGHT / 2 - foodImage.getBoundsInLocal().getHeight() / 2));
        return foodImage;
    }

    public Group getPacmen(){return pacmen;}

    public Group getGhosts(){return ghosts;}

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

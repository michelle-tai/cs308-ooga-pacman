package ooga.Player.Map;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ooga.controller.Controller;
import ooga.data.PathManager;

public class CoinView {

    public static final int FOOD_WIDTH = 10;
    public static final int FOOD_HEIGHT = 10;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;

    private Group myCoins;
    private ImageView myImage;
    private Controller myController;


    public CoinView(Group coins, int indexNum, int rowNum, Controller controller){
        myCoins = coins;
        myController = controller;
        myImage = generateFood(indexNum, rowNum);
    }


    private ImageView generateFood(int index, int rowNum){
        ImageView foodImage = new ImageView(PathManager.getFilePath(PathManager.FOODIMAGE));
        foodImage.setFitWidth(FOOD_WIDTH);
        foodImage.setFitHeight(FOOD_HEIGHT);
        foodImage.setX((BLOCK_WIDTH * (index)) + (BLOCK_WIDTH / 2 - foodImage.getBoundsInLocal().getWidth() / 2));
        foodImage.setY((BLOCK_HEIGHT * rowNum) + (BLOCK_HEIGHT / 2 - foodImage.getBoundsInLocal().getHeight() / 2));
        myCoins.getChildren().add(foodImage);
        return foodImage;
    }
}

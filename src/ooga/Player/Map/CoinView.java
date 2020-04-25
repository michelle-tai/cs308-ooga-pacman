package ooga.Player.Map;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ooga.controller.Controller;
import ooga.engine.sprites.Coin;

public class CoinView {

    public static final int FOOD_WIDTH = 10;
    public static final int FOOD_HEIGHT = 10;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;

    private Group myCoins;
    private ImageView myImage;
    private Controller myController;
    private Coin coinModel;


    /**
     * Creates an instance of each coin in the front end from the map created in the backend.
     * @param coins - a group of all the coins used
     * @param indexNum - the column of the coin used for placement
     * @param rowNum - the row of the coin used for placement
     * @param ID - the id given to the coin to help with identification
     * @param controller - an instance of the controller
     */
    public CoinView(Group coins, int indexNum, int rowNum, int ID, Controller controller){
        myCoins = coins;
        myController = controller;
        coinModel = (Coin) myController.getCurrentCoin(ID);
        myImage = generateFood(indexNum, rowNum, ID);
    }

    /**
     * updates the status of each coin at every step and keeps track of the points
     */
    public void update(){
        if(!coinModel.checkActive()){
            myCoins.getChildren().remove(myImage);
            myController.addScore(coinModel.getPoints());
        }
    }

    private ImageView generateFood(int index, int rowNum, int ID){
        ImageView foodImage = new ImageView(coinModel.getImagePath());
        if(coinModel.getStatus() == 0){
            foodImage.setFitWidth(FOOD_WIDTH);
            foodImage.setFitHeight(FOOD_HEIGHT);
        } else if (coinModel.getStatus() == 1 || coinModel.getStatus() == 2){
            foodImage.setFitWidth(FOOD_WIDTH * 2);
            foodImage.setFitHeight(FOOD_HEIGHT * 2);
        }
        foodImage.setX((BLOCK_WIDTH * (index)) + (BLOCK_WIDTH / 2 - foodImage.getBoundsInLocal().getWidth() / 2));
        foodImage.setY((BLOCK_HEIGHT * rowNum) + (BLOCK_HEIGHT / 2 - foodImage.getBoundsInLocal().getHeight() / 2));
        myCoins.getChildren().add(foodImage);
        return foodImage;
    }
}

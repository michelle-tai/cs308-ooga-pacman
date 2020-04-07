package ooga.Player.Map;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.*;

public class MapView {

    public static final int BLOCK_WIDTH = 10;
    public static final int BLOCK_HEIGHT = 10;
    public static final int FOOD_WIDTH = 5;
    public static final int FOOD_HEIGHT = 5;

    public MapView(){

    }

    //TODO: create map from a data file and create other classes for power ups
    public Node createMap(String level){
        VBox map = new VBox();
        File file = new File(level);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String string;
            int row = 0;
            while ((string = br.readLine()) != null){
                HBox rows = new HBox();
                for( int i = 0; i < string.length(); i++){
                    ImageView currImage = new ImageView();
                    if (string.charAt(i) == 'x'){
                        currImage = generateBlock(i, row);
                    } else if (string.charAt(i) == 'o') {
                        currImage = generateFood(i , row);
                    }
                    rows.getChildren().add(currImage);
                }
                row++;
                map.getChildren().add(rows);
            }
        } catch(FileNotFoundException e){
            //TODO: add error here
            System.out.println("File not found");
        } catch (IOException e) {
            //TODO: add error here
            System.out.println(e);
        }
        return map;
    }

    private ImageView generateBlock(int index, int rowNum){
        String string = "resources/map/block1.png";
        ImageView blockImage = new ImageView(string);
        blockImage.setFitWidth(BLOCK_WIDTH);
        blockImage.setFitWidth(BLOCK_HEIGHT);
        blockImage.setTranslateX(BLOCK_WIDTH * index);
        blockImage.setTranslateX(BLOCK_HEIGHT * rowNum);
        return blockImage;
    }

    private ImageView generateFood(int index, int rowNum){
        String string = "resources/map/food1.png";
        ImageView foodImage = new ImageView(string);
        foodImage.setFitWidth(FOOD_WIDTH);
        foodImage.setFitWidth(FOOD_HEIGHT);
        foodImage.setTranslateX((BLOCK_WIDTH * index) + (BLOCK_WIDTH / 2 - foodImage.getBoundsInLocal().getWidth() / 2));
        foodImage.setTranslateX((BLOCK_HEIGHT * rowNum) + (BLOCK_HEIGHT / 2 - foodImage.getBoundsInLocal().getHeight() / 2));
        return foodImage;
    }
}

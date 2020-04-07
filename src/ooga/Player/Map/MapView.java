package ooga.Player.Map;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.*;

public class MapView {

    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;
    public static final int FOOD_WIDTH = 10;
    public static final int FOOD_HEIGHT = 10;

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
                Group rows = new Group();
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
}

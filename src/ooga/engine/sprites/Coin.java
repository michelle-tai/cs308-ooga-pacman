package ooga.engine.sprites;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import ooga.Main;

import java.util.List;
import ooga.data.PathManager;

public class Coin extends StaticSprite {

    private Rectangle hitbox;
    private int myStatus;
    private boolean myActivity;
    private int myID;

    private int[] myPoints = new int[]{1, 2 ,3};

    private String movementType;

    public Coin(int x, int y, int type, int ID, String imagePath, PathManager pathManager) {
        super(x, y, imagePath);
        myID = ID;
        movementType = pathManager.getString(PathManager.PROPERTIES, "CoinMovement");
        int hitBoxDim = Integer.parseInt(pathManager.getString(PathManager.PROPERTIES, "CoinDim"));
        hitbox = new Rectangle(x, y, hitBoxDim, hitBoxDim);
        myStatus = type;
        myActivity = true;
    }

    @Override
    public Rectangle getHitBox() {
        return hitbox;
    }

    @Override
    public String getMovementType() {
        return movementType;
    }

    @Override
    public void setMovementType(String movementType, List<Sprite> targetSprite) {
        this.movementType = movementType;
    }

    public void setStatus(int status){

    }

    public boolean checkActive(){return myActivity;}

    public void setActive(){myActivity = !myActivity;}

    public int getMyID() {return myID;}

    public int getStatus(){
        return myStatus;
    }

    public int getPoints(){
        return myPoints[myStatus];
    }


}

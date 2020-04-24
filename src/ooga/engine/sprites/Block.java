package ooga.engine.sprites;

import javafx.scene.shape.Rectangle;
import ooga.Main;

import java.util.List;
import ooga.data.PathManager;


public class Block extends StaticSprite {

    private Rectangle hitbox;
    private int status;

    private static String movementType;

    public Block(int x, int y, String imagePath, PathManager pathManager) {
        super(x, y, imagePath);
        movementType = pathManager.getString(PathManager.PROPERTIES, "BlockMovement");
        int hitBoxDim = Integer.parseInt(pathManager.getString(PathManager.PROPERTIES,"BlockDim"));
        hitbox = new Rectangle(x, y, hitBoxDim, hitBoxDim);
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
        //do nothing
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public void setStatus(int newStatus) {
        //do nothing
    }
}

package ooga.engine.sprites;

import javafx.scene.shape.Rectangle;
import ooga.Main;
import ooga.engine.StaticSprite;


public class Block extends StaticSprite {

    private Rectangle hitbox;
    private int status;

    private static final String movementType = Main.MY_RESOURCES.getString("BlockMovement");

    public Block(int x, int y) {
        super(x, y);
        int hitBoxDim = Integer.parseInt(Main.MY_RESOURCES.getString("BlockDim"));
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
    public void setMovementType(String movementType) {
        //do nothing
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public void setStatus(int newStatus) {
        status = newStatus;
    }
}

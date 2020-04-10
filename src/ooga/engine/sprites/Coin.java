package ooga.engine.sprites;

import javafx.scene.shape.Rectangle;
import ooga.Main;
import ooga.engine.StaticSprite;

public class Coin extends StaticSprite {

    private Rectangle hitbox;

    private String movementType = Main.MY_RESOURCES.getString("CoinMovement");

    public Coin(int x, int y) {
        super(x, y);
        int hitBoxDim = Integer.parseInt(Main.MY_RESOURCES.getString("CoinDim"));
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
        this.movementType = movementType;
    }

}

package ooga.engine;

import javafx.scene.shape.Rectangle;

public abstract class DynamicSprite implements Sprite{
    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setX(int newX) {

    }

    @Override
    public void setY(int newY) {

    }

    @Override
    public Rectangle getHitBox() {
        return null;
    }

    @Override
    public String getMovementType() {
        return null;
    }

    @Override
    public void setMovementType(String movementType) {

    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed(int newSpeed) {

    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public void setStatus(int newStatus) {

    }

}

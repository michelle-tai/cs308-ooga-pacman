package ooga.engine;

import javafx.scene.shape.Rectangle;
import ooga.Main;

public abstract class StaticSprite implements Sprite{

    private int myXPos;
    private int myYPos;


    public StaticSprite(int x, int y){
        myXPos = x;
        myYPos = y;

    }

    @Override
    public int getX() {
        return myXPos;
    }

    @Override
    public int getY() {
        return myYPos;
    }

    @Override
    public void setX(int newX) {
        //do nothing
    }

    @Override
    public void setY(int newY) {
        //do nothing
    }


    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed(int newSpeed) {
        //do nothing
    }
}

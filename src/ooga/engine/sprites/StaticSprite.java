package ooga.engine.sprites;

import ooga.engine.sprites.Sprite;

public abstract class StaticSprite implements Sprite {

    private int myXPos;
    private int myYPos;
    private String myImagePath;


    public StaticSprite(int x, int y, String imagePath){
        myXPos = x;
        myYPos = y;
        myImagePath = imagePath;
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

    @Override
    public String getImagePath() {
        return myImagePath;
    }

    @Override
    public void setImagePath(String myImagePath) {
        this.myImagePath = myImagePath;
    }
}

package ooga.engine.sprites;

import javafx.scene.shape.Rectangle;
import ooga.engine.MapGraphNode;

public abstract class DynamicSprite implements Sprite {
    private int myID;
    private int prevX;
    private int prevY;
    private int homeXPos;
    private int homeYPos;
    private int xPos;
    private int yPos;
    private Rectangle hitbox;
    private int movedist = 35;



    public DynamicSprite(int startingX, int startingY, int hitBoxWidth, int hitBoxLength, int ID) {
        myID = ID;
        xPos = startingX + 20;
        yPos = startingY + 20;
        prevX = xPos;
        prevY = yPos;
        homeXPos = xPos;
        homeYPos = yPos;

        hitbox = new Rectangle(startingX, startingY, hitBoxWidth, hitBoxLength);
    }


    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
        return yPos;
    }

    @Override
    public void setX(int newX) {
        prevX = xPos;
        xPos = newX;
    }

    @Override
    public void setY(int newY) {
        prevY = yPos;
        yPos = newY;
    }

    @Override
    public Rectangle getHitBox() {
        return hitbox;
    }


    @Override
    public abstract int getStatus();

    @Override
    public abstract void setStatus(int newStatus);

    public int getID(){
        return myID;
    }

    public void setID(int ID){
        myID = ID;
    }

    public void setHome(){
        setX(homeXPos);
        setY(homeYPos);
    }

    public void setPreviousLocation(){
        xPos = prevX;
        yPos = prevY;
    }


    public abstract void move(MapGraphNode currentLocation);

}
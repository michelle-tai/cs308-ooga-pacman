/*
Backend Ghost object. Stores state information and contains methods to update state
 */

public interface Ghost{

    /*
    setter for object movement speed
     */
    public void setSpeed(int speed);

    /*
    getter for object movement speed
     */
    public int getSpeed();

    /*
    getter for object status, indicaticating the curent powerup, or lack of powerup
     */
    public String getStatus();


    /*
    updates the x and y of the object and checks for collisions. LocationObject is a subset of all game objects which
    allows for collision checking
     */
    public void move(LocationObject neighborhood);

    /*
    getter for x coordinate of object
     */
    public int getX();

    /*
    getter for y coordinate of object
     */
    public int getY();

    /*
    setter for x coordinate
     */
    public void setX(int x);

    /*
    setter for y coordinate
     */
    public void setY(int y);



}
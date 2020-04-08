/*
Backend PacMan object. Stores state information and contains methods to update state
 */

public interface iPacMan{

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
setter for object status, indicaticating the curent powerup, or lack of powerup
 */
    public String setStatus();

    /*
    getter for pacman id for multiplayer purposes
     */
    public int getID();

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

    /*
    increment pacman's lives by 1
     */
    public void incrementLives();

    /*
    decrements pacman's lives by 1
     */
    public void decrementLives();

    /*
    getter for number of lives
     */
    public int getLivesLeft();

    /*
    resets pacman based on xml file
     */
    public void setHome(){}
}
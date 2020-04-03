/*
Backend Collectable object. Stores state information and contains methods to update state
 */

public interface Collectable{

    /*
    getter for object type, indicaticating what should happen upon object collision using xml file
     */
    public String getType();


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
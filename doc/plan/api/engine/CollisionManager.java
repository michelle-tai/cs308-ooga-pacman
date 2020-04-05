/*
This class has access to all in game objects and checks for collisions between objects and takes appropriate action upon
involved objects
 */
public interface CollisionManager{

    /* returns hashmap of counts of objects that pacman collided with */
    public HashMap<String, Integer> PacManCollisionCounter();


    /* checks entire map for collisions */
    public void collisionChecker();

}
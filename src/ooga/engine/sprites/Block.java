package ooga.engine.sprites;

import java.util.List;
import ooga.data.PathManager;

/**
 * Backend object representing a block in the backend. This class has getter methods for currently unused parameters,
 * but these were initially included with a view of versitility in mind.
 *
 * @author Olga Suchankova
 *
 */
public class Block extends StaticSprite {

    private int status;

    private static String movementType;

    public Block(int x, int y, String imagePath, PathManager pathManager) {
        super(x, y, imagePath);
        movementType = pathManager.getString(PathManager.PROPERTIES, "BlockMovement");
    }


    /**
     * This is an unused method that is supposed to return the movement type.
     * @return the movement type of the block
     */
    @Override
    public String getMovementType() {
        return movementType;
    }

    /**
     * This is a method that is supposed to set the movement type (unimplemented feature)
     * @param movementType name of the movement class
     * @param targetSprite what the sprite is chasing for aggressive movement
     */
    @Override
    public void setMovementType(String movementType, List<Sprite> targetSprite) {
        //do nothing
    }

    /**
     * getter of the sprite status
     * @return 0 - static status
     */
    @Override
    public int getStatus() {
        return 0;
    }

    /**
     * inherited abstract method that is never used
     * @param newStatus
     */
    @Override
    public void setStatus(int newStatus) {
        //do nothing
    }
}

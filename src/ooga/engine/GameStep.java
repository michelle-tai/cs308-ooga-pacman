package ooga.engine;

import ooga.data.Collision;
import ooga.data.Level;
import ooga.data.LevelManager;
import ooga.engine.sprites.DynamicSprite;
import ooga.engine.sprites.Ghost;
import ooga.engine.sprites.PacMan;
import ooga.engine.sprites.Sprite;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameStep {
    private CollisionHandler myCollisionHandler;
    private Collision collision;
    private GameContainer myContainer;
    private String myStatus;
    private LevelManager myLevelManager;
    private Integer level = 0;

    public GameStep(LevelManager levelManager) {
        myLevelManager = levelManager;
        myLevelManager.setCurrentLevel(level);
        myContainer = new GameContainer(myLevelManager.getCurrentLevel(), myLevelManager.getPathManager());
        collision = new Collision(myContainer.getPathManager());
        myCollisionHandler = new CollisionHandler(collision.getCollisionRules());
    }

    public void step(){
        if (myContainer.getCompleteStatus()) {
            level++;
            myLevelManager.setCurrentLevel(level);
            myContainer = new GameContainer(myLevelManager.getCurrentLevel(), myLevelManager.getPathManager());
        } else {
            moveSprites(myContainer.getPacMen());
            moveSprites(myContainer.getGhosts());
            myContainer.mapStep();
            checkAndExecuteCollisions(myContainer.getPacMen());
            checkAndExecuteCollisions(myContainer.getGhosts());
        }
    }

    public String getStatus(){
        return myStatus;
    }

    public GameContainer getGameContainer() {
        return myContainer;
    }

    private void checkAndExecuteCollisions(List<Sprite> objectSet) {
        for(Sprite pM : objectSet){
            int X = pM.getX();
            int Y = pM.getY();
            Set<Sprite> neighborhood = myContainer.getNeighborhood(X, Y);
            if(!neighborhood.contains(pM)){
                //todo: throw error
            }else{
                neighborhood.remove(pM);
            }
            for(Sprite obj : neighborhood){
                myCollisionHandler.checkAndExecute(pM, obj, myContainer);
            }
        }
    }

    private void moveSprites(List<Sprite> objectSet){
        for(Sprite sprite : objectSet){
            ((DynamicSprite) sprite).move(myContainer.getSpriteMapNode(sprite));
        }
    }

}

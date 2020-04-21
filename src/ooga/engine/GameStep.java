package ooga.engine;

import ooga.data.Collision;
import ooga.data.Level;
import ooga.data.LevelManager;
import ooga.engine.sprites.DynamicSprite;
import ooga.engine.sprites.Sprite;

import java.util.HashSet;
import java.util.List;

public class GameStep {
    private CollisionHandler myCollisionHandler;
    private Collision collision;
    private GameContainer myContainer;
    private String myStatus;
    private LevelManager myLevelManager;

    public GameStep(GameContainer container) {
        myLevelManager = new LevelManager();
        myContainer = container;
        collision = new Collision();
        myCollisionHandler = new CollisionHandler();
    }

    public void step(){
        checkAndExecuteCollisions(myContainer.getPacMen());
        checkAndExecuteCollisions(myContainer.getGhosts());
        moveSprites(myContainer.getPacMen());
        moveSprites(myContainer.getGhosts());

    }

    public String getStatus(){
        return myStatus;
    }

    private void checkAndExecuteCollisions(List<Sprite> objectSet) {
        for(Sprite pM : objectSet){
            int X = pM.getX();
            int Y = pM.getY();
            HashSet<Sprite> neighborhood = myContainer.getNeighborhood(X, Y);
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

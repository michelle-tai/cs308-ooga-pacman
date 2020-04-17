package ooga.engine;

import ooga.data.Collision;
import ooga.engine.sprites.PacMan;

import javax.xml.crypto.Data;
import java.util.HashSet;
import java.util.List;

public class GameStep {
    private CollisionHandler myCollisionHandler;
    private Collision collision;
    private GameContainer myContainer;
    private String myStatus;

    public GameStep(GameContainer container) {
        myContainer = container;
        collision = new Collision();
        myCollisionHandler = new CollisionHandler();
        System.out.println(collision.getCollisionRulesMap(1));
    }


    public void step(){
        checkAndExecuteCollisions(myContainer.getPacMen());
        checkAndExecuteCollisions(myContainer.getGhosts());
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



}

package ooga.engine;

import java.util.HashSet;
import java.util.List;

public class GameStep {
    private String myStatus;
    private GameContainer myContainer;
    private CollisionHandler myCollisionHandler;

    public GameStep(GameContainer container){
        myContainer = container;
        //myCollisionHandler = new CollisionHandler();
    }

    public void step(){
        checkAndExecuteCollisions(myContainer.getPacMen());
        checkAndExecuteCollisions(myContainer.getGhosts());
    }

    public String getStatus(){
        return myStatus;
    }

    private void checkAndExecuteCollisions(HashSet<Sprite> objectSet) {
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

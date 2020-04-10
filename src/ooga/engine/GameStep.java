package ooga.engine;

public class GameStep {
    private String myStatus;
    private GameContainer myContainer;

    public GameStep(GameContainer container){
        myContainer = container;
    }

    public void step(){

    }

    public String getStatus(){
        return myStatus;
    }

}

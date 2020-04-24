package ooga.engine;


import ooga.Main;
import ooga.engine.sprites.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

public class MoveHandler {
    private HashSet<String> myMoveMethods;

    public MoveHandler(){
        Class<?> c = this.getClass();
        Method[] methods = c.getDeclaredMethods();
        myMoveMethods = new HashSet<String>();
        for(int i = 0; i<methods.length; i++) {
            myMoveMethods.add(methods[i].getName());
        }
    }

    public void moveSprite(Sprite sprite) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String spriteMovement = sprite.getMovementType();
        if(myMoveMethods.contains(spriteMovement)){
            Method moveMethod
                    = MoveHandler.class.getMethod(spriteMovement, Sprite.class);

            moveMethod.invoke(this, sprite);
        }
    }

    //used in reflection
    private void standard(Sprite sprite, String direction){
        switch (direction) {
            case Main.RIGHT:
                sprite.setX(sprite.getSpeed() + sprite.getX());
                System.out.println("moved right -> new x : " + sprite.getX());
                break;
            case Main.LEFT:
                sprite.setX(-sprite.getSpeed() + sprite.getX());
                System.out.println("moved left -> new x : " + sprite.getX());
                break;
            case Main.DOWN:
                sprite.setY(sprite.getSpeed() + sprite.getY());
                System.out.println("moved down -> new y : " + sprite.getY());
                break;
            case Main.UP:
                sprite.setY(sprite.getSpeed() + sprite.getY());
                System.out.println("moved up -> new y : " + sprite.getY());
                break;
        }
    }

}

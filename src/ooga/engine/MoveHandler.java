package ooga.engine;


import ooga.Main;

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

    public void moveSprite(Sprite sprite, String direction, int distance) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String spriteMovement = sprite.getMovementType();
        if(myMoveMethods.contains(spriteMovement)){
            Method moveMethod
                    = MoveHandler.class.getMethod(spriteMovement, Sprite.class, String.class, int.class);

            moveMethod.invoke(this, sprite, direction, distance);
        }
    }

    //used in reflection
    private void standard(Sprite sprite, String direction, int distance){
        if(direction.equals(Main.MY_RESOURCES.getString("Right"))){
            sprite.setX(distance + sprite.getX());
            System.out.println("moved right -> new x : " + sprite.getX());
        }
        else if(direction.equals(Main.MY_RESOURCES.getString("Left"))){
            sprite.setX(distance - sprite.getX());
            System.out.println("moved left -> new x : " + sprite.getX());
        }
        else if(direction.equals(Main.MY_RESOURCES.getString("Down"))){
            sprite.setY(distance + sprite.getY());
            System.out.println("moved down -> new y : " + sprite.getY());
        }
        else if(direction.equals(Main.MY_RESOURCES.getString("Up"))){
            sprite.setY(distance - sprite.getY());
            System.out.println("moved up -> new y : " + sprite.getY());
        }
    }

}

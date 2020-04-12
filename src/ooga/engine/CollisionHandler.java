package ooga.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.shape.Rectangle;
import ooga.engine.sprites.*;

public class CollisionHandler {

    private HashMap<Set<String>, Set<String>> myCollisionRules;
    private char dotDeliminator = '.';
    HashSet<String> myMethodNames;


    public CollisionHandler(HashMap<Set<String>, Set<String>> collisionRules) {
        myCollisionRules = collisionRules;
        Class<?> c = this.getClass();
        Method[] methods = c.getDeclaredMethods();
        myMethodNames = new HashSet<String>();
        for(int i = 0; i<methods.length; i++){
            myMethodNames.add(methods[i].getName());
        }
    }

    private boolean checkCollision(Sprite firstSprite, Sprite secondSprite){

        Rectangle firstBox = firstSprite.getHitBox();
        Rectangle secondBox = secondSprite.getHitBox();

        double topXFirst = firstBox.getX();
        double bottomXFirst = topXFirst + firstBox.getWidth();
        double topYFirst = firstBox.getY();
        double bottomYFirst = topYFirst + firstBox.getHeight();

        double topXSecond = secondBox.getX();
        double bottomXSecond = topXSecond + secondBox.getWidth();
        double topYSecond = secondBox.getY();
        double bottomYSecond = topYSecond + secondBox.getHeight();


        if (topXFirst > bottomXSecond // R1 is right to R2
                || bottomXFirst < topXSecond // R1 is left to R2
                || topYFirst < bottomYSecond // R1 is above R2
                || bottomYFirst > topYSecond) { // R1 is below R1
            return false;
        }
        return true;
    }

    public void checkAndExecute(Sprite firstSprite, Sprite secondSprite, GameContainer container){
        try {
            if (checkCollision(firstSprite, secondSprite)) {
                String firstSpriteName = simpleStringName(firstSprite);
                String secondSpriteName = simpleStringName(secondSprite);

                HashSet<String> collisionObjects = new HashSet<String>();
                collisionObjects.add(firstSpriteName);
                collisionObjects.add(secondSpriteName);

                for (String m : myCollisionRules.get(collisionObjects)) {
                    if (myMethodNames.contains(m)) {
                        Method collisionMethod
                                = CollisionHandler.class.getMethod(m, Sprite.class, Sprite.class, GameContainer.class);

                        collisionMethod.invoke(this, firstSprite, secondSprite, container);
                    }

                }
            }
        } catch(NoSuchMethodException e){
            //do nothing
        } catch(InvocationTargetException e){
            //do nothing
        } catch(IllegalAccessException e){
            //do nothing
        }

    }

    private String simpleStringName(Sprite sprite){
        String spriteClassName = sprite.getClass().getName();
        int nameIndex = spriteClassName.lastIndexOf(dotDeliminator);
        spriteClassName = spriteClassName.substring(nameIndex + 1);

        if(sprite instanceof PacMan){ //potential generalize to be character ie (ghost or pacman)
            PacMan pacManObj = (PacMan) sprite;
            return spriteClassName + pacManObj.getStatus();
        }else{
            return spriteClassName;
        }
    }

    //used in reflection
    private void destroyCoin(Sprite firstSprite, Sprite secondSprite, GameContainer container){
        if(firstSprite instanceof Coin){
            container.remove(firstSprite);
        }
        if(secondSprite instanceof Coin){
            container.remove(secondSprite);
        }
    }

}

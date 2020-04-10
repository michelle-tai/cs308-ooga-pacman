package ooga.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import ooga.engine.sprites.Coin;
import ooga.engine.sprites.PacMan;

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

    public void executeCollision(Sprite firstSprite, Sprite secondSprite, Set<Sprite> gameObjects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String firstSpriteName = simpleStringName(firstSprite);
        String secondSpriteName = simpleStringName(secondSprite);

        HashSet<String> collisionObjects = new HashSet<String>();
        collisionObjects.add(firstSpriteName);
        collisionObjects.add(secondSpriteName);

        for(String m: myCollisionRules.get(collisionObjects)){
            if(myMethodNames.contains(m)){
                Method collisionMethod
                        = CollisionHandler.class.getMethod(m, Sprite.class, Sprite.class, Set.class);

                collisionMethod.invoke(this, firstSprite, secondSprite, gameObjects);
            }

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
    private void destroyCoin(Sprite firstSprite, Sprite secondSprite, Set gameObjects){
        if(firstSprite instanceof Coin){
            gameObjects.remove(firstSprite);
        }
        if(secondSprite instanceof Coin){
            gameObjects.remove(secondSprite);
        }
    }





}

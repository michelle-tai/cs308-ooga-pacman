package ooga.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import ooga.engine.sprites.*;


/**
 * This is the CollisionHandler class, an instance of which will be held in the GameStep class, which is then called
 * several times for each step in the game loop, checking to see if a collision between two sprites occurred, and calling
 * apropriate methods to modify the statuses and GameContainer class in line with game rules. This class uses reflection
 * in order to call appropriate collision methods, all of which are contained in this class.
 * @author Michelle Tai
 * @author Olga
 */

public class CollisionHandler {

    private HashMap<Pair<String, String>, Set<String>> myCollisionRules;
    private char dotDeliminator = '.';
    private HashSet<String> myMethodNames;
    private Method[] myMethods;
    private int count;


    /*
    CollisionHandler constructor, currently requires a Hashmap specifying collisionRules. Also creates a hashset of all the
    method names in this class, in order to check against them so the NoSuchMethod exception will never be encountered with
    proper use.
     */
    public CollisionHandler() {
        mapTester();
        //myCollisionRules = collisionRules;
        Class<?> c = this.getClass();

        myMethods = c.getDeclaredMethods();
        for(int i = 0; i < myMethods.length; i++){
            System.out.println(myMethods[i]);
        }
        myMethodNames = new HashSet<String>();
        for (int i = 0; i < myMethods.length; i++) {
            myMethodNames.add(myMethods[i].getName());
        }
    }


    private void mapTester(){
        myCollisionRules = new HashMap<>();
        HashSet<String> methodSet = new HashSet<String>();
        methodSet.add("directMovement");
        //methodSet.add("private void ooga.engine.CollisionHandler.directMovement(ooga.engine.Sprite,ooga.engine.GameContainer,ooga.engine.Sprite)");
        myCollisionRules.put(new Pair<String, String>("PacMan0", "Block"), methodSet);
//        methodSet = new HashSet<String>();
//        methodSet.add("destroy");
//        myCollisionRules.put(new Pair<String, String>("PacMan0", "Coin"), methodSet);
        methodSet = new HashSet<String>();
        methodSet.add("destroy");
        myCollisionRules.put(new Pair<String, String>("Coin", "PacMan0"), methodSet);


    }

    /*
    private method which checks if the hitboxes of two sprites overlap, and returns a boolean value true if they do
    and false if they dont. It is used by the public checkAndExecuteMethod to check if a collision occurred.
     */
    private boolean checkCollision(Sprite firstSprite, Sprite secondSprite) {

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
            return true;
        }
            return true;
        }

    /*
    Method that uses reflection to call the appropriate methods that modify sprite status and the game status as required.
     */
    public void checkAndExecute(Sprite firstSprite, Sprite secondSprite, GameContainer container) {

        if (checkCollision(firstSprite, secondSprite)) {
            String firstSpriteName = simpleStringName(firstSprite);
            String secondSpriteName = simpleStringName(secondSprite);

            Pair<String, String> collisionObjects1 = new Pair<String, String>(firstSpriteName, secondSpriteName);
            Pair<String, String> collisionObjects2 = new Pair<String, String>(secondSpriteName, firstSpriteName);

            invokeMethods(collisionObjects1, container, firstSprite, secondSprite);
            invokeMethods(collisionObjects2, container, secondSprite, firstSprite);


        }
    }

    private void invokeMethods(Pair<String, String> collisionObjects, GameContainer container, Sprite firstSprite, Sprite secondSprite){

        try {
            Set<String> methodSet = myCollisionRules.get(collisionObjects);
            if(null != methodSet){
                for (String mName : methodSet) {
                   // if (myMethodNames.contains(m)) {
                    for(Method m : myMethods){
                        if(m.getName().startsWith(mName)){

                            m.invoke(this, firstSprite, container, secondSprite);
                        }

//                        Method collisionMethod
//                                = this.getClass().getMethod(mName, Sprite.class, GameContainer.class, Sprite.class);

//                        collisionMethod.invoke(this, firstSprite, container, secondSprite);
                    }

                   // }
            }

            }
        //}catch (NoSuchMethodException e) {
        //do nothing
        //    System.out.println("nosuch");
        } catch (InvocationTargetException e) {
            System.out.println("invocation");
        //do nothing
        } catch (IllegalAccessException e) {
            System.out.println("illegalAccess");
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
    private void destroy(Sprite sprite, GameContainer container, Sprite actor){
//        count++;
//        System.out.println(count);
//        System.out.println("destroyMethod");
        container.remove(sprite);

    }

    private void respawn(Sprite sprite, GameContainer container, Sprite actor){
        if(sprite instanceof PacMan){
            PacMan pM = (PacMan) sprite;
            pM.setHome();
        }
        if(sprite instanceof Ghost){
            Ghost g = (Ghost) sprite;
            g.setHome();
        }
    }

    private void decrementLives(Sprite sprite, GameContainer container, Sprite actor){
        if(sprite instanceof PacMan){
            PacMan pM = (PacMan) sprite;
            pM.decrementLives();
            if(pM.getLivesLeft() == 0){
                container.remove(sprite);
            }
        }
    }

    private void incrementPoints(Sprite sprite, GameContainer container, Sprite actor){
        if(sprite instanceof PacMan){
            PacMan pM = (PacMan) sprite;
            if(actor instanceof Coin){
                Coin c = (Coin) sprite;
                pM.addPoints(c.getPoints());
            }
        }
    }

    private void directMovement(Sprite sprite, GameContainer container, Sprite actor){
      //  System.out.println("directMovementMethod");
//        if(sprite instanceof PacMan){
//            PacMan pM = (PacMan) sprite;
//            pM.setPreviousLocation();
//        }
//        if(sprite instanceof Ghost){
//            Ghost g = (Ghost) sprite;
//            g.setPreviousLocation();
//        }
    }


    private void setStatus(Sprite sprite, GameContainer container, Sprite actor){
        sprite.setStatus(actor.getStatus());
    }
}

package dataTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import ooga.data.Collision;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testCollisionData {
  private Collision myCollision;

  @BeforeEach
  void setup () {
    myCollision = new Collision("test_resources/collisionTest.xml");
  }

  @Test
  void testMultipleActions() {
    Set<String> set1 = new HashSet<>();
    set1.add("destroyPacMan");
    set1.add("decrementLives");

    Set<String> actions1 = myCollision.getActions(1, "PacMan", "Ghost");
    assertEquals(set1, actions1);

    Set<String> set2 = new HashSet<>();
    set2.add("destroyPacMan");
    set2.add("decrementLives");
    set2.add("test");

    Set<String> actions2 = myCollision.getActions(2, "PacMan", "Ghost");
    assertEquals(set2, actions2);
  }

  @Test
  void testSingleAction() {
    Set<String> set1 = new HashSet<>();
    set1.add("Collect");

    Set<String> actions1 = myCollision.getActions(1, "PacMan", "Coin");
    assertEquals(set1, actions1);
  }

  @Test
  void testSpriteKeys() {
    Collision collision = new Collision("test_resources/collisionTest1.xml");
    Set<String> set1 = new HashSet<>();
    set1.add("test1");
    Set<String> set2 = new HashSet<>();
    set2.add("test2");
    Set<String> set3 = new HashSet<>();
    set3.add("test3");
    Set<String> set4 = new HashSet<>();
    set4.add("test4");
    Set<String> set5 = new HashSet<>();
    set5.add("test5");
    Set<String> set6 = new HashSet<>();
    set6.add("test6");

    Set<String> actual1 =  collision.getActions(1, "PacMan", "Ghost");
    Set<String> actual2 =  collision.getActions(1, "PacMan", "Coin");
    Set<String> actual3 =  collision.getActions(1, "Ghost", "PacMan");
    Set<String> actual4 =  collision.getActions(2, "PacMan", "Ghost");
    Set<String> actual5 =  collision.getActions(2, "PacMan", "Coin");
    Set<String> actual6 =  collision.getActions(2, "Ghost", "PacMan");

    assertEquals(set1, actual1);
    assertEquals(set2, actual2);
    assertEquals(set3, actual3);
    assertEquals(set4, actual4);
    assertEquals(set5, actual5);
    assertEquals(set6, actual6);
  }
}

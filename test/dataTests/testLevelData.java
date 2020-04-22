package dataTests;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import ooga.data.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testLevelData {

  private File levelFile1;

  @BeforeEach
  void beforeEach() {
    levelFile1 = new File("test_resources/levelTest.txt");
  }

  @Test
  void testFilePairs () {
    List<Pair<Integer, Integer>> pairs = new ArrayList<>();

    pairs.add(new Pair<>(0,0));
    pairs.add(new Pair<>(0,1));
    pairs.add(new Pair<>(0,2));
    pairs.add(new Pair<>(0,3));

    pairs.add(new Pair<>(1,0));
    pairs.add(new Pair<>(1,1));
    pairs.add(new Pair<>(1,2));
    pairs.add(new Pair<>(1,3));

    pairs.add(new Pair<>(2,0));
    pairs.add(new Pair<>(2,1));
    pairs.add(new Pair<>(2,2));
    pairs.add(new Pair<>(2,3));

    pairs.add(new Pair<>(3,0));
    pairs.add(new Pair<>(3,1));
    pairs.add(new Pair<>(3,2));
    pairs.add(new Pair<>(3,3));

//    Level level = new Level(levelFile1, 10);
//    Map<Pair<Integer, Integer>, Sprite> levelMap = level.getModelMap();
//
//    for (Pair<Integer, Integer> p: pairs){
//      assertTrue(levelMap.containsKey(p));
//    }
  }
}

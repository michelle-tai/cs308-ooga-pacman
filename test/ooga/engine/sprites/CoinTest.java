package ooga.engine.sprites;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import ooga.data.PathManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoinTest {
  private Coin coin1;
  private Coin coin2;
  private PathManager myPathManager1;
  private PathManager myPathManager2;


  @BeforeEach
  void setUp() {
    myPathManager1 = new PathManager("testGameForCoin1");
    coin1 = new Coin(0, 0, 0, 0,
        myPathManager1.getFilePath(PathManager.FOODIMAGES, 2), myPathManager1);

    myPathManager2 = new PathManager("testGameForCoin2");
    coin2 = new Coin(50, 50, 2, 1,
        myPathManager1.getFilePath(PathManager.FOODIMAGES, 1), myPathManager2);
  }

  @Test
  void getMovementTypeTest() {
    assertEquals(coin1.getMovementType(), "none");
    assertEquals(coin2.getMovementType(), "standard");
  }

  @Test
  void setMovementTypeTest() {
    coin1.setMovementType("running away", new ArrayList<Sprite>());
    assertEquals(coin1.getMovementType(), "running away");
    coin2.setMovementType("crying", new ArrayList<Sprite>());
    assertEquals(coin2.getMovementType(), "crying");
  }


  @Test
  void checkActiveTest() {
    assertEquals(coin1.checkActive(), coin2.checkActive());
    coin1.setActive();
    assertEquals(coin1.checkActive(), false);
    assertEquals(coin2.checkActive(), true);
  }

  @Test
  void setActiveTest() {
    coin1.setActive();
    assertEquals(coin1.checkActive(), false);
    coin2.setActive();
    coin2.setActive();
    assertEquals(coin2.checkActive(), true);
  }

  @Test
  void getMyIDTest() {
    assertEquals(coin1.getMyID(), 0);
    assertEquals(coin2.getMyID(), 1);
  }

  @Test
  void getStatusTest() {
    assertEquals(coin1.getStatus(), 0);
    assertEquals(coin2.getStatus(), 2);

  }

  @Test
  void getPointsTest() {
    assertEquals(coin1.getPoints(), 1);
    assertEquals(coin2.getPoints(), 3);
  }
}
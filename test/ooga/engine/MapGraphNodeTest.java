package ooga.engine;

import ooga.data.PathManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapGraphNodeTest {

  @BeforeEach
  void setup () {
    PathManager pathManager = new PathManager("testGameForCoin1"); //path manager is now this, and mapnode will use the path that goes to gameproperties
  }

  @Test
  void getXPosTest() {
  }

  @Test
  void getYPosTest() {
  }

  @Test
  void addNeighborTest() {
  }

  @Test
  void getTopNeighborTest() {
  }

  @Test
  void getBottomNeighborTest() {
  }

  @Test
  void getLeftNeighborTest() {
  }

  @Test
  void getRightNeighborTest() {
  }
}
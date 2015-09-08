package lasersharks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for testing level.
 * 
 * @author Youri
 *
 */
public class LevelTest {
  private static final int TEST_WAIT_TIME = 10000;
  private Level level1;

  /**
   * Setup before test.
   */
  @Before
  public void setup() {
    level1 = new Level();
  }

  /**
   * Get info from level.
   * 
   * @param level
   *          the level to retrieve from.
   * @return String containing info.
   */
  public String getInfo(Level level) {
    List<Fish> list = level.getFishCon().getNextCycleInformation();
    String res = "";
    for (int i = 0; i < list.size(); i++) {
      res = res + list.get(i).getClass().getName() + " at pos: "
          + list.get(i).getPosition().toString();
    }
    return res;
  }

  /**
   * Gernal level test.
   */
  @Test
  public void test() {
    System.out.println(getInfo(level1));
    level1.start();
    try {
      Thread.sleep(TEST_WAIT_TIME);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(getInfo(level1));
  }

}

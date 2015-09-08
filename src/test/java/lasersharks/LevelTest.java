package lasersharks;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LevelTest {
  Level level1;
  
  @Before
  public void setup() {
    level1 = new Level();
  }
  
  /**
   * prints information of what fishes are on the screen at the start.
   * after 10 seconds of waiting, this is repeated.
   * some fishes should have been spawned and moved from the sides of the screen.
   * @param level
   * @return
   */
  public String getInfo(Level level) {
    List<Fish> list = level.getFishCon().getNextCycleInformation();
    StringBuilder result = new StringBuilder();
    String res = "";
    for (int i = 0; i < list.size(); i++) {
      result.append(list.get(i).getClass().getName() + " at pos: " + list.get(i).getPosition().toString());
    }
     return result.toString();
  }
  
  @Test
  public void test() {
    System.out.println(getInfo(level1));
    level1.start();
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(getInfo(level1));
  }
  
}

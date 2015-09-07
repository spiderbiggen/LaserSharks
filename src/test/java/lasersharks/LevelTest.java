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
  
  public String getInfo(Level level) {
    List<Fish> list = level.getFishCon().getNextCycleInformation();
    String res = "";
    for(int i=0;i<list.size();i++){
      res = res+list.get(i).getClass().getName()+" at pos: "+list.get(i).getPosition().toString();
    }
     return res;
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

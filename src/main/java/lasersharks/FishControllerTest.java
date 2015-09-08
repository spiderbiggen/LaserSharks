package lasersharks;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * the test class for the FishController class.
 * @author Sytze
 */
public class FishControllerTest {

  FishController fishCon;
  
  /**
   * Sets up a fishcontroller object.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    fishCon = new FishController();
  }

  /**
   * test the setter for the setRng() method
   */
  @Test
  public void testSetRng() {
    fishCon.setRng(new Random(10));
  }

  /**
   * test the function to add fish to the cycle.
   */
  @Test
  public void testAddFish() {
    FishBot fishBot = new FishBot(new Position(10,10),10,10,Direction.East);
    assertFalse(fishCon.getNextCycleInformation().contains(fishBot));
    fishCon.addFish(fishBot);
    assertTrue(fishCon.getNextCycleInformation().contains(fishBot));
  }

}

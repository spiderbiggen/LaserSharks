package lasersharks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * the test class for the FishController class.
 * 
 * @author Sytze
 */
public class FishControllerTest {

  private static final int SPEED = 10;
  private static final int SEIZE = 10;
  private static final int POSITION_Y = 10;
  private static final int POSITION_X = 10;
  private static final int RANDOM_SEED = 10;
  private FishController fishCon;

  /**
   * Sets up a fishcontroller object.
   * 
   * @throws Exception
   *           if an error occurs.
   */
  @Before
  public void setUp() throws Exception {
    fishCon = new FishController();
  }

  /**
   * test the setter for the setRng() method.
   */
  @Test
  public void testSetRng() {
    fishCon.setRng(new Random(RANDOM_SEED));
  }

  /**
   * test the function to add fish to the cycle.
   */
  @Test
  public void testAddFish() {
    FishBot fishBot = new FishBot(
        new Position(POSITION_X, POSITION_Y), 
        SEIZE, 
        SPEED, 
        Direction.East
    );
    assertFalse(fishCon.getNextCycleInformation().contains(fishBot));
    fishCon.addFish(fishBot);
    assertTrue(fishCon.getNextCycleInformation().contains(fishBot));
  }

}

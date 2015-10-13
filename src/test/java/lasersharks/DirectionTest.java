package lasersharks;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * The test for the direction Enum.
 * 
 * @author Youri
 *
 */
@RunWith(Parameterized.class)
public class DirectionTest {

  private int expectedDeltaX;
  private int expectedDeltaY;
  private Direction direction;

  /**
   * Constructor witch is fed expected values.
   * 
   * @param dir
   *          input direction for witch to run the test.
   * @param expecDeltaX
   *          expected X
   * @param expecDeltaY
   *          expected Y
   */
  public DirectionTest(Direction dir, int expecDeltaX, int expecDeltaY) {
    this.expectedDeltaX = expecDeltaX;
    this.expectedDeltaY = expecDeltaY;
    this.direction = dir;
  }

  /**
   * Test for delta X.
   */
  @Test
  public void testDeltaX() {
    assertEquals(this.direction.getDeltaX(), this.expectedDeltaX);
  }

  /**
   * Test for delta Y.
   */
  @Test
  public void testDeltaY() {
    assertEquals(this.direction.getDeltaY(), this.expectedDeltaY);
  }

  /**
   * input parameters for the tests.
   * 
   * @return Array of object containing input and expected data.
   */
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays
        .asList(new Object[][] {

        { Direction.South, 0, 1 }, { Direction.SouthEast, 1, 1 }, { Direction.East, 1, 0 },
            { Direction.NorthEast, 1, -1 }, { Direction.North, 0, -1 },
            { Direction.NorthWest, -1, -1 }, { Direction.West, -1, 0 },
            { Direction.SouthWest, -1, 1 }, { Direction.None, 0, 0 } });
  }
}

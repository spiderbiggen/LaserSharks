/**
 *
 */
package lasersharks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author SEM Group 27
 */
@RunWith(Parameterized.class)
public class UpdatePositionTest {

  private final Direction direction;
  private final Position expectedPosition;
  private Position position;

  /**
   * Constructor for parameterized Test.
   *
   * @param direction DIRECTION to update to
   * @param expectedX expected X result from running the method
   * @param expectedY expected Y from running the method
   */
  public UpdatePositionTest(final Direction direction, final int expectedX, final int expectedY) {
    this.direction = direction;
    this.expectedPosition = new Position(expectedX, expectedY);
  }

  /**
   * Parameterized test for {@link lasersharks.Position#updatePosition(Direction, double, double)
   * }  }.
   *
   * @return list of parameters
   */
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] { { Direction.South, 0, 1 }, { Direction.SouthEast, 1, 1 },
        { Direction.East, 1, 0 }, { Direction.NorthEast, 1, -1 }, { Direction.North, 0, -1 },
        { Direction.NorthWest, -1, -1 }, { Direction.West, -1, 0 }, { Direction.SouthWest, -1, 1 },
        { Direction.None, 0, 0 }, { null, 0, 0 } });
  }

  /**
   * Setup the test scenario.
   */
  @Before
  public void setUp() {
    position = new Position(0, 0);
  }

  /**
   * Test methods for {@link lasersharks.Position#updatePosition(Direction, double, double) }.
   */

  @Test
  public void testUpdatePosition() {
    position.updatePosition(direction, 1, 0);
    assertEquals(expectedPosition, position);
  }

}

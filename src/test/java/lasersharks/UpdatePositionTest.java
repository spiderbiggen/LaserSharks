/**
 * 
 */
package lasersharks;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author Stefan
 *
 */
@RunWith(Parameterized.class)
public class UpdatePositionTest {

  private Position position;
  private Direction direction;
  private int speed;
  private Position expectedPosition;
  private boolean expectedOnScreen;

  /**
   * Setup the test scenario.
   */
  @Before
  public void setUp() {
    position = new Position(0, 0);
  }

  /**
   * Constructor for parameterized Test.
   * 
   * @param direction
   *          direction to update to
   * @param speed
   *          speed to update with
   * @param expectedX
   *          expected X result from running the method
   * @param expectedY
   *          expected Y from running the method
   * @param expectedOnScreen
   *          the expected return value
   */
  public UpdatePositionTest(Direction direction, int speed, int expectedX, int expectedY,
      boolean expectedOnScreen) {
    this.direction = direction;
    this.speed = speed;
    this.expectedPosition = new Position(expectedX, expectedY);
    this.expectedOnScreen = expectedOnScreen;
  }

  /**
   * Parameterized test for {@link laserSharks.Position#updatePosition(Direction) }.
   * 
   * @return list of parameters
   */
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] { { Direction.North, 1, 0, 1 , true},
        { Direction.NorthEast, 1, 1, 1 , true}, { Direction.East, -1, -1, 0 , false},
        { Direction.SouthEast, 1, 1, -1 , false}, { Direction.South, -1, 0, 1 , true },
        { Direction.SouthWest, 1, -1, -1 , false}, { Direction.West, -1, 1, 0 , true},
        { Direction.NorthWest, -1, 1, -1 , false}, { Direction.None, 1, 0, 0 , true} });
  }

  /**
   * Test methods for {@link laserSharks.Position#updatePosition(Direction) }.
   */
  @Test
  public void testUpdatePosition() {
    System.out.println("Parameterized direction is : " + direction.toString());
    assertEquals(position.updatePosition(direction, speed, 0), expectedOnScreen);
    assertEquals(expectedPosition, position);
  }

}

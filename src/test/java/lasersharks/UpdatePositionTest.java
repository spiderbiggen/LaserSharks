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
  private int expectedX;
  private int expectedY;

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
   * @param expectedX
   *          expected X result from running the method
   * @param expectedY
   *          expected Y from running the method
   */
  public UpdatePositionTest(Direction direction, int expectedX, int expectedY) {
    this.direction = direction;
    this.expectedX = expectedX;
    this.expectedY = expectedY;
  }

  /**
   * Parameterized test for {@link laserSharks.Position#updatePosition(Direction) }.
   * 
   * @return list of parameters
   */
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] { { Direction.North, 0, 1 }, { Direction.NorthEast, 1, 1 },
        { Direction.East, 1, 0 }, { Direction.SouthEast, 1, -1 }, { Direction.South, 0, -1 },
        { Direction.SouthWest, -1, -1 }, { Direction.West, -1, 0 }, { Direction.NorthWest, -1, 1 },
        { Direction.None, 0, 0 } });
  }

  /**
   * Test methods for {@link laserSharks.Position#updatePosition(Direction) }.
   */
  
  @Test
  public void testUpdatePosition() {
    System.out.println("Parameterized direction is : " + direction.toString());
    //TODO: fix these tests, as they make now no sense
 //   position.adjustPos(deltaX, deltaY);
 //   assertEquals(expectedX, position.getPosX());
 //   assertEquals(expectedY, position.getPosY());
  }
  

}

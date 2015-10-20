/**
 * 
 */
package lasersharks;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import lasersharks.controllers.Options;

/**
 * Parameterized test for the {@link Position#onScreen(double)} method.
 * 
 * @author SEMGroup27
 */
@RunWith(Parameterized.class)
public class OnScreenTest {

  private Position position;
  private boolean expectedBool;

  /**
   * Setup the test scenario.
   */
  @After
  public void tearDown() {
    this.position = null;
  }

  /**
   * Constructor for parameterized Test.
   * 
   * @param posX
   *          position x coordinate
   * @param posY
   *          position y coordinate
   * @param expectedBool
   *          expected return value from running the method
   */
  public OnScreenTest(double posX, double posY, boolean expectedBool) {
    this.position = new Position(posX, posY);
    this.expectedBool = expectedBool;
  }

  /**
   * Parameterized test for {@link laserSharks.Position#updatePosition(Direction) }.
   * 
   * @return list of parameters
   */
  @Parameters
  public static Collection<Object[]> data() {
    double width = Options.getGlobalWidth();
    double height = Options.getGlobalHeight();
    return Arrays.asList(new Object[][] { { 0, 0, true }, { -1, 0, false }, { 0, -1, false },
        { -1, -1, false }, { width, 0, true }, { width + 1, 0, false }, { width, -1, false },
        { width + 1, -1, false }, { 0, height, true }, { -1, height, false },
        { 0, height + 1, false }, { -1, height + 1, false }, { width, height, true },
        { width + 1, height, false }, { width, height + 1, false },
        { width + 1, height + 1, false } });
  }

  /**
   * Test method for {@link lasersharks.Position#onScreen(int)}.
   */
  @Test
  public void testOnScreen() {
    assertEquals(expectedBool, position.onScreen(0));
  }

}

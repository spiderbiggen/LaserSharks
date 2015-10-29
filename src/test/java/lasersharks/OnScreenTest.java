/**
 *
 */
package lasersharks;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Parametrized test for the {@link Position#onScreen(double)} method.
 *
 * @author SEMGroup27
 */
@RunWith(Parameterized.class)
public class OnScreenTest {

  private static final int SIZE = 100;
  private final Position position;
  private final boolean expectedBool;

  /**
   * Constructor for parametrized Test.
   *
   * @param posX
   *          position x coordinate
   * @param posY
   *          position y coordinate
   * @param expectedBool
   *          expected return value from running the method
   */
  public OnScreenTest(final double posX, final double posY, final boolean expectedBool) {
    this.position = new Position(posX, posY);
    this.expectedBool = expectedBool;
  }

  /**
   * Parametrized test for {@link lasersharks.Position#updatePosition(Direction, double, double)}.
   *
   * @return list of parameters
   */
  @Parameters
  public static Collection<Object[]> data() {
    Options.setGlobalHeight(SIZE);
    Options.setGlobalWidth(SIZE);

    return Arrays.asList(new Object[][] {
        { 0, 0, true },
        { -1, 0, false },
        { 0, -1, false },
        { -1, -1, false },
        { Position.getWidthPanel(), 0, true },
        { Position.getWidthPanel() + 1, 0, false },
        { Position.getWidthPanel(), -1, false },
        { Position.getWidthPanel() + 1, -1, false },
        { 0, Position.getHeightPanel(), true },
        { -1, Position.getHeightPanel(), false },
        { 0, Position.getHeightPanel() + 1, false },
        { -1, Position.getHeightPanel() + 1, false },
        { Position.getWidthPanel(), Position.getHeightPanel(), true },
        { Position.getWidthPanel() + 1, Position.getHeightPanel(), false },
        { Position.getWidthPanel(), Position.getHeightPanel() + 1, false },
        { Position.getWidthPanel() + 1, Position.getHeightPanel() + 1, false } });
  }

  /**
   * Setup the test scenario.
   */
  @After
  public void tearDown() {
    Options.destroyInstance();
  }

  /**
   * Test method for {@link lasersharks.Position#onScreen(double)}.
   */
  @Test
  public void testOnScreen() {
    Options.setGlobalHeight(SIZE);
    Options.setGlobalWidth(SIZE);
    assertEquals(expectedBool, position.onScreen(0));
  }

}

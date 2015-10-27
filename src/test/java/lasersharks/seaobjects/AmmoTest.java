/**
 *
 */
package lasersharks.seaobjects;

import lasersharks.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author SEMGroup27
 */
public class AmmoTest {

  private static final float SIZE = 40;
  private Ammo ammo;

  /**
   * Sets up the instances required for the tests.
   */
  @Before
  public void setUp() {
    Position pos = new Position(0, 0);
    ammo = new Ammo(pos, SIZE);
  }

  /**
   * Test method for {@link lasersharks.seaobjects.Ammo#getImageResource()}.
   */
  @Test
  public void testGetImageResource() {
    assertEquals("battery.png", ammo.getImageResource());
  }

  /**
   * Test method for {@link lasersharks.seaobjects.Ammo#getWidthScale()}.
   */
  @Test
  public void testGetWidthScale() {
    final int imgHeight = 228;
    final int imgWidth = 300;
    final double e = 0.001;
    assertEquals((double) imgWidth / (double) imgHeight, ammo.getWidthScale(), e);
  }

}

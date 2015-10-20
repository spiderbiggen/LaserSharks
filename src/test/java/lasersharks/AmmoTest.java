/**
 * 
 */
package lasersharks;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author SEMGroup27
 *
 */
public class AmmoTest {

  private Position pos;
  private Ammo ammo;

  private final float size = 40;

  /**
   * Sets up the instances required for the tests.
   */
  @Before
  public void setUp() {
    pos = new Position(0, 0);
    ammo = new Ammo(pos, size);
  }

  /**
   * Test method for {@link lasersharks.Ammo#getImageResource()}.
   */
  @Test
  public void testGetImageResource() {
    assertEquals("battery.png", ammo.getImageResource());
  }

  /**
   * Test method for {@link lasersharks.Ammo#getWidthScale()}.
   */
  @Test
  public void testGetWidthScale() {
    final int imgHeight = 228;
    final int imgWidth = 300;
    final double e = 0.001;
    assertEquals((double) imgWidth / (double) imgHeight, ammo.getWidthScale(), e);
  }

  /**
   * Test method for {@link lasersharks.Ammo#getPickupAmount()}.
   */
  @Test
  public void testGetAmount() {
    final int amount = 10;
    assertEquals(amount, ammo.getPickupAmount());
  }

}

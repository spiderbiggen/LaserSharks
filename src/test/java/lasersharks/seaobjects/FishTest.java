package lasersharks.seaobjects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The test for the enemy1 class.
 *
 * @author SEMGroup27
 */
public class FishTest extends SeaObjectTest {
  private static final String IMAGE = "enemy-1.png";

  /**
   * Run the test on correct item.
   */
  @Before
  public void setUp() {
    this.fish1 = new Fish(
        IMAGE, 1, this.posOnScreen, SIZE, (double) SPEED, DIRECTION
    );
  }

  /**
   * See if proper image is being used.
   */
  @Test
  public void testImage() {
    assertEquals(IMAGE, this.fish1.getImageResource());
  }
}
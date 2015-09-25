package lasershark.enemies;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import lasersharks.FishBotTest;
import lasersharks.enemies.Enemy10;

/**
 * The test for the enemy8 class.
 * 
 * @author Youri
 *
 */
public class Enemy10Test extends FishBotTest {
  private static final String IMAGE = "enemy-10.png";
  /**
   * Run the test on correct item.
   */
  @Before
  public void setUp() {
    this.fish1 = new Enemy10(
        this.posOnScreen,
        Float.valueOf(this.size),
        Double.valueOf(this.speed),
        this.direction
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
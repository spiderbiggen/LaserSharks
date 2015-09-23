package lasershark.enemies;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import lasersharks.FishBotTest;
import lasersharks.enemies.Enemy4;

/**
 * The test for the enemy1 class.
 * 
 * @author Youri
 *
 */
public class Enemy4Test extends FishBotTest {
  private static final String IMAGE = "enemy-4.png";
  /**
   * Run the test on correct item.
   */
  @Before
  public void setUp() {
    this.fish1 = new Enemy4(
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
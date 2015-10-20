package lasersharks;

import org.junit.Test;

import lasersharks.seaObjects.LaserBullet;

/**
 * Test class for the LaserBullet class.
 * @author sytze
 *
 */
public class LaserBulletTest {

  private static final int TEST_SIZE = 10;
  private static final double TEST_SPEED = 20;
  
  /**
   * calls the constructor and checks if the size is set correctly.
   */
  @Test
  public void testSize() {
    LaserBullet laserBullet = new LaserBullet(
        Position.middlePosition(), 
        TEST_SIZE, 
        TEST_SPEED, 
        Direction.East);
    assert (TEST_SIZE == laserBullet.getSize());
  }

}

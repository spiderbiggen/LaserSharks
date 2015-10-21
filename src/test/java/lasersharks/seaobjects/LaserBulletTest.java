package lasersharks.seaobjects;

import org.junit.Test;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.seaobjects.LaserBullet;

/**
 * Test class for the LaserBullet class.
 * 
 * @author SEMGroup27
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
    LaserBullet laserBullet = new LaserBullet(Position.middlePosition(), TEST_SIZE, TEST_SPEED,
        Direction.East);
    assert (TEST_SIZE == laserBullet.getSize());
  }

}

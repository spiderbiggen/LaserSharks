package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Test class for LaserFactory.
 * 
 * @author SEMGroup27
 *
 */
public class LaserFactoryTest {

  private LaserFactory laserFactory;

  /**
   * Setup a new instance of the factory for every test.
   */
  @Before
  public void setUp() {
    laserFactory = new LaserFactory();
  }

  /**
   * Test for creating a LaserBullet.
   */
  @Test
  public void testLaserCreation() {
    final LaserShark shark = Mockito.mock(LaserShark.class);
    Mockito.when(shark.getLastHorizontalDirection()).thenReturn(Direction.East);
    Mockito.when(shark.getPosition()).thenReturn(Position.middlePosition());
    final LaserBullet laserBulletCreated = laserFactory.createLaser(shark);
    assertEquals(laserBulletCreated.getPosition(), Position.middlePosition());
  }

  /**
   * Test for creating a LaserBullet.
   */
  @Test
  public void testLaserCreation2() {
    final LaserShark shark = Mockito.mock(LaserShark.class);
    Mockito.when(shark.getLastHorizontalDirection()).thenReturn(Direction.West);
    Mockito.when(shark.getPosition()).thenReturn(Position.middlePosition());
    final LaserBullet laserBulletCreated = laserFactory.createLaser(shark);
    assertEquals(laserBulletCreated.getPosition(), Position.middlePosition());
  }

}

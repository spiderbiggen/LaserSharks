package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import lasersharks.enemies.Enemy;

/**
 * Class for testing Lazershark object.
 * 
 * @author SEMGroup27
 *
 */
public class LaserSharkTest extends SeaObjectTest {
  /**
   * Container for test object.
   */
  private LaserShark laserShark;
  private static final int DEFAULT_SHARK_AMMO = 10;
  private static final int EXPECTED_AFTER_EATING_AMMO = 10;
  private static final int DEFAULT_SHARK_SIZE = 30;
  private static final int EXPECTED_AFTER_EATING_SHARK_SIZE = 34;

  /**
   * Set up which is used before the tests.
   */
  @Before
  public void setUp() {
    fish1 = new LaserShark(posOnScreen, size, speed, direction);
    laserShark = new LaserShark(posOnScreen, size, speed, direction);
  }

  /**
   * When the laser sharks eats a fish, it grows.
   */
  @Test
  public void testAmmoGrowsEatingAmmoAtFullAmmo() {
    Displayable ammo = mock(Ammo.class);

    assertEquals(DEFAULT_SHARK_AMMO, laserShark.getAmmo());
    laserShark.eat(ammo);
    assertEquals(EXPECTED_AFTER_EATING_AMMO, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a fish, it grows.
   */
  @Test
  public void testAmmoGrowsEatingAmmoAtNonFullAmmo() {
    Displayable ammo = new Ammo(new Position(0, 0), 1f);

    assertEquals(DEFAULT_SHARK_AMMO - 1, laserShark.decreaseAmmo());
    laserShark.eat(ammo);
    assertEquals(EXPECTED_AFTER_EATING_AMMO, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a dead fish, it doenst grow.
   */
  @Test
  public void testAmmoDoesntGrowsWhenEatingDeadAmmoAtFullAmmo() {
    Displayable ammo = mock(Ammo.class);
    when(ammo.isAlive()).thenReturn(false);

    assertEquals(DEFAULT_SHARK_AMMO, laserShark.getAmmo());
    laserShark.eat(ammo);
    assertEquals(DEFAULT_SHARK_AMMO, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a dead fish, it doenst grow.
   */
  @Test
  public void testAmmoDoesntGrowsWhenEatingDeadAmmoAtNonFullAmmo() {
    Displayable ammo = mock(Ammo.class);
    when(ammo.isAlive()).thenReturn(false);

    assertEquals(DEFAULT_SHARK_AMMO - 1, laserShark.decreaseAmmo());
    laserShark.eat(ammo);
    assertEquals(DEFAULT_SHARK_AMMO - 1, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a fish, it grows.
   */
  @Test
  public void testLaserSharkGrowsWhenEatingFish() {
    Displayable mockedFish = mock(Enemy.class);
    when(mockedFish.getSize()).thenReturn(size);
    when(mockedFish.isAlive()).thenReturn(true);

    assertEquals(laserShark.getSize(), DEFAULT_SHARK_SIZE, 0);
    laserShark.eat(mockedFish);
    assertEquals(laserShark.getSize(), EXPECTED_AFTER_EATING_SHARK_SIZE, 0);
  }

  /**
   * When the laser sharks eats a dead fish, it doenst grows.
   */
  @Test
  public void testLaserSharkDoesntGrowsWhenEatingDeadFish() {
    Displayable mockedFish = mock(SeaObject.class);
    when(mockedFish.getSize()).thenReturn(size);
    when(mockedFish.isAlive()).thenReturn(false);

    assertEquals(laserShark.getSize(), DEFAULT_SHARK_SIZE, 0);
    laserShark.eat(mockedFish);
    assertEquals(laserShark.getSize(), DEFAULT_SHARK_SIZE, 0);
  }

  /**
   * When the lasershark eats a fish, the fish schould be killed.
   */
  @Test
  public void testEatenFishIsKilled() {
    SeaObject mockedFish = mock(Enemy.class);
    when(mockedFish.isAlive()).thenReturn(true);

    assertTrue(mockedFish.isAlive());
    laserShark.eat(mockedFish);
    verify(mockedFish).kill();
  }

}

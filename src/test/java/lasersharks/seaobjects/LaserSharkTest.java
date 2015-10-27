package lasersharks.seaobjects;

import lasersharks.Position;
import lasersharks.behaviour.sizeincrement.FishGetSizeIncrementBehaviour;
import lasersharks.interfaces.Displayable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Class for testing Laser shark object.
 * 
 * @author SEMGroup27
 *
 */
public class LaserSharkTest extends SeaObjectTest {
  /**
   * Container for test object.
   */
  private LaserShark laserShark;
  private static final int DEFAULT_SHARK_AMMO = 5;
  private static final int EXPECTED_AFTER_EATING_AMMO = 5;
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
    laserShark.collideWith(ammo);
    assertEquals(EXPECTED_AFTER_EATING_AMMO, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a fish, it grows.
   */
  @Test
  public void testAmmoGrowsEatingAmmoAtNonFullAmmo() {
    Displayable ammo = new Ammo(new Position(0, 0), 1f);

    assertEquals(DEFAULT_SHARK_AMMO - 1, laserShark.decreaseAmmo());
    laserShark.collideWith(ammo);
    assertEquals(EXPECTED_AFTER_EATING_AMMO, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a dead fish, it doesn't grow.
   */
  @Test public void testAmmoDoesNotGrowsWhenEatingDeadAmmoAtFullAmmo() {
    Displayable ammo = mock(Ammo.class);
    when(ammo.isAlive()).thenReturn(false);

    assertEquals(DEFAULT_SHARK_AMMO, laserShark.getAmmo());
    laserShark.collideWith(ammo);
    assertEquals(DEFAULT_SHARK_AMMO, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a dead fish, it doesn't grow.
   */
  @Test public void testAmmoDoesNotGrowsWhenEatingDeadAmmoAtNonFullAmmo() {
    Displayable ammo = mock(Ammo.class);
    when(ammo.isAlive()).thenReturn(false);

    assertEquals(DEFAULT_SHARK_AMMO - 1, laserShark.decreaseAmmo());
    laserShark.collideWith(ammo);
    assertEquals(DEFAULT_SHARK_AMMO - 1, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a fish, it grows.
   */
  @Test
  public void testLaserSharkGrowsWhenEatingFish() {
    Displayable mockedFish = mock(Fish.class);
    //when(mockedFish.getSize()).thenReturn(size);
    when(mockedFish.isAlive()).thenReturn(true);
    when(mockedFish.onCollisionSizeIncrement())
        .thenReturn(size / FishGetSizeIncrementBehaviour.ENERGY_DISSIPATION_RATE);

    assertEquals(DEFAULT_SHARK_SIZE, laserShark.getSize(), 0);
    laserShark.collideWith(mockedFish);
    assertEquals(EXPECTED_AFTER_EATING_SHARK_SIZE, laserShark.getSize(), 0);
  }

  /**
   * When the laser sharks eats a dead fish, it doesn't grows.
   */
  @Test public void testLaserSharkDoesNotGrowsWhenEatingDeadFish() {
    Displayable mockedFish = mock(SeaObject.class);
    when(mockedFish.getSize()).thenReturn(size);
    when(mockedFish.isAlive()).thenReturn(false);

    assertEquals(laserShark.getSize(), DEFAULT_SHARK_SIZE, 0);
    laserShark.collideWith(mockedFish);
    assertEquals(laserShark.getSize(), DEFAULT_SHARK_SIZE, 0);
  }

  /**
   * When the laser shark eats a fish, the fish should be killed.
   */
  @Test
  public void testEatenFishIsKilled() {
    SeaObject mockedFish = mock(Fish.class);
    when(mockedFish.isAlive()).thenReturn(true);

    assertTrue(mockedFish.isAlive());
    laserShark.collideWith(mockedFish);
    verify(mockedFish).onCollisionEaten();
  }

}

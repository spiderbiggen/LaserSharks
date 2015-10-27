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
    fish1 = new LaserShark(posOnScreen, SIZE, SPEED, DIRECTION);
    laserShark = new LaserShark(posOnScreen, SIZE, SPEED, DIRECTION);
  }

  /**
   * When the laser sharks eats a fish, it grows.
   */
  @Test
  public void testAmmoGrowsEatingAmmoAtFullAmmo() {
    final Displayable ammo = mock(Ammo.class);

    assertEquals(DEFAULT_SHARK_AMMO, laserShark.getAmmo());
    laserShark.collideWith(ammo);
    assertEquals(EXPECTED_AFTER_EATING_AMMO, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a fish, it grows.
   */
  @Test
  public void testAmmoGrowsEatingAmmoAtNonFullAmmo() {
    final Displayable ammo = new Ammo(new Position(0, 0), 1f);

    assertEquals(DEFAULT_SHARK_AMMO - 1, laserShark.decreaseAmmo());
    laserShark.collideWith(ammo);
    assertEquals(EXPECTED_AFTER_EATING_AMMO, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a dead fish, it doenst grow.
   */
  @Test
  public void testAmmoDoesntGrowsWhenEatingDeadAmmoAtFullAmmo() {
    final Displayable ammo = mock(Ammo.class);
    when(ammo.isAlive()).thenReturn(false);

    assertEquals(DEFAULT_SHARK_AMMO, laserShark.getAmmo());
    laserShark.collideWith(ammo);
    assertEquals(DEFAULT_SHARK_AMMO, laserShark.getAmmo());
  }

  /**
   * When the laser sharks eats a dead fish, it doenst grow.
   */
  @Test
  public void testAmmoDoesntGrowsWhenEatingDeadAmmoAtNonFullAmmo() {
    final Displayable ammo = mock(Ammo.class);
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
    final Displayable mockedFish = mock(Fish.class);
    //when(mockedFish.getSize()).thenReturn(SIZE);
    when(mockedFish.isAlive()).thenReturn(true);
    when(mockedFish.onCollisionSizeIncrement())
        .thenReturn(SIZE / FishGetSizeIncrementBehaviour.ENERGY_DISSERPATION_RATE);

    assertEquals(DEFAULT_SHARK_SIZE, laserShark.getSize(), 0);
    laserShark.collideWith(mockedFish);
    assertEquals(EXPECTED_AFTER_EATING_SHARK_SIZE, laserShark.getSize(), 0);
  }

  /**
   * When the laser sharks eats a dead fish, it doenst grows.
   */
  @Test
  public void testLaserSharkDoesntGrowsWhenEatingDeadFish() {
    final Displayable mockedFish = mock(SeaObject.class);
    when(mockedFish.getSize()).thenReturn(SIZE);
    when(mockedFish.isAlive()).thenReturn(false);

    assertEquals(laserShark.getSize(), DEFAULT_SHARK_SIZE, 0);
    laserShark.collideWith(mockedFish);
    assertEquals(laserShark.getSize(), DEFAULT_SHARK_SIZE, 0);
  }

  /**
   * When the lasershark eats a fish, the fish schould be killed.
   */
  @Test
  public void testEatenFishIsKilled() {
    final SeaObject mockedFish = mock(Fish.class);
    when(mockedFish.isAlive()).thenReturn(true);

    assertTrue(mockedFish.isAlive());
    laserShark.collideWith(mockedFish);
    verify(mockedFish).onCollisionEaten();
  }

}

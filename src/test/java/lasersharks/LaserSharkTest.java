package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing Lazershark object.
 * 
 * @author Michiel
 *
 */
public class LaserSharkTest extends SeaObjectTest {
  /**
   * Container for test object.
   */
  private LaserShark laserShark;
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
  public void testLaserSharkGrowsWhenEatingFish() {
    Displayable mockedFish = mock(FishBot.class);
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
   * When the laser sharks eats a fish that is dead, no changes should happen.
   */
  @Test
  public void testLaserSharkGrowsWhenEatingDeadFish() {
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
    SeaObject mockedFish = mock(FishBot.class);
    when(mockedFish.isAlive()).thenReturn(true);

    assertTrue(mockedFish.isAlive());
    laserShark.eat(mockedFish);
    verify(mockedFish).kill();
  }

}

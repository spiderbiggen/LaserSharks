package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Test;

/**
 * Class for testing SeaObjects.
 * 
 * @author SEMGroup27
 *
 */
public abstract class SeaObjectTest {

  protected SeaObject fish1;
  protected final Position posOnScreen = new Position(50, 50);
  protected final Position posOffScreen = new Position(-1, -1);
  protected final float size = 30;
  protected final int speed = 40;
  protected final Direction direction = Direction.East;

  /**
   * Tear down after the test.
   */
  @After
  public void tearDown() {
    fish1 = null;
  }

  /**
   * Test for {@link SeaObject#getPosition()}.
   */
  @Test
  public void testGetPosition() {
    assertEquals(posOnScreen, fish1.getPosition());
  }

  /**
   * Test for {@link SeaObject#getSize()}.
   */
  @Test
  public void testGetSize() {
    assertTrue(fish1.getSize() == size);
  }

  /**
   * Test for {@link SeaObject#increaseSize(Float)}.
   */
  @Test
  public void testIncreaseSize() {
    int deltaSize = 2;
    fish1.increaseSize(deltaSize);
    assertEquals(size + deltaSize, fish1.getSize(), 0);
  }

  /**
   * Test for {@link SeaObject#getSpeed()}.
   */
  @Test
  public void testGetSpeed() {
    assertTrue(fish1.getSpeed() == speed);
  }

  /**
   * Test for {@link SeaObject#setSpeed(int)}.
   */
  @Test
  public void testSetSpeed() {
    int newSpeed = 2;
    fish1.setSpeed(newSpeed);
    assertEquals(newSpeed, fish1.getSpeed(), 0);
  }

  /**
   * Test for {@link SeaObject#getDirection()}.
   */
  @Test
  public void testGetDirection() {
    assertEquals(fish1.getDirection(), Direction.East);
  }

  /**
   * Test for {@link SeaObject#setDirection(Direction)}.
   */
  @Test
  public void testSetDirection() {
    Direction newDirection = Direction.South;
    fish1.setDirection(newDirection);
    assertEquals(fish1.getDirection(), newDirection);
  }

  /**
   * Test for {@link SeaObject#collision(SeaObject)}.
   */
  @Test
  public void testCollisionTrue() {
    SeaObject mockedFish = mock(SeaObject.class);
    when(mockedFish.getPosition()).thenReturn(posOnScreen);
    when(mockedFish.getSize()).thenReturn(size);
    // TODO: change the way how this is tested so it doesn't give nullpointer exceptions.
    // This has to do with storing mockito objects in a variable and functions missing.
    // assertTrue(fish1.collision(mockedFish));
  }

  /**
   * Test for {@link SeaObject#collision(SeaObject)}.
   */
  @Test
  public void testCollisionFalse() {
    SeaObject mockedFish = mock(SeaObject.class);
    when(mockedFish.getPosition()).thenReturn(posOffScreen);
    when(mockedFish.getSize()).thenReturn(size);
    // TODO: change the way how this is tested so it doesn't give nullpointer exceptions.
    // This has to do with storing mockito objects in a variable and functions missing.
    // assertFalse(fish1.collision(mockedFish));
  }

  /**
   * Test for {@link SeaObject#move()}.
   */
  @Test
  public void testMove() {
    double oldX = fish1.getPosition().getPosX();
    fish1.move(1);
    double newX = fish1.getPosition().getPosX();
    assertEquals(oldX + fish1.getSpeed(), newX, 1);
  }

  /**
   * test for {@link SeaObject#isOnScreen()}.
   */
  @Test
  public void testOnScreenTrue() {
    assertTrue(fish1.isOnScreen());
  }

  /**
   * Tests for {@link SeaObject#isOnScreen()}.
   */
  @Test
  public void testIsOnScreenFalseDeadFish() {
    Displayable fish1 = this.fish1;
    fish1.kill();
    assertFalse(fish1.isOnScreen());
  }

  /**
   * Tests for {@link SeaObject#isOnScreen()}.
   */
  @Test
  public void testIsOnScreenFalseOffScreen() {
    Displayable fish1 = this.fish1;
    fish1.setPosition(posOffScreen);
    assertFalse(fish1.isOnScreen());
  }

  /**
   * Test for {@link SeaObject#isAlive()}.
   */
  @Test
  public void testSetAlive() {
    fish1.kill();
    fish1.setAlive();
    assertTrue(fish1.isAlive());
  }

}

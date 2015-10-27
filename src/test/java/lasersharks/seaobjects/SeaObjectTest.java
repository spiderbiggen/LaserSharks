package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.interfaces.Displayable;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for testing SeaObjects.
 * 
 * @author SEMGroup27
 *
 */
public abstract class SeaObjectTest {

  protected AbstractSeaObject fish1;
  protected final Position posOnScreen = new Position(50, 50);
  protected final Position posOffScreen = new Position(-1, -1);
  protected static final float SIZE = 30;
  protected static final float SPEED = 40;
  protected static final Direction DIRECTION = Direction.East;

  private static final double DELTA = 0.0001;
  /**
   * Tear down after the test.
   */
  @After
  public void tearDown() {
    fish1 = null;
  }

  /**
   * Test for {@link AbstractSeaObject#getPosition()}.
   */
  @Test
  public void testGetPosition() {
    assertEquals(posOnScreen, fish1.getPosition());
  }

  /**
   * Test for {@link AbstractSeaObject#getSize()}.
   */
  @Test
  public void testGetSize() {
    assertEquals(SIZE, fish1.getSize(), DELTA);
  }

  /**
   * Test for {@link AbstractSeaObject#increaseSize(float)}.
   */
  @Test
  public void testIncreaseSize() {
    final int deltaSize = 2;
    fish1.increaseSize(deltaSize);
    assertEquals(SIZE + deltaSize, fish1.getSize(), 0);
  }

  /**
   * Test for {@link AbstractSeaObject#getSpeed()}.
   */
  @Test
  public void testGetSpeed() {
    assertEquals(SPEED, fish1.getSpeed(), DELTA);
  }

  /**
   * Test for {@link AbstractSeaObject#setSpeed(int)}.
   */
  @Test
  public void testSetSpeed() {
    final int newSpeed = 2;
    fish1.setSpeed(newSpeed);
    assertEquals(newSpeed, fish1.getSpeed(), 0);
  }

  /**
   * Test for {@link AbstractSeaObject#getDirection()}.
   */
  @Test
  public void testGetDirection() {
    assertEquals(fish1.getDirection(), Direction.East);
  }

  /**
   * Test for {@link AbstractSeaObject#setDirection(Direction)}.
   */
  @Test
  public void testSetDirection() {
    final Direction newDirection = Direction.South;
    fish1.setDirection(newDirection);
    assertEquals(fish1.getDirection(), newDirection);
  }

  /**
   * Test for {@link AbstractSeaObject#checkForCollision(Displayable)}.
   */
  @Test
  public void testCollisionTrue() {
    final AbstractSeaObject mockedFish = mock(AbstractSeaObject.class);
    when(mockedFish.getPosition()).thenReturn(posOnScreen);
    when(mockedFish.getSize()).thenReturn(SIZE);
    // TODO: change the way how this is tested so it doesn't give null pointer exceptions.
    // This has to do with storing mockito objects in a variable and functions missing.
    // TODO: Fix by changing collision reference in methods to getter calls.
    // assertTrue(fish1.collision(mockedFish));
  }

  /**
   * Test for {@link AbstractSeaObject#checkForCollision(Displayable)}.
   */
  @Test
  public void testCollisionFalse() {
    final AbstractSeaObject mockedFish = mock(AbstractSeaObject.class);
    when(mockedFish.getPosition()).thenReturn(posOffScreen);
    when(mockedFish.getSize()).thenReturn(SIZE);
    // TODO: change the way how this is tested so it doesn't give null pointer exceptions.
    // This has to do with storing mockito objects in a variable and functions missing.
    // TODO: Fix by changing collision reference in methods to getter calls.
    // assertFalse(fish1.collision(mockedFish));
  }

  /**
   * Test for {@link AbstractSeaObject#move(double)}.
   */
  @Test
  public void testMove() {
    final double oldX = fish1.getPosition().getPosX();
    fish1.move(1);
    final double newX = fish1.getPosition().getPosX();
    assertEquals(oldX + fish1.getSpeed(), newX, 1);
  }

  /**
   * test for {@link AbstractSeaObject#isOnScreen()}.
   */
  @Test
  public void testOnScreenTrue() {
    assertTrue(fish1.isOnScreen());
  }

  /**
   * Tests for {@link AbstractSeaObject#isOnScreen()}.
   */
  @Test
  public void testIsOnScreenFalseDeadFish() {
    final Displayable fish1 = this.fish1;
    fish1.kill();
    assertFalse(fish1.isOnScreen());
  }

  /**
   * Tests for {@link AbstractSeaObject#isOnScreen()}.
   */
  @Test
  public void testIsOnScreenFalseOffScreen() {
    final Displayable fish1 = this.fish1;
    fish1.getPosition().setPosX(posOffScreen.getPosX());
    fish1.getPosition().setPosY(posOffScreen.getPosY());
    assertFalse(fish1.isOnScreen());
  }

  /**
   * Test for {@link AbstractSeaObject#isAlive()}.
   */
  @Test
  public void testSetAlive() {
    fish1.kill();
    fish1.setAlive();
    assertTrue(fish1.isAlive());
  }

}

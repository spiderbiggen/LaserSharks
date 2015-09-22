package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Test;

/**
 * Class for testing Fish object.
 * 
 * @author Stefan
 *
 */
public abstract class FishTest {

  protected Fish fish1;
  protected final Position posOnScreen = new Position(50, 50);
  protected final Position posOffScreen = new Position(-1, -1);
  protected final float size = 30;
  protected final int speed = 40;
  protected final Direction direction = Direction.East;

  /**
   * Tear down the fish Object after the test.
   */
  @After
  public void tearDown() {
    fish1 = null;
  }

  /**
   * Test for {@link Fish#getPosition()}.
   */
  @Test
  public void testGetPosition() {
    assertEquals(posOnScreen, fish1.getPosition());
  }

  /**
   * Test for {@link Fish#getSize()}.
   */
  @Test
  public void testGetSize() {
    assertTrue(fish1.getSize() == size);
  }

  /**
   * Test for {@link Fish#increaseSize(Float)}.
   */
  @Test
  public void testIncreaseSize() {
    int deltaSize = 2;
    fish1.increaseSize(deltaSize);
    assertEquals(size + deltaSize, fish1.getSize(), 0);
  }

  /**
   * Test for {@link Fish#getSpeed()}.
   */
  @Test
  public void testGetSpeed() {
    assertTrue(fish1.getSpeed() == speed);
  }

  /**
   * Test for {@link Fish#setSpeed(int)}.
   */
  @Test
  public void testSetSpeed() {
    int newSpeed = 2;
    fish1.setSpeed(newSpeed);
    assertEquals(newSpeed, fish1.getSpeed(), 0);
  }

  /**
   * Test for {@link Fish#getDirection()}.
   */
  @Test
  public void testGetDirection() {
    assertEquals(fish1.getDirection(), Direction.East);
  }

  /**
   * Test for {@link Fish#setDirection(Direction)}.
   */
  @Test
  public void testSetDirection() {
    Direction newDirection = Direction.South;
    fish1.setDirection(newDirection);
    assertEquals(fish1.getDirection(), newDirection);
  }

  /**
   * Test for {@link Fish#collision(Fish)}.
   */
  @Test
  public void testCollisionTrue() {
    Fish mockedFish = mock(Fish.class);
    when(mockedFish.getPosition()).thenReturn(posOnScreen);
    when(mockedFish.getSize()).thenReturn(size);
    assertTrue(fish1.collision(mockedFish));
  }

  /**
   * Test for {@link Fish#collision(Fish)}.
   */
  @Test
  public void testCollisionFalse() {
    Fish mockedFish = mock(Fish.class);
    when(mockedFish.getPosition()).thenReturn(posOffScreen);
    when(mockedFish.getSize()).thenReturn(size);
    assertFalse(fish1.collision(mockedFish));
  }

  /**
   * Test for {@link Fish#move()}.
   */
  @Test
  public void testMove() {
    double oldX = fish1.getPosition().getPosX();
    fish1.move(1);
    double newX = fish1.getPosition().getPosX();
    assertEquals(oldX + fish1.getSpeed(), newX, 1);
  }

  /**
   * test a fish that is alive and on the screen.
   */
  @Test
  public void testOnScreenTrue() {
    assertTrue(fish1.isOnScreen());
  }

  /**
   * Tests if a s fish is considered off screen when it is killed.
   */
  @Test
  public void testIsOnScreenFalseDeadFish() {
    Fish fish1 = this.fish1;
    fish1.kill();
    assertFalse(fish1.isOnScreen());
  }

  /**
   * Tests if a fish is considered off screen when it's position is out of bounds.
   */
  @Test
  public void testIsOnScreenFalseOffScreen() {
    Fish fish1 = this.fish1;
    fish1.setPosition(posOffScreen);
    assertFalse(fish1.isOnScreen());
  }
  
  @Test
  public void testSetAlive() {
    fish1.kill();
    fish1.setAlive();
    assertTrue(fish1.isAlive());
  }

}

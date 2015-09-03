package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Stefan .
 *
 */
public class PositionTest {

  private Position position;
  private int posX;
  private int posY;

  /**
   * Make sure the test has the same variables each time.
   */
  @Before
  public void setUp() {
    posX = 0;
    posY = 0;
    position = new Position(posX, posY);
  }

  /**
   * Test method for {@link lasersharks.Position#getPosX()}.
   */
  @Test
  public void testGetPosX() {
    assertEquals(posX, position.getPosX());
  }

  /**
   * Test method for {@link lasersharks.Position#setPosX(int)}.
   */
  @Test
  public void testSetPosX() {
    position.setPosX(5);
    assertEquals(5, position.getPosX());
  }

  /**
   * Test method for {@link lasersharks.Position#adjustPosX(int)}.
   */
  @Test
  public void testAdjustPosX() {
    assertEquals(posX, position.getPosX());
    // Delta 1
    position.adjustPosX(1);
    assertEquals(posX + 1, position.getPosX());
    // Delta -1
    position.adjustPosX(-1);
    assertEquals(posX, position.getPosX());
  }

  /**
   * Test method for {@link lasersharks.Position#getPosY()}.
   */
  @Test
  public void testGetPosY() {
    assertEquals(posY, position.getPosY());
  }

  /**
   * Test method for {@link lasersharks.Position#setPosY(int)}.
   */
  @Test
  public void testSetPosY() {
    position.setPosY(5);
    assertEquals(5, position.getPosY());
  }

  /**
   * Test method for {@link lasersharks.Position#adjustPosY(int)}.
   */
  @Test
  public void testAdjustPosY() {
    assertEquals(posY, position.getPosY());
    // Delta 1
    position.adjustPosY(1);
    assertEquals(posY + 1, position.getPosY());
    // Delta -1
    position.adjustPosY(-1);
    assertEquals(posY, position.getPosY());
  }

  /**
   * Test method for {@link lasersharks.Position#adjustPos(int, int)}.
   */
  @Test
  public void testAdjustPos() {
    assertEquals(posX, position.getPosY());
    assertEquals(posY, position.getPosY());
    // Delta x 1
    position.adjustPos(1, 0);
    assertEquals(posX + 1, position.getPosX());
    assertEquals(posY, position.getPosY());
    // Delta x -1
    position.adjustPos(-1, 0);
    assertEquals(posX, position.getPosX());
    assertEquals(posY, position.getPosY());
    // Delta y 1
    position.adjustPos(0, 1);
    assertEquals(posX, position.getPosX());
    assertEquals(posY + 1, position.getPosY());
    // Delta y -1
    position.adjustPos(0, -1);
    assertEquals(posX, position.getPosX());
    assertEquals(posY, position.getPosY());
  }

  /**
   * Test method for {@link lasersharks.Position#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObjectTrue() {
    Position testPosition = new Position(0, 0);
    assertTrue(position.equals(testPosition));
    assertTrue(position.equals(position));
  }

  /**
   * Test method for {@link lasersharks.Position#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObjectFalse() {
    Position testPosition = new Position(1, 0);
    assertFalse(position.equals(testPosition));
    testPosition = new Position(0, 1);
    assertFalse(position.equals(testPosition));
    testPosition = new Position(1, 1);
    assertFalse(position.equals(testPosition));
  }

  /**
   * Test method for {@link lasersharks.Position#hashCode()}.
   */
  @Test
  public void testHashCode() {
    Position testPosition = new Position(0, 0);
    assertTrue(position.hashCode() == testPosition.hashCode());
    testPosition = new Position(1, 1);
    assertTrue(position.hashCode() != testPosition.hashCode());
  }

}

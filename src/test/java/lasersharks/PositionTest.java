package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


/**
 * Test for the {@link Position} class.
 * 
 * @author SEMGroup27
 *
 */
public class PositionTest {

  private static final int POSITION_Y = 5;
  private static final int POSITION_X = 5;
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
    assertEquals(posX, position.getPosX(), 0);
  }

  /**
   * Test method for {@link lasersharks.Position#setPosX(double)}.
   */
  @Test
  public void testSetPosX() {
    position.setPosX(POSITION_X);
    assertEquals(POSITION_X, position.getPosX(), 0);
  }

  /**
   * Test method for {@link lasersharks.Position#getPosY()}.
   */
  @Test
  public void testGetPosY() {
    assertEquals(posY, position.getPosY(), 0);
  }

  /**
   * Test method for {@link lasersharks.Position#setPosY(double)}.
   */
  @Test
  public void testSetPosY() {
    position.setPosY(POSITION_Y);
    assertEquals(POSITION_Y, position.getPosY(), 0);
  }

  /**
   * Test method for {@link lasersharks.Position#setHeightPanel(int)}.
   */
  @Test
  public void testSetHeightPanel() {
    final int height = 500;
    Position.setHeightPanel(height);
    assertEquals(height, Position.getHeightPanel(), 0);
  }

  /**
   * Test method for {@link lasersharks.Position#setWidthPanel(int)}.
   */
  @Test
  public void testSetWidthPanel() {
    final int width = 500;
    Position.setWidthPanel(width);
    assertEquals(width, Position.getWidthPanel(), 0);
  }

  /**
   * Test method for {@link lasersharks.Position#adjustPosX(double)}.
   */
  @Test
  public void testAdjustPosX1() {
    position.adjustPosX(1);
    assertEquals(posX + 1, position.getPosX(), 0);
  }

  /**
   * Test method for {@link lasersharks.Position#adjustPosX(double)}.
   */
  @Test
  public void testAdjustPosX2() {
    position.adjustPosX(-1);
    assertEquals(posX - 1, position.getPosX(), 0);
  }

  /**
   * Test method for {@link lasersharks.Position#adjustPosY(double)}.
   */
  @Test
  public void testAdjustPosY1() {
    position.adjustPosY(1);
    assertEquals(posY + 1, position.getPosY(), 0);
  }

  /**
   * Test method for {@link lasersharks.Position#adjustPosY(double)}.
   */
  @Test
  public void testAdjustPosY2() {
    position.adjustPosY(-1);
    assertEquals(posY - 1, position.getPosY(), 0);
  }

  /**
   * Test method for {@link lasersharks.Position#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObjectTrue1() {
    Position testPosition = new Position(0, 0);
    assertTrue(position.equals(testPosition));
    assertTrue(position.equals(position));
  }

  /**
   * Test method for {@link lasersharks.Position#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObjectTrue2() {
    assertTrue(position.equals(position));
  }

  /**
   * Test method for {@link lasersharks.Position#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObjectFalse1() {
    assertFalse(position.equals(new Position(1, 0)));
  }

  /**
   * Test method for {@link lasersharks.Position#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObjectFalse2() {
    assertFalse(position.equals(new Position(0, 1)));
  }

  /**
   * Test method for {@link lasersharks.Position#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObjectFalse3() {
    assertFalse(position.equals(new Position(1, 1)));
  }

  /**
   * Test method for {@link lasersharks.Position#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObjectFalse4() {
    assertFalse(position.equals("string"));
  }

  /**
   * Test method for {@link lasersharks.Position#calculateDistance(Position)}.
   */
  @Test
  public void testCalculateDistance() {
    final double doubleDelta = Math.pow(10, 6);
    Position testPosition = new Position(1, 1);
    assertEquals(Math.sqrt(2), position.calculateDistance(testPosition),
        Math.sqrt(2) / doubleDelta);
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

  /**
   * Test method for {@link lasersharks.Position#middlePosition()}.
   */
  @Test
  public void testMiddlePosition() {
    final int size = 200;
    final int halfScale = 2;
    Position.setHeightPanel(size);
    Position.setWidthPanel(size);
    Position testPosition = new Position(size / halfScale, size / halfScale);
    assertEquals(testPosition, Position.middlePosition());
  }
}

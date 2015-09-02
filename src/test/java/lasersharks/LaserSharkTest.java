package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LaserSharkTest {

  private LaserShark laserShark;
  private Position position;
  private float size;

  @Before
  public void setUp() {
    position = new Position(0, 0);
    size = 1.0f;
    laserShark = new LaserShark(position, Direction.East, size);
  }

  @Test
  public void testGetDirection() {
    assertEquals(Direction.East, laserShark.getDirection());
  }

  @Test
  public void testSetDirection() {
    laserShark.setDirection(Direction.West);
    assertEquals(Direction.West, laserShark.getDirection());
  }

  @Test
  public void testGetPosition() {
    assertEquals(position, laserShark.getPosition());
  }

  @Test
  public void testSetPosition() {
    Position position1 = new Position(10, 10);
    laserShark.setPosition(position1);
    assertEquals(position1, laserShark.getPosition());
  }

  @Test
  public void testGetSize() {
    assertTrue(size == laserShark.getSize());
  }

  @Test
  public void testSetSize() {
    float size2 = 5.0f;
    laserShark.setSize(size2);
    assertTrue(size2 == laserShark.getSize());
  }

  /*
   * @Test public void testSetNextMove() { fail("Not yet implemented"); // TODO }
   * 
   * 
   * @Test public void testCollision() { fail("Not yet implemented"); // TODO }
   */

  @Test
  public void testMove() {
    assertEquals(position, laserShark.getPosition());
    
    laserShark.move();
    position.updatePosition(Direction.East);
    assertEquals(position, laserShark.getPosition());
    
    laserShark.move();
    position.updatePosition(Direction.East);
    assertEquals(position, laserShark.getPosition());
  }

}

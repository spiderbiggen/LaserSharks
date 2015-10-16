package lasersharks.enemies;


import static org.junit.Assert.assertEquals;

import java.util.Random;

import lasersharks.Direction;
import lasersharks.LaserBullet;
import lasersharks.LaserShark;
import lasersharks.Position;
import lasersharks.Displayable;
import lasersharks.enemies.FishFactory;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test class for FishFactory.
 * @author SEMGroup27
 *
 */
public class FishFactoryTest {

  protected Displayable generatedFish;
  private final long seed = 12345622L;
  private final long seedWest = 11L;

  private final float expectedSize1 = 172;
  private final int expectedSpeed1 = 134;

  private final float expectedSize2 = 176;
  private final int expectedSpeed2 = 344;
  
  private FishFactory fishFactory;

  
  
  /**
   * 
   */
  @Test
  public void testRandomFish1() {
    fishFactory = new FishFactory();
    Random random = new Random(seed);
    generatedFish = fishFactory.generateFish(random);

    assertEquals(Direction.East, generatedFish.getDirection());
    assertEquals(-expectedSize1, generatedFish.getPosition().getPosX(), 1);
    assertEquals(expectedSize1, generatedFish.getSize(), 1);
    assertEquals(expectedSpeed1, generatedFish.getSpeed(), 1);
  }

  /**
   * 
   */
  @Test
  public void testRandomFish2() {
    fishFactory = new FishFactory();
    Random random = new Random(seedWest);
    generatedFish = fishFactory.generateFish(random);

    assertEquals(Direction.West, generatedFish.getDirection());
    assertEquals(Position.getWidthPanel(), generatedFish.getPosition().getPosX(), 1);
    assertEquals(expectedSize2, generatedFish.getSize(), 1);
    assertEquals(expectedSpeed2, generatedFish.getSpeed(), 1);
  }

  /**
   * Test for creating a LaserBullet.
   */
  @Test
  public void testLaserCreation() {
    fishFactory = new FishFactory();
    LaserShark shark = Mockito.mock(LaserShark.class);
    Mockito.when(shark.getLastHorizontalDirection()).thenReturn(Direction.East);
    Mockito.when(shark.getPosition()).thenReturn(Position.middlePosition());
    LaserBullet laserBulletCreated = fishFactory.createLaser(shark);
    assertEquals(laserBulletCreated.getPosition(), Position.middlePosition());
  }

  /**
   * Test for creating a LaserBullet.
   */
  @Test
  public void testLaserCreation2() {
    fishFactory = new FishFactory();
    LaserShark shark = Mockito.mock(LaserShark.class);
    Mockito.when(shark.getLastHorizontalDirection()).thenReturn(Direction.West);
    Mockito.when(shark.getPosition()).thenReturn(Position.middlePosition());
    LaserBullet laserBulletCreated = fishFactory.createLaser(shark);
    assertEquals(laserBulletCreated.getPosition(), Position.middlePosition());
  }
}

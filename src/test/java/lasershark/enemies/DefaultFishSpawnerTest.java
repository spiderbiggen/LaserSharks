package lasershark.enemies;

import static org.junit.Assert.*;

import java.util.Random;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.Swimmer;
import lasersharks.enemies.DefaultFishSpawner;

import org.junit.Test;

/**
 * Test class for fishfactory.
 * @author SEMGroup27
 *
 */
public class DefaultFishSpawnerTest {

  protected Swimmer generatedFish;
  private final long seed = 12345622L;
  private final long seedWest = 11L;

  private final float expectedSize1 = 172;
  private final int expectedSpeed1 = 134;

  private final float expectedSize2 = 176;
  private final int expectedSpeed2 = 344;
  
  private DefaultFishSpawner fishFactory;

  
  
  /**
   * 
   */
  @Test
  public void testRandomFish1() {
    fishFactory = new DefaultFishSpawner();
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
    fishFactory = new DefaultFishSpawner();
    Random random = new Random(seedWest);
    generatedFish = fishFactory.generateFish(random);

    assertEquals(Direction.West, generatedFish.getDirection());
    assertEquals(Position.getWidthPanel(), generatedFish.getPosition().getPosX(), 1);
    assertEquals(expectedSize2, generatedFish.getSize(), 1);
    assertEquals(expectedSpeed2, generatedFish.getSpeed(), 1);
  }


}

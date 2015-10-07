package lasershark.enemies;

import static org.junit.Assert.*;

import java.util.Random;

import lasersharks.Direction;
import lasersharks.FishBot;
import lasersharks.Position;
import lasersharks.Swimmer;
import lasersharks.enemies.DefaultFishSpawner;

import org.junit.Before;
import org.junit.Test;

public class DefaultFishSpawnerTest {

  protected Swimmer generatedFish;
  private final long seed = 123456L;
  private final long seedWest = 11L;

  private final float expectedSize1 = 206;
  private final int expectedSpeed1 = 186;

  private final float expectedSize2 = 172;
  private final int expectedSpeed2 = 344;
  
  private DefaultFishSpawner fishFactory;

  
  
  /**
   * 
   */
  @SuppressWarnings("deprecation")
  @Test
  public void testRandomFish1() {
    fishFactory = new DefaultFishSpawner();
    Random random = new Random(seed);
    generatedFish = fishFactory.generateFish(random);

    assertEquals(generatedFish.getDirection(), Direction.East);
    assertEquals(generatedFish.getPosition().getPosX(), 0, 0);
    assertEquals(expectedSize1, generatedFish.getSize(), 1);
    assertEquals(expectedSpeed1, generatedFish.getSpeed(), 0.0f);
  }

  /**
   * 
   */
  @Test
  public void testRandomFish2() {
    fishFactory = new DefaultFishSpawner();
    Random random = new Random(seedWest);
    generatedFish = fishFactory.generateFish(random);

    assertEquals(generatedFish.getDirection(), Direction.West);
    assertEquals(generatedFish.getPosition().getPosX(), Position.getWidthPanel(), 0);
    assertEquals(expectedSize2, generatedFish.getSize(), 1);
    assertEquals(expectedSpeed2, generatedFish.getSpeed(), 0.0f);
  }


}

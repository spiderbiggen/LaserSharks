package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

/**
 * Class for testing fishbot.
 * 
 * @author Youri
 *
 */
public abstract class FishBotTest extends FishTest {

  protected Swimmer generatedFish;
  private final long seed = 12345622L;
  private final long seedWest = 11L;

  private final float expectedSize1 = 172;
  private final int expectedSpeed1 = 134;

  private final float expectedSize2 = 176;
  private final int expectedSpeed2 = 344;

  /**
   * Test eastgoing fish
   */
  @Test
  public void testRandomFish1() {
    Random random = new Random(seed);
    generatedFish = FishBot.generateFish(random);

    assertEquals(generatedFish.getDirection(), Direction.East);
    assertEquals(-expectedSize1, generatedFish.getPosition().getPosX(), 1.0f);
    assertEquals(expectedSize1, generatedFish.getSize(), 1.0f);
    assertEquals(expectedSpeed1, generatedFish.getSpeed(), 1.0f);
  }

  /**
   * Test westgoing fish
   */
  @Test
  public void testRandomFish2() {
    Random random = new Random(seedWest);
    generatedFish = FishBot.generateFish(random);

    assertEquals(generatedFish.getDirection(), Direction.West);
    assertEquals(generatedFish.getPosition().getPosX(), Position.getWidthPanel(), 1.0f);
    assertEquals(expectedSize2, generatedFish.getSize(), 1.0f);
    assertEquals(expectedSpeed2, generatedFish.getSpeed(), 0.0f);
  }

}

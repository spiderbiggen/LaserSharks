package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing fishbot.
 * 
 * @author Youri
 *
 */
public class FishBotTest extends FishTest {

  private Fish generatedFish;
  private final long seed = 123456L;
  private final long seedWest = 11L;

  private final float expectedSize1 = 228;
  private final int expectedSpeed1 = 17;

  private final float expectedSize2 = 115;
  private final int expectedSpeed2 = 10;

  /**
   * Set up which is used before the tests.
   */
  @Before
  public void setUp() {
    fish1 = new FishBot(posOnScreen, size, speed, direction);
  }

  /**
   * 
   */
  @Test
  public void testRandomFish1() {
    Random random = new Random(seed);
    generatedFish = FishBot.generateFish(random);
    System.out.println(generatedFish.toString());

    assertEquals(generatedFish.getDirection(), Direction.East);
    assertEquals(generatedFish.getPosition().getPosX(), 0);
    assertEquals(generatedFish.getSize(), expectedSize1, 0);
    assertTrue(generatedFish.getSpeed() == expectedSpeed1);
  }
  
  /**
   * 
   */
  @Test
  public void testRandomFish2() {
    Random random = new Random(seedWest);
    generatedFish = FishBot.generateFish(random);

    System.out.println(generatedFish.toString());
    assertEquals(generatedFish.getDirection(), Direction.West);
    assertEquals(generatedFish.getPosition().getPosX(), Position.getWidthPanel());
    assertEquals(generatedFish.getSize(), expectedSize2, 0);
    assertTrue(generatedFish.getSpeed() == expectedSpeed2);
  }

}

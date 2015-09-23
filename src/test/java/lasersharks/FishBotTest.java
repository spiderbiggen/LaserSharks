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

  private Swimmer generatedFish;
  private final long seed = 123456L;
  private final long seedWest = 11L;

  private final float expectedSize1 = 206;
  private final int expectedSpeed1 = 277;

  private final float expectedSize2 = 172;
  private final int expectedSpeed2 = 170;

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
    assertEquals(generatedFish.getPosition().getPosX(), 0, 0);
    assertEquals(expectedSize1, generatedFish.getSize(), 1);
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
    assertEquals(generatedFish.getPosition().getPosX(), Position.getWidthPanel(), 0);
    assertEquals(expectedSize2, generatedFish.getSize(), 1);
    assertTrue(generatedFish.getSpeed() == expectedSpeed2);
  }

}

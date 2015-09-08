package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class FishBotTest extends FishTest {

  private Fish generatedFish;
  private final long seed = 123456L;

  private final float expectedSize1 = 40;
  private final int expectedSpeed1 = 14;

  private final float expectedSize2 = 28;
  private final int expectedSpeed2 = 9;

  /**
   * Set up which is used before the tests
   */
  @Before
  public void setUp() {
    fish1 = new FishBot(posOnScreen, size, speed, direction);
  }

  
  /**
   * 
   */
  @Test
  public void testRandomFish() {
    Random random = new Random(seed);
    generatedFish = FishBot.generateFish(random);
    System.out.println(generatedFish.toString());
    assertEquals(generatedFish.getDirection(), Direction.West);
    assertEquals(generatedFish.getPosition().getPosX(), 0);
    assertEquals(generatedFish.getSize(), expectedSize1, 0);
    assertTrue(generatedFish.getSpeed() == expectedSpeed1);
    generatedFish = FishBot.generateFish(random);
    System.out.println(generatedFish.toString());
    assertEquals(generatedFish.getDirection(), Direction.West);
    assertEquals(generatedFish.getPosition().getPosX(), 0);
    // TODO change 1920 to pull from the level class so that it changes depending on the size of the
    // level
    assertEquals(generatedFish.getSize(), expectedSize2, 0);
    assertTrue(generatedFish.getSpeed() == expectedSpeed2);
  }
  
}

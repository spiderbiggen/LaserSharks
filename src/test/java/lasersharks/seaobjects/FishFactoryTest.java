package lasersharks.seaobjects;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import lasersharks.Direction;
import lasersharks.Options;
import lasersharks.Position;
import lasersharks.interfaces.Displayable;

/**
 * Test class for FishFactory.
 * 
 * @author SEMGroup27
 *
 */
public class FishFactoryTest {

  private static final int WIDTH = 1920;
  private static final int HEIGHT = 1080;

  protected Displayable generatedFish;
  private final long seed = 12345622L;
  private final long seedWest = 11L;

  private final float expectedSize1 = 282;
  private final int expectedSpeed1 = 148;

  private final float expectedSize2 = 289;
  private final int expectedSpeed2 = 448;

  private FishFactory fishFactory;

  /**
   * Setup a new instance of the factory for every test.
   */
  @Before
  public void setUp() {
    fishFactory = new FishFactory();
  }

  /**
   * Test for generating an Fish.
   */
  @Test
  public void testRandomFish1() {
    Options.setGlobalHeight(HEIGHT);
    Options.setGlobalWidth(WIDTH);
    Random random = new Random(seed);
    generatedFish = fishFactory.generateFish(random);

    assertEquals(Direction.East, generatedFish.getDirection());
    assertEquals(-expectedSize1, generatedFish.getPosition().getPosX(), 1);
    assertEquals(expectedSize1, generatedFish.getSize(), 1);
    assertEquals(expectedSpeed1, generatedFish.getSpeed(), 1);
  }

  /**
   * Test for generating an Fish.
   */
  @Test
  public void testRandomFish2() {
    Random random = new Random(seedWest);
    generatedFish = fishFactory.generateFish(random);

    assertEquals(Direction.West, generatedFish.getDirection());
    assertEquals(Position.getWidthPanel(), generatedFish.getPosition().getPosX(), 1);
    assertEquals(expectedSize2, generatedFish.getSize(), 1);
    assertEquals(expectedSpeed2, generatedFish.getSpeed(), 1);
  }

}

package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Options;
import lasersharks.Position;
import lasersharks.interfaces.Displayable;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Test class for FishFactory.
 *
 * @author SEMGroup27
 */
public class FishFactoryTest {

  private static final int WIDTH = 1920;
  private static final int HEIGHT = 1080;
  private static final long SEED = 12345622L;
  private static final long SEED_WEST = 11L;
  private static final float EXPECTED_SIZE_1 = 282;
  private static final int EXPECTED_SPEED_1 = 148;
  private static final float EXPECTED_SIZE_2 = 289;
  private static final int EXPECTED_SPEED_2 = 448;
  private Displayable generatedFish;
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
    final Random random = new Random(SEED);
    generatedFish = fishFactory.generateFish(random);

    assertEquals(Direction.East, generatedFish.getDirection());
    assertEquals(-EXPECTED_SIZE_1, generatedFish.getPosition().getPosX(), 1);
    assertEquals(EXPECTED_SIZE_1, generatedFish.getSize(), 1);
    assertEquals(EXPECTED_SPEED_1, generatedFish.getSpeed(), 1);
  }

  /**
   * Test for generating an Fish.
   */
  @Test
  public void testRandomFish2() {
    final Random random = new Random(SEED_WEST);
    generatedFish = fishFactory.generateFish(random);

    assertEquals(Direction.West, generatedFish.getDirection());
    assertEquals(Position.getWidthPanel(), generatedFish.getPosition().getPosX(), 1);
    assertEquals(EXPECTED_SIZE_2, generatedFish.getSize(), 1);
    assertEquals(EXPECTED_SPEED_2, generatedFish.getSpeed(), 1);
  }

}

package lasersharks.seaobjects;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import lasersharks.Direction;
import lasersharks.Options;
import lasersharks.Position;
import lasersharks.interfaces.Displayable;
import lasersharks.interfaces.EnemyFactory;

/**
 * Test class for EnemyFactory.
 * 
 * @author SEMGroup27
 *
 */
public class EnemyFactoryTest {

  private static final int WIDTH = 1920;
  private static final int HEIGHT = 1080;

  protected Displayable generatedFish;
  private final long seed = 12345622L;
  private final long seedWest = 11L;

  private final float expectedSize1 = 172;
  private final int expectedSpeed1 = 134;

  private final float expectedSize2 = 176;
  private final int expectedSpeed2 = 344;

  private EnemyFactory enemyFactory;

  /**
   * Setup a new instance of the factory for every test.
   */
  @Before
  public void setUp() {
    enemyFactory = new EnemyFactory();
  }

  /**
   * Test for generating an Enemy.
   */
  @Test
  public void testRandomFish1() {
    Options.setGlobalHeight(HEIGHT);
    Options.setGlobalWidth(WIDTH);
    Random random = new Random(seed);
    generatedFish = enemyFactory.generateFish(random);

    assertEquals(Direction.East, generatedFish.getDirection());
    assertEquals(-expectedSize1, generatedFish.getPosition().getPosX(), 1);
    assertEquals(expectedSize1, generatedFish.getSize(), 1);
    assertEquals(expectedSpeed1, generatedFish.getSpeed(), 1);
  }

  /**
   * Test for generating an Enemy.
   */
  @Test
  public void testRandomFish2() {
    Random random = new Random(seedWest);
    generatedFish = enemyFactory.generateFish(random);

    assertEquals(Direction.West, generatedFish.getDirection());
    assertEquals(Position.getWidthPanel(), generatedFish.getPosition().getPosX(), 1);
    assertEquals(expectedSize2, generatedFish.getSize(), 1);
    assertEquals(expectedSpeed2, generatedFish.getSpeed(), 1);
  }

}
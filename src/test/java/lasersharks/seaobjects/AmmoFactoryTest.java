package lasersharks.seaobjects;

import lasersharks.Options;
import lasersharks.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Test class for AmmoFactory.
 * 
 * @author SEMGroup27
 *
 */
public class AmmoFactoryTest {

  private static final int WIDTH = 1920;
  private static final int HEIGHT = 1080;

  private static final long SEED = 12345622L;

  private static final double TEST_X_POS = 787.0;
  private static final double TEST_Y_POS = 214.0;

  private AmmoFactory ammoFactory;

  /**
   * Setup a new instance of the factory for every test.
   */
  @Before
  public void setUp() {
    Options.setGlobalHeight(HEIGHT);
    Options.setGlobalWidth(WIDTH);
    ammoFactory = new AmmoFactory();
  }
  
  /**
   * Clean up after the tests have been done.
   */
  @After
  public void tearDown() {
    Options.destroyInstance();
  }

  /**
   * Test for creating ammo.
   */
  @Test
  public void testAmmoCreation() {
    final Position testPos = new Position(TEST_X_POS, TEST_Y_POS);
    final Random random = new Random(SEED);
    final Ammo ammoCreated = ammoFactory.generateAmmo(random);
    assertEquals(testPos, ammoCreated.getPosition());
  }

}

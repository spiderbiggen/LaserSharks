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

  private AmmoFactory ammoFactory;

  /**
   * Setup a new instance of the factory for every test.
   */
  @Before
  public void setUp() {
    int height = 1080;
    Options.setGlobalHeight(height);
    int width = 1920;
    Options.setGlobalWidth(width);
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
    double testXPos = 787.0;
    double testYPos = 214.0;
    Position testPos = new Position(testXPos, testYPos);
    long seed = 12345622L;
    Random random = new Random(seed);
    Ammo ammoCreated = ammoFactory.generateAmmo(random);
    assertEquals(testPos, ammoCreated.getPosition());
  }

}

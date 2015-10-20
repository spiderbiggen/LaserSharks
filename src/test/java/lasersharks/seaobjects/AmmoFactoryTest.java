package lasersharks.seaobjects;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lasersharks.Options;
import lasersharks.Position;
import lasersharks.seaobjects.Ammo;
import lasersharks.seaobjects.AmmoFactory;

/**
 * Test class for AmmoFactory.
 * 
 * @author SEMGroup27
 *
 */
public class AmmoFactoryTest {

  private final int width = 1920;
  private final int height = 1080;
  
  private final long seed = 12345622L;

  private final double testXPos = 787.0;
  private final double testYPos = 214.0;

  private AmmoFactory ammoFactory;

  /**
   * Setup a new instance of the factory for every test.
   */
  @Before
  public void setUp() {
    Options.setGlobalHeight(height);
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
    Position testPos = new Position(testXPos, testYPos);
    Random random = new Random(seed);
    Ammo ammoCreated = ammoFactory.generateAmmo(random);
    assertEquals(testPos, ammoCreated.getPosition());
  }

}

package lasersharks;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AmmoFactory.
 * 
 * @author SEMGroup27
 *
 */
public class AmmoFactoryTest {

  private final long seed = 12345622L;

  private final double testXPos = 146.0;
  private final double testYPos = 177.0;

  private AmmoFactory ammoFactory;

  /**
   * Setup a new instance of the factory for every test.
   */
  @Before
  public void setUp() {
    ammoFactory = new AmmoFactory();
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

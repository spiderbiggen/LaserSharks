package lasersharks.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.controllers.FishController;
import lasersharks.enemies.FishFactory;
import lasersharks.seaObjects.Fish;
import lasersharks.seaObjects.FishBot;
import lasersharks.seaObjects.LaserShark;

/**
 * the test class for the FishController class.
 * 
 * @author Sytze
 */
public class FishControllerTest {

  private static final int SPEED = 10;
  private static final int SIZE = 10;
  private static final int POSITION_Y = 10;
  private static final int POSITION_X = 10;
  private static final int RANDOM_SEED = 10;
  private FishController fishCon;
  private static final int FISHAMOUNT = 10;
  private static final int DIST_BETW_FISH = 30;

  /**
   * Sets up a fishcontroller object.
   * 
   * @throws Exception
   *           if an error occurs.
   */
  @Before
  public void setUp() throws Exception {
    fishCon = new FishController();
  }

  /**
   * test the setter for the setRng() method.
   */
  @Test
  public void testSetRng() {
    fishCon.setRng(new Random(RANDOM_SEED));
  }

  /**
   * test the function to add fish to the cycle.
   */
  @Test
  public void testAddFish() {
    FishBot fishBot = new FishFactory().generateFish();
    assertFalse(fishCon.getNextCycleInformation(1).contains(fishBot));
    fishCon.addFish(fishBot);
    assertTrue(fishCon.getNextCycleInformation(3).contains(fishBot));
  }

  /**
   * A fishcontroller containing 10 fishes and 1 shark. one fish collides with the shark. The fishes
   * are size 10. Useful for testing multiple methods.
   * 
   * @param sizeOfShark
   *          the size of the shark to set to.
   * @return a fishcontroller with 10 fish and 1 shark. one shark and 1 fish collide.
   */
  public FishController fishConFilled(int sizeOfShark) {
    fishCon = new FishController();
    fishCon.setShark(
        new LaserShark(new Position(POSITION_X, POSITION_Y), sizeOfShark, SPEED, Direction.East));
    for (int i = 0; i < FISHAMOUNT; i++) {
      fishCon.addFish(new Fish("", 1, 1,
          new Position(POSITION_X + i * DIST_BETW_FISH, POSITION_Y + i * DIST_BETW_FISH)
          , Float.valueOf(SIZE),
          Double.valueOf(SPEED), 
          Direction.East));
    }
    return fishCon;
  }

  /**
   * A cycle is tested where the shark gets killed. After the cycle, the shark should not be alive.
   */
  @Test
  public void testGetNextCycleSharkKilled() {
    FishController fishCon = fishConFilled(SIZE);
    assertTrue(fishCon.getShark().isAlive());
    fishCon.getNextCycleInformation(1);
    assertFalse(fishCon.getShark().isAlive());
  }

  /**
   * A cycle is tested where the shark eats an other fish. After the cycle the shark should have
   * grown in size.
   */
  @Test
  public void testGetNextCycleFishKilled() {
    FishController fishCon = fishConFilled(SIZE + 1);
    assertTrue(fishCon.getShark().isAlive());
    double oldSize = fishCon.getShark().getSize();
    fishCon.getNextCycleInformation(1);
    assertTrue(fishCon.getShark().getSize() > oldSize);
  }
  
  /**
   * Test for shooting that fails.
   */
  @Test
  public void testShootLaserFalse() {
    fishCon.getShark().decreaseAmmo(Integer.MAX_VALUE);
    assertFalse(fishCon.shootLaser());
  }
  
  /**
   * Test for shooting that succeeds.
   */
  @Test
  public void testShootLaserTrue() {
    int oldAmmo = fishCon.getShark().getAmmo();
    assertTrue(fishCon.shootLaser());
    int newAmmo = fishCon.getShark().getAmmo();    
    assertTrue(oldAmmo - 1 == newAmmo);
  }
}

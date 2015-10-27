package lasersharks.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.seaobjects.Fish;
import lasersharks.seaobjects.LaserShark;
import lasersharksgui.MainGui;
import lasersharksgui.panes.LosingPane;
import lasersharks.seaobjects.FishFactory;

/**
 * the test class for the FishController class.
 * 
 * @author SEMGroup27
 */
public class FishControllerTest {

  private static final int SPEED = 10;
  private static final int SIZE = 10;
  private static final int POSITION_Y = 10;
  private static final int POSITION_X = 10;
  private static final int RANDOM_SEED = 10;
  private FishController fishCon;
  private static final int FISH_AMOUNT = 10;
  private static final int DIST_BETWEEN_FISH = 30;

  /**
   * Sets up a fish controller object.
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
    MainGui.setInstance(Mockito.mock(MainGui.class));
    Fish fishBot = new FishFactory().generateFish();
    assertFalse(fishCon.getNextCycleInformation(1).contains(fishBot));
    fishCon.addDisplayable(fishBot);
    assertTrue(fishCon.getNextCycleInformation(1).contains(fishBot));
  }

  /**
   * A fish controller containing 10 fishes and 1 shark. one fish collides with the shark. The fishes
   * are SIZE 10. Useful for testing multiple methods.
   * 
   * @param sizeOfShark
   *          the SIZE of the shark to set to.
   * @return a fish controller with 10 fish and 1 shark. one shark and 1 fish collide.
   */
  private FishController fishConFilled(final int sizeOfShark) {
    fishCon = new FishController();
    fishCon.setShark(
        new LaserShark(new Position(POSITION_X, POSITION_Y), sizeOfShark, SPEED, Direction.East));
    for (int i = 0; i < FISH_AMOUNT; i++) {
      fishCon.addDisplayable(new Fish("", 1,
          new Position(POSITION_X + i * DIST_BETWEEN_FISH, POSITION_Y + i * DIST_BETWEEN_FISH),
          (float) SIZE, (double) SPEED,
          Direction.East));
    }
    return fishCon;
  }

  /**
   * A cycle is tested where the shark gets killed. After the cycle, the shark should not be alive.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Test
  public void testGetNextCycleSharkKilled() {
    final MainGui guiMock = Mockito.mock(MainGui.class);
    final ArgumentCaptor<Class> argument = ArgumentCaptor.forClass(
            Class.class
    );
    MainGui.setInstance(guiMock);

    final FishController fishCon = fishConFilled(SIZE);
    assertTrue(fishCon.getShark().isAlive());
    fishCon.getNextCycleInformation(1);

    Mockito.verify(guiMock).browseTo(argument.capture());
    assertEquals(LosingPane.class, argument.getValue());
  }

  /**
   * A cycle is tested where the shark eats an other fish. After the cycle the shark should have
   * grown in SIZE.
   */
  @Test
  public void testGetNextCycleFishKilled() {
    final FishController fishCon = fishConFilled(SIZE + 1);
    assertTrue(fishCon.getShark().isAlive());
    final double oldSize = fishCon.getShark().getSize();
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
    final int oldAmmo = fishCon.getShark().getAmmo();
    assertTrue(fishCon.shootLaser());
    final int newAmmo = fishCon.getShark().getAmmo();
    assertSame(newAmmo, oldAmmo - 1);
  }
}

package lasersharks.controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * This test checks if the shootcontroller works properly.
 * @author SEMGroup27
 *
 */
public class ShootControllerTest {

  private ShootController shootController;
  private FishController fishController;

  /**
   * Setup so that all proper items are mocked.
   */
  @Before
  public void setUp() {
    fishController = mock(FishController.class);
    shootController = new ShootController(fishController);
  }

  /**
   * Test for {@link lasersharks.controllers.ShootController#handle(KeyEvent)}.
   */
  @Test
  public void correctKeyPressedHandlingTest() {
    final KeyEvent k = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.SPACE, false, false, false,
        false);
    this.shootController.handle(k);
    verify(fishController).shootLaser();
  }

  /**
   * Test for {@link lasersharks.controllers.ShootController#handle(KeyEvent)}.
   */
  @Test
  public void incorrectKeyPressedHandlingTest() {
    final KeyEvent k = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.R, false, false, false,
        false);
    this.shootController.handle(k);
    verifyZeroInteractions(fishController);
  }

  /**
   * Test for {@link lasersharks.controllers.ShootController#handle(KeyEvent)}.
   */
  @Test
  public void correctKeyReleasedHandlingTest() {
    final KeyEvent k = new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.SPACE, false, false, false,
        false);
    this.shootController.handle(k);
    verifyZeroInteractions(fishController);
  }

  /**
   * Test for {@link lasersharks.controllers.ShootController#handle(KeyEvent)}.
   */
  @Test
  public void incorrectKeyReleasedHandlingTest() {
    final KeyEvent k = new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.R, false, false, false,
        false);
    this.shootController.handle(k);
    verifyZeroInteractions(fishController);
  }
}
package lasersharks.controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lasersharksgui.MainGui;
import lasersharksgui.panes.GamePane;
import lasersharksgui.panes.OptionsPane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test class for {@link PauseController}.
 *
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")
public class PauseControllerTest {

  private MainGui gui;
  private PauseController pauseController;
  private KeyEvent pPressed;
  private KeyEvent pReleased;

  private KeyEvent rPressed;
  private GamePane gamePane;

  /**
   * Set up our mess.
   */
  @Before
  public void setUp() {
    gui = Mockito.mock(MainGui.class);
    gamePane = Mockito.mock(GamePane.class);
    pauseController = new PauseController(gamePane);
    MainGui.setInstance(gui);
    pPressed = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.P, false, false, false,
        false);
    rPressed = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.R, false, false, false,
        false);
    pReleased = new KeyEvent(KeyEvent.KEY_RELEASED, null, null, KeyCode.P, false, false, false,
        false);

  }

  /**
   * Cleanup our mess.
   */
  @After
  public void tearDown() {
    MainGui.clearInstance();
  }

  /**
   * Test for pressedP key event.
   */
  // @Test
  public void pressedPTest() {
    pauseController.handle(pPressed);
    Mockito.verify(gui).browseTo(OptionsPane.class);
  }

  /**
   * Test for pressedR key event.
   */
  @Test
  public void pressedRTest() {
    pauseController.handle(rPressed);
    Mockito.verifyZeroInteractions(gui);
  }

  /**
   * Test for releaseP key event.
   */
  @Test
  public void releasedPTest() {
    pauseController.handle(pReleased);
    Mockito.verifyZeroInteractions(gui);
  }

  /**
   * Tests if the game could go back to the original gamePane.
   */
  // @Test
  public void pressedPTwiceTest() {
    pauseController.handle(pPressed);
    pauseController.handle(pPressed);
    // Mockito.verify(gui).addOverlay();
    Mockito.verify(gui).browseTo(gamePane);
  }
}

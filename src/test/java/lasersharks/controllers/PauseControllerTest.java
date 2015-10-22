package lasersharks.controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lasersharksgui.MainGui;
import lasersharksgui.panes.GamePane;
import lasersharksgui.panes.OptionsPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

/**
 * Test class for {@link PauseController}.
 * @author SEMGroup27
 */
public class PauseControllerTest {

  private MainGui gui;
  private PauseController pauseController;
  private KeyEvent pPressed;
  private KeyEvent pReleased;
  private KeyEvent rPressed;
  private GamePane gamePane;

  /**
   * Set up our mess.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    gui = Mockito.mock(MainGui.class);
    gamePane = Mockito.mock(GamePane.class);
    pauseController = new PauseController(gamePane);
    MainGui.setInstance(gui);
    pPressed = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.P, false, false, false, false);
    rPressed = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.R, false, false, false, false);
    pReleased = new KeyEvent(KeyEvent.KEY_RELEASED, null, null, KeyCode.P, false, false, false, false);
    
  }
  
  /**
   * Cleanup our mess.
   */
  @After
  public void tearDown() {
    MainGui.clearInstance();
  }

  /**
   * Test for pressedP keyevent
   */
  @Test
  public void testPressedP() {
    pauseController.handle(pPressed);
    Mockito.verify(gui).browseTo(OptionsPanel.class);  
  }
  
  /**
   * Test for pressedR keyevent.
   */
  @Test
  public void testPressedR() {
    pauseController.handle(rPressed);
    Mockito.verifyZeroInteractions(gui);
  }
  
  /**
   * Test for releaseP keyevent.
   */
  @Test
  public void testReleasedP() {
    pauseController.handle(pReleased);
    Mockito.verifyZeroInteractions(gui);
  }


  /**
   * Tests if the game could go back to the original gamePane.
   */
  @Test
  public void testPressedPTwice() {
    pauseController.handle(pPressed);
    pauseController.handle(pPressed);
    Mockito.verify(gui).browseTo(OptionsPanel.class);
    Mockito.verify(gui).browseTo(gamePane);    
  }
}

package lasersharks.controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lasersharksgui.MainGui;
import lasersharksgui.panes.GamePane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing {@link DirectionInputController}.
 <<<<<<< HEAD
 =======
 *
 * @author SEMGroup27
>>>>>>> remotes/origin/master
 *
 * @author SEMGroup27
 */
@SuppressWarnings({ "restriction", "rawtypes" })
public class RestartGameControllerTest {
  private MainGui gui;
  private ArgumentCaptor<Class> argument;
  private RestartGameController restartGameController;

  /**
   * Setup so that all proper items are mocked.
   */
  @Before
  public void setUp() {
    gui = Mockito.mock(MainGui.class);
    argument = ArgumentCaptor.forClass(
        Class.class
    );
    MainGui.setInstance(gui);
    restartGameController = new RestartGameController();
  }

  /**
   * Cleanup our mess.
   */
  @After
  public void tearDown() {
    MainGui.clearInstance();
  }

  /**
   * Test the restart game controller.
   */
  @SuppressWarnings("unchecked")
  @Test
  public void testRestartGame() {
    final KeyEvent k = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.R, false, false, false,
        false);
    this.restartGameController.handle(k);

    Mockito.verify(gui).browseTo(argument.capture());
    assertEquals(GamePane.class, argument.getValue());
  }

  /**
   * Test the restart game controller.
   */
  @Test
  public void testNoInteractions() {
    final KeyEvent k = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.A, false, false, false,
        false);
    this.restartGameController.handle(k);

    Mockito.verifyZeroInteractions(gui);
  }

}
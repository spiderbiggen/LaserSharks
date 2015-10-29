package lasersharks.controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lasersharksgui.MainGui;
import lasersharksgui.panes.GamePane;
import lasersharksgui.panes.OptionsPane;

/**
 * The pauseController handles how the GamePane switches to and from the OptionsPane.
 *
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")
public class PauseController implements EventHandler<KeyEvent> {

  private final GamePane pane;
  private final OptionsPane optionsPane;
  private Boolean isPaused = false;

  /**
   * The constructor.
   *
   * @param pane the game pane so that we can browse back to it
   */
  public PauseController(final GamePane pane) {
    this.pane = pane;
    this.optionsPane = new OptionsPane();
  }

  @Override
  public void handle(final KeyEvent event) {
    if (event.getCode() == KeyCode.P && event.getEventType() == KeyEvent.KEY_PRESSED) {
      if (isPaused) {
        resumeGame();
      } else {
        pauseGame();
      }
      isPaused = !isPaused;
    }
  }

  /**
   * Pauses the game.
   */
  private void pauseGame() {
    pane.stopGame();
    MainGui.getInstance().addOverlay(optionsPane);
  }

  /**
   * Resumes the game.
   */
  private void resumeGame() {
    pane.resumeGame();
    MainGui.getInstance().removeOverlay(optionsPane);
  }

}

package lasersharks.controllers;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lasersharksgui.MainGui;
import lasersharksgui.panes.GamePane;
import lasersharksgui.panes.OptionsPanel;

/**
 * The pauseController handles how the GamePane switches to and from the OptionsPane.
 * @author SEMGroup27
 *
 */
public class PauseController implements EventHandler<KeyEvent> {

  private GamePane pane;
  private Boolean isPaused = false;
  
  /**
   * The constructor.
   * @param pane
   */
  public PauseController(GamePane pane) {
    this.pane = pane;
  }
  
  @Override
  public void handle(KeyEvent event) {
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
    MainGui.getInstance().browseTo(OptionsPanel.class);
  }
  
  /**
   * Resumes the game.
   */
  private void resumeGame() {
    pane.resumeGame();
    MainGui.getInstance().browseTo(pane);
  }
  
}

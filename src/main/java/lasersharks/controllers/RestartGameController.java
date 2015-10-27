package lasersharks.controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lasersharks.Logger;
import lasersharksgui.MainGui;
import lasersharksgui.panes.GamePane;

/**
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class RestartGameController implements EventHandler<KeyEvent> {
  private static final int AFTER_RESTART_DELAY = 100;

  private void restartGame() {
    MainGui.getInstance().browseTo(GamePane.class);
    try {
      Thread.sleep(AFTER_RESTART_DELAY);
    } catch (InterruptedException e) {
      Logger.getInstance().write("Restart delay interrupted", e.getMessage());
    }
    Logger.getInstance().write("Game", "Restarted");
  }

  @Override public void handle(final KeyEvent event) {
    if (event.getCode() == KeyCode.R) {
      restartGame();
    }
  }

}

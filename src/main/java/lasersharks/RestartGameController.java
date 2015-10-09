package lasersharks;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lasersharks.gui.GamePane;
import lasersharks.gui.MainGui;

/**
 * @author Stefan
 *
 */
@SuppressWarnings("restriction")
public class RestartGameController implements EventHandler<KeyEvent> {

  /**
   * Constructor.
   * 
   */
  public RestartGameController() {
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, this);
  }

  private void restartGame() {
    MainGui.getInstance().browseTo(GamePane.class);
    Logger.getInstance().write("Game", "Restarted");
  }

  @Override
  public void handle(KeyEvent event) {
    if (event.getCode() == KeyCode.R) {
      restartGame();
    }
  }

}

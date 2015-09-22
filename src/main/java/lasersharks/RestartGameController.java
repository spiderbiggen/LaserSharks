/**
 * 
 */
package lasersharks;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Stefan
 *
 */
@SuppressWarnings("restriction")
public class RestartGameController implements EventHandler<KeyEvent> {

  private ScreenController screenController;

  /**
   * Constructor.
   * 
   * @param screenCon
   *          scene holder
   */
  public RestartGameController(ScreenController screenCon) {
    this.screenController = screenCon;
  }

  private boolean restartGame() {
    boolean handled = true;
    try {
      this.screenController.restart();
      Logger.getInstance().write("Game", "Restarted");
    } catch (IOException e) {
      Logger.getInstance().write("IOException", e.getMessage());
      handled = false;
    }
    return handled;
  }

  @Override
  public void handle(KeyEvent event) {
    if (event.getCode() == KeyCode.R) {
      restartGame();
    }
  }

}

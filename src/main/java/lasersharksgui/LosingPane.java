package lasersharksgui;

import java.io.IOException;

import javafx.scene.input.KeyEvent;
import lasersharks.Highscores;
import lasersharks.Logger;
import lasersharks.Options;
import lasersharks.RestartGameController;

/**
 * This pane represents the screen that is shown when a player loses.
 * 
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class LosingPane extends StandardPane {
  private RestartGameController restartHandler;
  
  /**
   * The constructor makes a new panel with a few text objects shown.
   */
  public LosingPane() {

    addMidText("YOU LOSE!", TEXT_SCALE_SIZE_BIG, Options.getGlobalHeight() / SCREEN_POSITION_THREE);
    String message;
    try {
      Highscores.getInstance().writeHighscore();
      message = Highscores.getInstance().makeHighscoreString();
    } catch (IOException e) {
      message = "Highscores failed to load";
      Logger.getInstance().write("IOException ", e.getMessage());
      e.printStackTrace();
    }
    addMidText(message, TEXT_SCALE_SIZE_SMALL, Options.getGlobalHeight() / SCREEN_POSITION_HUNDRED);
    addMidText("\n Press R to restart", TEXT_SCALE_SIZE_SMALL,
        -Options.getGlobalHeight() / SCREEN_POSITION_FIVE);
    

    this.restartHandler = new RestartGameController();
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, this.restartHandler);
  }

  @Override
  public void stop() {
    MainGui.getInstance().getCurrentScene().removeEventHandler(KeyEvent.ANY, this.restartHandler);
  }
}

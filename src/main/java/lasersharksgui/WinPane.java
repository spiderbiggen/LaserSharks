package lasersharksgui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.input.KeyEvent;
import lasersharks.Highscores;
import lasersharks.Logger;
import lasersharks.controllers.Options;
import lasersharks.controllers.RestartGameController;

/**
 * This pane represents the screen that is shown when a player loses.
 * 
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class WinPane extends StandardPane {

  private static final double ADJUST_DY_FOR_BOTTOM = -400;
  private static final int CUSTOM_TEXT_SIZE = TEXT_SCALE_SIZE_SMALL + 10;

  private RestartGameController restartHandler;

  /**
   * The constructor makes a new panel with a few text objects shown.
   */
  public WinPane() {
    try {
      if (Highscores.getInstance().getHighScore() < Highscores.getInstance().getScore()) {
        addMidText("NEW HIGHSCORE!", CUSTOM_TEXT_SIZE, ADJUST_DY_FOR_BOTTOM);
      }
    } catch (FileNotFoundException e1) {
      addMidText("UNABLE TO FIND A HIGHSCORES FILE", TEXT_SCALE_SIZE_SMALL, ADJUST_DY_FOR_BOTTOM);
      Logger.getInstance().write("No highscores file found", e1.getMessage());
    }
    addMidText("YOU WON!", TEXT_SCALE_SIZE_BIG, Options.getGlobalHeight() / SCREEN_POSITION_THREE);
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

    addMidText("Press R to restart", TEXT_SCALE_SIZE_SMALL, -Options.getGlobalHeight()
        / SCREEN_POSITION_FIVE);

    this.restartHandler = new RestartGameController();
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, this.restartHandler);
  }

  @Override
  public void stop() {
    MainGui.getInstance().getCurrentScene().removeEventHandler(KeyEvent.ANY, this.restartHandler);
  }
}

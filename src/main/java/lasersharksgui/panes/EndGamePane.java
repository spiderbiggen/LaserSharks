/**
 * 
 */
package lasersharksgui.panes;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.input.KeyEvent;
import lasersharks.Highscores;
import lasersharks.Logger;
import lasersharks.Options;
import lasersharks.controllers.RestartGameController;
import lasersharksgui.MainGui;

/**
 * @author Stefan
 *
 */
@SuppressWarnings("restriction")
public abstract class EndGamePane extends StandardPane {

  private static final double ADJUST_DY_FOR_BOTTOM = -400;
  private static final int CUSTOM_TEXT_SIZE = TEXT_SCALE_SIZE_SMALL + 10;

  private RestartGameController restartHandler;

  /**
   * Creates a new EndGamePane with the given message.
   * 
   * @param message
   *          message to be shown
   */
  public EndGamePane(String message) {
    try {
      if (Highscores.getInstance().getHighScore() < Highscores.getInstance().getScore()) {
        addMidText("NEW HIGHSCORE!", CUSTOM_TEXT_SIZE, ADJUST_DY_FOR_BOTTOM);
      }
    } catch (FileNotFoundException e1) {
      addMidText("UNABLE TO FIND A HIGHSCORES FILE", TEXT_SCALE_SIZE_SMALL, ADJUST_DY_FOR_BOTTOM);
      Logger.getInstance().write("No highscores file found", e1.getMessage());
    }
    addMidText(message, TEXT_SCALE_SIZE_BIG,
        Options.getGlobalHeight() / SCALING_FACTOR_TO_UNDERNEATH_MIDDLE);
    String highscores;
    try {
      Highscores.getInstance().writeHighscore();
      highscores = Highscores.getInstance().makeHighscoreString();
    } catch (IOException e) {
      highscores = "Highscores failed to load";
      Logger.getInstance().write("IOException ", e.getMessage());
      e.printStackTrace();
    }
    addMidText(highscores, TEXT_SCALE_SIZE_SMALL,
        Options.getGlobalHeight() / SCALING_FACTOR_TO_LITTLE_BELOW_MIDDLE);
    addMidText("Press R to restart", TEXT_SCALE_SIZE_SMALL,
        Options.getGlobalHeight() / SCALING_FACTOR_TO_ABOVE_MIDDLE);

    this.restartHandler = new RestartGameController();
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, this.restartHandler);
  }

  /*
   * (non-Javadoc)
   * 
   * @see lasersharksgui.Stoppable#stop()
   */
  @Override
  public void stop() {
    MainGui.getInstance().getCurrentScene().removeEventHandler(KeyEvent.ANY, this.restartHandler);
  }

}
